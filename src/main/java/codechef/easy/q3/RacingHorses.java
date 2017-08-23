package codechef.easy.q3;

import java.io.*;
import java.util.InputMismatchException;

/*

Chef is very fond of horses. He enjoys watching them race. As expected, he has a stable full of horses. He, along with
his friends, goes to his stable during the weekends to watch a few of these horses race. Chef wants his friends to
enjoy the race and so he wants the race to be close. This can happen only if the horses are comparable on their skill
i.e. the difference in their skills is less.

There are N horses in the stable. The skill of the horse i is represented by an integer S[i]. The Chef needs to pick 2
horses for the race such that the difference in their skills is minimum. This way, he would be able to host a very
interesting race. Your task is to help him do this and report the minimum difference that is possible between 2 horses
in the race.

Input:
First line of the input file contains a single integer T, the number of test cases.
Every test case starts with a line containing the integer N.
The next line contains N space separated integers where the i-th integer is S[i].

Output:
For each test case, output a single line containing the minimum difference that is possible.
Constraints:
1 ≤ T ≤ 10
2 ≤ N ≤ 5000
1 ≤ S[i] ≤ 1000000000

Example:
Input:
1
5
4 9 1 32 13

Output:
3
 */
public class RacingHorses {
    private static int arr[];
    public static int length;

    public static void main(String[] args)
            throws Exception
    {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastReader in = new FastReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);

        StringBuilder res = new StringBuilder();

        int Z = in.nextInt();

        while(Z-->0)
        {
            int N = in.nextInt();

            arr=new int[N];

            for(int i=0;i<N;i++)
            {
                arr[i] = in.nextInt();
            }

            sort();

            int min= Integer.MAX_VALUE;

            for(int i=1;i<N;i++)
            {
                if(min > arr[i]-arr[i-1])
                    min= arr[i]-arr[i-1];
            }

            res.append(min+"\n");
        }

        System.out.print(res);
    }


    public static void sort() {

        if (arr.length == 0) {
            return;
        }
        length = arr.length;
        quickSort(0, length - 1);
    }

    private static void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        int pivot = arr[lowerIndex+(higherIndex-lowerIndex)/2];
        while (i <= j) {

            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private static void exchangeNumbers(int i, int j) {
        int temp1 = arr[i];
        arr[i] = arr[j];
        arr[j] = temp1;

    }

}


class FastReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public FastReader(InputStream stream) {
        this.stream = stream;
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public String next() {
        return readString();
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }

    public Long readLong() {
        return Long.parseLong(readString());
    }

    public Double readDouble() {
        return Double.parseDouble(readString());
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void println(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }
}
