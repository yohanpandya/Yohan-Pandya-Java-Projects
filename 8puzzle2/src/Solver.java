import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Solver {
    private MinPQ<SearchNode> MinPQ;
    private MinPQ<SearchNode> MinPQ2;
    SearchNode initial;

    private static class SearchNode implements Comparable<SearchNode> {
        private Board b;
        private SearchNode previous;
        private int manhattan;
        private int hamming;
        private int moves;

        public SearchNode(Board b, int moves, SearchNode previous) {
            this.b = b;
            this.moves = moves;
            this.previous = previous;
            manhattan = b.manhattan() + moves;
            hamming = b.hamming() + moves;
        }

        public int compareTo(SearchNode o) {
            return Integer.compare(manhattan, o.manhattan);
        }
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("null argument");
        }
        MinPQ = new MinPQ<>();
        SearchNode s = new SearchNode(initial, 0, null);
        this.initial = s;
        MinPQ.insert(s);
        isSolvable();
        /*Board board1 = MinPQ.delMin().b;
        SearchNode s1 = new SearchNode(initial, 0, null);
        s1.b = board1;
        MinPQ.insert(s1);
        while (!board1.isGoal()) {
            solve(MinPQ, board1);
            board1 = MinPQ.delMin().b;*/
    }


    public boolean isSolvable() {
        MinPQ2 = new MinPQ<>();
        Board board1 = initial.b;
        Board board2 = board1.twin();
        SearchNode s1 = new SearchNode(initial.b, 0, null);
        s1.b = board1;
        MinPQ.insert(s1);
        SearchNode s2 = new SearchNode(initial.b, 0, null);
        s2.b = board2;
        MinPQ2.insert(s2);

        while (!board1.isGoal() || !board2.isGoal()) {
            solve(MinPQ, board1);
            solve(MinPQ2, board2);
            board2 = MinPQ2.delMin().b;
            board1 = MinPQ.delMin().b;

        }
        return board1.isGoal();


    }

    private void solve(MinPQ pq, Board b) {
        Iterable<Board> neighbors = b.neighbors();
        ArrayList<Board> neighbors2 = (ArrayList<Board>) neighbors;
        System.out.println(neighbors2.size());
        for (Board neighbor : neighbors2) {
            if (pq.equals(MinPQ)) {
                System.out.println("MINPQ!");
                SearchNode s = new SearchNode(neighbor, 0, initial);
                if (!neighbor.equals(s.previous.b)) {
                    s.b = neighbor;
                    MinPQ.insert(s);
                }
            } else if (pq.equals(MinPQ2)) {

                System.out.println("MINPQ2!");
                SearchNode s = new SearchNode(neighbor, 0, initial);
                if (!neighbor.equals(s.previous.b)) {
                    s.b = neighbor;
                    MinPQ2.insert(s);
                }
            } else {
                System.out.println("Neither!");
            }
        }


    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        ArrayList<Board> aob = new ArrayList<Board>();
        while (!MinPQ.isEmpty()) {

            aob.add(MinPQ.delMin().b);
        }
        return aob;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}

