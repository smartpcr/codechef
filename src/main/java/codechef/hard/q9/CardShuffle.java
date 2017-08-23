package codechef.hard.q9;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by xd on 8/23/17.
 *
 * Chef is organizing an online card game tournament and for this purpose he has to provide a card shuffling software. This software has to simulate the following shuffling process. A stack of N cards is placed face down on the table. Cards in the stack are ordered by value. Topmost card has value 1 and the one on the bottom has value N. To shuffle the cards we repeat the following steps M times:

 take A cards from the top of the deck.
 take another B cards from the top of the deck.
 put the A cards, which you removed in the first step, back on top of the remaining deck.
 take C cards from the deck
 put the B cards, which you're still holding from the second move, card by card on top of the deck.
 finally, return the block of C cards on top
 Note: taking a block of cards from the top of the deck does not change their order. The entire block is removed in a single move and not card by card. The only exception is the fifth move, where you return cards one by one from the top.
 Input
 The first line contains integers N and M. The following M lines describe the moves by integers Ai, Bi, Ci as described in the previous section.

 In the spirit of random card shuffling, all test cases were generated with uniform random distributions to select where to cut the deck of cards.

 Output
 In a single line output the cards in the deck after performing all the moves. Cards should be listed from top of the deck to bottom and separated by spaces.

 Constraints
 1 <= N, M <= 100 000
 Example
 Input:
 10 2
 6 2 2
 5 3 6

 Output:
 1 2 8 7 3 9 6 5 4 10
 */
public class CardShuffle {
    public static void main(String[] args)throws Exception {
        InputReader in = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        ImplicitTreap treap = new ImplicitTreap();
        int n = in.nextInt(), m = in.nextInt();
        for(int i = 1; i <= n; i++){
            treap.insert(i, i);
        }
        for(int i = 1; i <= m; i++){
            int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
            treap.solve(a, b, c);
        }

        treap.print();
        pw.close();
    }



    static class ImplicitTreap {
        Node root;

        public void solve(int a, int b, int c){
            NodePair A = split(root, a);
            NodePair B = split(A.right, b);
            Node root1 = merge(A.left, B.right);
            NodePair C = split(root1, c);
            if(B.left != null) B.left.rev ^= true;
            root1 = merge(B.left, C.right);
            root = merge(C.left, root1);
        }

        static class Node{
            int size, value;
            double priority;
            boolean rev;
            Node left, right;
            public Node(int val){
                value = val; size = 1; rev = false;
                priority = Math.random();
            }
        }
        static class NodePair{
            Node left, right;
            public NodePair(Node l, Node r){
                left = l; right = r;
            }
        }
        public int size(){
            return size(root);
        }
        private int size(Node t){
            if(t == null) return 0;
            else return t.size;
        }
        private void update(Node t){
            if(t != null) t.size = size(t.left) + 1 + size(t.right);
        }
        public void insert(int key, int val){
            NodePair n = split(root, key);
            root = merge(n.left, merge(new Node(val), n.right));
        }

        private NodePair split(Node n, int k){
            NodePair res = new NodePair(null, null);
            if(n == null) return res;
            pushDown(n);
            int key = size(n.left) + 1;
            if(key > k){
                res = split(n.left, k);
                n.left = res.right;
                res.right = n;
                update(n); update(res.left); update(res.right);
            }
            else{
                res = split(n.right, k - key);
                n.right = res.left;
                res.left = n;
                update(n); update(res.left); update(res.right);
            }
            return res;
        }
        private Node merge(Node t1, Node t2){
            pushDown(t1); pushDown(t2);
            if (t1 == null) return t2;
            else if (t2 == null) return t1;
            Node newRoot = null;
            if (t1.priority > t2.priority) {
                t1.right = merge(t1.right, t2);
                newRoot = t1;
            }
            else {
                t2.left = merge(t1, t2.left);
                newRoot = t2;
            }
            update(newRoot);
            return newRoot;
        }
        public void clear(){
            root = null;
        }
        public void print(){
            print(root);
            if(root != null) System.out.println();
        }
        private void print(Node t){
            if(t == null) return;
            pushDown(t);
            print(t.left);
            System.out.print(t.value+" ");
            print(t.right);
        }

        private void pushDown(Node t){
            if(t != null){
                if(t.rev) {
                    Node temp = t.right;
                    t.right = t.left;
                    t.left = temp;
                    if(t.left != null) t.left.rev ^= true;
                    if(t.right != null) t.right.rev ^= true;
                    t.rev = false;
                }
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        //InputReader in = new InputReader(new FileReader("File_Name"));
        public InputReader(FileReader file) {
            reader = new BufferedReader(file);
            tokenizer = null;
        }

        public String next() {

            try {
                while (tokenizer == null || !tokenizer.hasMoreTokens())
                    tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                return null;
            }

            return tokenizer.nextToken();
        }

        public String nextLine() {
            String line = null;
            try {
                tokenizer = null;
                line =  reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return line;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
        public boolean hasNext(){
            try {
                while (tokenizer == null || !tokenizer.hasMoreTokens())
                    tokenizer = new StringTokenizer(reader.readLine());
            }
            catch (Exception e) {
                return false;
            }

            return true;

        }
    }
}
