package Algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


class Node {
    Node left, right;
    int value;
    int height;
    public Node(int value) {
        this.value = value;
    }
}
class compare implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.value > o2.value) {
            return 1;
        }
        if (o1.value < o2.value) {
            return -1;
        }
        if (o1.value == o2.value) {
            if (o1.height > o2.height) {
                return 1;
            }
            if (o1.height < o2.height) {
                return -1;
            }
        }
        return 0;
    }
}
public class Huffman {
    public Node buildHuffmanTree(int[] arr){
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new compare());
        for (int j : arr) {
            pq.add(new Node(j));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            parent.height = Integer.max(left.height, right.height)+1;
            pq.add(parent);
        }
        return pq.peek();
    }

    public void traverseHuffmanTree(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        traverseHuffmanTree(node.left, sb);
        sb.append(node.value);
        sb.append(" ");
        traverseHuffmanTree(node.right, sb);
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Node root = huffman.buildHuffmanTree(arr);
        sc.close();
        StringBuilder stringBuilder = new StringBuilder();
        huffman.traverseHuffmanTree(root, stringBuilder);
        System.out.println(stringBuilder.toString());
    }
}
