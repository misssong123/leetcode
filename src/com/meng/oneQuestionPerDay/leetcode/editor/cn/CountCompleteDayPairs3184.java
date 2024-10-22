package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountCompleteDayPairs3184 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了23.46% 的Java用户
     * 	内存消耗:41.8 MB,击败了5.39% 的Java用户
     * @param hours
     * @return
     */
    public int countCompleteDayPairsMy(int[] hours) {
        Map<Integer,Integer> cache = new HashMap<>();
        int ans = 0;
        for (int hour : hours) {
            int num = hour % 24;
            if (num == 0) {
                ans += cache.getOrDefault(0, 0);
            } else {
                ans += cache.getOrDefault(24 - num, 0);
            }
            cache.put(num, cache.getOrDefault(num, 0) + 1);
        }
        return ans;
    }

    /**
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 80.77%
     * 复杂度分析
     * 消耗内存分布
     * 41.41
     * MB
     * 击败
     * 57.31%
     * @param hours
     * @return
     */
    public int countCompleteDayPairs(int[] hours) {
        int ans = 0;
        for (int i = 1; i < hours.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((hours[i] + hours[j]) % 24 == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
