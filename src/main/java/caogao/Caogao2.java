package caogao;

import java.util.ArrayList;
import java.util.List;

public class Caogao2 {
    public static void main(String[] args) {
        // offer从后面插入 push从前面插入 poll从前面出队,pop从前面出队
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        linkedList.push(2);
//        linkedList.push(3);
//        linkedList.push(4);
//        linkedList.push(5);
//
//        System.out.println(linkedList);
//        ArrayList<Integer> list = new ArrayList<>();
//        list.addAll(linkedList);
//        System.out.println(list);

//        Caogao2 caogao2 = new Caogao2();
//        caogao2.test();

        List<Integer> list = new ArrayList<>();
    }

    public void test(){
        Treenode p1 = new Treenode(1);
        Treenode p2 = new Treenode(1);
        Treenode p3 = new Treenode(1);
        Treenode p4 = new Treenode(1);
        p1.left = p2;
        p1.right = p3;
        p2.right = p4;
        List<Integer> list = new ArrayList<>();
        recurse(p1, list);
        System.out.println(list);
    }

    public void recurse(Treenode node, List<Integer> list){
        if(node.left!=null){
            list.add(1);
            recurse(node.left, list);
        }
        if(node.right!=null){
            list.add(2);
            recurse(node.right, list);
        }
    }
}
class Treenode{
    public Treenode(){

    }
    public Treenode(int val){
        this.val = val;
    }
    Treenode left;
    Treenode right;
    int val;
}
