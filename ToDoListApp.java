import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a single task in the ToDo List.
 */
class Task {
    private String description;
    private boolean isCompleted;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isCompleted = false; // New tasks are not completed by default
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for completion status
    public boolean isCompleted() {
        return isCompleted;
    }

    // Setter to mark task as completed or incomplete
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Overrides the toString method for easy printing of the task status.
     * Example: [ ] Buy milk or [X] Finish homework
     */
    @Override
    public String toString() {
        String status = isCompleted ? "[X]" : "[ ]";
        return status + " " + description;
    }
}

/**
 * Main application class for the ToDo List.
 */
public class ToDoListApp {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("--- 📝 Welcome to the Console To-Do List App! ---");

        // Main application loop
        while (running) {
            displayMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        viewTasks();
                        break;
                    case 3:
                        markTaskCompleted();
                        break;
                    case 4:
                        deleteTask();
                        break;
                    case 5:
                        running = false; // Exit the loop
                        System.out.println("--- 👋 Application closed. Goodbye! ---");
                        break;
                    default:
                        System.out.println("⚠ Invalid choice. Please enter a number from 1 to 5.");
                }
            } else {
                System.out.println("⚠ Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    // --- Helper Methods ---

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Mark Task as Complete");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Prompts the user for a task description and adds it to the list.
     */
    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        if (!description.isEmpty()) {
            tasks.add(new Task(description));
            System.out.println("✅ Task added successfully!");
        } else {
            System.out.println("⚠ Task description cannot be empty.");
        }
    }

    /**
     * Prints all tasks in the list along with their index and status.
     */
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("ℹ Your to-do list is empty. Add a new task!");
            return;
        }

        System.out.println("\n--- 📌 Your To-Do List ---");
        for (int i = 0; i < tasks.size(); i++) {
            // Display index (i+1) for user-friendly 1-based indexing
            System.out.println((i + 1) + ". " + tasks.get(i)); 
        }
        System.out.println("--------------------------");
    }

    /**
     * Allows the user to select a task by its number and mark it as complete.
     */
    private static void markTaskCompleted() {
        if (tasks.isEmpty()) {
            System.out.println("ℹ No tasks to mark as complete.");
            return;
        }
        viewTasks();
        System.out.print("Enter the number of the task to mark as complete: ");
        
        if (scanner.hasNextInt()) {
            int taskNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Convert 1-based user index to 0-based list index
            int index = taskNumber - 1; 

            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                if (!task.isCompleted()) {
                    task.setCompleted(true);
                    System.out.println("✅ Task '" + task.getDescription() + "' marked as complete!");
                } else {
                    System.out.println("⚠ Task is already completed.");
                }
            } else {
                System.out.println("⚠ Invalid task number.");
            }
        } else {
            System.out.println("⚠ Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    /**
     * Allows the user to select a task by its number and delete it.
     */
    private static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("ℹ No tasks to delete.");
            return;
        }
        viewTasks();
        System.out.print("Enter the number of the task to delete: ");
        
        if (scanner.hasNextInt()) {
            int taskNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Convert 1-based user index to 0-based list index
            int index = taskNumber - 1; 

            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                System.out.println("🗑 Task '" + removedTask.getDescription() + "' deleted.");
            } else {
                System.out.println("⚠ Invalid task number.");
            }
        } else {
            System.out.println("⚠ Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
    }
}