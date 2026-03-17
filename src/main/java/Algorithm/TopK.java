package Algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TopK {
    public static void main(String[] args) {
        TopK topK = new TopK();

    }

    public List<Integer> TopK(int k, int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int j : arr) {
            if (minHeap.size() < k) {
                minHeap.offer(j);
            } else {
                if (minHeap.peek() < j) {
                    minHeap.poll();
                    minHeap.offer(j);
                }
            }
        }
        return new ArrayList<>(minHeap);
    }
}
