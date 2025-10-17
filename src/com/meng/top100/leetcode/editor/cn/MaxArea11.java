package com.meng.top100.leetcode.editor.cn;

class MaxArea11 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了79.36% 的Java用户
     * 	内存消耗:57 MB,击败了28.62% 的Java用户
     * @param height
     * @return
     */
    public int maxArea11(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r){
            ans = Math.max(ans,Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]){
                l++;
            }else {
                r--;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了32.02% 的Java用户
     * 	内存消耗:57 MB,击败了15.66% 的Java用户
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

}
