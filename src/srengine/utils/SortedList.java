package srengine.utils;

/**
 * <code>SortedList</code> is list of integer keys and values which type can vary and must be
 * specified by in template. <code>SortedList</code> is sorted ascending by keys with every newly
 * added value and key pair. If no key provided, it will be set to zero. If amount of value-key pairs
 * reaches maximum capacity, size doubles.
 * 
 * @author Vojtech 'Rain' Vladyka
 * 
 * @since 1.0
 */
public class SortedList<V> {
	private Object[] list = new Object[1];
	private int[] keys = new int[1];
	private int size;

	/**
	 * C'tor from integer
	 * @param capacity initial capacity of <code>SortedList</code>
	 */
	public SortedList(int capacity) {
		size = 0;
		list = new Object[capacity];
		keys = new int[capacity];
	}

	/**
	 * Implicit c'tor
	 */
	public SortedList() {
		size = 0;
	}

	/**
	 * Copy on <code>Object</code> array to another
	 * @param arrayToCopy array that will be copied
	 * @return copy of array
	 */
	private Object[] arrayCopy(Object[] arrayToCopy) {
		Object[] ret = new Object[arrayToCopy.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = arrayToCopy[i];
		}
		return ret;
	}

	/**
	 * Copy on <code>Integer</code> array to another
	 * @param arrayToCopy array that will be copied
	 * @return copy of array
	 */
	private int[] arrayCopy(int[] arrayToCopy) {
		int[] ret = new int[arrayToCopy.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = arrayToCopy[i];
		}
		return ret;
	}

	/**
	 * Add value to <code>SortedList</code> with key=0
	 * @param obj value to add
	 */
	public void add(V obj){
		this.add(0, obj);
	}
	
	/**
	 * Add value-key pair to <code>SortedList</code>
	 * @param key key to add
	 * @param obj value to add
	 */
	public void add(int key, V obj) {
		if (size == list.length) {
			Object[] tmplist = arrayCopy(list);
			int[] tmpkeys = arrayCopy(keys);
			list = new Object[list.length * 2];
			keys = new int[keys.length * 2];
			for (int i = 0; i < tmpkeys.length; i++) {
				list[i] = tmplist[i];
				keys[i] = tmpkeys[i];
			}
		}
		list[size] = obj;
		keys[size] = key;
		size++;
		sort();
	}

	/**
	 * Comb Sort list ascending. This is called from add method. Should be faster than bubble sort.
	 */
	private void sort() {
		
	    float shrink = 1.3f;
	    int swap;
	    Object swapObj;
	    int i, gap = this.size;
	    boolean swapped = false;
	 
	    while ((gap > 1) || swapped) {
	        if (gap > 1) {
	            gap = (int)((float)gap / shrink);
	        }
	 
	        swapped = false;
	 
	        for (i = 0; gap + i < size; ++i) {
	            if (keys[i] - keys[i + gap] > 0) {
	                swap = keys[i];
	                swapObj = list[i];
	                keys[i] = keys[i + gap];
	                list[i] = list[i + gap];
	                keys[i + gap] = swap;
	                list[i + gap] = swapObj;
	                swapped = true;
	            }
	        }
	    }
	}
	
	/**
	 * Sort list ascending. This is called from add method
	 */
	/*private void sort() {
		boolean swap;
		do {
			swap = false;
			for (int i = 0; i < size - 1; i++) {
				if (keys[i] > keys[i + 1]) {
					swap = true;
					int tmpKey = keys[i];
					keys[i] = keys[i + 1];
					keys[i + 1] = tmpKey;
					Object tmpObj = list[i];
					list[i] = list[i + 1];
					list[i + 1] = tmpObj;
				}
			}
		} while (swap == true);
	}*/

	/**
	 * Shows value founded by index
	 * @param index key to find
	 * @return wanted object or null if key is invalid
	 */
	@SuppressWarnings("unchecked")
	public V get(int index) {
		if (index < size)
			return (V) list[index];
		else
			return null;
	}

	/**
	 * Shows list size
	 * @return size of list
	 */
	public int size() {
		return size;
	}

	/**
	 * Remove value-key pair by index
	 * @param index key to remove
	 * @return removed object or null if key is invalid
	 */
	public V remove(int index) {
		V obj = null;
		if (index > size)
			return obj;
		for (int i = index+1; i < size; i++) {
			keys[i-1] = keys[i];
			list[i-1] = list[i];
		}
		size--;
		return obj;
	}
	
	/**
	 * Remove value-key pair by index
	 * @param object object to remove
	 * @return removed object or null if key is invalid
	 */
	public V remove(Object object){
		for(int i=0; i<size(); i++){
			if(get(i).equals(object)){
				return remove(i);
			}
		}
		return null;
	}
	
	/**
	 * Shows value founded by index
	 * @param object obejct to find
	 * @return wanted object or null if key is invalid
	 */
	public V get(Object object){
		for(int i=0; i<size(); i++){
			if(get(i).equals(object)){
				return get(i);
			}
		}
		return null;
	}
	
	/**
	 * Change key of selected item and resort
	 * @param index old key
	 * @param newIndex new key
	 */
	public void changeKey(int index, int newIndex){
		add(newIndex, remove(index));
	}
	
	/**
	 * Change key of selected item and resort
	 * @param object searched object
	 * @param newIndex new key
	 */
	public void changeKey(Object object, int newIndex){
		add(newIndex, remove(object));
	}
}
