package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class HasGroupsSizeX914 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了11.24% 的Java用户
     * 	内存消耗:44.6 MB,击败了19.66% 的Java用户
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX914(int[] deck) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int i : deck){
            cache.put(i,cache.getOrDefault(i,0)+1);
        }
        int target = -1;
        for(int cnt : cache.values()){
            if(target == -1){
                target = cnt;
            }else if (cnt % target == 0 || target % cnt == 0){
                target = Math.min(target,cnt);
            }else {
                target = gcd914(target,cnt);
                if (target == 1){
                    return false;
                }
            }
        }
        return target >= 2;
    }

    private int gcd914(int target, int cnt) {
        while (cnt != 0){
            int temp = cnt;
            cnt = target % cnt;
            target = temp;
        }
        return target;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了84.27% 的Java用户
     * 	内存消耗:44.5 MB,击败了31.46% 的Java用户
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int c: deck) {
            count[c]++;
        }

        int g = -1;
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                if (g == -1) {
                    g = count[i];
                } else {
                    g = gcd(g, count[i]);
                }
            }
        }
        return g >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

}
