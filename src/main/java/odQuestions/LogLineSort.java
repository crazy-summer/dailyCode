package odQuestions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLineSort {
    public static int convertToMillisecond(String timeStr) {
        Pattern pattern = Pattern.compile("(\\d+):(\\d+):(\\d+).(\\d+)");
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.find()) {
            return ((Integer.parseInt(matcher.group(1)) * 60 + Integer.parseInt(matcher.group(2)))
                    * 60 + Integer.parseInt(matcher.group(3))) * 1000 + Integer.parseInt(matcher.group(4));
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> logs = new ArrayList<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String timeStr = sc.nextLine();
            logs.add(timeStr);
        }
        logs.sort((o1, o2) -> {
            int time1 = convertToMillisecond(o1);
            int time2 = convertToMillisecond(o2);
            return time1 - time2;
        });
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
