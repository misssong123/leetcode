package com.meng.interview150.leetcode.editor.cn;

class Interview027MaxArea {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了69.88% 的Java用户
     * 	内存消耗:56.9 MB,击败了13.25% 的Java用户
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0,right = height.length-1;
        int res =0;
        while (left<right){
            int len = right - left;
            res = Math.max(res,Math.min(height[left],height[right])*len);
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }
        }
        return res;
    }
}
