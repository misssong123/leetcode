package com.meng.weeklycompetition.week368;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinGroupsForValidAssignment100097 {
    /**
     * 时间
     * 详情
     * 303ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 58.96MB
     * 击败 100.00%使用 Java 的用户
     * @param nums
     * @return
     */
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int num : nums) {
            cache.put(num, cache.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        queue.addAll(cache.values());
        Integer[] numCache = cache.values().toArray(new Integer[0]);
        int k = queue.poll();
        while (k>=1) {
            int res = getRes(numCache, k, k+1);
            if (res != -1) {
                return res;
            }
            k--;
        }
        return -1;
    }

    private int getRes(Integer[] nums, int firstNum, int secondNum) {
        boolean flag = true;
        int res = 0;
        for (int num : nums) {
            int k = contains(num, firstNum, secondNum);
            System.out.println(num + "," + k);
            if (k == -1) {
                flag = false;
                break;
            }
            res += k;
        }
        if (flag) {
            return res;
        }
        return -1;
    }

    private int contains(int num, int n, int m) {
        if (num % m == 0) {
            if (num % m == 0) {
                return num / m;
            }
        }
        int k = num / m;
        while (k >= 0) {
            if ((num - k * m) % n == 0) {
                return k + (num - k * m) / n;
            }
            k--;
        }
        return -1;
    }

    /**
     * 37ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 59.79MB
     * 击败 100.00%使用 Java 的用户
     * @param nums
     * @return
     */
    public int minGroupsForValidAssignment1(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int k = nums.length;
        for (int c : cnt.values()) {
            k = Math.min(k, c);
        }
        for (; ; k--) {
            int ans = 0;
            for (int c : cnt.values()) {
                if (c / k < c % k) {
                    ans = 0;
                    break;
                }
                ans += (c + k) / (k + 1);
            }
            if (ans > 0) {
                return ans;
            }
        }
    }

    public static void main(String[] args) {
        MinGroupsForValidAssignment100097 demo = new MinGroupsForValidAssignment100097();

    }
}