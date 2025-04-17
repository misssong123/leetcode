package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class CountPairs2176 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了20.69% 的Java用户
     * 	内存消耗:42.5 MB,击败了10.35% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int countPairs2176(int[] nums, int k) {
        List<Integer>[] list = new ArrayList[101];
        int res= 0;
        for(int i = 0 ; i < nums.length ; i++){
            if (list[nums[i]] == null){
                list[nums[i]] = new ArrayList<>();
            }
            for(int num : list[nums[i]]){
                if ((num * i) % k == 0){
                    res++;
                }
            }
            list[nums[i]].add(i);
        }
        return res;
    }
    private static final int MX = 101;
    private static final List<Integer>[] divisors = new ArrayList[MX];

    static {
        Arrays.setAll(divisors, i -> new ArrayList<>());
        // 预处理每个数的因子
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) {
                divisors[j].add(i);
            }
        }
    }
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了5.75% 的Java用户
     * 	内存消耗:43.5 MB,击败了9.20% 的Java用户
     */
    public int countPairsOther(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int j = 0; j < nums.length; j++) { // 枚举 j，计算左边有多少个符合要求的 i
            int x = nums[j];
            if (j > 0 && x == nums[0]) {
                ans++; // 单独统计 i=0 的情况
            }
            int k2 = k / gcd(k, j); // i 必须是 k2 的倍数
            // 用位运算把二元组 (x, k2) 合并成一个整数
            ans += cnt.getOrDefault(k2 << 10 | x, 0);
            for (int d : divisors[j]) { // j 是 d 的倍数
                cnt.merge(d << 10 | x, 1, Integer::sum); // cnt[d<<10|x]++
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = b % a;
            b = a;
            a = tmp;
        }
        return b;
    }

    /**
     * 官方解答
     * 解答成功:
     * 	执行耗时:4 ms,击败了20.69% 的Java用户
     * 	内存消耗:41.9 MB,击败了27.59% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int countPairsOfficial(int[] nums, int k) {
        int n = nums.length;
        int res = 0;   // 符合要求数对个数
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((i * j) % k == 0 && nums[i] == nums[j]) {
                    ++res;
                }
            }
        }
        return res;
    }
}
