package deque;

import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] array;
    private int capacity;
    private int size;
    private int first;


    public ArrayDeque(){
        this.capacity = 8;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
        this.first = 0;
    }

    @Override
    public void addFirst(T item) {
        if (isFull()){
            resize();
        }
        first = Math.floorMod(first  - 1, capacity);
        array[first] = item;
        size++;
    }

    private boolean isFull(){
        return size == capacity;
    }

    private void resize() {
        capacity *= 2;
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[first];
            first = (first + 1) % size;
        }
        array = newArray;
        first = 0;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity){
            resize();
        }
        array[(first + size) % capacity] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        T removed = array[first];
        array[first] = null;
        first = (first + 1) % capacity;
        size--;
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        size--;
        T removed = array[(first + size) % capacity];
        array[(first + size) % capacity] = null;
        return removed;
    }

    @Override
    public T get(int index) {
        return array[(first + index) % capacity];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return get(index++);
            }
        };
    }
}
