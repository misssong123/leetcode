package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MinOperations1658_0923 {
    /**
     * 解答成功:
     * 	执行耗时:167 ms,击败了5.02% 的Java用户
     * 	内存消耗:139.9 MB,击败了5.02% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public int minOperations1658(int[] nums, int x) {
        int len = nums.length;
        //记录前缀下标
        Map<Integer,Integer> preMap = new HashMap<>();
        int sum = 0 ;
        for (int i = 0 ; i < len ; i++){
            sum += nums[i];
            if (sum > x){
                break;
            }
            if(!preMap.containsKey(sum)){
                preMap.put(sum,i);
            }
        }
        //记录后缀下标
        Map<Integer,Integer> sufMap = new HashMap<>();
        sum = 0;
        for (int i = len - 1 ; i >= 0 ; i--){
            sum += nums[i];
            if (sum > x){
                break;
            }
            if(!sufMap.containsKey(sum)){
                sufMap.put(sum,i);
            }
        }
        int res = Integer.MAX_VALUE;
        //前序验证
        sum = 0;
        for(int i = 0 ; i < len ; i++){
            sum += nums[i];
            if (sum > x || i + 1 >= res){
                break;
            }
            if (sum == x){
                res = Math.min(res,i + 1);
                break;
            }
            if(sufMap.containsKey(x - sum) && sufMap.get(x - sum) > i){
                res = Math.min(res,i + 1 + len - sufMap.get(x - sum));
            }
        }
        //后序验证
        sum = 0;
        for(int i = len - 1 ; i >= 0 ; i--){
            sum += nums[i];
            if (sum > x || len - i >= res){
                break;
            }
            if (sum == x){
                res = Math.min(res,len - i);
                break;
            }
            if(preMap.containsKey(x - sum) && preMap.get(x - sum) < i){
                res = Math.min(res,preMap.get(x - sum) + 1 + len - i);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了97.85% 的Java用户
     * 	内存消耗:100.2 MB,击败了16.49% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public int minOperationsOther1(int[] nums, int x) {
        int target = -x;
        for (int num : nums) {
            target += num;
        }
        if (target < 0) {
            return -1; // 全部移除也无法满足要求
        }

        int n = nums.length;
        int ans = -1;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left];
                left++; // 缩小子数组长度
            }
            if (sum == target) {
                ans = Math.max(ans, right - left + 1);
            }
        }

        return ans < 0 ? -1 : n - ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了36.20% 的Java用户
     * 	内存消耗:100.2 MB,击败了18.46% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        int right = n;
        while (right > 0 && sum + nums[right - 1] <= x) { // 计算最长后缀
            right--;
            sum += nums[right];
        }
        if (right == 0 && sum < x) {
            return -1; // 全部移除也无法满足要求
        }

        int ans = sum == x ? n - right : n + 1;
        for (int left = 0; left < n; left++) {
            sum += nums[left];
            while (right < n && sum > x) {
                sum -= nums[right];
                right++; // 缩小后缀长度
            }
            if (sum > x) {
                break; // 缩小失败，说明前缀过长
            }
            if (sum == x) {
                ans = Math.min(ans, left + 1 + n - right); // 前缀+后缀长度
            }
        }

        return ans > n ? -1 : ans;
    }

}
