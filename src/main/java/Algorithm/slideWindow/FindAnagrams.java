package Algorithm.slideWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sl = s.length();
        int pl = p.length();
        if(sl < pl){
            return res;
        }
        int[] scount = new int[26];
        int[] pcount = new int[26];
        for(int i=0; i < pl; i++){
            pcount[p.charAt(i) - 'a'] += 1;
            scount[p.charAt(i) - 'a'] += 1;
        }
        if(Arrays.equals(pcount, scount)){
            res.add(0);
        }
        for(int i = pl; i< sl; i++){
            char newC = s.charAt(i);
            scount[newC - 'a'] += 1;

            char old = s.charAt(i-pl);
            scount[old - 'a'] -= 1;
            if(Arrays.equals(scount, pcount)){
                res.add(i - pl + 1);
            }
        }
        return res;
    }
}
