package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MaxWidthOfVerticalArea1637 {
    /**
     * 执行用时：
     * 41 ms
     * , 在所有 Java 提交中击败了
     * 39.57%
     * 的用户
     * 内存消耗：
     * 68.4 MB
     * , 在所有 Java 提交中击败了
     * 87.77%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     * @param points
     * @return
     */
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points,(a,b)-> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int pre = points[0][0];
        int ans = 0 ;
        for(int i = 1 ; i < points.length ; i++){
            int cur = points[i][0];
            if (pre != cur){
                ans = Math.max(ans,cur-pre);
                pre = cur;
            }
        }
        return ans;
    }

    /**
     *执行用时：
     * 40 ms
     * , 在所有 Java 提交中击败了
     * 50.36%
     * 的用户
     * 内存消耗：
     * 68.5 MB
     * , 在所有 Java 提交中击败了
     * 74.82%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     * @param points
     * @return
     */
    public int maxWidthOfVerticalArea1(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int mx = 0;
        for (int i = 1; i < points.length; i++) {
            mx = Math.max(points[i][0] - points[i - 1][0], mx);
        }
        return mx;
    }


}

