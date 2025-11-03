package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class Trap42 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了73.00% 的Java用户
     * 	内存消耗:45.5 MB,击败了46.22% 的Java用户
     * @param height
     * @return
     */
    public int trap42(int[] height) {
        int n = height.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        //左侧最大值
        for(int i = 1 ; i < n ; i++){
            pre[i] = Math.max(pre[i-1],height[i-1]);
        }
        int res = 0;
        //右侧最大值
        for(int i = n -2 ; i >= 0 ; i --){
            suf[i] = Math.max(suf[i+1],height[i+1]);
            res += Math.max(0,Math.min(pre[i],suf[i])-height[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了20.79% 的Java用户
     * 	内存消耗:47.5 MB,击败了5.14% 的Java用户
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!st.isEmpty() && height[st.peek()] <= h) {
                int bottomH = height[st.pop()];
                if (st.isEmpty()) {
                    break;
                }
                int left = st.peek();
                int dh = Math.min(height[left], height[i]) - bottomH; // 面积的高
                ans += dh * (i - left - 1);
            }
            st.push(i);
        }
        return ans;
    }
}
