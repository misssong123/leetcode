package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class GcdSum3867 {
    /**
     *
     * 解答成功:
     * 	执行耗时:75 ms,击败了30.00% 的Java用户
     * 	内存消耗:123.3 MB,击败了30.00% 的Java用户
     * @param nums
     * @return
     */
    public long gcdSum3867(int[] nums) {
        int len = nums.length;
        //最大数组
        int[] max = new int[len];
        for (int i = 0 ; i < len ; i++){
            max[i] = i == 0 ? nums[i] : Math.max(max[i-1],nums[i]);
        }
        //最大公约数
        int[] gcd = new int[len];
        for (int i = 0 ; i < len ; i++){
            gcd[i] = gcdRecursive(max[i],nums[i]);
        }
        //公约数排序
        Arrays.sort(gcd);
        long res = 0;
        for (int i = 0 ; i < len / 2 ; i++){
            res += gcdRecursive(gcd[i],gcd[len-i-1]);
        }
        return res;
    }
    public static int gcdRecursive(int a, int b) {
        return b == 0 ? a : gcdRecursive(b, a % b);
    }

    /**
     * 解答成功:
     * 	执行耗时:68 ms,击败了90.00% 的Java用户
     * 	内存消耗:105.9 MB,击败了55.00% 的Java用户
     * @param nums
     * @return
     */
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int mx = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            mx = Math.max(mx, x);
            pre[i] = gcd(x, mx);
        }

        Arrays.sort(pre);
        long ans = 0;
        for (int i = 0; i < n / 2; i++) {
            ans += gcd(pre[i], pre[n - 1 - i]);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
