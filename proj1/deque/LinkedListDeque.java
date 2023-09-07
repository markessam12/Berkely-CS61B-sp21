package deque;

import org.apache.commons.collections.iterators.ArrayListIterator;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{

    private static class Node<T>{
        public T value;
        public Node<T> next;
        public Node<T> previous;

        public Node(T value, Node<T> next, Node<T> previous) {
            this(value);
            this.next = next;
            this.previous = previous;
        }

        public Node(T value) {
            this.value = value;
        }

        public Node() {
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>();
        this.sentinel.next = sentinel;
        this.sentinel.previous = sentinel;
        this.size = 0;
    }

    public LinkedListDeque(T item) {
        this.sentinel = new Node<T>();
        Node<T> newElement = new Node<T>(item);
        this.sentinel.next = newElement;
        this.sentinel.previous = newElement;
        this.size = 1;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newElement = new Node<T>(item, sentinel.next, sentinel);
        sentinel.next.previous = newElement;
        sentinel.next = newElement;
        this.size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newElement = new Node<T>(item, sentinel, sentinel.previous);
        sentinel.previous.next = newElement;
        sentinel.previous = newElement;
        this.size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T element: this){
            System.out.print(element + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Node<T> removedItem = sentinel.next;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        if (size == 0){
            sentinel.previous = sentinel;
        }
        return removedItem.value;
    }

    @Override
    public T removeLast() {
        if (sentinel.previous == sentinel){
            return null;
        }
        Node<T> removedItem = sentinel.previous;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return removedItem.value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        Node<T> item = sentinel.next;
        int i = 0;
        while(i != index){
            item = item.next;
            i++;
        }
        return item.value;
    }

    public T getRecursive(int index){
        if (index < 0 || index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> node, int index){
        if(index == 0){
            return node.value;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private Node<T> currentNode = sentinel;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                index++;
                currentNode = currentNode.next;
                return currentNode.value;
            }
        };
    }
}
