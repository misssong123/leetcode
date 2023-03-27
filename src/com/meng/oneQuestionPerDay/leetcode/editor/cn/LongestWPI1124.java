package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

class LongestWPI1124 {
    /**
     * 执行用时：
     * 19 ms
     * , 在所有 Java 提交中击败了
     * 35.98%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 74.72%
     * 的用户
     * 通过测试用例：
     * 98 / 98
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int len = hours.length;
        int[] cache = new int[len+1];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1 ; i <= len ; i++){
            cache[i] = cache[i-1] + (hours[i-1]>8?1:-1);
            if (cache[stack.peek()] > cache[i]){
                stack.push(i);
            }
        }
        int res = 0;
        for (int r = len; r >= 1; r--) {
            while (!stack.isEmpty() && cache[stack.peek()] < cache[r]) {
                res = Math.max(res, r - stack.pop());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestWPI1124 demo = new LongestWPI1124();
        int[] res = {6,6,9};
        System.out.println(demo.longestWPI(res));
        System.out.println(demo.longestWPI1(res));
        System.out.println(demo.longestWPI2(res));
    }
    /**
     *方法一：单调栈
     */
    public int longestWPI1(int[] hours) {
        int n = hours.length, ans = 0;
        int[] s = new int[n + 1]; // 前缀和
        Deque<Integer> st = new ArrayDeque<Integer>();
        st.push(0); // s[0]
        for (int j = 1; j <= n; ++j) {
            s[j] = s[j - 1] + (hours[j - 1] > 8 ? 1 : -1);
            if (s[j] < s[st.peek()]) st.push(j); // 感兴趣的 j
        }
        for (int i = n; i > 0; --i)
            while (!st.isEmpty() && s[i] > s[st.peek()])
                ans = Math.max(ans, i - st.pop()); // [栈顶,i) 可能是最长子数组
        return ans;
    }
    /**
     * 方法二：利用前缀和的连续性
     */
    public int longestWPI2(int[] hours) {
        int n = hours.length, ans = 0, s = 0;
        int[] pos = new int[n + 2]; // 记录前缀和首次出现的位置
        for (int i = 1; i <= n; ++i) {
            s -= hours[i - 1] > 8 ? 1 : -1; // 取反，改为减法
            if (s < 0) ans = i;
            else {
                if (pos[s + 1] > 0) ans = Math.max(ans, i - pos[s + 1]);
                if (pos[s] == 0) pos[s] = i;
            }
        }
        return ans;
    }

}

