package codechef.hard.q8;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xd on 8/23/17.
 *
 * Given an initially empty integer array (1-indexed) and some queries:

 Type 0: Add the integer number x at the end of the array.
 Type 1: On the interval L..R find a number y, to maximize (x xor y).
 Type 2: Delete last k numbers in the array
 Type 3: On the interval L..R, count the number of integers less than or equal to x.
 Type 4: On the interval L..R, find the kth smallest integer (kth order statistic).
 Input
 The first line contains a single integer M denoting number of queries.
 The following M lines contain queries, form of queries is as follows.
 Query type 0 has the form "0 x".
 Query type 1 has the form "1 L R x".
 Query type 2 has the form "2 k".
 Query type 3 has the form "3 L R x".
 Query type 4 has the form "4 L R k".
 Note that, there will be no invalid query in the input.
 Output
 For each Query of type 1, 3 and 4 output the result in a single line.

 Constraints
 Let N denote numbers of elements in before executing the query.
 1 ≤ M ≤ 5 * 105
 1 ≤ x ≤ 5 * 105
 1 ≤ L ≤ R ≤ N
 For query type 2, 1 ≤ k ≤ N	and for query type 4, k ≤ R-L+1
 Subtasks
 Subtask #1 (40 points): 1 ≤ M ≤ 5 * 104
 Subtask #2: (60 points): 1 ≤ M ≤ 5 * 105
 Example
 Input:
 10
 0 8
 4 1 1 1
 0 2
 1 2 2 7
 1 2 2 7
 0 1
 3 2 2 2
 1 1 2 3
 3 1 3 5
 0 6

 Output:
 8
 2
 2
 1
 8
 2
 */
public class XorQueries {
    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        int M = reader.readInt();
        Node[] array = new Node[M+1];
        int depth = 20;
        array[0] = emptyData(depth);
        int length = 1;
        StringBuilder output = new StringBuilder(8*M);
        for (int m=0; m<M; m++) {
            int type = reader.readInt();
            if (type == 0) {
                int x = reader.readInt();
                Node node = new Node();
                Node newNode = node;
                Node oldNode = array[length-1];
                for (int bit=depth-1; bit>=0; bit--) {
                    newNode.count = oldNode.count+1;
                    if ((x&(1 << bit)) == 0) {
                        newNode.zero = new Node();
                        newNode.one = oldNode.one;
                        oldNode = oldNode.zero;
                        newNode = newNode.zero;
                    } else {
                        newNode.one = new Node();
                        newNode.zero = oldNode.zero;
                        oldNode = oldNode.one;
                        newNode = newNode.one;
                    }
                }
                newNode.count = oldNode.count+1;
                array[length++] = node;
            } else if (type == 1) {
                int L = reader.readInt();
                int R = reader.readInt();
                int x = reader.readInt();
                Node lNode = array[L-1];
                Node rNode = array[R];
                int answer = 0;
                for (int bit=depth-1; bit>=0; bit--) {
                    int mask = (1 << bit);
                    if ((x&mask) == 0) {
                        if (rNode.one.count-lNode.one.count > 0) {
                            answer += mask;
                            rNode = rNode.one;
                            lNode = lNode.one;
                        } else {
                            rNode = rNode.zero;
                            lNode = lNode.zero;
                        }
                    } else {
                        if (rNode.zero.count-lNode.zero.count > 0) {
                            rNode = rNode.zero;
                            lNode = lNode.zero;
                        } else {
                            answer += mask;
                            rNode = rNode.one;
                            lNode = lNode.one;
                        }
                    }
                }
                output.append(answer).append('\n');
            } else if (type == 2) {
                int k = reader.readInt();
                length -= k;
            } else if (type == 3) {
                int L = reader.readInt();
                int R = reader.readInt();
                int x = reader.readInt();
                int answer = R-L+1;
                Node lNode = array[L-1];
                Node rNode = array[R];
                for (int bit=depth-1; bit>=0; bit--) {
                    int mask = (1 << bit);
                    if ((x&mask) == 0) {
                        answer -= rNode.one.count-lNode.one.count;
                        lNode = lNode.zero;
                        rNode = rNode.zero;
                    } else {
                        lNode = lNode.one;
                        rNode = rNode.one;
                    }
                }
                output.append(answer).append('\n');
            } else {
                int L = reader.readInt();
                int R = reader.readInt();
                int k = reader.readInt();
                int answer = 0;
                Node lNode = array[L-1];
                Node rNode = array[R];
                for (int bit=depth-1; bit>=0; bit--) {
                    int zeros = rNode.zero.count-lNode.zero.count;
                    if (zeros >= k) {
                        lNode = lNode.zero;
                        rNode = rNode.zero;
                    } else {
                        k -= zeros;
                        lNode = lNode.one;
                        rNode = rNode.one;
                        answer += (1<<bit);
                    }
                }
                output.append(answer).append('\n');
            }
        }
        System.out.print(output);
    }

    static Node emptyData(int depth) {
        Node node = new Node();
        if (depth > 0) {
            node.zero = emptyData(depth-1);
            node.one = emptyData(depth-1);
        }
        return node;
    }

    static class Node {
        int count;
        Node zero;
        Node one;
    }

    static final class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}
