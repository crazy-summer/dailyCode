package com.liuao.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//传入一个字符串队列，找出所有字符串中的奇数数并按顺序储存到队列中。
//
//public List<String> getOdds(String[] inputVariables);

public class Test {
    public static void main(String[] args) {
        String[] strArr = new String[]{"234","23","55","66","77","abc123","231000$@#$!@$#1","",null};
        Test t = new Test();
        System.out.println(t.getOdds(strArr));

    }

    public List<String> getOdds(String[] inputVariables){
        return Arrays.stream(inputVariables)
                .filter(Test::isDigitStr)
                .filter(a -> (a.charAt(a.length() - 1) - '0') % 2 == 1)
                .collect(Collectors.toList());
    }

    public static boolean isDigitStr(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
