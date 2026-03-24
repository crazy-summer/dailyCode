package Algorithm.LinkedList;

public class SortList {
    public static void main(String[] args) {}

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode list1 = sortList(head);
        ListNode list2 = sortList(temp);
        return mergeList(list1, list2);
    }

    private ListNode mergeList(ListNode list1, ListNode list2) {
        ListNode p1 = list1, p2 = list2;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                curr.next = p1;
                p1 = p1.next;
                curr = curr.next;
            }else{
                curr.next = p2;
                p2 = p2.next;
                curr = curr.next;
            }
        }
        if (p1 != null) {
            curr.next = p1;
        }
        if (p2 != null) {
            curr.next = p2;
        }
        return dummy.next;
    }
}
