
import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;


public class PlaylistPublicTest {

    private Playlist playlist;

    @Before
    public void setUp() {
        playlist = new Playlist("Test Playlist");
        playlist.addSong(new Song("Song A", "Artist 1"));
        playlist.addSong(new Song("Song B", "Artist 2"));
    }


    @Test
    public void testGetCurrentSong() {
        Song current = playlist.getCurrentSong();
        assertEquals("Song A", current.getTitle());
    }

    @Test
    public void testNextSong() {
        assertEquals("Song A",  playlist.nextSong().getTitle());
        assertEquals("Song B",  playlist.nextSong().getTitle());
    }

    @Test
    public void testPreviousSong() {
        assertEquals(null, playlist.previousSong());
    }



}
