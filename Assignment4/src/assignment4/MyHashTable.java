package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
	// num of entries to the table
	private int numEntries;
	// num of buckets 
	private int numBuckets;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<HashPair<K,V>>> buckets; 

	// constructor
	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS

		numBuckets = initialCapacity;
		numEntries = 0;
		buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);

		for (int i = 0; i<numBuckets; i++) {
			buckets.add(new LinkedList<HashPair<K,V>>());
		}
		//ADD YOUR CODE ABOVE THIS
	}                                                                                                                                               

	public int size() {
		return this.numEntries;
	}

	public int numBuckets() {
		return this.numBuckets;
	}

	/**
	 * Returns the buckets vairable. Usefull for testing  purposes.
	 */
	public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
		return this.buckets;
	}
	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.numBuckets;
		return hashValue;
	}
	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */

	public V put(K key, V value) { 
		//  ADD YOUR CODE BELOW HERE
		//System.out.println(hashFunction(key));
		if (((double)numEntries+1)/(double)numBuckets >= MAX_LOAD_FACTOR) {
			rehash();
		}
		
		int bucketIndex = hashFunction(key); 

		if (!buckets.get(bucketIndex).isEmpty()) {

			LinkedList<HashPair<K, V>> list = buckets.get(bucketIndex); 

			// Check if key is already present 

			for (HashPair<K,V> pair : list) {
				if (pair.getKey().equals(key))	{

					V oldval = pair.getValue();
					pair.setValue(value); 
					return oldval; 
				} 
			}

			

		}


		// Insert key in list
		numEntries ++;
		HashPair<K, V> newPair = new HashPair<K, V>(key, value);
		buckets.get(bucketIndex).add(newPair); 



		return null;


		//  ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Get the value corresponding to key. Expected average runtime = O(1)
	 */

	public V get(K key) {
		//ADD YOUR CODE BELOW HERE

		int bucketIndex = hashFunction(key); 

		if (!buckets.get(bucketIndex).isEmpty()) {

			LinkedList<HashPair<K, V>> list = buckets.get(bucketIndex); 

			for (HashPair<K,V> pair : list) {
				if (pair.getKey().equals(key)) 
				{  
					return pair.getValue(); 
				} 
			}
		}

		return null;
		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		//ADD YOUR CODE BELOW HERE

		int bucketIndex = hashFunction(key); 

		LinkedList<HashPair<K, V>> list = buckets.get(bucketIndex); 

		// Check if key is already present 

		for (HashPair<K,V> pair : list) {
			if (pair.getKey().equals(key)) 
			{ 
				V oldval = pair.getValue(); 
				list.remove(pair);
				numEntries --;
				return oldval; 
			} 
		}


		return null;//remove
		//ADD YOUR CODE ABOVE HERE
	}

	// Method to double the size of the hashtable if load factor increases
	// beyond MAX_LOAD_FACTOR.
	// Made public for ease of testing.

	public void rehash() {
		//ADD YOUR CODE BELOW HERE

		ArrayList<LinkedList<HashPair<K, V>>> temp = buckets;
		numEntries = 0;
		numBuckets = 2 * numBuckets;
		buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);
		for (int i = 0; i<numBuckets; i++) {
			buckets.add(new LinkedList<HashPair<K,V>>());
		}

		for (LinkedList<HashPair<K,V>> pairList : temp)  
		{ 
			if(!pairList.isEmpty()) {

				for(HashPair<K,V> pair : pairList) {

					int bucketIndex = hashFunction(pair.getKey());
					buckets.get(bucketIndex).add(pair);
					numEntries ++;

				}

			}

		}

		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 */

	public ArrayList<K> keys() {
		//ADD YOUR CODE BELOW HERE

		ArrayList<K> keyArray = new ArrayList<K>();

		for(LinkedList<HashPair<K,V>> pairList : buckets) {
			for(HashPair<K,V> pair : pairList) {
				K key = pair.getKey();
				keyArray.add(key);
			}
		}

		return keyArray;
		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(n)
	 */
	public ArrayList<V> values() { 
		//ADD CODE BELOW HERE
		
		MyHashTable<V, V> valMap = new MyHashTable<V,V>(numBuckets);

		int lim = numBuckets;
		
		for(int i=0; i<lim; i++) {
			for(HashPair pair : this.getBuckets().get(i)) {
				valMap.put((V)pair.getValue(), (V)pair.getValue());
			} 
		}
	
		return valMap.keys();
		//ADD CODE ABOVE HERE
	}


	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<HashPair<K,V>> {
		private LinkedList<HashPair<K,V>> entries;
		
		//HashPair<K,V> current = entries;

		private MyHashIterator() { 
			//ADD YOUR CODE BELOW HERE

			entries = new LinkedList<HashPair<K,V>>();
			
			for(LinkedList<HashPair<K,V>> list : buckets) {

				if(!list.isEmpty()) {

					for(HashPair<K,V> pair : list) {
						
							entries.addLast(pair);
						}

					}

				}

			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public boolean hasNext() {
			//ADD YOUR CODE BELOW HERE

			return entries.size() != 0;

			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public HashPair<K,V> next() { //tengo que lanzar excepcion? *********
			//ADD YOUR CODE BELOW HERE

			return entries.removeFirst();

			//ADD YOUR CODE ABOVE HERE
		}

	}
}
