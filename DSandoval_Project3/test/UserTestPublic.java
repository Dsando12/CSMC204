
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTestPublic{
    private User user1;
    private Playlist playlist1,playlist2;

    @Before
    public void setUp() {
        user1 = new User("student", "1234");

        playlist1 = new Playlist("Chill");
        playlist2 = new Playlist("Pop");
    }

    @Test
    public void testAddPlaylistAndGetCount() {
        user1.addPlaylist(playlist1);
        assertEquals(1, user1.getPlaylistCount());
        user1.addPlaylist(playlist2);
        assertEquals(2, user1.getPlaylistCount());
    }


}
