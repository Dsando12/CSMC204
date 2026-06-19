import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter {

    private static MorseCodeTree tree = new MorseCodeTree();

    public static String convertToEnglish(String morseCode) {
        StringBuilder result = new StringBuilder();

        String[] words = morseCode.trim().split(" / ");

        for (String word : words) {
            String[] letters = word.split(" ");

            for (String letter : letters) {
                result.append(tree.fetch(letter));
            }
            result.append(" ");
        }

        return result.toString().trim();
    }

    public static String convertToEnglish(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder content = new StringBuilder();

        while (scan.hasNext()) {
            content.append(scan.next()).append(" ");
        }
        scan.close();

        return convertToEnglish(content.toString());
    }

    public static String printTree() {
        return String.join(" ", tree.toArrayList()).trim();
    }
}