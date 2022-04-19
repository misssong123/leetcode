package com.meng.dynamicprogramming.nine09;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class Trap {
    /**
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 22.56%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 7.91%
     * 的用户
     * 通过测试用例：
     * 321 / 321
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length,ans = 0;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = height[0];
        right[len-1] = height[len-1];
        for(int i = 1 ; i < len - 1 ; i++){
            left[i] = Math.max(left[i-1],height[i]);
        }
        for(int i = len -2 ; i > 0; i--){
            right[i] = Math.max(right[i+1],height[i]);
        }
        for(int i = 1 ; i < len -1 ; i++){
            ans+= Math.min(left[i],right[i])-height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Trap demo = new Trap();
        System.out.println(demo.trap(new int[]{4,2,0,3,2,5}));
    }
    /**
     * 方法二：单调栈
     * 除了计算并存储每个位置两边的最大高度以外，也可以用单调栈计算能接的雨水总量。
     *
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 \textit{height}height 中的元素递减。
     *
     * 从左到右遍历数组，遍历到下标 ii 时，如果栈内至少有两个元素，记栈顶元素为 \textit{top}top，\textit{top}top 的下面一个元素是 \textit{left}left，则一定有 \textit{height}[\textit{left}] \ge \textit{height}[\textit{top}]height[left]≥height[top]。如果 \textit{height}[i]>\textit{height}[\textit{top}]height[i]>height[top]，则得到一个可以接雨水的区域，该区域的宽度是 i-\textit{left}-1i−left−1，高度是 \min(\textit{height}[\textit{left}],\textit{height}[i])-\textit{height}[\textit{top}]min(height[left],height[i])−height[top]，根据宽度和高度即可计算得到该区域能接的雨水量。
     *
     * 为了得到 \textit{left}left，需要将 \textit{top}top 出栈。在对 \textit{top}top 计算能接的雨水量之后，\textit{left}left 变成新的 \textit{top}top，重复上述操作，直到栈变为空，或者栈顶下标对应的 \textit{height}height 中的元素大于或等于 \textit{height}[i]height[i]。
     *
     * 在对下标 ii 处计算能接的雨水量之后，将 ii 入栈，继续遍历后面的下标，计算能接的雨水量。遍历结束之后即可得到能接的雨水总量。
     *
     * 下面用一个例子 \textit{height}=[0,1,0,2,1,0,1,3,2,1,2,1]height=[0,1,0,2,1,0,1,3,2,1,2,1] 来帮助读者理解单调栈的做法。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param height
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 37.60%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 56.91%
     * 的用户
     * 通过测试用例：
     * 321 / 321
     */
    public int trap1(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

}
