package com.liuao.dp;

public class PukeGame {
    // question:有几张扑克牌从左至右排列，小明和小绿先后从其中从左边拿一张或者是从右边拿一张，最后谁的点数和大谁赢，两人都是聪明人

    public static void main(String[] args) {
        int arr[] = new int[]{5,7,4,5,8,1,6,0,3,4,6,1,7};
        int p1 = f(0,arr.length-1,arr);
        int p2 = g(0,arr.length-1,arr);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(dp(0,arr.length-1,arr));
    }
    /**
     * 先手函数
     * @param L 左边起始位置
     * @param R 右边起始位置
     * @param arr 扑克牌
     * @return 在L~R上先手能获得的最大点数
     */
    public static int f(int L, int R, int[] arr){
        //边界条件
        if(L>R || L<0 || R>arr.length-1){
            return -1;
        }
        if(L==R){
            return arr[L];
        }
        // 先手等于在左边或者是在右边挑选一张牌后再在剩下的牌里后手
        int p1 = arr[L] + g(L+1, R, arr);
        int p2 = arr[R] + g(L, R-1, arr);
        return Math.max(p1, p2);
    }

    public static int g(int L, int R, int[] arr) {
        //边界条件
        if(L>R || L<0 || R>arr.length-1){
            return -1;
        }
        if(L==R){
            return 0;
        }
        // 后手是在对手先手后留下的牌中先手，对手是个聪明人，两种选择中留下的剩下的牌的最大点数是两种选择中小的
        int p1 = f(L,R-1, arr);
        int p2 = f(L+1,R, arr);
        return Math.min(p1, p2);
    }

    // 动态规划版本
    public static int dp(int L,int R,int[] arr){
        int[][] fmap = new int[arr.length][arr.length];
        int[][] gmap = new int[arr.length][arr.length];

        for(int i=0; i<arr.length; i++){
            fmap[i][i] = arr[i];
            gmap[i][i] = 0;
        }
        for(int startCol=1;startCol<arr.length;startCol++){
            int r = 0;
            int l = startCol;
            while(l<arr.length){
                fmap[r][l] = Math.max(arr[r]+gmap[r+1][l],arr[l]+gmap[r][l-1]);
                gmap[r][l] = Math.min(fmap[r][l-1],fmap[r+1][l]);
                r++;
                l++;
            }
        }
        return Math.max(fmap[0][arr.length-1],gmap[0][arr.length-1]);
    }
}
