package sorters;

import cs1c.FHsort;

/**
 * Using shell sort to sorts an individual chunk in place using the static sortChunk() method which receives an Integer[] object as argument.
 * Created by Fu on 19/3/2018.
 */
public class BasicSorter{
    public static void sortChunk(Integer[] chunk) {
        FHsort.shellSort1(chunk);
    }
}
