package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class LargestRectangleArea84 {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了84.67% 的Java用户
     * 	内存消耗:74.9 MB,击败了61.38% 的Java用户
     * @param heights
     * @return
     */
    public int largestRectangleArea84(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(-1);
        for (int i = 0 ; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                res = Math.max(res,heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            res = Math.max(res,heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return res;
    }
}
