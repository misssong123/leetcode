package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class BeautifulSubsets2597 {
    Map<Integer,Integer> map = new HashMap<>();
    int res = 0;

    /**
     * 解答成功:
     * 	执行耗时:265 ms,击败了67.50% 的Java用户
     * 	内存消耗:44.2 MB,击败了56.67% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int beautifulSubsets(int[] nums, int k) {
        dfs(nums,0,k);
        return res - 1;
    }
    private void dfs(int[] nums, int index, int k) {
        if(index == nums.length){
            res++;
            return;
        }
        dfs(nums,index+1,k);
        if (map.getOrDefault(nums[index] + k,0) == 0 && map.getOrDefault(nums[index] - k,0) == 0){
            map.put(nums[index],map.getOrDefault(nums[index],0)+1);
            dfs(nums,index+1,k);
            map.put(nums[index],map.getOrDefault(nums[index],0)-1);
        }
    }
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了91.67% 的Java用户
     * 	内存消耗:43.3 MB,击败了95.83% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int beautifulSubsetsOther(int[] nums, int k) {
        Map<Integer, TreeMap<Integer, Integer>> groups = new HashMap<>();
        for (int x : nums) {
            // 模 k 同余的数分到同一组，记录元素 x 及其出现次数
            groups.computeIfAbsent(x % k, i -> new TreeMap<>()).merge(x, 1, Integer::sum);
        }

        int ans = 1;
        for (TreeMap<Integer, Integer> cnt : groups.values()) {
            // 计算这一组的方案数
            int f0 = 1;
            int f1 = 1; // 下面第一轮循环无论进入哪个分支，都会算出 f1 = 1 << c0
            int pre = 0; // 可以初始化成任意值
            for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
                int x = e.getKey();
                int c = e.getValue();
                int newF = x - pre == k ? f1 + f0 * ((1 << c) - 1) : f1 << c;
                f0 = f1;
                f1 = newF;
                pre = x;
            }
            ans *= f1; // 每组方案数相乘
        }
        return ans - 1; // 去掉空集
    }
}
