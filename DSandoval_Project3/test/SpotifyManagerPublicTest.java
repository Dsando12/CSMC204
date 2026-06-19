
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class SpotifyManagerPublicTest {
    private SpotifyManager manager;
    private File tempFile;
    private String testData;

    @Before
    public void setUp() throws IOException {
        manager = new SpotifyManager();

        testData =
                "# USER\n" +
                        "username: demo\n" +
                        "password: DM\n" +
                        "playlist: Favorites\n" +
                        "song: Imagine - John Lennon\n" +
                        "song: Hello - Adele\n" +
                        "playlist: Workout\n" +
                        "song: Stronger - Kanye West\n" +
                        "# USER\n" +
                        "username: alice\n" +
                        "password: 1234\n" +
                        "playlist: Chill\n" +
                        "song: Yellow - Coldplay\n" +
                        "song: Someone Like You - Adele\n";

        tempFile = File.createTempFile("test_users", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(testData.replace("\n", System.lineSeparator()));
        }
    }

    @Test
    public void testLoadUsersFromFile_MatchOriginalInput()  throws Exception{
        // Load users and their playlists from the temp file
        manager.loadUsersFromFile(tempFile.getAbsolutePath());

        // Rebuild the file content from the loaded data to compare with original input
        StringBuilder reconstructed = new StringBuilder();
        for (User user : manager.getUsers()) {
            reconstructed.append("# USER\n");
            reconstructed.append("username: ").append(user.getUsername()).append("\n");
            reconstructed.append("password: ").append(user.getPassword()).append("\n");

            // Add each playlist and song to the reconstructed text
            for (Playlist p : user.getPlaylists()) {
                reconstructed.append("playlist: ").append(p.getName()).append("\n");
                for (Song s : p.getSongs()) {
                    reconstructed.append("song: ").append(s.getTitle()).append(" - ").append(s.getArtist()).append("\n");
                }
            }
        }

        // Compare reconstructed content with original test data
        assertEquals(testData.trim(), reconstructed.toString().trim());

        // Verify that specific users exist and have the correct passwords
        User demo = manager.findUser("demo","DM");
        assertNotNull("User 'demo' should be found", demo);
        assertEquals("DM", demo.getPassword());

        User alice = manager.findUser("alice","1234");
        assertNotNull("User 'alice' should be found", alice);
        assertEquals("1234", alice.getPassword());
    }


}
