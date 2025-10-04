package com.meng.oneday.leetcode.editor.cn;

class MaxArea11 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了77.29% 的Java用户
     * 	内存消耗:57 MB,击败了15.45% 的Java用户
     * @param height
     * @return
     */
    public int maxArea11(int[] height) {
        int res = 0,l = 0, r = height.length - 1;
        while(l < r){
            if (height[l] < height[r]){
                res = Math.max(res,height[l] * (r-l));
                l++;
            }else {
                res = Math.max(res,height[r] * (r-l));
                r--;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了24.21% 的Java用户
     * 	内存消耗:57 MB,击败了18.36% 的Java用户
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
