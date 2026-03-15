//import java.util.Arrays;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Entry{
//    Character c;
//    Integer count;
//    public Entry(Character c, Integer count){
//        this.c = c;
//        this.count = count;
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        // while (in.hasNextInt()) { // 注意 while 处理多个 case
//        //     int a = in.nextInt();
//        //     int b = in.nextInt();
//        //     System.out.println(a + b);
//        // }
//
//
//        String s = in.nextLine();
//        int n = s.length();
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < n; i++){
//            char c = s.charAt(i);
//            map.put(c, map.getOrDefault(c, 0)+1);
//        }
//        StringBuilder sb = new StringBuilder();
////        map.entrySet().stream().sorted((o1, o2) -> {
////            return Integer.compare(o2.getValue(), o1.getValue());
////
////        }).forEach(o -> sb.append(o.getKey()));
////        // 拼接cs为字符串
////        System.out.println(sb.toString());
//        Entry[] entries = map.entrySet().stream().map(e -> new Entry(e.getKey(), e.getValue())).toArray();
//        System.out.println(entries);
//        bubbleSort(entries);
//        for(Entry e : entries){
//            sb.append(e.c);
//        }
//        System.out.println(sb.toString());
//    }
//    public static void bubbleSort(Entry[] list){
//        int n = list.length;
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                if (list[j].c.compareTo(list[j + 1].c) > 0) {
//                    Entry temp = list[j];
//                    list[j] = list[j + 1];
//                    list[j + 1] = temp;
//                }
//            }
//        }
//    }
//}
