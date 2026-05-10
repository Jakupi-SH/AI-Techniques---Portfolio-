import java.util.*;

public class UniversityCSP {

    static final int N = 5;

    // -------------------------------------------------
    // VARIABLES
    // Each variable stores an office position (1..5)
    // -------------------------------------------------

    static int CS, History, Mathematics, Philosophy, Physics;

    static int Tesla, BMW, Mercedes, Volvo, Audi;

    static int AI, ClimateChange, QuantumPhysics, Neuroscience, MedievalLiterature;

    static int Cambridge, Oxford, Harvard, MIT, Stanford;

    static int Blue, Red, White, Green, Yellow;

    static int Espresso, HerbalTea, GreenTea, BlackCoffee, Tea;

    static boolean solved = false;

    public static void main(String[] args) {

        List<int[]> perms = permutations();

        // -------------------------------------------------
        // UNIVERSITY ASSIGNMENTS
        // -------------------------------------------------

        for (int[] uni : perms) {

            Cambridge = uni[0];
            Oxford = uni[1];
            Harvard = uni[2];
            MIT = uni[3];
            Stanford = uni[4];

            // Constraint:
            // Cambridge = 1
            if (Cambridge != 1)
                continue;

            // Constraint:
            // Stanford > Harvard
            if (!(Stanford > Harvard))
                continue;

            // -------------------------------------------------
            // COLOR ASSIGNMENTS
            // -------------------------------------------------

            for (int[] col : perms) {

                Blue = col[0];
                Red = col[1];
                White = col[2];
                Green = col[3];
                Yellow = col[4];

                // Constraint:
                // White = MIT
                if (White != MIT)
                    continue;

                // Constraint:
                // |Cambridge - Yellow| = 1
                if (Math.abs(Cambridge - Yellow) != 1)
                    continue;

                // -------------------------------------------------
                // CAR ASSIGNMENTS
                // -------------------------------------------------

                for (int[] car : perms) {

                    Tesla = car[0];
                    BMW = car[1];
                    Mercedes = car[2];
                    Volvo = car[3];
                    Audi = car[4];

                    // Constraint:
                    // Oxford = Tesla
                    if (Oxford != Tesla)
                        continue;

                    // Constraint:
                    // |BMW - Green| = 1
                    if (Math.abs(BMW - Green) != 1)
                        continue;

                    // -------------------------------------------------
                    // DRINK ASSIGNMENTS
                    // -------------------------------------------------

                    for (int[] drink : perms) {

                        Espresso = drink[0];
                        HerbalTea = drink[1];
                        GreenTea = drink[2];
                        BlackCoffee = drink[3];
                        Tea = drink[4];

                        // Constraint:
                        // GreenTea = 3
                        if (GreenTea != 3)
                            continue;

                        // -------------------------------------------------
                        // RESEARCH ASSIGNMENTS
                        // -------------------------------------------------

                        for (int[] res : perms) {

                            AI = res[0];
                            ClimateChange = res[1];
                            QuantumPhysics = res[2];
                            Neuroscience = res[3];
                            MedievalLiterature = res[4];

                            // AI = Espresso
                            if (AI != Espresso)
                                continue;

                            // ClimateChange = HerbalTea
                            if (ClimateChange != HerbalTea)
                                continue;

                            // Mercedes = QuantumPhysics
                            if (Mercedes != QuantumPhysics)
                                continue;

                            // |Neuroscience - Audi| = 1
                            if (Math.abs(Neuroscience - Audi) != 1)
                                continue;

                            // -------------------------------------------------
                            // DEPARTMENT ASSIGNMENTS
                            // -------------------------------------------------

                            for (int[] dep : perms) {

                                CS = dep[0];
                                History = dep[1];
                                Mathematics = dep[2];
                                Philosophy = dep[3];
                                Physics = dep[4];

                                // CS = Blue
                                if (CS != Blue)
                                    continue;

                                // Mathematics = Red
                                if (Mathematics != Red)
                                    continue;

                                // Volvo = Philosophy
                                if (Volvo != Philosophy)
                                    continue;

                                // History = BlackCoffee
                                if (History != BlackCoffee)
                                    continue;

                                printSolution();
                                solved = true;
                                return;
                            }
                        }
                    }
                }
            }
        }

        if (!solved) {
            System.out.println("No solution found.");
        }
    }

    // -------------------------------------------------
    // GENERATE ALL PERMUTATIONS OF [1,2,3,4,5]
    // -------------------------------------------------

    static List<int[]> permutations() {

        List<int[]> result = new ArrayList<>();

        int[] nums = {1,2,3,4,5};

        permute(nums, 0, result);

        return result;
    }

    static void permute(int[] arr, int idx, List<int[]> result) {

        if (idx == arr.length) {
            result.add(arr.clone());
            return;
        }

        for (int i = idx; i < arr.length; i++) {

            swap(arr, idx, i);

            permute(arr, idx + 1, result);

            swap(arr, idx, i);
        }
    }

    static void swap(int[] arr, int a, int b) {

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // -------------------------------------------------
    // PRINT SOLUTION
    // -------------------------------------------------

    static void printSolution() {

        System.out.println("SOLUTION:\n");

        for (int office = 1; office <= 5; office++) {

            String dept = "";
            String car = "";
            String research = "";
            String uni = "";
            String color = "";
            String drink = "";

            if (CS == office) dept = "Computer Science";
            if (History == office) dept = "History";
            if (Mathematics == office) dept = "Mathematics";
            if (Philosophy == office) dept = "Philosophy";
            if (Physics == office) dept = "Physics";

            if (Tesla == office) car = "Tesla";
            if (BMW == office) car = "BMW";
            if (Mercedes == office) car = "Mercedes";
            if (Volvo == office) car = "Volvo";
            if (Audi == office) car = "Audi";

            if (AI == office) research = "Artificial Intelligence";
            if (ClimateChange == office) research = "Climate Change";
            if (QuantumPhysics == office) research = "Quantum Physics";
            if (Neuroscience == office) research = "Neuroscience";
            if (MedievalLiterature == office) research = "Medieval Literature";

            if (Cambridge == office) uni = "Cambridge";
            if (Oxford == office) uni = "Oxford";
            if (Harvard == office) uni = "Harvard";
            if (MIT == office) uni = "MIT";
            if (Stanford == office) uni = "Stanford";

            if (Blue == office) color = "Blue";
            if (Red == office) color = "Red";
            if (White == office) color = "White";
            if (Green == office) color = "Green";
            if (Yellow == office) color = "Yellow";

            if (Espresso == office) drink = "Espresso";
            if (HerbalTea == office) drink = "Herbal Tea";
            if (GreenTea == office) drink = "Green Tea";
            if (BlackCoffee == office) drink = "Black Coffee";
            if (Tea == office) drink = "Tea";

            System.out.println(
                "Office " + office + ": " +
                dept + ", " +
                car + ", " +
                research + ", " +
                uni + ", " +
                color + ", " +
                drink
            );
        }

        System.out.println("\nQUESTION ANSWER:");

        if (CS == MedievalLiterature)
            System.out.println("Computer Science researches Medieval Literature");

        if (History == MedievalLiterature)
            System.out.println("History researches Medieval Literature");

        if (Mathematics == MedievalLiterature)
            System.out.println("Mathematics researches Medieval Literature");

        if (Philosophy == MedievalLiterature)
            System.out.println("Philosophy researches Medieval Literature");

        if (Physics == MedievalLiterature)
            System.out.println("Physics researches Medieval Literature");
    }
}