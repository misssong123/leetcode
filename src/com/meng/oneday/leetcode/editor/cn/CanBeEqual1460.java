package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CanBeEqual1460 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了48.65% 的Java用户
     * 	内存消耗:45.7 MB,击败了62.16% 的Java用户
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual1460(int[] target, int[] arr) {
        if (target.length != arr.length){
            return false;
        }
        Map<Integer,Integer> cache = new HashMap<>();
        for (int num : target){
            cache.put(num,cache.getOrDefault(num,0)+1);
        }
        for (int num : arr){
            if (cache.getOrDefault(num,0) == 0){
                return false;
            }
            cache.put(num,cache.get(num)-1);
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了62.16% 的Java用户
     * @param target
     * @param arr
     * @return
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        int n = arr.length, tot = 0;
        int[] cnt = new int[1010];
        for (int i = 0; i < n; i++) {
            if (++cnt[target[i]] == 1) tot++;
            if (--cnt[arr[i]] == 0) tot--;
        }
        return tot == 0;
    }

}
