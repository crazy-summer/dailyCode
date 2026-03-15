package odQuestions;

import java.util.*;

public class CompetitionScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("[,\n]");
        int m = sc.nextInt();
        int n = sc.nextInt();

        if (!(m>=3 && m <= 100) || !(n>=3 && n <= 100)) {
            System.out.println(-1);
            return;
        }

        int[][] scores = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                scores[i][j] = sc.nextInt();
                if (!(scores[i][j] <= 10 && scores[i][j] >= 1)) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        // key 是选手编号也就是列号，value是选手的所有得分也就是一列
        HashMap<Integer, Integer[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer[] score = new Integer[m];
            for (int j = 0; j < m; j++) {
                score[j] = scores[j][i];
            }
            Arrays.sort(score, (a, b) -> b - a);
            map.put(i, score);
        }
        StringJoiner sj = new StringJoiner(",");
        // 按照选手的总分降序排序，如果总分相等，得高分多的排前面
        map.entrySet().stream().sorted(
                (o1, o2) -> {
                    Integer[] score1 = o1.getValue();
                    Integer[] score2 = o2.getValue();

                    int sum1 = Arrays.stream(score1).reduce(0, Integer::sum);
                    int sum2 = Arrays.stream(score2).reduce(0, Integer::sum);

                    if (sum1 != sum2) {
                        return Integer.compare(sum2, sum1);
                    }
                    for (int i = 0; i < m; i++) {
                        if (!Objects.equals(score1[i], score2[i])) {
                            return Integer.compare(score2[i], score1[i]);
                        }
                    }
                    return 0;
                }
        ).limit(3).forEach(e -> sj.add(String.valueOf(e.getKey()+1)));
        System.out.println(sj);
    }
}
