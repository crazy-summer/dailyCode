package com.liuao.dp;

public class Lianbiao {
    // question:合并两个有序链表为一个有序链表
    // 方法一：双指针法
    public static Node mergeTwoNode(Node node1,Node node2){
        // 参数里如果有一个链表为空，则返回另一个
        if(node1==null){
            return node2;
        }
        if(node2==null){
            return node1;
        }
        // 先创建两个分别指向链表头部的指针,然后new一个新的节点作为新链表的头，然后创建一个指针指向新链表的头
        Node p1 = node1;
        Node p2 = node2;
        Node node3 = new Node(-1,null);
        Node p3 = node3;

        // 当p1和p2都不为空时，比较两个节点的大小，将较小的节点挂载到新链表末尾。
        while(p1!=null && p2!=null){
            if(p1.val<=p2.val){
                p3.next = p1;
                p3 = p3.next;
                p1 = p1.next;
            }
            if(p2.val<p1.val){
                p3.next = p2;
                p3 = p3.next;
                p2 = p2.next;
            }
        }
        // 当有一个链表遍历到末尾时，返回另一个链表
        if(p1==null){
            p3.next = p2;
        }
        if(p2==null){
            p3.next=p1;
        }

        return node3.next;
    }

    // 方法二：递归 1.返回两个链表合并后的有序链表2.结束条件：一个链表为空，返回另一个3，每一级处理：描述不来，看代码
    public static Node recurse(Node node1,Node node2){
        if(node1==null){
            return node2;
        }
        if(node2==null){
            return node1;
        }
        if(node1.val<=node2.val){
            node1.next = recurse(node1.next, node2);
            return node1;
        }
        else{
            node2.next = recurse(node1, node2.next);
            return node2;
        }

    }
    public static void main(String[] args) {
        Node n1 = new Node(2);
        Node n2 = new Node(5);
        Node n3 = new Node(6);
        Node n4 = new Node(9);
        Node n5 = new Node(13);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        Node a1 = new Node(4);
        Node a2 = new Node(7);
        Node a3 = new Node(8);
        a1.next=a2;
        a2.next=a3;

        Node node = recurse(n1, a1);
        Node p = node;
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }
    }

}
class Node{
    public Node(){

    }
    public Node(int val) {
        this.val = val;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    int val;
    Node next;
}
