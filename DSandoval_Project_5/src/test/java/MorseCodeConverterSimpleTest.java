import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class MorseCodeConverterSimpleTest {

    // ✅ Test simple string conversion
    @Test
    public void testConvertSimpleString() {
        String result = MorseCodeConverter.convertToEnglish(
                ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
        );
        assertEquals("hello world", result);
    }

    // ✅ Test another string (covers more letters)
    @Test
    public void testConvertAnotherString() {
        String result = MorseCodeConverter.convertToEnglish(
                "- .... . / --.- ..- .. -.-. -.-"
        );
        assertEquals("the quick", result);
    }

    // ✅ Test file conversion
    @Test
    public void testConvertFile() {
        try {
            File file = new File("howDoILoveThee.txt"); // make sure file is in project root
            String result = MorseCodeConverter.convertToEnglish(file);

            assertEquals("how do i love thee let me count the ways", result);

        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    // ✅ Test printTree (important for grading)
    @Test
    public void testPrintTree() {
        String expected = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
        assertEquals(expected, MorseCodeConverter.printTree());
    }
}