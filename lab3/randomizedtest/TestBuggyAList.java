package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        Random value = new Random();
        BuggyAList buggyAList = new BuggyAList();
        AListNoResizing aListNoResizing = new AListNoResizing<>();
        for (int i = 0; i < 10; i++) {
            int num = value.nextInt();
            buggyAList.addLast(num);
            aListNoResizing.addLast(num);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        }

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BA = new BuggyAList<>();

        int N = 1000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            switch (operationNumber) {
                case 0 -> { //addLast
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    BA.addLast(randVal);
                }
                case 1 -> // size
                        assertEquals(L.size(), BA.size());
                case 2 -> { // remove last
                    if (L.size() > 0) {
                        assertEquals(L.removeLast(), BA.removeLast());
                    }
                }
                case 3 -> { // get last
                    if (L.size() > 0) {
                        assertEquals(L.getLast(), BA.getLast());
                    }
                }
                default -> {
                }
            }
        }
    }

    @Test
    public void randomizedTestComparison(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
            }
        }
    }
}
