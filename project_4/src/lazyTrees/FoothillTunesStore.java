package lazyTrees;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Simulates the store scenario of removing and adding tune to a store's inventory.
 * Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted)
 * and current inventory (non deleted only).
 * Created by Fu on 17/2/2018.
 */
public class FoothillTunesStore {
    public static final boolean SHOW_DETAILS = true;

    // TODO: Define the functor class PrintObject to traverse and print out data
    //       from LazySearchTree.
    PrintObject<tunes> printObject = new PrintObject<tunes>();

    // The data structure, which we use to add and remove items.
    private LazySearchTree<tunes> inventory;

    /**
     * Instantiates inventory to be a LazySearchTree of Item objects.
     */
    public FoothillTunesStore()
    {
        inventory = new LazySearchTree<tunes>();
    }

    /**
     * Add a new item with the name as in parameter into inventory. If there is
     * already same name product, increase amount by one, if not create a new object.
     * @param song		The item to be added to the inventory tree.
     */
    public void addToInventory(SongEntry song)
    {
        // Create a temporary object to hold the item.
        tunes tmp = new tunes(song);

        // Check if the item is in the inventory tree.
        boolean isFound = inventory.contains(tmp);

        // If the item is not found, add the temporary object as another node (category) to the tree.
        if (!isFound)
        {
            // TODO: Modify insert method to work with lazy deletion such that it updates
            //       both hard and soft sizes.
            inventory.insert(tmp);

            // NOTE: Need to check if the item was lazily deleted, then we need to increment the count
            tunes found = inventory.find(tmp);
            if (found.getCount() == 0)
            {
                found.incrementCount();
            }
            return;
        }

        // If the item is found, increase the number of items in that item category.
        tunes found = inventory.find(tmp);

        // item was previously in tree, so increment the count
        found.incrementCount();
    }

    /**
     * If the item is in the inventory, decrease the count by one.
     * If only one item is left, remove it from the inventory.
     * @param song		The item to be removed to the inventory tree.
     */
    public void removeFromInventory(SongEntry song)
    {
        tunes tmp = new tunes(song);
        boolean isFound = inventory.contains(tmp);

        // check if the item exists in the inventory disregarding lazy deletion
        if (!isFound)
        {
            throw new NoSuchElementException();
        }

        tunes found = inventory.find(tmp);

        // if the items has zero left in stock,
        // then treat it as if it does not exist in the tree.
        if (found.getCount() == 0)
        {
            throw new NoSuchElementException();
        }
        // if the item has one left in stock,
        // then decrement its count and lazy delete it in the tree.
        else if (found.getCount() == 1)
        {
            found.decrementCount();

            // TODO: Modify to be a lazy deletion remove method, which marks
            //       found nodes as "deleted".
            inventory.remove(tmp);
        }
        // otherwise, simply decrement its count.
        else
        {
            found.decrementCount();
        }
    }


    /**
     * Display the first item and last time of the soft tree in lexical order.
     */
    public void showFirstAndLastItem(String message)
    {
        System.out.println("\n" + message);

        // TODO: Modify the protected methods findMin() and findMax() to implement lazy deletion.
        //       Searches from the root of the tree and return sthe minimum and maximum node that
        //       has NOT been "deleted".
        try
        {
            tunes min = inventory.findMin();
            System.out.println ( "First item: " + min.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

        try
        {
            tunes max = inventory.findMax();
            System.out.println ( "Last item: " + max.toString());
        }
        catch (Exception NoSuchElementException)
        {
            System.out.println("Warning: minimum element not found!");
        }

    }

    /**
     * Shows the state of the tree by displaying:
     * - the *hard* inventory, which includes deleted nodes.
     * - the *soft* inventory, which excludes deleted nodes.
     * @param message	Additional details about the state.
     * @param showTree	Set to true if we want to display the contents of the tree
     */
    protected void displayInventoryState(String message, boolean showTree)
    {
        System.out.println("\n" + message);
        System.out.println("\"hard\" number of unique items (i.e. mSizeHard) = " + inventory.sizeHard());
        System.out.println("\"soft\" number of unique items (i.e. mSize) = " + inventory.size());

        if (!showTree)
            return;

        System.out.println( "\nTesting traversing \"hard\" inventory:");

        // TODO: First, rename the public/private pair traverse() method of FHsearch_tree to traverseHard() method.
        //       Then, reuse this public/private pair of methods to traverses the tree
        //       and displays all the nodes.
        // NOTE: Here, we call the public version.
        inventory.traverseHard(printObject);


        System.out.println( "\n\nTesting traversing \"soft\" inventory:");

        // TODO: Define a public/private pair of methods that traverses the tree
        //       and displays only nodes that have not been lazily deleted.
        // NOTE: Here, we call the public version.
        inventory.traverseSoft(printObject);
        System.out.println("\n");
    }

    public static void main(String[] args)
    {

        final String DATAFILE = "resources/music_genre_subset.json";

        final String TESTFILE = "resources/tunes_additional.txt";

        MillionSongDataSubset msd = new MillionSongDataSubset(DATAFILE);

        SongEntry [] allSongs = msd.getArrayOfSongs();

        System.out.printf("Test file: %s \n", TESTFILE);

        FoothillTunesStore store = new FoothillTunesStore();

        File infile = new File(TESTFILE);

        try
        {
            Scanner input = new Scanner(infile);

            String line = "";
            int lineNum = 0;
            while (input.hasNextLine())
            {
                lineNum++;
                line = input.nextLine();
                String [] tokens = line.split(",");
                String selection = tokens[0];
                String songName = tokens[1];

                String message = "at line #" + lineNum + ": " + line;

                if (selection.equals("add"))
                {
                    for(SongEntry current: allSongs){
                        if (songName.equals(current.getTitle())) {
                            store.addToInventory(current);
                            //System.out.print(current);
                            break;
                        }
                    }

                    if (SHOW_DETAILS)
                        store.displayInventoryState("\nUpdate " + message, true);
                }

                else if (selection.equals("remove"))
                {
                    try
                    {
                        for(SongEntry current: allSongs) {
                            if (songName.equals(current.getTitle())) {
                                store.removeFromInventory(current);
                                break;
                            }
                        }

                        if (SHOW_DETAILS)
                            store.displayInventoryState("\nUpdate " + message, true);
                    }
                    catch (java.util.NoSuchElementException ex)
                    {
                        System.out.printf("\nWarning: Unable to fulfill request: %s \n", message);
                        System.out.printf("Warning: Song %s is out of stock.\n", songName);
                    }
                }
                else
                {
                    System.out.println("Warning: Inventory selection not recognized!");
                }

                if (SHOW_DETAILS)
                    store.showFirstAndLastItem(message);
            }
            input.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        // Display the inventory
        System.out.println("\n");
        store.displayInventoryState("\nFinal state of inventory:", true);

        // flush the error stream
        System.err.flush();

        System.out.println("\nDone with FoothillTunesStore.");
    }
}
