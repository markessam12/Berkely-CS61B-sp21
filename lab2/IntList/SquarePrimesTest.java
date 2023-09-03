package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSinglePrime() {
        IntList lst = IntList.of(2621);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6869641", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesNoPrimes() {
        IntList lst = IntList.of(14, 15, 16, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 18", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesSingleComposite() {
        IntList lst = IntList.of(4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimesAllPrimes() {
        IntList lst = IntList.of(2, 3, 5, 109);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 11881", lst.toString());
        assertTrue(changed);
    }
}
