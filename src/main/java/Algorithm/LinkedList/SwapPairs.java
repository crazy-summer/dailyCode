package Algorithm.LinkedList;

public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;
            prev.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            prev = node1;
        }
        return dummy.next;
    }
}
