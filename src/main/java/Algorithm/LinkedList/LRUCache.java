package Algorithm.LinkedList;

import java.util.HashMap;
import java.util.LinkedList;

class DLinkedNode{
    int key;
    int value;
    DLinkedNode next;
    DLinkedNode prev;
    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    private int size;

    private int capacity;

    private HashMap<Integer, DLinkedNode> map = new HashMap<>();

    private DLinkedNode head = new DLinkedNode(0, 0);

    private DLinkedNode tail = new DLinkedNode(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        DLinkedNode node = map.get(key);
        if (node != null) {
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLinkedNode node = map.get(key);
            node.value = value; // 更新值
            moveToHead(node);   // 移动到头部
        }else {
            DLinkedNode node = new DLinkedNode(key, value);
            map.put(key, node);
            addToHead(node);
            size++;
            if(size > capacity){
                DLinkedNode remove = removeFromTail();
                map.remove(remove.key);
                size --;
            }
        }
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeFromTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
