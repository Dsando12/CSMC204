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

/**
 * This represents a user using Spotify
 */

public class User {

    /** The username that identifies the user */
    private String username;

    /** The password that links with the user's account */
    private String password;

    /** The playlists owned made by the user */
    private GenericLinkedList<Playlist> playlists;

    /**
     * Constructs a new {@code User} with the specified username and password
     *
     * @param username the username used that links the user
     * @param password the password used for authentication to open up Spotify
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        playlists = new GenericLinkedList<>();
    }

    /**
     * Adds an option to add a playlist to the user's other playlists
     *
     * @param playlist the {@code Playlist} to add
     */
    public void addPlaylist(Playlist playlist) {
        playlists.addLast(playlist);
    }

    /**
     * Returns the number of playlists owned by the user
     *
     * @return the total number of playlists
     */
    public int getPlaylistCount() {
        return playlists.size();
    }

    /**
     * Returns the username of the user
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password associated with the user
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the list of playlists belonging to the user
     *
     * @return a {@code GenericLinkedList} containing the user's playlists
     */
    public GenericLinkedList<Playlist> getPlaylists() {
        return playlists;
    }
}