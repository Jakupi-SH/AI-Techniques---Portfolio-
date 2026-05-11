import java.util.*;

public class UniversityCSP {

    static List<int[]> perms = new ArrayList<>();

    static boolean solved = false;

    public static void main(String[] args) {

        generatePermutations(new int[]{1,2,3,4,5}, 0);

        for (int[] dep : perms) {

            int CS = dep[0];
            int History = dep[1];
            int Mathematics = dep[2];
            int Philosophy = dep[3];
            int Physics = dep[4];

            for (int[] car : perms) {

                int Tesla = car[0];
                int BMW = car[1];
                int Mercedes = car[2];
                int Volvo = car[3];
                int Audi = car[4];

                for (int[] res : perms) {

                    int AI = res[0];
                    int ClimateChange = res[1];
                    int QuantumPhysics = res[2];
                    int Neuroscience = res[3];
                    int MedievalLiterature = res[4];

                    for (int[] uni : perms) {

                        int Cambridge = uni[0];
                        int Oxford = uni[1];
                        int Harvard = uni[2];
                        int MIT = uni[3];
                        int Stanford = uni[4];

                        for (int[] col : perms) {

                            int Blue = col[0];
                            int Red = col[1];
                            int White = col[2];
                            int Green = col[3];
                            int Yellow = col[4];

                            for (int[] drink : perms) {

                                int Espresso = drink[0];
                                int HerbalTea = drink[1];
                                int GreenTea = drink[2];
                                int BlackCoffee = drink[3];
                                int Tea = drink[4];

                                // -------------------------------------------------
                                // CONSTRAINTS
                                // -------------------------------------------------

                                if (CS != Blue) continue;

                                if (Oxford != Tesla) continue;

                                if (AI != Espresso) continue;

                                if (Cambridge != 1) continue;

                                if (Math.abs(BMW - Green) != 1) continue;

                                if (ClimateChange != HerbalTea) continue;

                                if (Mathematics != Red) continue;

                                if (Mercedes != QuantumPhysics) continue;

                                if (GreenTea != 3) continue;

                                if (Math.abs(Cambridge - Yellow) != 1) continue;

                                if (Volvo != Philosophy) continue;

                                if (Math.abs(Neuroscience - Audi) != 1) continue;

                                if (History != BlackCoffee) continue;

                                if (White != MIT) continue;

                                if (!(Stanford > Harvard)) continue;

                                // -------------------------------------------------
                                // FIRST VALID SOLUTION FOUND
                                // -------------------------------------------------

                                System.out.println("VALID SOLUTION FOUND\n");

                                for (int office = 1; office <= 5; office++) {

                                    String dept = "";
                                    String carName = "";
                                    String research = "";
                                    String university = "";
                                    String color = "";
                                    String drinkName = "";

                                    if (CS == office) dept = "Computer Science";
                                    if (History == office) dept = "History";
                                    if (Mathematics == office) dept = "Mathematics";
                                    if (Philosophy == office) dept = "Philosophy";
                                    if (Physics == office) dept = "Physics";

                                    if (Tesla == office) carName = "Tesla";
                                    if (BMW == office) carName = "BMW";
                                    if (Mercedes == office) carName = "Mercedes";
                                    if (Volvo == office) carName = "Volvo";
                                    if (Audi == office) carName = "Audi";

                                    if (AI == office) research = "Artificial Intelligence";
                                    if (ClimateChange == office) research = "Climate Change";
                                    if (QuantumPhysics == office) research = "Quantum Physics";
                                    if (Neuroscience == office) research = "Neuroscience";
                                    if (MedievalLiterature == office) research = "Medieval Literature";

                                    if (Cambridge == office) university = "Cambridge";
                                    if (Oxford == office) university = "Oxford";
                                    if (Harvard == office) university = "Harvard";
                                    if (MIT == office) university = "MIT";
                                    if (Stanford == office) university = "Stanford";

                                    if (Blue == office) color = "Blue";
                                    if (Red == office) color = "Red";
                                    if (White == office) color = "White";
                                    if (Green == office) color = "Green";
                                    if (Yellow == office) color = "Yellow";

                                    if (Espresso == office) drinkName = "Espresso";
                                    if (HerbalTea == office) drinkName = "Herbal Tea";
                                    if (GreenTea == office) drinkName = "Green Tea";
                                    if (BlackCoffee == office) drinkName = "Black Coffee";
                                    if (Tea == office) drinkName = "Tea";

                                    System.out.println(
                                        "Office " + office + ": "
                                        + dept + ", "
                                        + carName + ", "
                                        + research + ", "
                                        + university + ", "
                                        + color + ", "
                                        + drinkName
                                    );
                                }

                                System.out.println("\nQUESTION ANSWER:");

                                if (CS == MedievalLiterature)
                                    System.out.println("Computer professor Science researches Medieval Literature");

                                if (History == MedievalLiterature)
                                    System.out.println("History professor researches Medieval Literature");

                                if (Mathematics == MedievalLiterature)
                                    System.out.println("Mathematics professor researches Medieval Literature");

                                if (Philosophy == MedievalLiterature)
                                    System.out.println("Philosophy professor researches Medieval Literature");

                                if (Physics == MedievalLiterature)
                                    System.out.println("Physics professor researches Medieval Literature");

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

    static void generatePermutations(int[] arr, int idx) {

        if (idx == arr.length) {
            perms.add(arr.clone());
            return;
        }

        for (int i = idx; i < arr.length; i++) {

            swap(arr, idx, i);

            generatePermutations(arr, idx + 1);

            swap(arr, idx, i);
        }
    }

    static void swap(int[] arr, int a, int b) {

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}