package odQuestions.splitString;

import java.util.Scanner;
//题目描述
//给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
//
//对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
//
//反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
//
//输入描述
//输入为两行，第一行为参数K，第二行为字符串S。
//
//输出描述
//输出转换后的字符串。
//
//示例1
//        输入
//
//3
//        12abc-abCABc-4aB@
//1
//        2
//输出
//
//12abc-abc-ABC-4aB-@
//1
//说明
//
//子串为12abc、abCABc、4aB@，第一个子串保留，
//后面的子串每3个字符一组为abC、ABc、4aB、@，
//abC中小写字母较多，转换为abc，
//ABc中大写字母较多，转换为ABC，
//        4aB中大小写字母都为1个，不做转换，
//@中没有字母，连起来即12abc-abc-ABC-4aB-@
//
//        示例2
//输入
//
//12
//        12abc-abCABc-4aB@
//1
//        2
//输出
//
//12abc-abCABc4aB@
//
//1
//        2
//说明
//
//子串为12abc、abCABc、4aB@，第一个子串保留，
//后面的子串每12个字符一组为abCABc4aB@，
//这个子串中大小写字母都为4个，不做转换，
//连起来即12abc-abCABc4aB@
public class SplitString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        String[] scraps = s.split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < scraps.length; i++) {
            sb.append(scraps[i]);
        }
        char[] charArray = sb.toString().toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            result.append(charArray[i]);
            if ((i+1) % k == 0 && (i+1) != charArray.length) {
                result.append("-");
            }
        }

        String[] split = result.toString().split("-");
        StringBuilder finalResult = new StringBuilder();
        for (String temp : split) {
            int uppercaseCount = 0;
            int lowercaseCount = 0;
            for (int j = 0; j < temp.length(); j++) {
                if (Character.isUpperCase(temp.charAt(j))) {
                    uppercaseCount++;
                } else if (Character.isLowerCase(temp.charAt(j))) {
                    lowercaseCount++;
                }
            }
            if (uppercaseCount > lowercaseCount) {
                finalResult.append(temp.toUpperCase()).append("-");
            }
            if (lowercaseCount > uppercaseCount) {
                finalResult.append(temp.toLowerCase()).append("-");
            }
            if (uppercaseCount == lowercaseCount) {
                finalResult.append(temp).append("-");
            }
        }
        String substring = finalResult.substring(0, finalResult.length() - 1);
        System.out.println(scraps[0] + "-" + substring);
    }
}
