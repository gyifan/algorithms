/**
 * An implementation of a minimum heap with handles
 */

public class Heap {

    private HeapElt[] array;
    int heapsize;
    int arraysize;

    /*
      The constructor has been set up with an initial array of size 4
      so that your doubleHeap() method will be tested.  Don't change
      this!
    */
    public Heap() {
    	array = new HeapElt[4];
    	heapsize = 0;
    	arraysize = 4;
    }



    /*
      Exchanges that values at positions pos1 and pos2 in the heap array.
      Handles must be exchanged correctly as well.

      Running time = O(1)
    */
    private void exchange(int pos1, int pos2) {
    	HeapElt temp = array[pos1];
    	int tempHandle = array[pos2].getHandle();
    	array[pos1] = array[pos2];
    	array[pos1].setHandle(temp.getHandle());
    	array[pos2] = temp;
    	array[pos2].setHandle(tempHandle);
    }



    /*
      Doubles the size of the array.  A new array is created, the elements in
      the heap are copied to the new array, and the array data member is set
      to the new array.  Data member arraysize is set to the size of the
      new array.

      Running time = O(n)
    */
    private void doubleHeap() {
    	HeapElt[] newArray = new HeapElt[arraysize*2];
    	for (int i = 1; i <= heapsize; i++){
    		newArray[i] = array[i];
    	}
    	array = newArray;
    	arraysize *= 2;
    }



    /*
      Fixes the heap if the value at position pos may be smaller than its
      parent.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(lg(n))
    */
    public void heapifyUp(int pos) {
    	while (pos > 1){
    		if (array[pos].getRecord().compareTo(array[pos/2].getRecord()) < 0){
    			exchange(pos, pos/2);
    			pos = pos/2;
    		}
    		else
    			break;
    	}

    }

    /*
      Fixes the heap if the value at position pos may be bigger than one of
      its children.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(lgn)
    */
    public void heapifyDown(int pos) {
    	int l = pos * 2;
    	int r = pos * 2 + 1;
    	int smallest = pos;
    	if (l <= heapsize && array[l].getRecord().compareTo(array[pos].getRecord()) < 0)
    		smallest = l;
    	
    	if (r <= heapsize && array[r].getRecord().compareTo(array[smallest].getRecord())<0)
    		smallest = r;
    	if (smallest != pos){
    		exchange(smallest, pos);
    		heapifyDown(smallest);
    	}
    }



    /*
      Insert inElt into the heap.  Before doing so, make sure that there is
      an open spot in the array for doing so.  If you need more space, call
      doubleHeap() before doing the insertion.

      Running time = O(1) when heap is empty or the inserted value is larger 
      than its parent. Else running time = O(lg(n)). 
    */
    public void insert(HeapElt inElt) {
    	if (heapsize == 0){
    		array[1] = inElt;
    		array[1].setHandle(1);
    		heapsize = 1;
    	} else if (heapsize >= (arraysize-1)){
    		doubleHeap();
    		inElt.setHandle(heapsize+1);
    		array[heapsize+1] = inElt;
    		heapsize += 1;
    		heapifyUp(heapsize);
    	} else {
    		inElt.setHandle(heapsize+1);
    		array[heapsize+1] = inElt;
    		heapsize += 1;
    		heapifyUp(heapsize);
    	}
    }



    /*
      Remove the minimum element from the heap and return it.  Restore
      the heap to heap order.  Assumes heap is not empty, and will
      cause an exception if the heap is empty.

      Running time = O(lg(n))
    */
    public HeapElt removeMin() {
	// WARNING: Will fail with empty heap!
    	HeapElt temp = array[1];
    	exchange(1, heapsize);
    	array[heapsize] = null;
    	heapsize -= 1;
    	heapifyDown(1);
    	return temp;
    }



    /*
      Return the number of elements in the heap..

      Running time = O(1)
    */
    public int getHeapsize() {

    	return heapsize;

    }

 

   /*
      Print out the heap for debugging purposes.  It is recommended to 
      print both the key from the record and the handle.

      Running time = O(nlgn)
    */
    public void printHeap() {
    	for (int i = 1; i <= heapsize; i=2*i){
    		for (int current = i; current < 2*i && current <= heapsize; current++){
    			System.out.print("V" + array[current].getRecord() + "H" + array[current].getHandle() + "  ");
    		}
    		System.out.println();
    	}
     }

}