package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<String> operationsExecuted = new ArrayDequeSolution<>();

        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            switch (operationNumber) {
                case 0 -> { //addLast
                    int randVal = StdRandom.uniform(-100000, 100000);
                    operationsExecuted.addLast(String.format("addLast(%d)", randVal));
                    correctDeque.addLast(randVal);
                    studentDeque.addLast(randVal);
                    assertEquals(operationsFormatter(operationsExecuted), correctDeque.size(), studentDeque.size());
                }
                case 1 -> { //addFirst
                    int randVal = StdRandom.uniform(-100000, 100000);
                    operationsExecuted.addLast(String.format("addFirst(%d)", randVal));
                    correctDeque.addFirst(randVal);
                    studentDeque.addFirst(randVal);
                    assertEquals(operationsFormatter(operationsExecuted), correctDeque.size(), studentDeque.size());
                }
                case 2 -> { // remove last
                    if (!correctDeque.isEmpty()) {
                        operationsExecuted.addLast("removeLast()");
                        assertEquals(operationsFormatter(operationsExecuted), correctDeque.removeLast(), studentDeque.removeLast());
                        assertEquals(operationsFormatter(operationsExecuted), correctDeque.size(), studentDeque.size());
                    }
                }
                case 3 -> { // get last
                    if (!studentDeque.isEmpty()) {
                        int randVal = StdRandom.uniform(0, studentDeque.size());
                        operationsExecuted.addLast(String.format("get(%d)", randVal));
                        assertEquals(operationsFormatter(operationsExecuted), correctDeque.get(randVal), studentDeque.get(randVal));
                    }
                }
                case 4 -> {
                    operationsExecuted.addLast("isEmpty()");
                    assertEquals(operationsFormatter(operationsExecuted), correctDeque.isEmpty(), studentDeque.isEmpty());
                }
                case 5 -> {
                    if (!studentDeque.isEmpty()) {
                        operationsExecuted.addLast("removeFirst()");
                        assertEquals(operationsFormatter(operationsExecuted), correctDeque.removeFirst(), studentDeque.removeFirst());
                        assertEquals(operationsFormatter(operationsExecuted), correctDeque.size(), studentDeque.size());
                    }
                }
                default -> {
                }
            }
        }
    }

    private String operationsFormatter(ArrayDequeSolution<String> operationsExecuted){
        return String.join("\n", operationsExecuted);
    }
}
