package codechef.hard.q1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * Problem description.
 You are given a tree. If we select 2 distinct nodes uniformly at random, what's the probability that the distance
 between these 2 nodes is a prime number?

 Input
 The first line contains a number N: the number of nodes in this tree.
 The following N-1 lines contain pairs a[i] and b[i], which means there is an edge with length 1 between a[i] and b[i].

 Output
 Output a real number denote the probability we want.
 You'll get accept if the difference between your answer and standard answer is no more than 10^-6.

 Constraints
 2 ≤ N ≤ 50,000

 The input must be a tree.

 Example
 Input:
 5
 1 2
 2 3
 3 4
 4 5

 Output:
 0.5
 Explanation
 We have C(5, 2) = 10 choices, and these 5 of them have a prime distance:

 1-3, 2-4, 3-5: 2

 1-4, 2-5: 3

 Note that 1 is not a prime number.
 */
public class PrimeNumberOnTree {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    int n;
    int N = (int) (5e4+10);
    boolean P[] = new boolean[N];
    long dist[][] = new long[20][N];
    long sub[] = new long[N];
    int nn = 0;
    long ans = 0;
    HashSet<Integer> a[] = new HashSet[N];
    ArrayList<Integer> prime = new ArrayList<Integer>();
    void solve() {
        prime();
        n = ni();
        for(int i=0;i<=n;i++)
            a[i] = new HashSet<>();
        for(int i=0;i<n-1;i++){
            int u = ni();
            int v = ni();
            a[u].add(v);
            a[v].add(u);
        }
        decompose(1,0);
        ans /= 2.0;
        double total = ((long)n*(n-1L))/2.0;
        out.println(ans/total);
    }

    void dfs1(int u,int p){
        nn++;
        sub[u] = 1;
        for(int k:a[u])
            if(k!=p)
            {
                dfs1(k,u);
                sub[u] += sub[k];
            }
        return;
    }

    int dfs2(int u,int p){
        for(int k:a[u])
            if(k!=p && sub[k]>nn/2)
                return dfs2(k,u);
        return u;
    }

    void dfs3(int u,int p,int depth,int dis,int add){
        dist[depth][dis]+=add;
        for(int k:a[u]){
            if(k!=p)
                dfs3(k,u,depth,dis+1,add);
        }
    }

    long dfs4(int u,int p,int depth,int dis){
        long ret = 0;
        for(int k:prime){
            if(k-dis<0)
                continue;
            if(dist[depth][k-dis]==0)break;
            if(k==dis)
                ret += 2*dist[depth][k-dis];
            else
                ret += dist[depth][k-dis];
        }
        for(int k:a[u]){
            if(k!=p){
                ret += dfs4(k,u,depth,dis+1);
            }
        }
        return ret;
    }

    void decompose(int u,int depth){
        nn = 0;
        dfs1(u,u);
        int centroid = dfs2(u,u);
        dfs3(centroid,centroid,depth,0,1);
        long add = 0;
        for(int k:a[centroid]){
            dfs3(k,centroid,depth,1,-1);
            add += dfs4(k,centroid,depth,1);
            dfs3(k,centroid,depth,1,1);
        }
        ans += add;
        for(int k:a[centroid]){
            a[k].remove(centroid);
            decompose(k,depth+1);
        }
        for(int i=0;i<N && dist[depth][i]!=0;i++)
            dist[depth][i]=0;
    }

    void prime(){
        Arrays.fill(P,true);
        for(int i=2;i*i<N;i++){
            if(P[i]){
                for(int j=2;j*i<N;j++){
                    P[i*j]=false;
                }
            }
        }
        for(int i=2;i<N;i++)
            if(P[i])
                prime.add(i);
    }



    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        if (!INPUT.isEmpty())
            tr(System.currentTimeMillis() - s + "ms");
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new PrimeNumberOnTree().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1)
            throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0)
                return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b))
            ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b !=
            // ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++)
            map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
            ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private void tr(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
}
