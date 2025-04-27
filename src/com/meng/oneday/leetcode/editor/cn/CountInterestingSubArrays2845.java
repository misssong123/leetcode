package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CountInterestingSubArrays2845 {
    /**
     * 超时
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    public long countInterestingSubArrays2845(List<Integer> nums, int modulo, int k) {
        if (modulo == 1 && k == 0){
            return (long) nums.size() * (nums.size() + 1) / 2;
        }
        List<Integer> cache = new ArrayList<>();
        //计算符合条件的索引
        for (int i = 0 ; i < nums.size() ; i++) {
            if (nums.get(i) % modulo == k) {
                cache.add(i);
            }
        }
        long res =0;int len = cache.size();
        int mod = k == 0 ? modulo : k;
        //依次遍历
        for(int i = 0 ; i < cache.size() ; i++) {
            int index = cache.get(i);
            int left = i > 0 ? index -cache.get(i-1) : index + 1;
            //符合条件的下标位置
            int n = 0;
            while (true){
                int y = i + mod - 1 + n * modulo;
                if (y >= len){
                    break;
                }
                int right = y == len - 1 ? (nums.size() - cache.get(y)) : (cache.get(y + 1) - cache.get(y));;
                res += (long) left * right;
                n++;
            }
        }
        if (k == 0){
            for(int i = 0 ; i < cache.size(); i++){
                int num = i == 0 ? cache.get(i)  : cache.get(i) - cache.get(i - 1) - 1;
                res += (long) (num + 1) * num /2;
            }
            int num = nums.size() - cache.get(len - 1) - 1;
            res += (long) (num + 1) * num /2;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了77.71% 的Java用户
     * 	内存消耗:61.7 MB,击败了52.19% 的Java用户
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    public long countInterestingSubarraysOther1(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }
        int[] cnt = new int[Math.min(n + 1, modulo)];
        long ans = 0;
        for (int s : sum) {
            if (s >= k) {
                ans += cnt[(s - k) % modulo];
            }
            cnt[s % modulo]++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了77.71% 的Java用户
     * 	内存消耗:55.4 MB,击败了97.44% 的Java用户
     * @param nums
     * @param modulo
     * @param k
     * @return
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int[] cnt = new int[Math.min(nums.size() + 1, modulo)];
        cnt[0] = 1; // 单独统计 s[0]=0
        long ans = 0;
        int s = 0;
        for (int x : nums) {
            if (x % modulo == k) {
                s++;
            }
            if (s >= k) {
                ans += cnt[(s - k) % modulo];
            }
            cnt[s % modulo]++;
        }
        return ans;
    }
}
