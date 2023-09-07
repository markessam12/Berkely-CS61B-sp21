package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        Deque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        Deque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeque with different parameterized types*/
    public void multipleParamTest() {
        Deque<String>  lld1 = new ArrayDeque<String>();
        Deque<Double>  lld2 = new ArrayDeque<Double>();
        Deque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        Deque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        Deque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void getWithIndexTest() {
        Deque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0){
                lld1.addLast(i);
            } else {
                lld1.addFirst(i);
            }
        }
        // resulted list is [9,7,5,3,1,0,2,4,6,8,10], capacity should be 16

        assertNull(lld1.get(12));
        assertEquals(9, (int) lld1.get(0));
        assertEquals(0, (int) lld1.get(5));
        assertEquals(10, (int) lld1.get(10));

        lld1.removeLast();
        lld1.removeFirst();
        // resulted list is [7,5,3,1,0,2,4,6,8], capacity should be 16
        assertEquals(7, (int) lld1.get(0));
        assertEquals(8, (int) lld1.get(8));
        assertEquals(0, (int) lld1.get(4));
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void iteratorTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0){
                lld1.addLast(i);
            } else {
                lld1.addFirst(i);
            }
        }
        // resulted list is [9,7,5,3,1,0,2,4,6,8,10], capacity should be 16
        StringBuilder s = new StringBuilder();
        for (Integer i : lld1) {
            s.append(i.toString()).append(',');
        }

        assertEquals("9,7,5,3,1,0,2,4,6,8,10,", s.toString());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void randomizedTest() {
        Deque<Integer> AD = new ArrayDeque<>();
        Deque<Integer> LLD = new LinkedListDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            switch (operationNumber) {
                case 0 -> { //addLast
                    int randVal = StdRandom.uniform(-100000, 100000);
                    AD.addLast(randVal);
                    LLD.addLast(randVal);
                    assertEquals(AD.size(), LLD.size());
                }
                case 1 -> {
                    int randVal = StdRandom.uniform(-100000, 100000);
                    AD.addFirst(randVal);
                    LLD.addFirst(randVal);
                    assertEquals(AD.size(), LLD.size());
                }
                case 2 -> { // remove last
                    if (!AD.isEmpty()) {
                        assertEquals(AD.removeLast(), LLD.removeLast());
                        assertEquals(AD.size(), LLD.size());
                    }
                }
                case 3 -> { // get last
                    if (!LLD.isEmpty()) {
                        int randVal = StdRandom.uniform(0, LLD.size());
                        assertEquals(LLD.get(randVal), AD.get(randVal));
                    }
                }
                case 4 -> {
                    assertEquals(AD.isEmpty(), LLD.isEmpty());
                }
                case 5 -> {
                    if (!LLD.isEmpty()) {
                        assertEquals(AD.removeFirst(), LLD.removeFirst());
                        assertEquals(AD.size(), LLD.size());
                    }
                }
                default -> {
                }
            }
        }
    }
}
