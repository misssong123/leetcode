package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class SpecialTriplets3583 {
    private static final int mod = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:247 ms,击败了38.00% 的Java用户
     * 	内存消耗:187.7 MB,击败了5.13% 的Java用户
     * @param nums
     * @return
     */
    public int specialTriplets3583(int[] nums) {
        long res = 0;
        Map<Integer,Integer> suf = new HashMap<>();
        Map<Integer,Integer> pre =  new HashMap<>();
        //构建后缀和
        for (int i = nums.length-1; i >= 0; i--) {
            suf.put(nums[i],suf.getOrDefault(nums[i],0)+1);
        }
        //计算结果
        for (int num : nums) {
            //后缀移除
            suf.put(num, suf.get(num) - 1);
            //计算结果
            int target = num * 2;
            if (pre.containsKey(target) && suf.containsKey(target)) {
                res = (res + (long) pre.get(target) * suf.get(target)) % mod;
            }
            //前缀添加
            pre.put(num, pre.getOrDefault(num, 0) + 1);
        }
        return (int) (res % mod);
    }

    /**
     * 解答成功:
     * 	执行耗时:236 ms,击败了57.11% 的Java用户
     * 	内存消耗:184.3 MB,击败了21.45% 的Java用户
     * @param nums
     * @return
     */
    public int specialTripletsOther(int[] nums) {
        final int MOD = 1_000_000_007;
        Map<Integer, Integer> suf = new HashMap<>();
        for (int x : nums) {
            suf.merge(x, 1, Integer::sum); // suf[x]++
        }

        long ans = 0;
        Map<Integer, Integer> pre = new HashMap<>();
        for (int x : nums) { // x = nums[j]
            suf.merge(x, -1, Integer::sum); // suf[x]-- // 撤销
            // 现在 pre 中的是 [0,j-1]，suf 中的是 [j+1,n-1]
            ans += (long) pre.getOrDefault(x * 2, 0) * suf.getOrDefault(x * 2, 0);
            pre.merge(x, 1, Integer::sum); // pre[x]++
        }
        return (int) (ans % MOD);
    }

    /**
     * 解答成功:
     * 	执行耗时:175 ms,击败了82.52% 的Java用户
     * 	内存消耗:179.2 MB,击败了32.87% 的Java用户
     * @param nums
     * @return
     */
    public int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Long> cnt12 = new HashMap<>();
        long cnt123 = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                cnt123 += cnt12.getOrDefault(x / 2, 0L); // 把 x 当作 nums[k]
            }
            cnt12.merge(x, (long) cnt1.getOrDefault(x * 2, 0), Long::sum); // 把 x 当作 nums[j]
            cnt1.merge(x, 1, Integer::sum); // 把 x 当作 nums[i]
        }
        return (int) (cnt123 % MOD);
    }

}
