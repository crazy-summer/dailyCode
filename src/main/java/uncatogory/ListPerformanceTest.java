package uncatogory;

import java.util.*;

public class ListPerformanceTest {
    public static void main(String[] args) {
        int size = 100000;

        // 1. 随机访问测试
        testRandomAccess(size);

        // 2. 中间插入测试
        testInsertion(size);
    }

    private static void testRandomAccess(int size) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            arrayList.get(i); // 极速
        }
        long arrayTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linkedList.get(i); // 极慢，每次都要遍历
        }
        long linkTime = System.nanoTime() - start;

        System.out.println("随机访问 (size=" + size + "):");
        System.out.println("ArrayList: " + arrayTime / 1_000_000 + " ms");
        System.out.println("LinkedList: " + linkTime / 1_000_000 + " ms (慢 " + (linkTime/arrayTime) + " 倍)");
    }

    private static void testInsertion(int size) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // 在中间位置插入
        int mid = size / 2;

        long start = System.nanoTime();
        arrayList.add(mid, -1);
        long arrayTime = System.nanoTime() - start;

        // 注意：LinkedList 需要先遍历到 mid
        start = System.nanoTime();
        linkedList.add(mid, -1);
        long linkTime = System.nanoTime() - start;

        System.out.println("\n中间插入 (size=" + size + "):");
        System.out.println("ArrayList: " + arrayTime / 1_000_000 + " ms");
        System.out.println("LinkedList: " + linkTime / 1_000_000 + " ms");
        // 结果往往出乎意料：在数据量较大时，ArrayList 的 System.arraycopy 往往比 LinkedList 的遍历更快！
    }
}