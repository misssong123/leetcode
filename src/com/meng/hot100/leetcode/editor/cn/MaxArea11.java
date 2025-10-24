package com.meng.hot100.leetcode.editor.cn;

class MaxArea11 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了79.47% 的Java用户
     * 	内存消耗:56.8 MB,击败了55.97% 的Java用户
     * @param height
     * @return
     */
    public int maxArea11(int[] height) {
        int l = 0 , r = height.length - 1;
        int res = 0;
        while (l < r){
            res = Math.max(res,Math.min(height[l],height[r]) * (r - l));
            if (height[l] < height[r]){
                l++;
            }else {
                r--;
            }
        }
        return res;
    }
}
