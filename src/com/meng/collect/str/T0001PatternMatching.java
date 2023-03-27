package com.meng.collect.str;

import java.util.ArrayList;
import java.util.List;

/**
 * 从主串中查询处所有模版串的位置
 */
public class T0001PatternMatching {
    public static void main(String[] args) {
        String str = "afnirnabvridsnvsdfvsdfnvijwbnaire";
        String pattern = "na";
        T0001PatternMatching demo = new T0001PatternMatching();
        System.out.println(demo.match(str,pattern));
        System.out.println(str.indexOf(pattern));
    }
    public List<Integer> match(String str,String pattern){
        List<Integer> res = new ArrayList<>();
        int n = str.length();
        int m = pattern.length();
        if (n >= m){
            for(int i = 0 ; i < n - m + 1 ; i++){
                boolean flag = true;
                for(int j = 0 ; j < m ; j++){
                    if (str.charAt(j+i)!=pattern.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    res.add(i);
                }
            }
        }
        return res;
    }
}
