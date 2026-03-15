package caogao;

import java.util.*;

public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // while (in.hasNextInt()) { // 注意 while 处理多个 case
        //     int a = in.nextInt();
        //     int b = in.nextInt();
        //     System.out.println(a + b);
        // }
        int index = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        int n = s.length();

//        2
//        aaa_password_"a12_45678"_timeout__100_""_
        String[] orders = s.split("_+");
        orders[index] = "******";
        System.out.println(String.join("_", orders));
    }
}