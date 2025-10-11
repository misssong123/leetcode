package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaximumTotalDamage3186 {
    /**
     * 解答成功:
     * 	执行耗时:352 ms,击败了10.47% 的Java用户
     * 	内存消耗:62 MB,击败了52.38% 的Java用户
     * @param power
     * @return
     */
    public long maximumTotalDamage3186(int[] power) {
        Map<Integer,Long> cache = new HashMap<>();
        List<Integer> nums = new ArrayList<>();
        //计算数值
        for(int p : power){
            if (!cache.containsKey(p)){
                cache.put(p,0L);
                nums.add(p);
            }
            cache.put(p,cache.get(p)+p);
        }
        //排序
        Collections.sort(nums);
        int size = nums.size();
        long[][] dp = new long[size][2];
        dp[0][0] = cache.get(nums.get(0));
        for(int i = 1 ; i < size ; i++){
            //不选取当前数值
            dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);
            //选取当前数值
            dp[i][0] = cache.get(nums.get(i));
            //前一个数值与当前数值相邻
            if(nums.get(i-1) + 2 >= nums.get(i)){
                //相差为2
                if (nums.get(i -1) == nums.get(i) - 2){
                    dp[i][0] += dp[i-1][1];
                }else {
                    //相差为1
                    if (i >= 2&&nums.get(i -2) == nums.get(i) - 2){
                        dp[i][0] += dp[i-2][1];
                    }else{
                        dp[i][0] += dp[i-1][1];
                    }
                }
            }else {
                //前一个数值与当前数值不相邻
                dp[i][0] += Math.max(dp[i-1][0],dp[i-1][1]);
            }
        }
        return Math.max(dp[size-1][0],dp[size-1][1]);
    }

    /**
     * 解答成功:
     * 	执行耗时:147 ms,击败了85.00% 的Java用户
     * 	内存消耗:62.3 MB,击败了45.95% 的Java用户
     * @param power
     * @return
     */
    public long maximumTotalDamageOther(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }
        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        long[] f = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x - 2) {
                j++;
            }
            f[i + 1] = Math.max(f[i], f[j] + (long) x * cnt.get(x));
        }
        return f[n];
    }

    /**
     * 解答成功:
     * 	执行耗时:440 ms,击败了7.86% 的Java用户
     * 	内存消耗:61.9 MB,击败了54.76% 的Java用户
     * @param power
     * @return
     */
    public long maximumTotalDamageOfficial(int[] power) {
        TreeMap<Integer,Integer> count = new TreeMap<>();
        for (int p : power) {
            count.put(p, count.getOrDefault(p, 0) + 1);
        }
        List<int[]> vec = new ArrayList<>();
        vec.add(new int[]{-1000000000,0});
        for (Map.Entry<Integer,Integer> e : count.entrySet()) {
            vec.add(new int[]{e.getKey(), e.getValue()});
        }
        int n = vec.size();
        long[] f = new long[n];
        long mx = 0, ans = 0;
        int j = 1;
        for (int i = 1; i < n; i++) {
            while (j < i && vec.get(j)[0] < vec.get(i)[0] - 2) {
                mx = Math.max(mx, f[j]);
                j++;
            }
            f[i] = mx + (long) vec.get(i)[0] * vec.get(i)[1];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
