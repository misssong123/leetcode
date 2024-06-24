package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

class NextGreaterElements503 {
    /**
     * 解答成功:
     * 	执行耗时:90 ms,击败了5.09% 的Java用户
     * 	内存消耗:45 MB,击败了45.17% 的Java用户
     * @param nums
     * @return
     */
    public int[] nextGreaterElementsMy(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] res = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            if (nums[i] == max){
                res[i] = -1;
                continue;
            }
            for (int j = i +1;j<nums.length+i;j++){
                if (nums[j%nums.length]> nums[i]){
                    res[i] = nums[j%nums.length];
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了69.55% 的Java用户
     * 	内存消耗:44.8 MB,击败了79.77% 的Java用户
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
