package fourB;

import fourB.MF.MembershipFunction;
import java.util.Scanner;

/**
 * Main
 *
 * Entry point for the Fuzzy Logic Membership Function Tool.
 *
 * Program flow:
 *   1. User selects a membership function from the menu
 *   2. User enters a crisp input value x
 *   3. The selected function computes the membership degree μ(x)
 *   4. The result is displayed with an interpretation
 *   5. User is asked whether to run again or exit
 */
public class program {

    public static void main(String[] args) {

        Scanner scanner       = new Scanner(System.in);
        InputHandler  input   = new InputHandler(scanner);
        ResultOutput display = new ResultOutput();

        boolean running = true;

        while (running) {

            // Step 1 — User selects a membership function
            String key = input.promptFunctionSelection();

            MembershipFunction function;
            if (input.promptUseDefaults(key)) {

                // Step 2a — Use the hard-coded default instance from the registry
                function = FunctionRegistry.getFunction(key);

            } else {

                // Step 2b — Collect custom parameters and build a fresh instance
                double[] params = input.promptCustomParameters(key);
                function = FunctionRegistry.buildCustom(key, params);
            }

            String variable =  input.promptVariableName();

            // Step 2 — User enters a crisp input value
            double x = input.promptCrispInput();

            // Step 3 — Compute the membership degree μ(x)
            double mu = function.compute(x);

            // Step 4 — Display the result
            display.displayResult(function, x, mu, variable);

            // Step 5 — Ask whether to run again
            running = input.promptRunAgain();
        }

        // Clean up and exit
        display.displayGoodbye();
        scanner.close();
    }
}
