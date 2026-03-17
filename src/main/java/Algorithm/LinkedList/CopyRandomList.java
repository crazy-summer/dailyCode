package Algorithm.LinkedList;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        map.put(null, null);
        cur = head;
        while (cur != null) {
            Node newCur = map.get(cur);
            Node newNext = map.get(cur.next);
            Node newRandom = map.get(cur.random);
            newCur.next = newNext;
            newCur.random = newRandom;
            cur = cur.next;
        }
        return map.get(head);
    }
}
