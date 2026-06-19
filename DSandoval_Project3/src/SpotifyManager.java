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

import java.io.*;
import java.util.ListIterator;

/**
 * Manages the Spotify user accounts and their associated playlists
 */
public class SpotifyManager {

    /** Linked list containing all registered users. */
    private GenericLinkedList<User> users;

    /**
     * Constructs a new {@code SpotifyManager} with an empty user list
     */
    public SpotifyManager() {
        users = new GenericLinkedList<>();
    }

    /**
     * Loads users, playlists, and songs from a structured text file
     *
     * @param filename the name of the file that contains user data
     * @throws IOException if there is an error reading the file
     * @throws InvalidUserFormatException if the file contains invalid
     *        or uncorrected formatted user, playlist, or song data
     */
    public void loadUsersFromFile(String filename) throws IOException, InvalidUserFormatException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        User currentUser = null;
        Playlist currentPlaylist = null;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("# USER")) {

                currentUser = null;
                currentPlaylist = null;

            } else if (line.startsWith("username:")) {
                String username = line.substring(9).trim();
                if (username.isEmpty()) throw new InvalidUserFormatException("Missing username");

                currentUser = new User(username, "");
                users.addLast(currentUser);

            } else if (line.startsWith("password:")) {
                if (currentUser == null) throw new InvalidUserFormatException("Password without user");

                String password = line.substring(9).trim();


                currentUser = new User(currentUser.getUsername(), password);
                users.removeLast();
                users.addLast(currentUser);

            } else if (line.startsWith("playlist:")) {
                if (currentUser == null) throw new InvalidUserFormatException("Playlist without user");

                String playlistName = line.substring(9).trim();
                currentPlaylist = new Playlist(playlistName);
                currentUser.addPlaylist(currentPlaylist);

            } else if (line.startsWith("song:")) {
                if (currentPlaylist == null) throw new InvalidUserFormatException("Song without playlist");

                String songData = line.substring(5).trim();
                int dashIndex = songData.indexOf("-");

                if (dashIndex < 0)
                    throw new InvalidUserFormatException("Invalid song format: " + songData);

                String title = songData.substring(0, dashIndex).trim();
                String artist = songData.substring(dashIndex + 1).trim();

                currentPlaylist.addSong(new Song(title, artist));
            }
        }

        br.close();
    }

    /**
     * Searches for a user with the specified username and password
     *
     * @param username the username to search for
     * @param password the password used to authenticate the user
     * @return the matching {@code User} if authentication succeeds
     * @throws UserNotFoundException if no user with the specified username exists
     * @throws InvalidPasswordException if the password does not match the stored password
     */
    public User findUser(String username, String password)
            throws UserNotFoundException, InvalidPasswordException {

        ListIterator<User> it = users.iterator();

        while (it.hasNext()) {
            User u = it.next();

            if (u.getUsername().equals(username)) {
                if (!u.getPassword().equals(password)) {
                    throw new InvalidPasswordException(
                            "Incorrect password for user: " + username);
                }
                return u;
            }
        }

        throw new UserNotFoundException("User not found: " + username);
    }

    /**
     * Returns the list of all users currently stored in the manager
     * @return a {@code GenericLinkedList} containing all users
     */
    public GenericLinkedList<User> getUsers() {
        return users;
    }
}