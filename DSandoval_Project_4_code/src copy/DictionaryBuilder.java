
/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 3/29/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryBuilder {

    private Node[] table;

    public DictionaryBuilder(int size) {
        table = new Node[size];
    }

    public DictionaryBuilder(String filename) throws FileNotFoundException {
        this(100);
        loadFile(filename);
    }

    private String normalize(String word) {
        if (word == null) return null;
        return word.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    private int getIndex(String word) {
        return Math.abs(word.hashCode()) % table.length;
    }

    public void addWord(String word) {
        word = normalize(word);
        if (word == null || word.isEmpty()) return;

        int index = getIndex(word);
        Node current = table[index];

        while (current != null) {
            if (current.data.word.equals(word)) {
                current.data.frequency++;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(new DictionaryEntry(word));
        newNode.next = table[index];
        table[index] = newNode;
    }

    public int getFrequency(String word) {
        word = normalize(word);
        if (word == null || word.isEmpty()) return 0;

        int index = getIndex(word);
        Node current = table[index];

        while (current != null) {
            if (current.data.word.equals(word)) {
                return current.data.frequency;
            }
            current = current.next;
        }
        return 0;
    }

    public void removeWord(String word) throws DictionaryEntryNotFoundException {
        word = normalize(word);
        int index = getIndex(word);

        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.data.word.equals(word)) {

                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }

            prev = current;
            current = current.next;
        }

        throw new DictionaryEntryNotFoundException("Word not found: " + word);
    }

    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();

        for (Node head : table) {
            Node current = head;
            while (current != null) {
                words.add(current.data.word);
                current = current.next;
            }
        }

        Collections.sort(words);
        return words;
    }

    public int getTotalWords() {
        int total = 0;

        for (Node head : table) {
            Node current = head;
            while (current != null) {
                total += current.data.frequency;
                current = current.next;
            }
        }

        return total;
    }

    public int getUniqueWords() {
        int count = 0;

        for (Node head : table) {
            Node current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
        }

        return count;
    }

    public double getLoadFactor() {
        return (double) getUniqueWords() / table.length;
    }

    private void loadFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNext()) {
            addWord(sc.next());
        }

        sc.close();
    }
}