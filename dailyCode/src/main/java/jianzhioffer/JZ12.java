package com.liuao.jianzhioffer;
import java.util.*;


public class JZ12 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param matrix char字符型二维数组
     * @param word string字符串
     * @return bool布尔型
     */
    public static boolean hasPath (char[][] matrix, String word) {
        // write code here
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(f(i,j,matrix,word)==true){
                    return true;
                }
            }
        }
        return false;
    }

    // 每一级问题，从当前坐标点出发，上下左右都能走，是否能走出给定字符串路径
    // 每一级问题 = 当前坐标对应字符是否匹配 + 上下左右下一个坐标点能否走出剩余字符串
    //
    public static boolean f(int i,int j,char[][]matrix,String word){
        // 如果走出了矩阵，返回false
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length){
            return false;
        }
        // 如果走到了之前走过的位置，返回false
        if(matrix[i][j]=='1'){
            return false;
        }
        // 如果是最后一个字符并且和坐标对应的字符相等，返回true
        if(word.length()==1 && matrix[i][j]==word.charAt(0)){
            return true;
        }
        // 当前位置字符是否和word的第一个字符相等
        char c = word.charAt(0);
        String rest = word.substring(1);
        if(matrix[i][j]!=c){
            return false;
        }
        else{
            char temp = matrix[i][j];
            matrix[i][j]='1';
            boolean flag = f(i-1,j,matrix,rest) || f(i+1,j,matrix,rest) || f(i,j-1,matrix,rest) || f(i,j+1,matrix,rest);
            if(flag==false){
                matrix[i][j] = temp;
            }
            return flag;
        }
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}};
        String word="abcced";
        boolean b = hasPath(matrix, word);
        System.out.println(b);
    }
}