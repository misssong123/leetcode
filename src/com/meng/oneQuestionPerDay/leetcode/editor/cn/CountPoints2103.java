package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountPoints2103 {
    public static Map<Character,Integer> cache = new HashMap<>();
    static {
        cache.put('R',1);
        cache.put('G',2);
        cache.put('B',4);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了43.79% 的Java用户
     * @param rings
     * @return
     */
    public int countPointsMy(String rings) {
        int[] nums = new int[10];
        int len = rings.length();
        if (len < 6){
            return 0;
        }
        for(int i = 0 ; i < len ; i+=2){
            nums[rings.charAt(i+1)-'0'] |= cache.get(rings.charAt(i));
        }
        int res = 0;
        for (int i = 0 ; i < 10 ; i++){
            if (nums[i]==7){
                res++;
            }
        }
        return res;
    }

    static final int POLE_NUM = 10;

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.3 MB,击败了60.13% 的Java用户
     * @param rings
     * @return
     */
    public int countPoints(String rings) {
        int[] state = new int[POLE_NUM];
        int n = rings.length();
        for (int i = 0; i < n; i += 2) {
            char color = rings.charAt(i);
            int poleIndex = rings.charAt(i + 1) - '0';
            if (color == 'R') {
                state[poleIndex] |= 1;
            } else if (color == 'G') {
                state[poleIndex] |= 2;
            } else {
                state[poleIndex] |= 4;
            }
        }
        int res = 0;
        for (int i = 0; i < POLE_NUM; i++) {
            res += state[i] == 7 ? 1 : 0;
        }
        return res;
    }


}

