package codechef.hard.q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by xd on 8/23/17.
 *
 * Problem Statement
 Chef has a undirected graph G. This graph consists of N vertices and M edges. Each vertex of the graph has an unique index from 1 to N, also each edge of the graph has an unique index from 1 to M.

 Also Chef has Q pairs of integers: Li, Ri (1 ≤ Li ≤ Ri ≤ M). For each pair Li, Ri, Chef wants to know: how many connected components will contain graph G if Chef erase all the edges from the graph, except the edges with indies X, where Li ≤ X ≤ Ri. Please, help Chef with these queries.

 Input
 The first line of the input contains an integer T denoting the number of test cases. The description of T test cases follows.
 The first line of each test case contains three integers N, M, Q. Each of the next M lines contains a pair of integers Vi, Ui - the current edge of graph G. Each of the next Q lines contains a pair of integers Li, Ri - the current query.

 Output
 For each query of each test case print the required number of connected components.

 Constraints
 1 ≤ T ≤ 1000.
 1 ≤ N, M, Q ≤ 200000.
 1 ≤ Ui, Vi ≤ N.
 1 ≤ Li ≤ Ri ≤ M.
 Sum of all values of N for test cases is not greater than 200000. Sum of all values of M for test cases is not greater than 200000. Sum of all values of Q for test cases is not greater than 200000.
 Graph G can contain self-loops and multiple edges.
 Example
 Input:
 2
 3 5 4
 1 3
 1 2
 2 1
 3 2
 2 2
 2 3
 1 5
 5 5
 1 2
 1 1 1
 1 1
 1 1
 Output:
 2
 1
 3
 1
 1
 */
public class ChefAndGraphQuery implements Runnable {
    public void _main() throws IOException {
        int numTests = nextInt();
        for (int test = 1; test <= numTests; test++) {
            int n = nextInt();
            int m = nextInt();
            int numQueries = nextInt();
            int[] edgeA = new int[m];
            int[] edgeB = new int[m];
            for (int i = 0; i < m; i++) {
                edgeA[i] = nextInt() - 1;
                edgeB[i] = nextInt() - 1;
            }
            Query[] queries = new Query[numQueries];
            for (int i = 0; i < numQueries; i++) {
                queries[i] = new Query();
                queries[i].l = nextInt() - 1;
                queries[i].r = nextInt() - 1;
                queries[i].id = i;
            }
            int bucketSize = 1;
            while (bucketSize * bucketSize <= m) {
                ++bucketSize;
            }
            final int bs = bucketSize;
            Arrays.sort(queries, new Comparator<Query>() {
                public int compare(Query a, Query b) {
                    int ab = a.l / bs;
                    int bb = b.l / bs;
                    if (ab != bb) {
                        return ab < bb ? -1 : 1;
                    }
                    if (a.r != b.r) {
                        return a.r < b.r ? -1 : 1;
                    }
                    if (a.l != b.l) {
                        return a.l < b.l ? -1 : 1;
                    }
                    return 0;
                }
            });
            int[] ans = new int[numQueries];
            int curBucket = -1;
            DSUWithRollbacks dsu = new DSUWithRollbacks(n);
            int r = -1;
            for (Query q : queries) {
                int bucket = q.l / bucketSize;
                if (bucket != curBucket) {
                    dsu.reset();
                    r = Math.min(m-1,bucketSize * (bucket + 1));
                    curBucket = bucket;
                }
                while (r <= q.r) {
                    dsu.unite(edgeA[r], edgeB[r]);
                    ++r;
                }
                int lastLInBucket = Math.min(m - 1, bucketSize * (curBucket + 1) - 1);
                for (int i = q.l; i <= lastLInBucket && i <= q.r; i++) {
                    dsu.unite(edgeA[i], edgeB[i]);
                }
                ans[q.id] = dsu.getConnectedComponents();
                for (int i = q.l; i <= lastLInBucket && i <= q.r; i++) {
                    dsu.rollback();
                }
            }
            for (int i = 0; i < numQueries; i++) {
                out.println(ans[i]);
            }
        }
    }

    class Query {
        int l, r;
        int id, ans;
    }

    class DSUWithRollbacks {
        int n;
        int[] parent;
        int[] rank;
        int numComponents;

        class Operation {
            int id;
            int v, p;
            boolean incRank;
        }
        List<Operation> operations = new ArrayList<Operation>();

        public DSUWithRollbacks(int n) {
            this.n = n;
            parent = new int[n];
            rank = new int[n];
        }

        public void reset() {
            numComponents = n;
            operations.clear();
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int getConnectedComponents() {
            return numComponents;
        }

        public int getParent(int x) {
            if (x == parent[x]) {
                return x;
            }
            // no path compression
            return getParent(parent[x]);
        }

        public void unite(int a, int b) {
            Operation op = new Operation();
            a = getParent(a);
            b = getParent(b);
            if (a == b) {
                op.v = -1;
                operations.add(op);
                return;
            }
            --numComponents;
            if (rank[a] < rank[b]) {
                parent[a] = b;
                op.v = a;
                op.p = b;
                op.incRank = false;
            } else {
                parent[b] = a;
                op.v = b;
                op.p = a;
                if (rank[a] == rank[b]) {
                    ++rank[a];
                    op.incRank = true;
                } else {
                    op.incRank = false;
                }
            }
            operations.add(op);
        }

        // revokes the result of the last unite operation
        public void rollback() {
            Operation op = operations.get(operations.size() - 1);
            operations.remove(operations.size() - 1);
            if (op.v < 0) {
                return;
            }
            ++numComponents;
            if (op.incRank) {
                --rank[op.p];
            }
            parent[op.v] = op.v;
        }

    }


    private BufferedReader in;
    private PrintWriter out;
    private StringTokenizer st;

    private String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String rl = in.readLine();
            if (rl == null)
                return null;
            st = new StringTokenizer(rl);
        }
        return st.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    private double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public static void main(String[] args) {
        //Locale.setDefault(Locale.UK);
        new Thread(new ChefAndGraphQuery()).start();
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            //in = new BufferedReader(new FileReader("a.in"));
            //out = new PrintWriter(new FileWriter("a.out"));

            _main();

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(202);
        }
    }

}
