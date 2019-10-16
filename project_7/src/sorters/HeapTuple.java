package sorters;

/**
 * Helper class for sorting input chunks via minHeap.
 * 
 * @author CS1C, Foothill College, Bita M.
 * @version 1.0
 */
public class HeapTuple implements Comparable<HeapTuple>
{
	/**
	 *  The number we read from a file
	 */
	private int data;

	/**
	 *  The index of the array that this data belongs to.
	 */
	private int arrayIndex;

	/**
	 *  The index of this data in its specific array. 
	 */
	private int indexInArray;

	/**
	 * Constructs a tuple to stores the value and the 
	 * array number it belongs to.
	 * @param number          the number
	 * @param arrayIndex    the index of the array
	 * @param indexInArray  the index of the data
	 */
	public HeapTuple(int number, int arrayIndex, int indexInArray)
	{
		data = number;
		this.arrayIndex = arrayIndex;
		this.indexInArray = indexInArray;
	}

	/**
	 * Accessor method returns the data.
	 * @return the data
	 */
	public int getData()
	{	return data;	}

	/**
	 * Accessor method returns which array the data is from.
	 * @return the array index
	 */
	public int getArrayIndex()
	{	return arrayIndex;	}

	/**
	 * Accessor method returns the index of this data in its specific array.
	 * @return the array index
	 */
	public int getIndexInArray()
	{	return indexInArray;	}

	/**
	 * Setter method sets the data.
	 */
	public void setData(int number)
	{	data = number;	}

	/**
	 * Setter method sets the index of this data in its array to i
	 */
	public void setIndexInArray(int i)
	{	indexInArray = i;	}



	@Override
	/**
	 *  Compares to the data of another HeapTuple.
	 * @param other  heap tuple instance
	 */
	public int compareTo(HeapTuple other) 
	{
		if (data < other.getData()) 
		{
			return -1;
		}
		else if (data > other.getData())
		{

			return 1;
		}
		return 0;
	}

	/**
	 * String representation of the tuple.
	 * NOTE: For debugging purposes.
	 *       You may change the formatting of the String representation
	 *       as you see fit.
	 */
	public String toString()
	{	return "at array #" + arrayIndex + " index="+ indexInArray + " is " + data ;	}
}
