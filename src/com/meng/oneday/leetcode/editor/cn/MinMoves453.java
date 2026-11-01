package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinMoves453 {
    /**
     * 思路错误
     * @param nums
     * @return
     */
    public int minMoves453(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(nums);
        long max = (long) nums[nums.length - 1] * n;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //差值
        long diff = max - sum;
        if(diff % (n-1) == 0) {
            return (int) (diff / (n-1));
        }
        //最大公约数
        long gcd = gcd(diff, n-1);
        return (int )(diff / gcd);
    }
    // 求最大公约数 (GCD)
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了77.59% 的Java用户
     * 	内存消耗:46.5 MB,击败了53.45% 的Java用户
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        long sum = 0;
        int min = Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            min = Math.min(min, x);
        }
        return (int) (sum - (long) min * nums.length);
    }

}
