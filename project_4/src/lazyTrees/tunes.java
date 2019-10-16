package lazyTrees;

import cs1c.SongEntry;

/**
  * One object of tune class represents one tune in the inventory, with two class members.
 * Created by Fu on 17/2/2018.
 */
public class tunes implements Comparable<tunes> {
    // the name of the item
    private SongEntry song;

    // the count of this item
    private int count;

    /**
     * Constructor takes name for item. Instantiates count to 1.
     * @param song	name of the tune to add created.
     */
    public tunes(SongEntry song)
    {
        this.song = song;
        this.count = 1;
    }

    /**
     * Increase the count by 1 each call.
     */
    public void incrementCount()
    {	count++;	};

    /**
     * Reduce the count by 1 each call and return false when count is less than 1.
     * @return Whether the count of the tune was successfully decreased by 1.
     */
    public boolean decrementCount()
    {
        if (count < 1)
            return false;

        count--;
        return true;
    };

    /**
     * Get current number of tunes
     * @return	int value of current number of tunes.
     */
    public int getCount()
    {	return count;	}

    /**
     * Use tune name for comparing.
     */
    @Override
    public int compareTo(tunes other)
    {
        return song.getTitle().compareToIgnoreCase(other.song.getTitle());
    };

    /**
     * Returns a string representation with the item name and count.
     */
    public String toString()
    {
        return song + ":" + count + " ";
    }
}
