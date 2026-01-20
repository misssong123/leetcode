package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MinOperations1658 {
    Map<String,Integer> cache;
    public int minOperationsTimeOut(int[] nums, int x) {
        if (Arrays.stream(nums).sum() < x){
            return -1;
        }
        cache = new HashMap<>();
        return dfs(nums,0,nums.length-1,x);
    }

    private int dfs(int[] nums, int l, int r, int x) {
        String key = l + "-" + r + "-" + x;
        if (cache.containsKey(key)){
            return cache.get(key);
        }
        if((l > r &&x != 0) || x < 0){
            return -1;
        }
        if(x == 0){
            return 0;
        }
        int res = 1;
        //左侧
        int left = dfs(nums,l+1,r,x-nums[l]);
        //右侧
        int right = dfs(nums,l,r-1,x-nums[r]);
        if(left != -1 || right != -1 ){
            if (left == -1){
                res += right;
            }else if (right == -1){
                res += left;
            }else{
                res += Math.min(left,right);
            }
            return res;
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了7.80% 的Java用户
     * 	内存消耗:100.1 MB,击败了26.11% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public int minOperations1658(int[] nums, int x) {
        int target = Arrays.stream(nums).sum() - x ;
        if (target < 0){
            return -1;
        }
        if (target == 0){
            return nums.length;
        }
        int low = 0 , fast = 0;
        int temp = 0 , res = -1;
        int len = nums.length;
        while (fast < len){
            //寻找小于的最大下标
            while (fast < len && temp < target){
                temp += nums[fast];
                fast++;
            }
            if (temp == target){
                res = res == -1 ? len -(fast - low) : Math.min(res,len -(fast - low));
                temp -= nums[low++];
            }
            while (temp > target && low <= fast){
                temp -= nums[low];
                low++;
            }
            if (temp == target){
                res = res == -1 ? len -(fast - low) : Math.min(res,len -(fast - low));
                temp -= nums[low++];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了97.94% 的Java用户
     * 	内存消耗:99.7 MB,击败了78.53% 的Java用户
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum - x ;
        int len = nums.length;
        int windowSum = 0 ,left = 0;
        int maxLen = -1;
        for(int i = 0 ; i < len ; i++){
            windowSum += nums[i];
            while (windowSum > target && left <= i){
                windowSum -= nums[left++];
            }
            if (windowSum == target){
                maxLen = Math.max(maxLen,i - left + 1);
            }
        }
        return maxLen == -1 ? -1 : len - maxLen;
    }
}
