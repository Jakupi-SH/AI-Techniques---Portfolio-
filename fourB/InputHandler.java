package fourB;

import java.util.Scanner;

/**
 * InputHandler
 *
 * Responsible for all console input and validation logic.
 * Keeps user interaction code out of Main and FunctionRegistry.
 *
 * Responsibilities:
 *   - Displaying the membership function selection menu
 *   - Reading and validating the user's function choice
 *   - Asking whether the user wants default or custom parameters
 *   - Prompting for each custom parameter by name with constraint validation
 *   - Reading and validating the crisp input value x
 *   - Asking the user if they want to run another computation
 */
public class InputHandler {

    private final Scanner scanner;

    /**
     * Constructs an InputHandler using the provided Scanner.
     * A single Scanner instance should be created in Main and passed here
     * to avoid resource conflicts.
     *
     * @param scanner The shared Scanner instance reading from System.in
     */
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays the membership function selection menu and reads the user's
     * choice, repeating until a valid menu key is entered.
     *
     * @return The validated menu key string (e.g. "1" through "5")
     */
    public String promptFunctionSelection() {
        System.out.println("========================================");
        System.out.println("   Fuzzy Logic Membership Function Tool ");
        System.out.println("========================================");
        System.out.println("Select a membership function:");
        System.out.println();
        System.out.print(FunctionRegistry.buildMenuString());
        System.out.println();

        while (true) {
            System.out.print("Enter your choice (1-" + FunctionRegistry.size() + "): ");
            String input = scanner.nextLine().trim();

            if (FunctionRegistry.isValidKey(input)) {
                return input;
            }

            System.out.println("  Invalid choice. Please enter a number between 1 and "
                    + FunctionRegistry.size() + ".");
        }
    }

    /**
     * Asks the user whether they want to use the default hard-coded parameters
     * or define their own custom values for the selected function.
     *
     * @return true if the user wants to use default parameters, false for custom
     */
    public boolean promptUseDefaults(String key) {
        System.out.println();
        while (true) {
            System.out.print("Use default parameters? (y/n): ");
            System.out.println("  Default parameters for this function:");
            System.out.println("  " + FunctionRegistry.getFunction(key).getParameterInfo());
            System.out.println();
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("  Invalid input. Please enter 'y' for yes or 'n' for no.");
        }
    }

    /**
     * Guides the user through entering custom parameters for the selected
     * membership function identified by its menu key. Each parameter is
     * prompted individually with its name and role. Ordering constraints
     * (e.g. a <= b <= c) are validated after all values are entered, and
     * the user is re-prompted from the start if a violation is found.
     *
     * @param key The menu key of the selected function (e.g. "1" through "5")
     * @return    A double array of validated custom parameter values in the
     *            correct order for the selected function's constructor
     */
    public double[] promptCustomParameters(String key) {
        System.out.println();
        System.out.println("  Enter custom parameters (press Enter after each value):");
        System.out.println();

        switch (key) {

            case "1": // Triangular — a, b, c  with  a <= b <= c
                while (true) {
                    double a = promptSingleParam("  a (left foot  — membership starts rising, μ=0): ");
                    double b = promptSingleParam("  b (peak       — maximum membership,         μ=1): ");
                    double c = promptSingleParam("  c (right foot — membership finishes,        μ=0): ");

                    if (a <= b && b <= c) {
                        return new double[]{a, b, c};
                    }
                    System.out.println();
                    System.out.println("  Constraint violated: values must satisfy a <= b <= c.");
                    System.out.println("  Please re-enter all parameters.");
                    System.out.println();
                }

            case "2": // Trapezoidal — a, b, c, d  with  a <= b <= c <= d
                while (true) {
                    double a = promptSingleParam("  a (left foot      — membership starts rising,  μ=0): ");
                    double b = promptSingleParam("  b (left shoulder  — flat top begins,           μ=1): ");
                    double c = promptSingleParam("  c (right shoulder — flat top ends,             μ=1): ");
                    double d = promptSingleParam("  d (right foot     — membership finishes,       μ=0): ");

                    if (a <= b && b <= c && c <= d) {
                        return new double[]{a, b, c, d};
                    }
                    System.out.println();
                    System.out.println("  Constraint violated: values must satisfy a <= b <= c <= d.");
                    System.out.println("  Please re-enter all parameters.");
                    System.out.println();
                }

            case "3": // Gaussian — c, sigma  with  sigma > 0
                while (true) {
                    double c     = promptSingleParam("  c     (center — peak of the curve,   μ=1): ");
                    double sigma = promptSingleParam("  sigma (spread — controls curve width, must be > 0): ");

                    if (sigma > 0) {
                        return new double[]{c, sigma};
                    }
                    System.out.println();
                    System.out.println("  Constraint violated: sigma must be greater than 0.");
                    System.out.println("  Please re-enter all parameters.");
                    System.out.println();
                }

            case "4": // Bell-Shaped — a, b, c  with  a > 0 and b > 0
                while (true) {
                    double a = promptSingleParam("  a (width  — half-width of the bell,      must be > 0): ");
                    double b = promptSingleParam("  b (slope  — steepness of the sides,      must be > 0): ");
                    double c = promptSingleParam("  c (center — peak of the bell,            μ=1): ");

                    if (a > 0 && b > 0) {
                        return new double[]{a, b, c};
                    }
                    System.out.println();
                    System.out.println("  Constraint violated: a and b must both be greater than 0.");
                    System.out.println("  Please re-enter all parameters.");
                    System.out.println();
                }

            case "5": // Sigmoid — a, c  with  a != 0
                while (true) {
                    double a = promptSingleParam("  a (slope     — steepness; positive=rising, negative=falling, must not be 0): ");
                    double c = promptSingleParam("  c (crossover — inflection point where μ=0.5): ");

                    if (a != 0) {
                        return new double[]{a, c};
                    }
                    System.out.println();
                    System.out.println("  Constraint violated: a must not be 0 (a slope of zero produces a flat line).");
                    System.out.println("  Please re-enter all parameters.");
                    System.out.println();
                }

            default:
                throw new IllegalArgumentException("Unknown function key: " + key);
        }
    }

    /**
     * Prompts for and reads a single numeric parameter value, repeating
     * until the user enters a valid double.
     *
     * @param prompt The label/description to display for this parameter
     * @return       The validated double value entered by the user
     */
    private double promptSingleParam(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("    Invalid input. Please enter a numeric value (e.g. 3.5).");
            }
        }
    }

    public String promptVariableName(){
        System.out.println("Enter the variable name: ");
        return  scanner.nextLine().trim();
    }

    /**
     * Prompts the user to enter a crisp input value x and reads it,
     * repeating until a valid decimal number is entered.
     *
     * @return The validated crisp input value as a double
     */
    public double promptCrispInput() {
        System.out.println();
        while (true) {
            System.out.print("Enter a crisp input value x: ");
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a numeric value (e.g. 3.5).");
            }
        }
    }

    /**
     * Asks the user whether they want to perform another computation.
     * Accepts "y" or "n" (case-insensitive), repeating until a valid
     * response is given.
     *
     * @return true if the user wants to continue, false if they want to exit
     */
    public boolean promptRunAgain() {
        System.out.println();
        while (true) {
            System.out.print("Would you like to try another value or function? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("  Invalid input. Please enter 'y' for yes or 'n' for no.");
        }
    }
}