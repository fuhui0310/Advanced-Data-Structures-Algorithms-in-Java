package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * Wrapper class for a SongEntry object. We will use this to compares objects based on the songs String genre field.
 * We will use this to determine the number of songs in each genre.
 * Created by Fu on 11/3/2018.
 */
public class SongsCompGenre implements Comparable<String> {
    private ArrayList<SongEntry> songsOfGenre;
    private String genre;

    /**
     * constructor to set up SongsCompGenre
     * @param song
     * @param genre
     */
    public SongsCompGenre(SongEntry song, String genre)
    {
        this();
        addSong(song);
        setGenre(genre);
    }

    /**
     * default constructor
     */
    public SongsCompGenre()
    {
        songsOfGenre = new ArrayList<SongEntry>();
        genre = null;
    }

    /**
     * returns song
     * @return song
     */
    public ArrayList<SongEntry> getData() { return songsOfGenre; }

    /**
     * returns genre
     * @return genre
     */
    public String getName() { return genre; }

    /**
     * add song into the list
     * @param song
     * @return boolean
     */
    public boolean addSong(SongEntry song)
    {
        if (song == null)
            return false;
        songsOfGenre.add(song);
        return true;
    }

    /**
     * sets genre
     * @param genre
     * @return boolean
     */
    public boolean setGenre(String genre)
    {
        if (genre == null)
            return false;
        this.genre = genre;
        return true;
    }

    /**
     *compares two genre.
     * @param o
     * @return int
     */
    @Override
    public int compareTo(String o) {
        return this.genre.compareToIgnoreCase(o);
    }

    /**
     * check if two SongsCompGenre are equal.
     * @param rhs
     * @return boolean
     */
    public boolean equals(SongsCompGenre rhs)
    {
        return this.genre.equals(rhs.getName());
    }

    /**
     * returns hashcode based on the String key.
     * @return int
     */
    public int hashCode()
    {
        return this.genre.hashCode();
    }

    /**
     * returns all the data of song
     * @return string
     */
    public String toString()
    {
        return songsOfGenre.toString();
    }
}
