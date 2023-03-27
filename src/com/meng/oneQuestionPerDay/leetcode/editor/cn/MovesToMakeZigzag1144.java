package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MovesToMakeZigzag1144 {
    /**
     * 计算左右两侧相比的最小值，把差值结果合并在一起
     * 思路错误，将中间值变为最大值，会比将中间值变为最小值移动的步数短
     * @param nums
     * @return
     */
    public int movesToMakeZigzag(int[] nums) {
        int len = nums.length;
        int [] odds = Arrays.copyOf(nums,len);
        int odd = compare(0,odds,len);
        int even = compare(1,nums,len);
        return Math.min(odd,even);
    }
    private int compare(int i,int[] nums,int len){
        int res = 0;
        for(int j = i ; j < len ; j+=2){
            if (j-1>=0){
                int step =  nums[j-1] >= nums[j] ? nums[j-1] - nums[j]:-1;
                if (step >=0){
                    res+= step+1;
                }
            }
            if (j+1<len){
                int step = nums[j+1] >= nums[i]?nums[j+1]-nums[i]:-1;
                if (step >=0){
                    res+= step+1;
                    nums[j+1] -= step+1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,10,9,8,9};
        MovesToMakeZigzag1144 demo = new MovesToMakeZigzag1144();
        System.out.println(demo.movesToMakeZigzag(nums));
    }

    public int movesToMakeZigzag1(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    public int help(int[] nums, int pos) {
        int res = 0;
        for (int i = pos; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }

}

