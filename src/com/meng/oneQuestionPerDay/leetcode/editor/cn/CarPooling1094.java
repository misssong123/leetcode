package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class CarPooling1094 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了18.60% 的Java用户
     * 	内存消耗:42.3 MB,击败了10.70% 的Java用户
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling1(int[][] trips, int capacity) {
        Integer size = Arrays.stream(trips).map(item -> item[2]).max(Integer::compareTo).get();
        int[] cache = new int[size];
        for(int[] trip : trips){
            for (int i = trip[1] ; i < trip[2] ; i++){
                cache[i] += trip[0];
            }
        }
        for (int i = 0 ; i < size ; i++){
            if (cache[i] > capacity){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.30% 的Java用户
     * 	内存消耗:42.4 MB,击败了8.49% 的Java用户
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int toMax = 0;
        for (int[] trip : trips) {
            toMax = Math.max(toMax, trip[2]);
        }

        int[] diff = new int[toMax + 1];
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }

        int count = 0;
        for (int i = 0; i <= toMax; ++i) {
            count += diff[i];
            if (count > capacity) {
                return false;
            }
        }
        return true;
    }

}

