import java.util.*;

public class BFS_Maze {

    static int[][] maze;
    static boolean[][] visited;
    static int rows, cols;

    static int nodesVisited;

    static int[] dRow = {-1, 1, 0, 1};
    static int[] dCol = {0, 0, -1, 1};

    static class Node {
        int r, c;
        Node(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) {

        runTest(100, 0.0);
        runTest(100, 0.2);
        runTest(1000, 0.25);
        //runTest(5000, 0.2);
    }

    static void runTest(int size, double obstacleChance) {

        rows = size;
        cols = size;

        maze = generateMaze(size, obstacleChance);
        visited = new boolean[rows][cols];

        nodesVisited = 0;

        long start = System.nanoTime();

        boolean found = bfs(0, 0, rows - 2, cols - 2);

        long end = System.nanoTime();

        System.out.println("\nBFS TEST " + size + "x" + size);
        System.out.println("Found: " + found);
        System.out.println("Nodes visited: " + nodesVisited);
        System.out.println("Time ms: " + (end - start) / 1_000_000);
    }

    static boolean bfs(int sr, int sc, int gr, int gc) {

        Queue<Node> q = new LinkedList<>();

        q.add(new Node(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {

            Node cur = q.poll();
            nodesVisited++;

            if (cur.r == gr && cur.c == gc) return true;

            for (int i = 0; i < 4; i++) {

                int nr = cur.r + dRow[i];
                int nc = cur.c + dCol[i];

                if (nr >= 0 && nc >= 0 && nr < rows && nc < cols &&
                    maze[nr][nc] == 0 && !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }
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