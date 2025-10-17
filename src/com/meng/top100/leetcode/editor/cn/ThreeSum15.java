package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum15 {
    /**
     * 解答成功:
     * 	执行耗时:33 ms,击败了58.15% 的Java用户
     * 	内存消耗:50.5 MB,击败了63.22% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum15(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        if (nums[0] > 0 || nums[len - 1] < 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < len ; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int l = i + 1;
            int r = len - 1;
            int target = -nums[i];
            while(l < r){
                if(nums[l] + nums[r] == target){
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while(l < r && nums[l + 1] == nums[l]){
                        l++;
                    }
                    l++;
                    r--;
                }else if (nums[l] + nums[r] < target){
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
     * 	执行耗时:27 ms,击败了95.32% 的Java用户
     * 	内存消耗:50.3 MB,击败了90.35% 的Java用户
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
