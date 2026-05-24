package fourB.MF;

/**
 * Sigmoid Membership Function
 *
 * Defined by two parameters:
 *   a - slope     : controls the steepness and direction of the curve
 *                   Positive a → rising sigmoid (S-shaped, left to right)
 *                   Negative a → falling sigmoid (Z-shaped, right to left)
 *   c - crossover : the inflection point where membership is exactly 0.5
 *
 * Formula:
 *   μ(x) = 1 / ( 1 + e^( -a * (x - c) ) )
 *
 * Constraint: a != 0
 *   - As |a| increases, the transition from 0 to 1 becomes steeper.
 *   - As |a| decreases toward 0, the transition becomes more gradual.
 *
 * Note: Unlike the other membership functions, the Sigmoid never fully
 *       reaches 0 or 1 — it approaches them asymptotically.
 */
public class SigmoidMF implements MembershipFunction {

    // Hard-coded definition parameters
    private final double a;
    private final double c;

    /**
     * Constructs a SigmoidMF with the given parameters.
     *
     * @param a Slope parameter (positive = rising, negative = falling, must not be 0)
     * @param c Crossover point (inflection point where μ = 0.5)
     * @throws IllegalArgumentException if a is zero
     */
    public SigmoidMF(double a, double c) {
        if (a == 0) {
            throw new IllegalArgumentException(
                    "Sigmoid MF requires a != 0. A slope of zero produces a flat line."
            );
        }
        this.a = a;
        this.c = c;
    }

    /**
     * Computes the degree of membership μ(x) for a crisp input value x.
     * The Sigmoid always returns a value in (0.0, 1.0), approaching but
     * never reaching the bounds asymptotically.
     *
     * @param x The crisp input value
     * @return  Membership degree in the range (0.0, 1.0)
     */
    @Override
    public double compute(double x) {
        return 1.0 / (1.0 + Math.exp(-a * (x - c)));
    }

    /**
     * Returns whether this sigmoid is rising or falling based on the sign of a.
     *
     * @return "rising" if a > 0, "falling" if a < 0
     */
    public String getShape() {
        return a > 0 ? "rising" : "falling";
    }

    /**
     * Returns the display name of this membership function.
     *
     * @return "Sigmoid"
     */
    @Override
    public String getName() {
        return "Sigmoid";
    }

    /**
     * Returns a formatted string of this function's hard-coded parameters.
     *
     * @return Parameter summary string
     */
    @Override
    public String getParameterInfo() {
        return String.format(
                "a (slope) = %.2f [%s] | c (crossover) = %.2f",
                a, getShape(), c
        );
    }
}
