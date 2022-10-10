import edu.princeton.cs.algs4.MinPQ;

public class PriorityQueues2 {
    private int i;
    private int j;
    private int max;

    private class triple() {
        int i;
        int j;
        int cubesum;

        public triple(int i, int j) {
            this.i = i;
            this.j = j;
            cubesum = i * i * i + j * j * j;
        }
    }

    public static MinPQ<triple> pq;

    public PriorityQueues2(int n) {
        i = 0;
        j = 0;
        max = n;
        for (int k = 0; k < n; k++) {
            pq.insert(new triple(k, 0));
        }
    }

    public void iterate

    {
        for (int a = i; a <= max; a++) {
            for (int b = j; b <= max; b++) {
                insert(a, b, max);
            }
        }
    }


    private void insert(int i, int j, int max) {
        pq.insert(new triple(i, j));
    }
