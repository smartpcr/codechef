package codechef.tests;

import codechef.hard.PrimeNumberInTree;
import junit.framework.TestCase;

import java.util.ArrayList;

/*
 * Created by xd on 8/24/17 8:32 PM
 */
public class PrimeTest extends TestCase {

    public void testPopulatePrimeNumbers() {
        int size = 1000;
        ArrayList<Integer> primeNumbers = PrimeNumberInTree.populatePrimeNumbers(size);
        assertTrue(primeNumbers.size() == 4);
        assertEquals(2, primeNumbers.get(0).intValue());
        assertEquals(3, primeNumbers.get(1).intValue());
        assertEquals(5, primeNumbers.get(2).intValue());
        assertEquals(7, primeNumbers.get(3).intValue());
    }
}
