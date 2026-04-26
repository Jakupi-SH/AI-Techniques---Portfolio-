import java.util.*;

public class IDDFS_Maze_Fixed {

    static int[][] maze;
    static boolean[][] visited;
    static int rows, cols;

    static int nodesVisited;

    static int[] dRow = {-1, 1, 0, 1};
    static int[] dCol = {0, 0, -1, 1};

    static class Node {
        int r, c, depth;

        Node(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {

        runTest(100, 0.0);
        runTest(100, 0.2);
        runTest(1000, 0.3);
        //runTest(5000, 0.2);
    }

    static void runTest(int size, double obstacleChance) {

        rows = size;
        cols = size;

        maze = generateMaze(size, obstacleChance);

        nodesVisited = 0;

        long start = System.nanoTime();

        boolean found = false;

        for (int depth = 0; depth < size * size; depth++) {

            visited = new boolean[rows][cols];

            if (dlsIterative(0, 0, rows - 1, cols - 1, depth)) {
                found = true;
                break;
            }
        }

        long end = System.nanoTime();

        System.out.println("\nIDDFS (ITERATIVE FIX) " + size + "x" + size);
        System.out.println("Found: " + found);
        System.out.println("Nodes visited: " + nodesVisited);
        System.out.println("Time ms: " + (end - start) / 1_000_000);
    }

    static boolean dlsIterative(int sr, int sc, int gr, int gc, int maxDepth) {

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(sr, sc, 0));

        while (!stack.isEmpty()) {

            Node cur = stack.pop();

            if (cur.r < 0 || cur.c < 0 || cur.r >= rows || cur.c >= cols)
                continue;

            if (maze[cur.r][cur.c] == 1 || visited[cur.r][cur.c])
                continue;

            visited[cur.r][cur.c] = true;
            nodesVisited++;

            if (cur.r == gr && cur.c == gc)
                return true;

            if (cur.depth >= maxDepth)
                continue;

            for (int i = 0; i < 4; i++) {
                stack.push(new Node(
                    cur.r + dRow[i],
                    cur.c + dCol[i],
                    cur.depth + 1
                ));
            }
        }

        return false;
    }

    static int[][] generateMaze(int size, double obstacleChance) {

        int[][] m = new int[size][size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if ((i == 0 && j == 0) || (i == size - 1 && j == size - 1)) {
                    m[i][j] = 0;
                } else {
                    m[i][j] = rand.nextDouble() < obstacleChance ? 1 : 0;
                }
            }
        }

        return m;
    }
}