package fourB;

import fourB.MF.MembershipFunction;

/**
 * ResultDisplay
 *
 * Responsible for all formatted output after a membership function
 * computation has been performed. Keeps display logic out of Main
 * so that the output format can be changed in one place if needed.
 *
 * Responsibilities:
 *   - Printing the selected function's name and hard-coded parameters
 *   - Printing the crisp input value provided by the user
 *   - Printing the computed membership/truth value μ(x)
 *   - Printing a plain-language interpretation of the result
 *   - Printing a goodbye message on exit
 */
public class ResultOutput {

    /**
     * Prints the full result block for a completed membership function
     * computation, including the function details, input, output, and
     * an interpretation of the membership degree.
     *
     * @param function The membership function that was evaluated
     * @param x        The crisp input value provided by the user
     * @param mu       The computed membership degree μ(x)
     */
    public void displayResult(MembershipFunction function, double x, double mu, String var) {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("  Result");
        System.out.println("----------------------------------------");
        System.out.println("Variable Name: " + var);
        System.out.printf ("  Function   : %s%n",   function.getName());
        System.out.printf ("  Parameters : %s%n",   function.getParameterInfo());
        System.out.printf ("  Input  x   : %.4f%n", x);
        System.out.printf ("  Output μ(x): %.4f%n", mu);
        System.out.println();
        System.out.println("  Interpretation: " + interpret(mu));
        System.out.println("----------------------------------------");
    }

    /**
     * Prints a farewell message when the user chooses to exit.
     */
    public void displayGoodbye() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("  Exiting. Goodbye!");
    }

    /**
     * Produces a plain-language interpretation of a membership degree,
     * divided into five descriptive bands:
     *
     *   0.00 – 0.20 : No membership
     *   0.20 – 0.40 : Low membership
     *   0.40 – 0.60 : Moderate membership
     *   0.60 – 0.80 : High membership
     *   0.80 – 1.00 : Full membership
     *
     * @param mu The membership degree to interpret, expected in [0.0, 1.0]
     * @return   A descriptive string for the given membership degree
     */
    private String interpret(double mu) {
        if      (mu <= 0.20) return "No membership       (μ ≈ 0.00 – 0.20)";
        else if (mu <= 0.40) return "Low membership      (μ ≈ 0.20 – 0.40)";
        else if (mu <= 0.60) return "Moderate membership (μ ≈ 0.40 – 0.60)";
        else if (mu <= 0.80) return "High membership     (μ ≈ 0.60 – 0.80)";
        else                 return "Full membership     (μ ≈ 0.80 – 1.00)";
    }
}
