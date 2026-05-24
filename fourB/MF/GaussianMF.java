package fourB.MF;

/**
 * Gaussian Membership Function
 *
 * Defined by two parameters:
 *   c     - center/mean : the point of maximum membership (μ = 1)
 *   sigma - spread      : controls the width of the curve (standard deviation)
 *
 * Formula:
 *   μ(x) = e^( -(x - c)^2 / (2 * sigma^2) )
 *
 * Constraint: sigma > 0
 *   - A larger sigma produces a wider, flatter curve.
 *   - A smaller sigma produces a narrower, sharper peak.
 */
public class GaussianMF implements MembershipFunction {

    // Hard-coded definition parameters
    private final double c;
    private final double sigma;

    /**
     * Constructs a GaussianMF with the given parameters.
     *
     * @param c     Center/mean of the curve (μ = 1 at this point)
     * @param sigma Standard deviation controlling the width of the curve
     * @throws IllegalArgumentException if sigma is not greater than zero
     */
    public GaussianMF(double c, double sigma) {
        if (sigma <= 0) {
            throw new IllegalArgumentException(
                    "Gaussian MF requires sigma > 0. " +
                            "Got: sigma=" + sigma
            );
        }
        this.c     = c;
        this.sigma = sigma;
    }

    /**
     * Computes the degree of membership μ(x) for a crisp input value x.
     * The Gaussian function always returns a value in (0.0, 1.0],
     * approaching 0 asymptotically at the extremes but never reaching it.
     *
     * @param x The crisp input value
     * @return  Membership degree in the range (0.0, 1.0]
     */
    @Override
    public double compute(double x) {
        double exponent = -Math.pow(x - c, 2) / (2 * Math.pow(sigma, 2));
        return Math.exp(exponent);
    }

    /**
     * Returns the display name of this membership function.
     *
     * @return "Gaussian"
     */
    @Override
    public String getName() {
        return "Gaussian";
    }

    /**
     * Returns a formatted string of this function's hard-coded parameters.
     *
     * @return Parameter summary string
     */
    @Override
    public String getParameterInfo() {
        return String.format("c (center) = %.2f | sigma (spread) = %.2f", c, sigma);
    }
}
