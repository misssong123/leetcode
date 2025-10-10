package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class RepeatedNTimes961 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了25.19% 的Java用户
     * 	内存消耗:44.4 MB,击败了34.08% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimes961(int[] nums) {
        int len = nums.length;
        int n = len / 2;
        int max = Arrays.stream(nums).max().getAsInt();
        int[] count = new int[max + 1];
        for(int num : nums){
            count[num]++;
        }
        for(int i = 0; i < count.length; i++){
            if(count[i] == n){
                return i;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了68.89% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimesOfficial(int[] nums) {
        int n = nums.length;
        for (int gap = 1; gap <= 3; ++gap) {
            for (int i = 0; i + gap < n; ++i) {
                if (nums[i] == nums[i + gap]) {
                    return nums[i];
                }
            }
        }
        // 不可能的情况
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了65.19% 的Java用户
     * 	内存消耗:44.1 MB,击败了87.41% 的Java用户
     * @param nums
     * @return
     */
    public int repeatedNTimes(int[] nums) {
        Set<Integer> found = new HashSet<Integer>();
        for (int num : nums) {
            if (!found.add(num)) {
                return num;
            }
        }
        // 不可能的情况
        return -1;
    }


}
