import java.util.Scanner;

public class ConsoleCalculator {

    // --- Calculator Logic Methods (Same as before) ---

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Error: Cannot divide by zero.");
        }
        return num1 / num2;
    }

    // --- Application Main Method ---

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("---------------------------------------");
        System.out.println("  Simple Console Calculator Activated  ");
        System.out.println("---------------------------------------");

        // Main application loop
        while (running) {
            System.out.println("\nPlease select an operation:");
            System.out.println("1: Add (+)");
            System.out.println("2: Subtract (-)");
            System.out.println("3: Multiply (*)");
            System.out.println("4: Divide (/)");
            System.out.println("5: Exit");
            System.out.print("Enter your choice (1-5): ");

            // Check if the next token is an integer (for robust input)
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 5) {
                running = false;
                System.out.println("\nCalculator shutting down. Goodbye!");
                break;
            }

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please select a number between 1 and 5.");
                continue;
            }
            
            double num1 = 0;
            double num2 = 0;
            String operationSymbol = "";
            
            try {
                // Get the first number
                System.out.print("Enter the first number: ");
                num1 = scanner.nextDouble();

                // Get the second number
                System.out.print("Enter the second number: ");
                num2 = scanner.nextDouble();

                double result = 0;
                
                // Perform the calculation based on the user's choice
                switch (choice) {
                    case 1:
                        result = add(num1, num2);
                        operationSymbol = " + ";
                        break;
                    case 2:
                        result = subtract(num1, num2);
                        operationSymbol = " - ";
                        break;
                    case 3:
                        result = multiply(num1, num2);
                        operationSymbol = " * ";
                        break;
                    case 4:
                        result = divide(num1, num2);
                        operationSymbol = " / ";
                        break;
                    default:
                        // This case is already covered by the check above, but included for completeness.
                        System.out.println("Internal error in operation selection.");
                        continue;
                }
                
                // Display the result
                System.out.printf("\nResult: %.2f%s%.2f = %.2f\n", num1, operationSymbol, num2, result);
                
            } catch (IllegalArgumentException e) {
                // Catches the division by zero error from the divide method
                System.out.println(e.getMessage());
            } catch (java.util.InputMismatchException e) {
                // Catches errors if the user enters non-numeric input for the numbers
                System.out.println("Invalid number format. Please enter valid numbers.");
                scanner.next(); // Consume the invalid input to prevent infinite loop
            } catch (Exception e) {
                // Catch any other unexpected errors
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        } // End of while loop
        
        // Close the scanner resource
        scanner.close();
    }
}