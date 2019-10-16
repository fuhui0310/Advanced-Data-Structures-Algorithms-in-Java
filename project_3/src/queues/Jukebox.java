package queues;

import cs1c.SongEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * the class Jukebox to manage three objects of type Queue<String>.
 * The constructor reads the file with user's request for what song to add to which playlist.
 * Created by Fu on 4/2/2018.
 */
public class Jukebox {
    private Queue<SongEntry> favoritePL;
    private Queue<SongEntry> loungePL;
    private Queue<SongEntry> roadTripPL;

    /**
     * default constructor
     */
    public Jukebox(){
        favoritePL = new Queue<SongEntry>();
        loungePL = new Queue<SongEntry>();
        roadTripPL = new Queue<SongEntry>();
        favoritePL.setName("favorites");
        loungePL.setName("lounge");
        roadTripPL.setName("road trip");
    }

    /**
     * reads the test file and then adds songs to one of the three queues.
     * @param requestFile the path of the file.
     * @param allSongs the list of all songs.
     */
    public void fillPlaylists(String requestFile, SongEntry[] allSongs) {
        try {
            Scanner input = new Scanner(new File(requestFile));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] linesOfText = line.split(",");
                String name = linesOfText[0];
                switch(name)
                {
                    case "favorites":
                        for(SongEntry current: allSongs){
                            if (linesOfText[1].equals(current.getTitle())) {
                                favoritePL.enqueue(current);
                                break;
                            }
                        }
                        break;
                    case "lounge":
                        for(SongEntry current: allSongs){
                            if (linesOfText[1].equals(current.getTitle())) {
                                loungePL.enqueue(current);
                                break;
                            }
                        }
                        break;
                    case "road trip":
                        for(SongEntry current: allSongs){
                            if (linesOfText[1].equals(current.getTitle())) {
                                roadTripPL.enqueue(current);
                                break;
                            }
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + requestFile + " not found!");
        }
    }

    /**
     * Accessor methods
     * @return the favorite list
     */
    public Queue<SongEntry> getFavoritePL() {
        return favoritePL;
    }

    /**
     * Accessor methods
     * @return the lounge list
     */
    public Queue<SongEntry> getLoungePL() {
        return loungePL;
    }

    /**
     * Accessor methods
     * @return the road trip list
     */
    public Queue<SongEntry> getRoadTripPL() {
        return roadTripPL;
    }
}
