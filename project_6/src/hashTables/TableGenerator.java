package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *  class will create and populate two hash tables of type FHhashQPwFind class, one for each wrapper class
 * Created by Fu on 11/3/2018.
 */
public class TableGenerator {
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;
    private FHhashQPwFind<String,SongsCompGenre> tableOfSongGenres;
    private ArrayList<String> genreNames;

    /**
     * default constructor
     */
    public TableGenerator(){
        tableOfSongGenres = null;
        tableOfSongGenres = null;
        genreNames = null;
    }
    /**
     * populates the tableOfIDs hash table
     * @param allSongs
     * @return tableOfSongIDs
     */
    public FHhashQPwFind<Integer,SongCompInt> populateIDtable(SongEntry[] allSongs) {
        int size = allSongs.length;
        tableOfSongIDs = new FHhashQPwFind(size);
        for(int i = 0; i< size; i++) {
                //System.out.print("test");
                SongCompInt current = new SongCompInt(allSongs[i], i);
                tableOfSongIDs.insert(current);
            }
        return tableOfSongIDs;
    }

    /**
     * populates the tableOfGenres hash table and ArrayList of genre names
     * @param allSongs
     * @return tableOfSongGenres
     */
    public FHhashQPwFind<String,SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {
        int size = allSongs.length;
        tableOfSongGenres = new FHhashQPwFind(size);
        genreNames = new ArrayList<String>();
        for (int i = 0; i< size; i++){
            String genre = allSongs[i].getGenre();
            try{
                //System.out.print("test");
                SongsCompGenre current = tableOfSongGenres.find(genre);
                current.addSong(allSongs[i]);
            }catch (NoSuchElementException e){
                //System.out.print("test");
                SongsCompGenre current = new SongsCompGenre(allSongs[i], allSongs[i].getGenre());
                tableOfSongGenres.insert(current);
                genreNames.add(current.getName());
            }
        }
        return tableOfSongGenres;
    }

    /**
     * returns ArrayList of genre name
     * @return ArrayList of genre name
     */
    public ArrayList<String> getGenreNames() {
        return genreNames;
    }
}
