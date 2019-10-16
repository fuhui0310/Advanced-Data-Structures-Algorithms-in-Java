package hashTables;
import cs1c.SongEntry;

/**
 * Wrapper class for a SongEntry object. We will use this to compares objects based on the songs int id field.
 * Created by Fu on 11/3/2018.
 */
public class SongCompInt implements Comparable<Integer> {
    private SongEntry song;
    private int id;

    /**
     * constructor to set up SongCompInt
     * @param song
     * @param id
     */
    public SongCompInt(SongEntry song, int id)
    {
        this();
        setSong(song);
        setID(id);
    }

    /**
     * default constructor
     */
    public SongCompInt()
    {
        song = null;
        id = 0;
    }

    /**
     * returns song
     * @return song
     */
    public SongEntry getSong() { return song; }

    /**
     * returns ID
     * @return ID
     */
    public int getID() { return id; }

    /**
     * sets song
     * @param song
     * @return boolean
     */
    public boolean setSong( SongEntry song )
    {
        if (song == null)
            return false;
        this.song = song;
        return true;
    }

    /**
     * sets ID
     * @param id
     * @return boolean
     */
    boolean setID( int id )
    {
        if (id < 0 || id > 999999999 )
            return false;
        this.id = id;
        return true;
    }



    /**
     *compares two id.
     * @param o
     * @return int
     * returns 0 if they are equal;
     * returns 1 if id less than o;
     * returns -1 if id larger than o
     */
    @Override
    public int compareTo(Integer o) {
        if (id == o) {
            return 0;
        } else {
            if (id > o) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * check if two SongComInt objects are equal
     * @param rhs
     * @return boolean
     */
    public boolean equals(SongCompInt rhs )
    {
        return id == rhs.id;
    }

    /**
     * return hashcod.
     * @return int
     */
    public int hashCode()
    {
        return id;
    }

    /**
     * returns all the data of song
     * @return string
     */
    public String toString()
    {
        return song.toString();
    }
}
