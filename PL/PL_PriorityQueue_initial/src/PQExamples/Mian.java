package PQExamples;

import Priority_queue.HeapPriorityQueue;

public class Mian {
    public static void main(String[] args) {
        HeapPriorityQueue<Integer,String> heapPriorityQueue = new HeapPriorityQueue<>();
        heapPriorityQueue.insert(1,"Boca");
        heapPriorityQueue.insert(2,"Boca");
        heapPriorityQueue.insert(3,"Boca");
        heapPriorityQueue.insert(4,"Boca");
        heapPriorityQueue.insert(5,"Boca");
        heapPriorityQueue.insert(6,"Boca");
        heapPriorityQueue.insert(7,"Boca");
        System.out.println(heapPriorityQueue);
        System.out.println(heapPriorityQueue.min().getValue() + " "+ heapPriorityQueue.min().getKey());
        heapPriorityQueue.removeMin();
        System.out.println(heapPriorityQueue.size());
        System.out.println(heapPriorityQueue);
        System.out.println(heapPriorityQueue.min().getValue() + " "+ heapPriorityQueue.min().getKey());





    }
}
