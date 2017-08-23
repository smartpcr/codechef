package codechef.medium.q3;

import java.io.*;
import java.util.InputMismatchException;

/**
 * Created by xd on 8/23/17.
 *
 * All submissions for this problem are available.
 Little kids, Jack and Evan like playing their favorite game Glass-and-Stone. Today they want to play something new and came across Twitter on their father's laptop.

 They saw it for the first time but were already getting bored to see a bunch of sentences having at most 140 characters each. The only thing they liked to play with it is, closing and opening tweets.

 There are N tweets on the page and each tweet can be opened by clicking on it, to see some statistics related to that tweet. Initially all the tweets are closed. Clicking on an open tweet closes it and clicking on a closed tweet opens it. There is also a button to close all the open tweets. Given a sequence of K clicks by Jack, Evan has to guess the total number of open tweets just after each click. Please help Evan in this game.

 Input
 First line contains two integers N K, the number of tweets (numbered 1 to N) and the number of clicks respectively (1 ≤ N, K ≤ 1000). Each of the following K lines has one of the following.
 CLICK X , where X is the tweet number (1 ≤ X ≤ N)
 CLOSEALL

 Output
 Output K lines, where the ith line should contain the number of open tweets just after the ith click.

 Example
 Input:
 3 6
 CLICK 1
 CLICK 2
 CLICK 3
 CLICK 2
 CLOSEALL
 CLICK 1

 Output:
 1
 2
 3
 2
 0
 1

 Explanation:

 Let open[x] = 1 if the xth tweet is open and 0 if its closed.
 Initially open[1..3] = { 0 , 0 , 0 }. Here is the state of open[1..3] after each click and corresponding count of open tweets.

 CLICK 1 : { 1, 0, 0 }, open count = 1
 CLICK 2 : { 1, 1, 0 }, open count = 2
 CLICK 3 : { 1, 1, 1 }, open count = 3
 CLICK 2 : { 1, 0, 1 }, open count = 2
 CLOSEALL : { 0, 0, 0 }, open count = 0
 CLICK 1 : { 1, 0, 0 }, open count = 1
 */
public class ClosingTheTweets {
    public static void main(String[] args){
        InputReader in = new InputReader(System.in);
        OutputWriter os = new OutputWriter(System.out);
        boolean[] input = new boolean[in.readInt()+1];

        int K= in.readInt();
        int i=0;
        for(int k=0; k<K; k++){

            String com = in.readString();
            if(com.equals("CLICK")){
                int p =in.readInt();
                if(input[p]==false){
                    System.out.println(++i);
                    input[p]=true;
                }
                else{
                    System.out.println(--i);
                    input[p] = false;
                }
            }
            else{
                input = new boolean[input.length+1];
                i=0;
                System.out.println(i);
            }
        }
    }
}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
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

    public int readInt() {
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
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        return readString();
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
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

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

    public void flush() {
        writer.flush();
    }

}

class IOUtils {

    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }

}
