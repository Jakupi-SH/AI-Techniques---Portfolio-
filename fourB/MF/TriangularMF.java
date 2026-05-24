package fourB.MF;

/**
 * Triangular Membership Function
 *
 * Defined by three parameters:
 *   a - left foot  : the point where membership begins rising  (μ = 0)
 *   b - peak       : the point of maximum membership           (μ = 1)
 *   c - right foot : the point where membership finishes       (μ = 0)
 *
 * Formula:
 *   μ(x) = max( min( (x-a)/(b-a), (c-x)/(c-b) ), 0 )
 *
 * Constraint: a <= b <= c
 */
public class TriangularMF implements MembershipFunction {

    // Hard-coded definition parameters
    private final double a;
    private final double b;
    private final double c;

    /**
     * Constructs a TriangularMF with the given parameters.
     *
     * @param a Left foot  (μ = 0, membership starts rising)
     * @param b Peak       (μ = 1, maximum membership)
     * @param c Right foot (μ = 0, membership finishes)
     * @throws IllegalArgumentException if the constraint a <= b <= c is violated
     */
    public TriangularMF(double a, double b, double c) {
        if (!(a <= b && b <= c)) {
            throw new IllegalArgumentException(
                    "Triangular MF requires a <= b <= c. " +
                            "Got: a=" + a + ", b=" + b + ", c=" + c
            );
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Computes the degree of membership μ(x) for a crisp input value x.
     *
     * @param x The crisp input value
     * @return  Membership degree in the range [0.0, 1.0]
     */
    @Override
    public double compute(double x) {

        // Left of the triangle — no membership
        if (x <= a) return 0.0;

        // Right of the triangle — no membership
        if (x >= c) return 0.0;

        // Rising slope: from a up to the peak b
        if (x <= b) {
            // Guard against division by zero when a == b
            if (b == a) return 1.0;
            return (x - a) / (b - a);
        }

        // Falling slope: from the peak b down to c
        // Guard against division by zero when b == c
        if (c == b) return 1.0;
        return (c - x) / (c - b);
    }

    /**
     * Returns the display name of this membership function.
     *
     * @return "Triangular"
     */
    @Override
    public String getName() {
        return "Triangular";
    }

    /**
     * Returns a formatted string of this function's hard-coded parameters.
     *
     * @return Parameter summary string
     */
    @Override
    public String getParameterInfo() {
        return String.format("a (left foot) = %.2f | b (peak) = %.2f | c (right foot) = %.2f", a, b, c);
    }
}
