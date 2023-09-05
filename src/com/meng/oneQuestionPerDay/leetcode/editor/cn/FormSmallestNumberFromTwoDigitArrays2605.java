package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SolutionMinNumber2605 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.2 MB,击败了82.17% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int minNumber1(int[] nums1, int[] nums2) {
        int [] nums = new int[10];
        int first = 10,second = 10;
        for(int num : nums1){
            nums[num]++;
            first = Math.min(num,first);
        }
        for(int num : nums2){
            nums[num]++;
            second = Math.min(num,second);
        }
        for(int i = 1 ; i<=9 ; i++){
            if (nums[i] ==2){
                return i;
            }
        }

        return first > second ? (second*10 + first):(first*10+second);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了8.70% 的Java用户
     * 	内存消耗:39.8 MB,击败了5.22% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int minNumber(int[] nums1, int[] nums2) {
        int s = same(nums1, nums2);
        if (s != -1) {
            return s;
        }

        int x = Arrays.stream(nums1).min().getAsInt();
        int y = Arrays.stream(nums2).min().getAsInt();
        return Math.min(x * 10 + y, y * 10 + x);
    }

    public int same(int[] nums1, int[] nums2) {
        Set<Integer> s = new HashSet<Integer>();
        for (int num : nums1) {
            s.add(num);
        }
        int x = 10;
        for (int num : nums2) {
            if (s.contains(num)) {
                x = Math.min(x, num);
            }
        }
        return x == 10 ? -1 : x;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
