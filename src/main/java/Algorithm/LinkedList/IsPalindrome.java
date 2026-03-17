package Algorithm.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        for (int i = 0, j = list.size()-1; i < list.size() / 2; i++,j--) {
            if (list.get(i).val != list.get(j).val) {
                return false;
            }
        }
        return true;
    }
}
