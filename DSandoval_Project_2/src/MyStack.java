/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description: Implementation of a generic stack using an array-based structure.
 * Due: 2/22/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 * Name: David Sandoval
 */

import java.util.NoSuchElementException;


public class MyStack<T> implements StackADT<T> {

    /** This Array is used to store stack elements */
    private T[] items;

    /** The Index makes the number of elements in the stack */
    private int top;

    /**
     * This constructs an empty stack with an initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public MyStack() {
        items = (T[]) new Object[10];
        top = 0;
    }

    /**
     * Pushes an item to the top of the stack.
     *
     * @param item the element to be added to the stack
     * @throws IllegalArgumentException if the item fails
     * @throws IllegalStateException if the stack has reached MAX_CAPACITY
     */
    @Override
    public void push(T item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot push null");

        if (top >= MAX_CAPACITY)
            throw new IllegalStateException("Stack is full");

        if (top == items.length)
            resize(Math.min(items.length * 2, MAX_CAPACITY));

        items[top++] = item;
    }

    /**
     * This Removes and then returns the element at the top of the stack.
     *
     * @return the element removed from the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        T val = items[--top];
        items[top] = null;
        return val;
    }

    /**
     * This returns the element at the top of the stack without removing it.
     *
     * @return the stack is reached with the element on top
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");

        return items[top - 1];
    }

    /**
     * Determines whether the stack is empty.
     *
     * @return true if the stack contains no elements; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Returns the number of elements currently stored in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return top;
    }

    /**
     * Returns an array containing all elements in the stack
     * in stack order (bottom to top).
     *
     * @return an array representation of the stack
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        T[] arr = (T[]) new Object[top];
        System.arraycopy(items, 0, arr, 0, top);
        return arr;
    }

    /**
     * Resizes the internal array to the specified new size.
     *
     * @param newSize the new capacity of the internal array
     */
    private void resize(int newSize) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[newSize];
        System.arraycopy(items, 0, temp, 0, top);
        items = temp;
    }
}