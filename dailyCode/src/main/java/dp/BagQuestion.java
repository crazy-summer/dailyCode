package com.liuao.dp;

import java.util.Arrays;

public class BagQuestion {

    //问题：有两个数组w[i],v[i],分别是第i个物品重量和价值，现在有一个背包能装W重量的物品，求能装的最大价值。
    public static int maxValue(int[] w, int[] v, int W){

        // 尝试策略：从左往右思想
        // 求i个物品选择放入背包的最大价值==(考虑最左边的物品放入背包+求剩余物品选择放入背包的最大值) or (考虑最左边物品不放入背包+求剩余物品选择放入背包的最大值)两者中大的。
         return process(w,v,0,W);
    }

    private static int process(int[] w, int[] v, int index, int W) {
        //边界限定
        if(w==null || v==null || index<0 || W<0){
            return -1;
        }
        //basecase
        if(index==w.length){
            return 0;
        }
        // 不要当前物品
        int p1 = process(w,v,index+1,W);
        // 要当前物品(这里当前物品重量可能大与剩余背包重量）要判断

        int temp = process(w,v,index+1,W-w[index]);
        int p2 = 0;
        if(temp>=0){
            p2 = v[index] + temp;
        }
        return Math.max(p1,p2);
    }

    public static void main(String[] args) {
        int[] w = {3,2,4,7,3,1,7};
        int[] v = {5,6,3,19,12,4,2};
        int W = 15;
        System.out.println(maxValue(w,v,W));
        System.out.println(dp(w,v,W));
    }

    public static int dp(int[] w, int[] v, int W){
        int N = w.length;
        int[][] dp = new int[N+1][W+1];
        // 初始化arr最后一行为0，因为java本身会初始化，就不写了
        for(int index = N-1;index>=0;index--){
            for(int rest=0;rest<=W;rest++){
                int p1= dp[index+1][rest];
                int p2 = 0;
                int next = rest-w[index]<0 ? -1 : dp[index+1][rest-w[index]];
                if(next!=-1){
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

//        for(int i=0;i<arr.length;i++){
//           for(int j=0;j<arr[i].length;j++){
//               System.out.println(arr[i][j]);
//           }
//        }

        return dp[0][W];

    }
}
