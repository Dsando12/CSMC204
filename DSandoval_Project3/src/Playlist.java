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

import java.util.ListIterator;

/**
 * This represents a playlist that stores and lists a collection of {@code Song} objects.
 */
public class Playlist {

    /** The name of the playlist */
    private String name;

    /** Linked list containing all songs in the playlist */
    private GenericLinkedList<Song> songs;

    /** Iterator used to traverse the songs in the playlist */
    private ListIterator<Song> iterator;

    /** The currently selected or playing song in the playlist */
    private Song currentSong;

    /**
     * Constructs a new {@code Playlist} with the specified name
     *
     * @param name the name assigned to this playlist
     */
    public Playlist(String name) {
        this.name = name;
        songs = new GenericLinkedList<>();
        iterator = null;
        currentSong = null;
    }

    /**
     * This adds a song to the bottom of the playlist
     *
     * @param song the {@code Song} object to be added to the playlist
     * @return {@code true} after the song is added
     */
    public boolean addSong(Song song) {
        songs.addLast(song);
        if (iterator == null) iterator = songs.iterator();
        if (currentSong == null) currentSong = songs.getFirst();
        return true;
    }

    /**
     * This removes a song from the playlist.

     * @param song the {@code Song} to remove from the playlist
     * @return {@code true} if the song was removed; {@code false} otherwise
     */
    public boolean removeSong(Song song) {
        boolean removed = songs.remove(song);
        if (removed) {
            iterator = songs.iterator();
            currentSong = songs.isEmpty() ? null : songs.getFirst();
        }
        return removed;
    }

    /**
     * This retrieves the current song in the playlist
     *
     * @return the current {@code Song}, or {@code null} if the playlist is empty
     */
    public Song getCurrentSong() {
        if (currentSong == null && !songs.isEmpty()) currentSong = songs.getFirst();
        return currentSong;
    }

    /**
     * This advances the playlist to the next song using the iterator
     *
     * @return the next {@code Song} in the playlist, or {@code null}
     * if there are no more songs
     */
    public Song nextSong() {
        if (iterator == null) iterator = songs.iterator();
        if (iterator.hasNext()) currentSong = iterator.next();
        else currentSong = null;
        return currentSong;
    }

    /**
     * Moves the playlist to the previous song using the iterator.
     *
     * @return the previous {@code Song} in the playlist, or {@code null}
     * if there are no previous songs
     */
    public Song previousSong() {
        if (iterator == null) iterator = songs.iterator();
        if (iterator.hasPrevious()) currentSong = iterator.previous();
        else currentSong = null;
        return currentSong;
    }

    /**
     * This returns the collection of songs stored in this playlist
     *
     * @return the {@code GenericLinkedList} containing all songs
     */
    public GenericLinkedList<Song> getSongs() {
        return songs;
    }

    /**
     * Retrieves the name of the playlist
     *
     * @return the playlist name
     */
    public String getName() { return name; }

    /**
     * Determines whether the playlist contains any songs
     *
     * @return {@code true} if the playlist is empty, {@code false} otherwise
     */
    public boolean isEmpty() { return songs.isEmpty(); }

    /**
     * Returns the number of songs currently stored in the playlist
     *
     * @return the total number of songs in the playlist
     */
    public int getSize() { return songs.size(); }
}