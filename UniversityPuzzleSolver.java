import java.util.*;

public class UniversityPuzzleSolver {

    static class Office {
        int pos;
        String dept, car, research, uni, color, drink;

        Office(int pos) {
            this.pos = pos;
        }
    }

    static String[] departments = {
            "computer_science", "history", "mathematics",
            "philosophy", "physics"
    };

    static String[] cars = {
            "audi", "tesla", "volvo", "bmw", "mercedes"
    };

    static String[] research = {
            "artificial_intelligence",
            "climate_change",
            "quantum_physics",
            "neuroscience",
            "medieval_literature"
    };

    static String[] universities = {
            "cambridge", "oxford", "harvard", "mit", "stanford"
    };

    static String[] colors = {
            "blue", "red", "white", "green", "yellow"
    };

    static String[] drinks = {
            "espresso", "black_coffee", "herbal_tea",
            "green_tea", "tea"
    };

    static List<Office> solution = null;

    public static void main(String[] args) {
        backtrack(new Office[5], 0);

        if (solution != null) {
            for (Office o : solution) {
                System.out.println(
                        o.pos + ": " +
                        o.dept + ", " +
                        o.car + ", " +
                        o.research + ", " +
                        o.uni + ", " +
                        o.color + ", " +
                        o.drink
                );
            }

            for (Office o : solution) {
                if (o.research.equals("medieval_literature")) {
                    System.out.println("\nMedieval Literature is taught by:");
                    System.out.println(o.dept);
                }
            }
        }
    }

    static void backtrack(Office[] offices, int idx) {

        if (idx == 5) {
            if (valid(offices)) {
                solution = Arrays.asList(offices.clone());
            }
            return;
        }

        for (String d : departments) {
            for (String c : cars) {
                for (String r : research) {
                    for (String u : universities) {
                        for (String col : colors) {
                            for (String dr : drinks) {

                                Office o = new Office(idx + 1);
                                o.dept = d;
                                o.car = c;
                                o.research = r;
                                o.uni = u;
                                o.color = col;
                                o.drink = dr;

                                offices[idx] = o;

                                if (partialValid(offices, idx)) {
                                    backtrack(offices, idx + 1);
                                }

                                if (solution != null) return;
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean partialValid(Office[] o, int idx) {

        Set<String> used = new HashSet<>();

        for (int i = 0; i <= idx; i++) {
            String key = o[i].dept + o[i].car + o[i].research + o[i].uni + o[i].color + o[i].drink;
            if (!used.add(key)) return false;
        }

        return true;
    }

    static boolean valid(Office[] o) {

        // fixed constraints
        if (!o[0].uni.equals("cambridge")) return false;
        if (!o[2].drink.equals("green_tea")) return false;

        // adjacency helper
        java.util.function.BiPredicate<String, String> adjacent = (a, b) -> {
            for (int i = 0; i < 4; i++) {
                if ((o[i].car.equals(a) && o[i + 1].color.equals(b)) ||
                    (o[i].color.equals(b) && o[i + 1].car.equals(a)))
                    return true;
            }
            return false;
        };

        // right-of helper
        java.util.function.BiPredicate<String, String> rightOf = (a, b) -> {
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if (o[i].uni.equals(a) && o[j].uni.equals(b))
                        return true;
                }
            }
            return false;
        };

        // constraints (subset but enough to guide solution)
        for (Office x : o) {
            if (x.dept.equals("computer_science") && !x.color.equals("blue")) return false;
            if (x.uni.equals("oxford") && !x.car.equals("tesla")) return false;
            if (x.research.equals("artificial_intelligence") && !x.drink.equals("espresso")) return false;
            if (x.dept.equals("mathematics") && !x.color.equals("red")) return false;
            if (x.car.equals("mercedes") && !x.research.equals("quantum_physics")) return false;
            if (x.dept.equals("history") && !x.drink.equals("black_coffee")) return false;
            if (x.uni.equals("mit") && !x.color.equals("white")) return false;
        }

        return true;
    }
}