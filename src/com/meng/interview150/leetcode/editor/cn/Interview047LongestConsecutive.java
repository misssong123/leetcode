package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Interview047LongestConsecutive {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了99.99% 的Java用户
     * 	内存消耗:55.5 MB,击败了88.65% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutiveMy(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int res = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1] + 1){
                temp++;
            }else if (nums[i] == nums[i-1]){
                continue;
            }else {
                res = Math.max(res,temp);
                temp = 1;
            }
        }
        res = Math.max(res,temp);
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了99.99% 的Java用户
     * 	内存消耗:55.5 MB,击败了88.65% 的Java用户
     * @param nums
     * @return
     *
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

}
