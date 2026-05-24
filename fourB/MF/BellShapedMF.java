package fourB.MF;

/**
 * Generalized Bell-Shaped Membership Function
 *
 * Defined by three parameters:
 *   a - width  : controls the half-width of the curve
 *   b - slope  : controls the steepness of the transition slopes
 *                (typically a positive integer; higher = steeper)
 *   c - center : the point of maximum membership (μ = 1)
 *
 * Formula:
 *   μ(x) = 1 / ( 1 + | (x - c) / a |^(2b) )
 *
 * Constraints:
 *   a > 0  — width must be positive
 *   b > 0  — slope must be positive
 *
 * Note: As b increases, the bell shape approaches a rectangular function.
 *       When b = 1, it reduces to a standard Cauchy/Lorentzian distribution.
 */
public class BellShapedMF implements MembershipFunction {

    // Hard-coded definition parameters
    private final double a;
    private final double b;
    private final double c;

    /**
     * Constructs a BellShapedMF with the given parameters.
     *
     * @param a Width parameter  (half-width of the bell, must be > 0)
     * @param b Slope parameter  (steepness of the sides, must be > 0)
     * @param c Center parameter (peak of the bell, μ = 1)
     * @throws IllegalArgumentException if a or b are not greater than zero
     */
    public BellShapedMF(double a, double b, double c) {
        if (a <= 0) {
            throw new IllegalArgumentException(
                    "Bell-Shaped MF requires a > 0. " +
                            "Got: a=" + a
            );
        }
        if (b <= 0) {
            throw new IllegalArgumentException(
                    "Bell-Shaped MF requires b > 0. " +
                            "Got: b=" + b
            );
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Computes the degree of membership μ(x) for a crisp input value x.
     * Like the Gaussian, the Bell-Shaped function approaches 0 asymptotically
     * but never fully reaches it; it always returns a value in (0.0, 1.0].
     *
     * @param x The crisp input value
     * @return  Membership degree in the range (0.0, 1.0]
     */
    @Override
    public double compute(double x) {
        double base     = Math.abs((x - c) / a);
        double exponent = 2 * b;
        return 1.0 / (1.0 + Math.pow(base, exponent));
    }

    /**
     * Returns the display name of this membership function.
     *
     * @return "Bell-Shaped"
     */
    @Override
    public String getName() {
        return "Bell-Shaped";
    }

    /**
     * Returns a formatted string of this function's hard-coded parameters.
     *
     * @return Parameter summary string
     */
    @Override
    public String getParameterInfo() {
        return String.format("a (width) = %.2f | b (slope) = %.2f | c (center) = %.2f", a, b, c);
    }
}