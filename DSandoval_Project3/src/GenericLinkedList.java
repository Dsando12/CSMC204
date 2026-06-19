/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 3/8/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This is a double linked list that can store any elements of type T
 * <p>
 * This list supports adding and removing elements from both ends,
 * accessing elements by index, and iterating forward and backward
 * using the {@link ListIterator}.
 *
 * @param <T> the type of elements stored in the list
 */
public class GenericLinkedList<T> implements Iterable<T> {

    /** The first node in the list */
    private Node<T> head;

    /** The last node in the list */
    private Node<T> tail;

    /** Number of elements stored in the list */
    private int size;

    /**
     * Node class shows each element in the linked list
     *
     * @param <T> the data type stored in the node
     */
    private static class Node<T> {

        /** Data stored in this node */
        T data;

        /** The next node in the list */
        Node<T> next;

        /** Calls back to the previous node in the list */
        Node<T> prev;

        /**
         * Makes  a new node that has stored the given data
         *
         * @param data element stored in the node
         */
        Node(T data) {
            this.data = data;
        }
    }

    /**
     * Constructs an empty GenericLinkedList
     */
    public GenericLinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * This is adding a new element to the beginning of the list
     *
     * @param element the element that is being added
     */
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the end of the list
     *
     * @param element the element to add
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the first element in the list without removing it.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.data;
    }

    /**
     * Returns the last element in the list without removing it.
     *
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.data;
    }

    /**
     * Returns the element at a specific index.
     *
     * @param index position of the element
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<T> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }

        return current.data;
    }

    /**
     * Checks whether the list contains the specified element.
     *
     * @param element element to search for
     * @return true if the element exists in the list, false otherwise
     */
    public boolean contains(T element) {
        for (Node<T> curr = head; curr != null; curr = curr.next) {
            if (curr.data.equals(element)) return true;
        }
        return false;
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return removed element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        T data = head.data;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return data;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return removed element
     * @throws NoSuchElementException if the list is empty
     */
    public T removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        T data = tail.data;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return data;
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * @param index position of the element
     * @return removed element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<T> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }

        return unlink(current);
    }

    /**
     * Removes the first occurrence of the specified element.
     *
     * @param element element to remove
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(T element) {
        for (Node<T> curr = head; curr != null; curr = curr.next) {
            if (curr.data.equals(element)) {
                unlink(curr);
                return true;
            }
        }
        return false;
    }

    /**
     * Internal helper method that removes a node from the list.
     *
     * @param node node to remove
     * @return the data stored in the removed node
     */
    private T unlink(Node<T> node) {

        if (node.prev == null)
            head = node.next;
        else
            node.prev.next = node.next;

        if (node.next == null)
            tail = node.prev;
        else
            node.next.prev = node.prev;

        size--;
        return node.data;
    }

    /**
     * This removes all elements from the list.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Converts the linked list to an array.
     *
     * @return array containing all elements in order
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;

        for (Node<T> curr = head; curr != null; curr = curr.next)
            arr[i++] = curr.data;

        return arr;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if the list has no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a list iterator for traversing the list.
     *
     * @return a ListIterator for this list
     */
    @Override
    public ListIterator<T> iterator() {
        return new GenericIterator();
    }

    /**
     * Iterator implementation for traversing the linked list
     * forward and backward.
     */
    private class GenericIterator implements ListIterator<T> {

        /** Reference to the next node to return */
        private Node<T> nextNode = head;

        /** Last node returned by next() or previous() */
        private Node<T> lastReturned = null;

        /**
         * Checks if there is another element when moving forward.
         *
         * @return true if next element exists
         */
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Returns the next element in the list.
         *
         * @return next element
         * @throws NoSuchElementException if no next element exists
         */
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            lastReturned = nextNode;
            nextNode = nextNode.next;
            return lastReturned.data;
        }

        /**
         * Checks if there is an element when moving backward.
         *
         * @return true if previous element exists
         */
        public boolean hasPrevious() {
            return (nextNode == null && tail != null)
                    || (nextNode != null && nextNode.prev != null);
        }

        /**
         * Returns the previous element in the list.
         *
         * @return previous element or null if none exists
         */
        public T previous() {
            if (!hasPrevious()) return null;

            if (nextNode == null) {
                nextNode = tail;
            } else {
                nextNode = nextNode.prev;
            }

            lastReturned = nextNode;
            return lastReturned.data;
        }

        /**
         * This removes the last element returned by next() or previous().
         *
         * @throws IllegalStateException if next() or previous() has not been called
         */
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            if (lastReturned == nextNode)
                nextNode = nextNode.next;

            unlink(lastReturned);
            lastReturned = null;
        }

        /** These are the unsupported operations */
        public int nextIndex() { throw new UnsupportedOperationException(); }

        public int previousIndex() { throw new UnsupportedOperationException(); }

        public void set(T e) { throw new UnsupportedOperationException(); }

        public void add(T e) { throw new UnsupportedOperationException(); }
    }
}