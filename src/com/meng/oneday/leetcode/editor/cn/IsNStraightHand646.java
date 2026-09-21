package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class IsNStraightHand646 {
    /**
     * 解答成功:
     * 	执行耗时:28 ms,击败了78.89% 的Java用户
     * 	内存消耗:47.8 MB,击败了8.89% 的Java用户
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand646(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : hand) {
            if (map.get(i) == 0) {
                continue;
            }
            for(int j = i ; j <= i + groupSize - 1; j++) {
                if (map.getOrDefault(j, 0) == 0) {
                    return false;
                }
                map.put(j, map.get(j) - 1);
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了52.22% 的Java用户
     * 	内存消耗:47.2 MB,击败了60.00% 的Java用户
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : hand) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        for (int x : hand) {
            if (!cnt.containsKey(x)) {
                continue;
            }
            for (int j = 0; j < groupSize; j++) {
                int num = x + j;
                if (!cnt.containsKey(num)) {
                    return false;
                }
                cnt.put(num, cnt.get(num) - 1);
                if (cnt.get(num) == 0) {
                    cnt.remove(num);
                }
            }
        }
        return true;
    }
}
