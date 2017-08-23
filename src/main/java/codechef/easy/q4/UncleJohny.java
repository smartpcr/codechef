package codechef.easy.q4;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

/*
Vlad enjoys listening to music. He lives in Sam's Town. A few days ago he had a birthday, so his parents gave him a
gift: MP3-player! Vlad was the happiest man in the world! Now he can listen his favorite songs whenever he wants!

Vlad built up his own playlist. The playlist consists of N songs, each has a unique positive integer length. Vlad likes
all the songs from his playlist, but there is a song, which he likes more than the others. It's named "Uncle Johny".

After creation of the playlist, Vlad decided to sort the songs in increasing order of their lengths. For example, if the
lengths of the songs in playlist was {1, 3, 5, 2, 4} after sorting it becomes {1, 2, 3, 4, 5}. Before the sorting,
"Uncle Johny" was on K-th position (1-indexing is assumed for the playlist) in the playlist.

Vlad needs your help! He gives you all the information of his playlist. Your task is to find the position of "Uncle
Johny" in the sorted playlist.

Input
The first line of the input contains an integer T denoting the number of test cases. The description of T test cases
follows. The first line of each test case contains one integer N denoting the number of songs in Vlad's playlist. The
second line contains N space-separated integers A1, A2, ..., AN denoting the lenghts of Vlad's songs. The third line
contains the only integer K - the position of "Uncle Johny" in the initial playlist.


Output
For each test case, output a single line containing the position of "Uncle Johny" in the sorted playlist.


Constraints
1 ≤ T ≤ 1000
1 ≤ K ≤ N ≤ 100
1 ≤ Ai ≤ 109


Example
Input:
3
4
1 3 4 2
2
5
1 2 3 9 4
5
5
1 2 3 9 4
1

Output:
3
4
1


Explanation
In the example test there are T=3 test cases.

Test case 1

In the first test case N equals to 4, K equals to 2, A equals to {1, 3, 4, 2}. The answer is 3, because {1, 3, 4, 2} ->
{1, 2, 3, 4}. A2 now is on the 3-rd position.

Test case 2

In the second test case N equals to 5, K equals to 5, A equals to {1, 2, 3, 9, 4}. The answer is 4, because {1, 2, 3, 9,
4} -> {1, 2, 3, 4, 9}. A5 now is on the 4-th position.

Test case 3

In the third test case N equals to 5, K equals to 1, A equals to {1, 2, 3, 9, 4}. The answer is 1, because {1, 2, 3, 9,
4} -> {1, 2, 3, 4, 9}. A1 stays on the 1-th position.
 */

public class UncleJohny {
    public static void main(String[] args) throws Exception {
        ChefInputHandler scan=new ChefInputHandler(System.in);
        ChefOutputHandler out = new ChefOutputHandler(System.out);

        int totalCases = scan.getInt();

        int[] output = new int[totalCases];

        for (int c = 0; c < totalCases; c++) {
            int numSongs = scan.getInt();

            ArrayList<Integer> songs = new ArrayList<>(numSongs);

            for (int s = 0; s < numSongs; s++) {
                songs.add(scan.getInt());
            }

            int johnyLen = songs.get(scan.getInt() - 1);


            Collections.sort(songs);
            //System.out.println(songs);
            //System.out.println(johnyLen);
            output[c] = songs.indexOf(johnyLen);
        }

        for (int i : output) {
            out.writeInt(i + 1);
            out.newLine();
        }

        out.flush();

    }

}

// hopefully a fast input handler for CodeChef problems
class ChefInputHandler {
    private static final int BUFF_SIZE = 8192;
    private static final byte SPACE = ' ';
    private static final byte MINUS_SIGN = '-';
    private static final byte ZERO = '0';
    private static final byte NINE = '9';

    private byte[] buffer;
    private InputStream is;
    private int index;
    private int bytesRead;

    public ChefInputHandler(InputStream inputStream) {
        is = inputStream;
        buffer = new byte[BUFF_SIZE];
        index = bytesRead = 0;
    }

    public char getChar() throws Exception {
        return (char) getNextByte();
    }

    public long getLong() throws Exception {
        return (long) getInt();
    }

    public String getString() throws Exception {
        StringBuffer buf = new StringBuffer();

        // get the next character
        byte currentByte = getNextByte();

        // chew through whitespace
        while (currentByte <= SPACE) {
            currentByte = getNextByte();
        }

        // get the string
        do {
            buf.append((char) currentByte);
            currentByte = getNextByte();
        } while (currentByte > SPACE);

        return buf.toString();
    }

    // moves past whitespace and returns the next int
    public int getInt() throws Exception {
        int retVal = 0;

        // get the next character
        byte currentByte = getNextByte();

        // chew through whitespace
        while (currentByte <= SPACE) {
            currentByte = getNextByte();
        }

        // now we're at the number (maybe) - handle a negative sign
        boolean isNegative = (currentByte == MINUS_SIGN);
        if (isNegative) {
            // read past the negative sign
            currentByte = getNextByte();
        }

        // now we're really at the number - build it
        do {
            retVal = (retVal * 10) + currentByte - ZERO;
            currentByte = getNextByte();
        } while (isDigit(currentByte));

        // handle negative value
        if (isNegative) {
            return -retVal;
        }

        return retVal;
    }

    private boolean isDigit(byte theByte) {
        return ((theByte >= ZERO) && (theByte <= NINE));
    }

    // fills the buffer if necessary and returns the next byte
    private byte getNextByte() throws Exception {
        if (index == bytesRead) {
            // we need more data - reset the index and read
            bytesRead = is.read(buffer, index=0, BUFF_SIZE);

            if (bytesRead <= 0) {
                // 4 is supposed to mean end of transmission
                return 4;
            }
        }

        // return the next byte in the buffer
        return buffer[index++];
    }
}

// hopefully a fast output handler for CodeChef problems
class ChefOutputHandler {
    private static final int BUFF_SIZE = 8192;
    private static final char NEW_LINE = '\n';
    private static final char ZERO = '0';
    private static final char MINUS_SIGN = '-';

    private byte[] buffer;
    private OutputStream os;
    private int index;

    public ChefOutputHandler(OutputStream outputStream) {
        os = outputStream;
        buffer = new byte[BUFF_SIZE];
        index = 0;
    }

    public void writeString(String theString) throws Exception {
        for (int i=0; i<theString.length(); i++) {
            writeChar(theString.charAt(i));
        }
    }

    public void writeInt(int theInt) throws Exception {
        // handle zero - exit immediately
        if (theInt == 0) {
            writeChar(ZERO);
            return;
        }

        // handle negatives
        if (theInt < 0) {
            writeChar(MINUS_SIGN);
            theInt = -theInt;
        }

        // recursively handle the number
        if (theInt < 10) {
            // down to a single digit - write it
            writeChar((char) (theInt + ZERO));
        } else {
            // strip this digit for the next recursion
            writeInt(theInt/10);

            // write this digit
            writeChar((char) ((theInt % 10) + ZERO));
        }
    }

    public void newLine() throws Exception {
        writeChar(NEW_LINE);
    }

    public void writeChar(char theChar) throws Exception {
        writeByte((byte) theChar);
    }

    public void writeByte(byte theByte) throws Exception {
        if (index == BUFF_SIZE) {
            // the buffer's full
            flush();
        }

        buffer[index++] = theByte;
    }

    // write whatever we have waiting
    public void flush() throws Exception {
        if (index > 0) {
            // there is something to write
            os.write(buffer, 0, index);
            index = 0;
        }
    }
}
