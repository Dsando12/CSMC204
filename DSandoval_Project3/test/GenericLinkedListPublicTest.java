

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;

public class GenericLinkedListPublicTest {

    private GenericLinkedList<String> list;

    @Before
    public void setUp() {
        list = new GenericLinkedList<>();
    }

    @Test
    public void testAddFirst() {
        list.addFirst("Banana");
        list.addFirst("Apple"); // should now be at index 0
        assertEquals("Apple", list.get(0)); // confirms order
    }

    @Test
    public void testAddLast() {
        list.addLast("Apple");
        list.addLast("Banana"); // should now be at the end (index 1)
        assertEquals("Banana", list.get(1)); // confirms position at end
    }

    @Test
    public void testClear() {
        list.addFirst("Date");
        list.clear();
        assertFalse(list.contains("Date"));
    }

    @Test
    public void testContains() {
        list.addLast("Elderberry");
        assertTrue(list.contains("Elderberry"));
        assertFalse(list.contains("Fig"));
    }

    @Test
    public void testIteratorHasNext() {
        list.addLast("Grape");
        ListIterator<String> it = list.iterator();
        assertTrue(it.hasNext());
    }

    @Test
    public void testIteratorNext() {
        list.addLast("Honeydew");
        list.addLast("Melon");
        ListIterator<String> it = list.iterator();
        assertEquals("Honeydew", it.next());
        assertEquals("Melon", it.next());
    }
}
