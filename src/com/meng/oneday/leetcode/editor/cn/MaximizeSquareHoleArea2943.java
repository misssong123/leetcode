package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MaximizeSquareHoleArea2943 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了17.65% 的Java用户
     * 	内存消耗:44.7 MB,击败了47.06% 的Java用户
     * @param n
     * @param m
     * @param hBars
     * @param vBars
     * @return
     */
    public int maximizeSquareHoleArea2943(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int xStep = 1, xStepTemp = 1;
        for(int i = 1 ; i < hBars.length; i++){
            if (hBars[i] - hBars[i-1] == 1){
                xStepTemp++;
                xStep = Math.max(xStepTemp,xStep);
            }else{
                xStepTemp = 1;
            }
        }
        int yStep = 1 , yStepTemp = 1;
        for(int i = 1 ; i < vBars.length; i++){
            if (vBars[i] - vBars[i-1] == 1){
                yStepTemp++;
                yStep = Math.max(yStepTemp,yStep);
            }else {
                yStepTemp = 1;
            }
        }
        int side = Math.min(xStep,yStep) + 1;
        return side * side;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了70.59% 的Java用户
     * 	内存消耗:45.4 MB,击败了5.88% 的Java用户
     * @param n
     * @param m
     * @param hBars
     * @param vBars
     * @return
     */
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int side = Math.min(longestConsecutive(hBars), longestConsecutive(vBars)) + 1;
        return side * side;
    }

    // 128. 最长连续序列
    private int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }

        int ans = 0;
        for (int x : st) { // 遍历哈希集合
            if (st.contains(x - 1)) { // 如果 x 不是序列的起点，直接跳过
                continue;
            }
            // x 是序列的起点
            int y = x + 1;
            while (st.contains(y)) { // 不断查找下一个数是否在哈希集合中
                y++;
            }
            // 循环结束后，y-1 是最后一个在哈希集合中的数
            ans = Math.max(ans, y - x); // 从 x 到 y-1 一共 y-x 个数
        }
        return ans;
    }

}
