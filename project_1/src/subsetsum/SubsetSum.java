package subsetsum;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * a class SubsetSum to find the closest subset sum to the target number
 * Created by Fu on 1/16/2018.
 */
public class SubsetSum {
    /**
     * Return the subset  which exactly match or closest to the target number.
     *
     * @param shoppingList the list of the price of the items.
     * @param budget       the budget which is required by the user.
     * @return the closest subset sum to the target number.
     */
    public static ArrayList<Double> findSubset(ArrayList<Double> shoppingList, double budget) {
        ArrayList<Double> targetSubset = new ArrayList<Double>();
        ArrayList<ArrayList<Double>> subsetList = new ArrayList<ArrayList<Double>>();
        double totalPrice = 0;
        double difference = budget;
        for (Double priceOfItem : shoppingList) {
            totalPrice += priceOfItem;
        }
        if (budget >= totalPrice) {
            targetSubset = shoppingList;
            return targetSubset;
        } else {
            for (int i = 0; i < shoppingList.size(); i++) {
                ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
                for (ArrayList<Double> subset : subsetList) {
                    temp.add(new ArrayList<Double>(subset));
                }
                for (ArrayList<Double> subset : temp) {
                    subset.add(shoppingList.get(i));
                }
                ArrayList<Double> single = new ArrayList<Double>();
                single.add(shoppingList.get(i));
                temp.add(single);

                subsetList.addAll(temp);
                for (ArrayList<Double> current : subsetList) {
                    double currentSum = 0;
                    for (double priceOfItem : current) {
                        currentSum += priceOfItem;
                    }
                    if ((budget - currentSum) < difference && (budget - currentSum) > 0) {
                        difference = (budget - currentSum);
                        targetSubset = current;
                    }
                    if ((budget - currentSum) == 0) {
                        targetSubset = current;
                        return targetSubset;
                    }
                }
            }
            return targetSubset;
        }
    }

    /**
     * Return the subset  which exactly match or closest to the target number.
     *
     * @param songList the list of song.
     * @param duration the duration which the user requires.
     * @return the closest subset sum to the target number.
     */
    public static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration) {
        ArrayList<SongEntry> targetSubset = new ArrayList<SongEntry>();
        ArrayList<ArrayList<SongEntry>> subsetList = new ArrayList<ArrayList<SongEntry>>();
        double durationSec = duration * 60;
        double difference = durationSec;
        int totalDuration = 0;
        for (SongEntry current : songList) {
            totalDuration += current.getDuration();
        }
        if (durationSec >= totalDuration) {
            targetSubset = songList;
            return targetSubset;
        } else {
            for (int i = 0; i < songList.size(); i++) {
                ArrayList<ArrayList<SongEntry>> temp = new ArrayList<ArrayList<SongEntry>>();
                for (ArrayList<SongEntry> subset : subsetList) {
                    temp.add(new ArrayList<SongEntry>(subset));
                }
                for (ArrayList<SongEntry> subset : temp) {
                    subset.add(songList.get(i));
                }
                ArrayList<SongEntry> single = new ArrayList<SongEntry>();
                single.add(songList.get(i));
                temp.add(single);
                int walker = 0;
                ArrayList<ArrayList<SongEntry>> list = new ArrayList<ArrayList<SongEntry>>();
                list.addAll(temp);
                for (ArrayList<SongEntry> current : list) {
                    double currentSum = 0;
                    for (SongEntry song : current) {
                        currentSum += song.getDuration();
                    }
                    if ((durationSec - currentSum) < difference && (durationSec - currentSum) > 0) {
                        difference = (durationSec - currentSum);
                        targetSubset = current;
                    }
                    if ((durationSec - currentSum) == 0) {
                        targetSubset = current;
                        return targetSubset;
                    }
                    if ((durationSec - currentSum) >= difference) {
                        temp.remove(walker);
                    }
                }
                subsetList.addAll(list);
                walker++;
            }
            return targetSubset;
        }
    }
}
