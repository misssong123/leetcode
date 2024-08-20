package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class Interview048SummaryRanges {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了32.95% 的Java用户
     * 	内存消耗:40.9 MB,击败了14.66% 的Java用户
     * @param nums
     * @return
     */
    public List<String> summaryRangesMy(int[] nums) {
        if (nums.length == 0){
            return new ArrayList<>();
        }
        int pre = 0;
        List<String> res = new ArrayList<>();
        for(int i = 1; i < nums.length; i++){
            if (nums[i] != nums[i-1] + 1){
                if(pre == i-1){
                    res.add(nums[i-1] + "");
                }else {
                    res.add(nums[pre] + "->" + nums[i-1]);
                }
                pre = i;
            }
        }
        if(pre == nums.length-1){
            res.add(nums[nums.length-1] + "");
        }else {
            res.add(nums[pre] + "->" + nums[nums.length-1]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了66.79% 的Java用户
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
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
