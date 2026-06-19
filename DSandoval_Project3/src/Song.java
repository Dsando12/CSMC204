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
 * Represents a song with a title and an artist
 */
public class Song {

    /** The title of the song */
    private String title;

    /** The artist who performs the song */
    private String artist;

    /**
     * Constructs a new {@code Song} with the specified title and artist
     *
     * @param title the title of the song
     * @param artist the artist who sings the song
     */
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    /**
     * Returns the title of the song
     *
     * @return the song title
     */
    public String getTitle() { return title; }

    /**
     * Returns the artist of the song
     *
     * @return the song artist
     */
    public String getArtist() { return artist; }

    /**
     * Compares this song with another object for equality
     *
     * @param o the object to compare with
     * @return {@code true} if the given object represents the same song,{@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song other = (Song) o;
        return title.equals(other.title) && artist.equals(other.artist);
    }

    /**
     * Returns a string representation of the song
     *
     * @return a formatted string containing the song title and artist
     */
    @Override
    public String toString() {
        return title + " - " + artist;
    }
}