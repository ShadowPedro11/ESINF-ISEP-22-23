/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Priority_queue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * 
 * 
 */
public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
  /** primary collection of priority queue entries */
  protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

  /** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   * @param keys an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values) {
    int kLength = keys.length;
    int vLength = values.length;

    if(kLength > vLength ){
      kLength = vLength;
    }

    for(int i = 0; i < kLength; i++){
      heap.add(new PQEntry<>(keys[i],values[i]));
    }
    buildHeap();
  }

  // protected utilities
  protected int parent(int j) { return (j-1) / 2; }     // truncating division
  protected int left(int j) { return 2*j + 1; }
  protected int right(int j) { return 2*j + 2; }
  protected boolean hasLeft(int j) { return left(j) < heap.size(); }
  protected boolean hasRight(int j) { return right(j) < heap.size(); }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j) {
    Entry<K,V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void percolateUp(int j) {
    int parentIndex = parent(j);

    while(parentIndex >= 0 && (compare(heap.get(j),heap.get(parentIndex)) < 0 )){
      swap(j,parentIndex);
      j = parentIndex;
      parentIndex = parent(j);
    }
  }

  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void percolateDown(int j) {
    int leftIndex = left(j);
    int rightIndex = right(j);
    boolean swaps = true;

    while(hasLeft(j) && swaps){

      int smallIndex = leftIndex;

      if(hasRight(j)) {
        if (compare(heap.get(rightIndex), heap.get(leftIndex)) < 0) {
          smallIndex = rightIndex;
        }
      }

      if(compare(heap.get(j),heap.get(smallIndex)) > 0){
        swap(j,smallIndex);
        j = smallIndex;
        leftIndex = left(j);
        rightIndex = right(j);

      } else swaps = false;
    }
  }

  /** Performs a batch bottom-up construction of the heap in O(n) time. */
  protected void buildHeap() {
    int startIndex = parent(size()-1);
    for(int i = startIndex; i >= 0;i--){
      percolateDown(i);
    }
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  @Override
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K,V> min() {
    return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
    PQEntry <K,V> entry = new PQEntry<>(key,value);
    heap.add(entry);
    percolateUp(size()-1);
    return entry;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K,V> removeMin() {
    Entry<K,V> entry = heap.remove(0);
    heap.add(0, heap.remove(size()-1));
    percolateDown(0);
    return entry;
  }
  
  @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();

    for(int i = 0; i < size();i++){
      sb.append(heap.get(i).getKey());
      sb.append("-");
      sb.append(heap.get(i).getValue());
      sb.append("\n");
    }
    return sb.toString();
    }
    
  @Override
      public HeapPriorityQueue<K,V> clone() {
    K[] keys = (K[])new Object[size()];
    V[] values = (V[])new Object[size()];

    for(int i = 0; i < size();i++){
      keys[i] = heap.get(i).getKey();
      values[i] = heap.get(i).getValue();
    }
    return new HeapPriorityQueue<>(keys,values);
      }


    
}

