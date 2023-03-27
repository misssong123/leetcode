package com.meng.hot100;

/**
 * 11. 盛最多水的容器(中等)
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 *
 *
 * 提示：
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class T007MaxArea {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 92.18%
     * 的用户
     * 内存消耗：
     * 51.6 MB
     * , 在所有 Java 提交中击败了
     * 41.90%
     * 的用户
     * 通过测试用例：
     * 60 / 60
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0 , right = height.length-1;
        int count = 0;
        while (right > left){
            if (height[right] > height[left]){
                count = Math.max(count,height[left] * (right-left));
                left++;
            }else {
                count = Math.max(count,height[right] * (right-left));
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        T007MaxArea demo = new T007MaxArea();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(demo.maxArea(height));
    }

    /**
     *执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 65.26%
     * 的用户
     * 内存消耗：
     * 51.2 MB
     * , 在所有 Java 提交中击败了
     * 88.80%
     * 的用户
     * 通过测试用例：
     * 60 / 60
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
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
