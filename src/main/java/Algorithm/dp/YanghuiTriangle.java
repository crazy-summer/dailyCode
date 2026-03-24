package Algorithm.dp;

import java.util.ArrayList;
import java.util.List;

public class YanghuiTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // 每一行有 i+1 个元素
            for (int j = 0; j <= i; j++) {
                // 第一个和最后一个元素是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 中间元素 = 上一行同位置 + 上一行前一个位置
                    List<Integer> prevRow = res.get(i - 1);
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            res.add(row);
        }
        return res;
    }
}
