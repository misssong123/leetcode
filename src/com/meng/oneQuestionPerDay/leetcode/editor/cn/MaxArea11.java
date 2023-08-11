package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MaxArea11 {
    /**
     * 详情
     * 4ms
     * 击败 59.70%使用 Java 的用户
     * 内存
     * 详情
     * 53.15mb
     * 击败 37.77%使用 Java 的用户
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0 , right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right){
            max = Math.max(max,Math.min(height[left],height[right])*(right-left));
            if (height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }
        return max;
    }

    /**
     * 详情
     * 3ms
     * 击败 90.13%使用 Java 的用户
     * 内存
     * 详情
     * 53.18mb
     * 击败 33.54%使用 Java 的用户
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }



}

