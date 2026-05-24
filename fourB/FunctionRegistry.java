package fourB;

import fourB.MF.MembershipFunction;
import fourB.MF.TriangularMF;
import fourB.MF.TrapezoidalMF;
import fourB.MF.GaussianMF;
import fourB.MF.BellShapedMF;
import fourB.MF.SigmoidMF;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * FunctionRegistry
 *
 * Central store for all available membership function instances.
 *
 * Default parameter values are declared as final constants and never
 * modified at runtime. When the user requests custom parameters, a
 * fresh function instance is built via buildCustom() without touching
 * the defaults or the registry map.
 *
 * Default parameter values:
 *
 *   Triangular  : a=2.0,  b=5.0,  c=8.0
 *   Trapezoidal : a=1.0,  b=3.0,  c=7.0,  d=9.0
 *   Gaussian    : c=5.0,  sigma=1.5
 *   Bell-Shaped : a=2.0,  b=3.0,  c=5.0
 *   Sigmoid     : a=2.0,  c=5.0
 *
 * All functions are keyed by a numeric string ("1"–"5") matching the
 * menu options presented to the user in InputHandler.
 */
public class FunctionRegistry {

    // ---------------------------------------------------------------
    // Hard-coded default parameter constants — never modified at runtime
    // ---------------------------------------------------------------

    // Triangular defaults
    private static final double TRI_A = 2.0;   // left foot
    private static final double TRI_B = 5.0;   // peak
    private static final double TRI_C = 8.0;   // right foot

    // Trapezoidal defaults
    private static final double TRAP_A = 1.0;  // left foot
    private static final double TRAP_B = 3.0;  // left shoulder
    private static final double TRAP_C = 7.0;  // right shoulder
    private static final double TRAP_D = 9.0;  // right foot

    // Gaussian defaults
    private static final double GAUSS_C     = 5.0;  // center
    private static final double GAUSS_SIGMA = 1.5;  // spread

    // Bell-Shaped defaults
    private static final double BELL_A = 2.0;  // width
    private static final double BELL_B = 3.0;  // slope
    private static final double BELL_C = 5.0;  // center

    // Sigmoid defaults
    private static final double SIG_A = 2.0;   // slope (positive = rising)
    private static final double SIG_C = 5.0;   // crossover point

    // ---------------------------------------------------------------
    // Singleton registry map — built once from defaults, never modified
    // ---------------------------------------------------------------

    private static final Map<String, MembershipFunction> REGISTRY = buildRegistry();

    /**
     * Builds and returns an unmodifiable map of default membership function
     * instances keyed by their menu selection number.
     *
     * @return Unmodifiable map of menu key -> MembershipFunction
     */
    private static Map<String, MembershipFunction> buildRegistry() {
        Map<String, MembershipFunction> map = new LinkedHashMap<>();

        map.put("1", new TriangularMF (TRI_A,    TRI_B,      TRI_C               ));
        map.put("2", new TrapezoidalMF(TRAP_A,   TRAP_B,     TRAP_C,   TRAP_D    ));
        map.put("3", new GaussianMF   (GAUSS_C,  GAUSS_SIGMA                     ));
        map.put("4", new BellShapedMF (BELL_A,   BELL_B,     BELL_C              ));
        map.put("5", new SigmoidMF    (SIG_A,    SIG_C                           ));

        return Collections.unmodifiableMap(map);
    }

    // ---------------------------------------------------------------
    // Public API
    // ---------------------------------------------------------------

    /**
     * Returns the full unmodifiable registry of default membership functions.
     *
     * @return Unmodifiable map of menu key -> MembershipFunction
     */
    public static Map<String, MembershipFunction> getFunctions() {
        return REGISTRY;
    }

    /**
     * Returns the default membership function instance for a given menu key.
     *
     * @param key The menu selection string (e.g. "1" through "5")
     * @return    The default MembershipFunction, or null if key not found
     */
    public static MembershipFunction getFunction(String key) {
        return REGISTRY.get(key);
    }

    /**
     * Builds and returns a fresh membership function instance using
     * user-supplied custom parameter values. The default registry and
     * its final constants are not affected in any way.
     *
     * The params array must match the expected parameter order for the
     * selected function (same order as prompted by InputHandler):
     *
     *   "1" Triangular  : params = { a, b, c }
     *   "2" Trapezoidal : params = { a, b, c, d }
     *   "3" Gaussian    : params = { c, sigma }
     *   "4" Bell-Shaped : params = { a, b, c }
     *   "5" Sigmoid     : params = { a, c }
     *
     * @param key    The menu key identifying which function type to build
     * @param params The user-supplied parameter values in the correct order
     * @return       A new MembershipFunction instance with the custom parameters
     * @throws IllegalArgumentException if the key is unrecognised
     */
    public static MembershipFunction buildCustom(String key, double... params) {
        switch (key) {
            case "1": return new TriangularMF (params[0], params[1], params[2]              );
            case "2": return new TrapezoidalMF(params[0], params[1], params[2], params[3]   );
            case "3": return new GaussianMF   (params[0], params[1]                         );
            case "4": return new BellShapedMF (params[0], params[1], params[2]              );
            case "5": return new SigmoidMF    (params[0], params[1]                         );
            default:
                throw new IllegalArgumentException("Unknown function key: " + key);
        }
    }

    /**
     * Checks whether a given menu key maps to a registered function.
     *
     * @param key The menu selection string to validate
     * @return    true if the key exists in the registry, false otherwise
     */
    public static boolean isValidKey(String key) {
        return REGISTRY.containsKey(key);
    }

    /**
     * Returns the number of membership functions available in the registry.
     *
     * @return Total count of registered functions
     */
    public static int size() {
        return REGISTRY.size();
    }

    /**
     * Builds and returns a formatted menu string listing all registered
     * membership functions by their selection number and display name.
     *
     * @return Multi-line menu string
     */
    public static String buildMenuString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, MembershipFunction> entry : REGISTRY.entrySet()) {
            sb.append(String.format("  [%s] %s%n", entry.getKey(), entry.getValue().getName()));
        }
        return sb.toString();
    }

    // Private constructor — this class is not meant to be instantiated.
    private FunctionRegistry() {}
}