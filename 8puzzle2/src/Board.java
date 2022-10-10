import java.util.ArrayList;

public class Board {
    private int n;
    private int[][] correctboard;
    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        n = tiles.length;
        correctboard = new int[n][n];
        int count = 1;
        while (count < n * n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    correctboard[i][j] = count;
                    count++;
                    if (i == n - 1 && j == n - 1) {
                        correctboard[i][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != correctboard[i][j]) {
                    count++;
                }
            }
        }
        return count;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int netcount = 0;
        for (int i = 1; i < n * n; i++) {
            int correctr = 0;
            int correctc = 0;
            int r = 0;
            int c = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (correctboard[row][col] == i) {
                        correctr = row;
                        correctc = col;
                    }
                    if (tiles[row][col] == i) {
                        r = row;
                        c = col;
                    }
                }
            }


            netcount = netcount + Math.abs(correctr - r);
            netcount = netcount + Math.abs(correctc - c);

        }
        return netcount;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return tiles == correctboard;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (that.tiles == this.tiles);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> aob = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    if (i - 1 > -1) {
                        int[][] up = tiles;
                        up[i][j] = up[i - 1][j];
                        up[i - 1][j] = 0;
                        Board a = new Board(up);
                        aob.add(a);
                    }

                    if (i + 1 < n) {
                        int[][] down = tiles;
                        down[i][j] = down[i + 1][j];
                        down[i + 1][j] = 0;
                        Board b = new Board(down);
                        aob.add(b);
                    }
                    if (j - 1 > -1) {
                        int[][] left = tiles;
                        left[i][j] = left[i][j - 1];
                        left[i][j - 1] = 0;
                        Board c = new Board(left);
                        aob.add(c);
                    }
                    if (j + 1 < n) {
                        int[][] right = tiles;
                        right[i][j] = right[i][j + 1];
                        right[i][j + 1] = 0;
                        Board d = new Board(right);
                        aob.add(d);
                    }
                    return aob;
                }
            }
        }
        return null;


    }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twin = tiles;
        int r = 0;
        int c = 0;
        if (twin[r][c] == 0) {
            r++;
        }

        int temp = twin[r][c];
        twin[r][c] = twin[r + 1][c];
        twin[r + 1][c] = temp;

        return new Board(twin);


    }


    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] aoi = new int[3][3];
        aoi[0][0] = 6;
        aoi[0][1] = 8;
        aoi[0][2] = 1;
        aoi[1][0] = 0;
        aoi[1][1] = 4;
        aoi[1][2] = 2;
        aoi[2][0] = 7;
        aoi[2][1] = 5;
        aoi[2][2] = 3;
        Board b = new Board(aoi);
        System.out.println(b.manhattan());
    }


}


