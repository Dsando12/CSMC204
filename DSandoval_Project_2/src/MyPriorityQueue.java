/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description: Implementation of a generic Priority Queue using
 * a binary heap structure backed by an array.
 * Due: 2/22/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 * Name: David Sandoval
 */

import java.util.Comparator;
import java.util.NoSuchElementException;


public class MyPriorityQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 1000;

    private T[] items;
    private int size;
    private Comparator<T> comparator;

    /**
     * Constructing a priority queue for the default capacity
     * and the comparator.
     *
     * @param comparator used to order elements
     * @throws IllegalArgumentException if the comparator fails
     */
    @SuppressWarnings("unchecked")
    public MyPriorityQueue(Comparator<T> comparator) {
        if (comparator == null)
            throw new IllegalArgumentException("Comparator cannot be null");

        this.comparator = comparator;
        this.items = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructing a priority queue for a specified capacity
     * and comparator.
     *
     * @param capacity the maximum number of elements the queue holds
     * @param comparator the comparator used to order elements
     * @throws IllegalArgumentException if capacity is invalid
     * or comparator fails
     */
    @SuppressWarnings("unchecked")
    public MyPriorityQueue(int capacity, Comparator<T> comparator) {
        if (capacity <= 0 || capacity > MAX_CAPACITY)
            throw new IllegalArgumentException("Invalid capacity");

        if (comparator == null)
            throw new IllegalArgumentException("Comparator cannot be null");

        this.comparator = comparator;
        this.items = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Returns when it is true if the priority queue contains no elements.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return the current size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * The element is placed into the priority queue.
     *
     * @param item the element to insert
     * @throws IllegalArgumentException if the item is null
     * @throws IllegalStateException if the queue is full
     */
    public void enqueue(T item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot insert null");

        if (size == items.length)
            throw new IllegalStateException("PriorityQueue is full");

        items[size] = item;
        size++;
        heapifyUp(size - 1);
    }

    /**
     * Removes and returns the highest-priority element in the queue.
     *
     * @return the element with the highest priority
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("PriorityQueue is empty");

        T result = items[0];
        items[0] = items[size - 1];
        items[size - 1] = null;
        size--;

        heapifyDown(0);

        return result;
    }

    /**
     * Returns the highest-priority element without removing it.
     *
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("PriorityQueue is empty");

        return items[0];
    }

    /**
     * Returns any array that contains current elements
     * in the priority queue.
     *
     * @return an array of elements in heap order
     */
    public Object[] toArray() {
        Object[] copy = new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        return copy;
    }

    /**
     * Restores the heap order by moving the element at the specified
     * index upward until the heap property is met with expectation.
     *
     * @param index the index of the element to move upward
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (comparator.compare(items[index], items[parent]) >= 0)
                break;

            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Restores heap order by moving the element at the specified
     * index downward until the heap property is satisfied.
     *
     * @param index the index of the element to move downward
     */
    private void heapifyDown(int index) {
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size &&
                    comparator.compare(items[left], items[smallest]) < 0) {
                smallest = left;
            }

            if (right < size &&
                    comparator.compare(items[right], items[smallest]) < 0) {
                smallest = right;
            }

            if (smallest == index)
                break;

            swap(index, smallest);
            index = smallest;
        }
    }

    /**
     * Swaps two elements in the heap array.
     *
     * @param i index of first element
     * @param j index of second element
     */
    private void swap(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}