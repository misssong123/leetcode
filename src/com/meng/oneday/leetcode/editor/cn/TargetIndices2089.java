package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TargetIndices2089 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了80.43% 的Java用户
     * 	内存消耗:50.1 MB,击败了36.96% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public List<Integer> targetIndices2089(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0,right = nums.length - 1;
        int ansLeft = nums.length;
        while (left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] >= target){
                ansLeft = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        if (ansLeft == nums.length || nums[ansLeft] != target){
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        for (int i = ansLeft; i < nums.length; i++) {
            if (nums[i] == target){
                list.add(i);
            }else {
                break;
            }
        }
        return list;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.1 MB,击败了84.78% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0;
        int equal = 0;
        for (int x : nums) {
            if (x < target) {
                less++;
            } else if (x == target) {
                equal++;
            }
        }

        List<Integer> ans = new ArrayList<>(equal);
        for (int i = 0; i < equal; i++) {
            ans.add(less + i);
        }
        return ans;
    }

}
