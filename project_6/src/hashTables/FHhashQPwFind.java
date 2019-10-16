package hashTables;

import java.util.NoSuchElementException;

/**
 * Implement the class FHhashQPwFind to extend FHhashQP.
 * Take a second parameter KeyType to find the item in the hash map.
 * Created by Fu on 11/3/2018.
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType> > extends FHhashQP<E>{

    /**
     * extends the constructor of parent class FHhashQPwFind
     * @param size
     */
    public FHhashQPwFind(int size){
        super(size);
    }

    /**
     * returns the found object, or throws a java.util.NoSuchElementException
     * @param x
     * @return the found object, or throws a java.util.NoSuchElementException
     */
    public E find(KeyType x){
     int index = findPosKey(x) ;
     return mArray[index].data;
    }

    /**
     * a protected method, which provides a trivial modification to myHash() which uses the key rather than the object, to hash.
     * @param x
     * @return gets the hash value
     */
    protected int myHashKey(KeyType x){
        int hashVal;
        hashVal = x.hashCode() % mTableSize;
        if(hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     * a protected method, which provides trivial modification to findPos() which uses the key rather than the object, to get a position.
     * @param x
     * @return finds the position
     */
    protected int findPosKey( KeyType x) {
        int kthOddNum = 1;
        int index = myHashKey(x);
        if (mArray[index].data == null) {
            throw new NoSuchElementException();
        }
        while ( mArray[index].data.compareTo(x) != 0) {
            if (mArray[index].data == null) {
                throw new NoSuchElementException();
            }
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }
}
