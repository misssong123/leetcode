package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SummaryRanges228 {
    /**
     * 9ms
     * 击败 11.61%使用 Java 的用户
     * 内存
     * 详情
     * 39.04MB
     * 击败 5.89%使用 Java 的用户
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        int left = 0,len = nums.length;
        List<String> res = new ArrayList<>();
        if (len > 0){
            for(int i = 1 ; i < len ; i++){
                if (nums[i] != nums[i-1] + 1){
                    res.add(nums[left] + (left == i-1 ? "" : "->" + nums[i-1]));
                    left = i;
                }
            }
            res.add(nums[left] + (left == len-1 ? "" : "->" + nums[len-1]));
        }
        return res;
    }

    /**
     *时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 38.59MB
     * 击败 72.01%使用 Java 的用户
     * @param nums
     * @return
     */
    public List<String> summaryRanges1(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(nums[high]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }

}

