package Algorithm.LinkedList;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode head = new ListNode(0);
        ListNode res = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                head.next = p1;
                head = head.next;
                p1 = p1.next;
            }
            else {
                head.next = p2;
                head = head.next;
                p2 = p2.next;
            }
        }
        if (p1 != null) {
            head.next = p1;
        }
        if (p2 != null) {
            head.next = p2;
        }
        return res.next;
    }
}
