
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

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DictionaryShell {

    private DictionaryBuilder db;

    public static void main(String[] args) {
        DictionaryShell shell = new DictionaryShell();
        shell.run(args);
    }

    public void run(String[] args) {

        db = new DictionaryBuilder(100);


        if (args.length > 0) {
            loadFileSafe(args[0]);
        }

        Scanner sc = new Scanner(System.in);

        while (true) {

            if (!sc.hasNextLine()) break;

            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String cmd = parts[0].toLowerCase();

            switch (cmd) {

                case "add":
                    if (parts.length >= 2) {
                        db.addWord(parts[1]);
                    }
                    break;

                case "delete":
                    if (parts.length >= 2) {
                        try {
                            db.removeWord(parts[1]);
                        } catch (DictionaryEntryNotFoundException e) {
                            System.out.println("\"" + parts[1] + "\" not found.");
                        }
                    }
                    break;

                case "search":
                    if (parts.length >= 2) {
                        int freq = db.getFrequency(parts[1]);
                        if (freq == 0) {
                            System.out.println("\"" + parts[1] + "\" not found.");
                        } else {
                            System.out.println(freq + " instance(s) of \"" + parts[1] + "\" found.");
                        }
                    }
                    break;

                case "list":
                    List<String> words = db.getAllWords();
                    System.out.println();
                    for (String w : words) {
                        System.out.println(w);
                    }
                    break;

                case "stats":
                    System.out.println("Total words: " + db.getTotalWords());
                    System.out.println("Unique words: " + db.getUniqueWords());
                    System.out.printf("Estimated load factor: %.2f%n", db.getLoadFactor());
                    break;

                case "exit":
                    sc.close();
                    return;

                default:
                    break;
            }
        }

        sc.close();
    }

    private void loadFileSafe(String filename) {
        try {
            DictionaryBuilder temp = new DictionaryBuilder(filename);

            for (String word : temp.getAllWords()) {
                int freq = temp.getFrequency(word);

                for (int i = 0; i < freq; i++) {
                    db.addWord(word);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}