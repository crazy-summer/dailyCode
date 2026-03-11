package odQuestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 示例1：
// 输入：19801211 5
// 输出：8

//示例 2：
//输入：432111111111 4
//输出：111
public class RestoreNumConsequese {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读取输入的打乱字符的字符串
        String s = sc.next();
        // 读取输入的正整数序列的长度
        int k = sc.nextInt();
        System.out.println(s);
        System.out.println(k);
        // 创建一个HashMap，用于统计打乱字符的字符串中各字符的数量
        Map<Character, Integer> base = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            base.put(c, base.getOrDefault(c, 0) + 1);
        }
        // 初始化滑动窗口的起始位置
        int i = 1;
        // 当滑动窗口的起始位置小于等于1000减去序列长度加1时，继续循环
        while (i <= 1000 - k + 1) {
            HashMap<Character, Integer> count = new HashMap<>();
            for (int j = i; j < i + k; j++) {
                String numStr = String.valueOf(j);
                for (char c : numStr.toCharArray()) {
                    count.put(c, count.getOrDefault(c, 0) + 1);
                }
            }
            boolean isMatch = true;
            for (Character c : base.keySet()) {
                if (!count.containsKey(c) || count.get(c) != base.get(c)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                System.out.println(i);
                return;
            }
            i++;
        }
    }
}
