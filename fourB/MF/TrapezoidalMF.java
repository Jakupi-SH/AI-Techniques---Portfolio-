package fourB.MF;

    /**
     * Trapezoidal Membership Function
     *
     * Defined by four parameters:
     *   a - left foot     : the point where membership begins rising  (μ = 0)
     *   b - left shoulder : the point where membership reaches its peak (μ = 1)
     *   c - right shoulder: the point where membership begins falling  (μ = 1)
     *   d - right foot    : the point where membership finishes        (μ = 0)
     *
     * Formula:
     *   μ(x) = max( min( (x-a)/(b-a), 1, (d-x)/(d-c) ), 0 )
     *
     * Constraint: a <= b <= c <= d
     *
     * Note: A Triangular MF is a special case of this where b == c.
     */
    public class TrapezoidalMF implements MembershipFunction {

        // Hard-coded definition parameters
        private final double a;
        private final double b;
        private final double c;
        private final double d;

        /**
         * Constructs a TrapezoidalMF with the given parameters.
         *
         * @param a Left foot      (μ = 0, membership starts rising)
         * @param b Left shoulder  (μ = 1, flat top begins)
         * @param c Right shoulder (μ = 1, flat top ends)
         * @param d Right foot     (μ = 0, membership finishes)
         * @throws IllegalArgumentException if the constraint a <= b <= c <= d is violated
         */
        public TrapezoidalMF(double a, double b, double c, double d) {
            if (!(a <= b && b <= c && c <= d)) {
                throw new IllegalArgumentException(
                        "Trapezoidal MF requires a <= b <= c <= d. " +
                                "Got: a=" + a + ", b=" + b + ", c=" + c + ", d=" + d
                );
            }
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        /**
         * Computes the degree of membership μ(x) for a crisp input value x.
         *
         * @param x The crisp input value
         * @return  Membership degree in the range [0.0, 1.0]
         */
        @Override
        public double compute(double x) {

            // Left of the trapezoid — no membership
            if (x <= a) return 0.0;

            // Right of the trapezoid — no membership
            if (x >= d) return 0.0;

            // Flat top — full membership
            if (x >= b && x <= c) return 1.0;

            // Rising slope: from a up to b
            if (x < b) {
                // Guard against division by zero when a == b
                if (b == a) return 1.0;
                return (x - a) / (b - a);
            }

            // Falling slope: from c down to d
            // Guard against division by zero when c == d
            if (c == d) return 1.0;
            return (d - x) / (d - c);
        }

        /**
         * Returns the display name of this membership function.
         *
         * @return "Trapezoidal"
         */
        @Override
        public String getName() {
            return "Trapezoidal";
        }

        /**
         * Returns a formatted string of this function's hard-coded parameters.
         *
         * @return Parameter summary string
         */
        @Override
        public String getParameterInfo() {
            return String.format(
                    "a (left foot) = %.2f | b (left shoulder) = %.2f | c (right shoulder) = %.2f | d (right foot) = %.2f",
                    a, b, c, d
            );
        }
    }

