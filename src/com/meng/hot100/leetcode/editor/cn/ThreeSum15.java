package com.meng.hot100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum15 {
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了87.86% 的Java用户
     * 	内存消耗:50.5 MB,击败了56.87% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum15(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int  i = 0 ; i < nums.length - 2 ; i++){
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int target = - nums[i],l = i + 1,r = nums.length - 1;
            if (nums[l] + nums[l+1] > target || nums[r-1] + nums[r] < target){
                continue;
            }
            while(l < r){
                int sum = nums[l] + nums[r];
                if (sum == target){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while(l < r && nums[l] == nums[l + 1]){
                        l++;
                    }
                    while(l < r && nums[r] == nums[r - 1]){
                        r--;
                    }
                    l++;
                    r--;
                }else if(sum < target){
                    l++;
                }else {
                    r--;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:28 ms,击败了91.42% 的Java用户
     * 	内存消耗:50.4 MB,击败了66.83% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) continue; // 跳过重复数字
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = x + nums[j] + nums[k];
                if (s > 0) {
                    k--;
                } else if (s < 0) {
                    j++;
                } else { // 三数之和为 0
                    // j = i+1 表示刚开始双指针，此时 j 左边没有数字
                    // nums[j] != nums[j-1] 说明与上一轮循环的三元组不同
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        ans.add(Arrays.asList(x, nums[j], nums[k]));
                    }
                    j++;
                    k--;
                }
            }
        }
        return ans;
    }
}
