package com.meng.algorithm;

import java.util.Arrays;

/**
 * 492. 构造矩形
 *
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 *
 * 1. 你设计的矩形页面必须等于给定的目标面积。
 *
 * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
 *
 * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
 *
 * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
 *
 * 示例：
 *
 * 输入: 4
 * 输出: [2, 2]
 * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
 * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
 *
 * 说明:
 *
 *     给定的面积不大于 10,000,000 且为正整数。
 *     你设计的页面的长度和宽度必须都是正整数。
 */
public class ConstructRectangle {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了7.26% 的用户
     * 通过测试用例：52 / 52
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int max = (int)Math.sqrt(area);
        int [] res = new int[2];
        for(int i = max ; i >= 1 ; i--){
            if (area % i == 0){
                res[0] = area / i;
                res[1] = i;
                break;
            }
        }
        return res;
    }

    /**
     * 方法一：数学
     *
     * 根据题目给出的三个要求，可知：
     *
     *     L⋅W=areaL\cdot W=\textit{area}L⋅W=area，这也意味着 area\textit{area}area 可以被 WWW 整除；
     *     L≥WL\ge WL≥W，结合要求 111 可得 W⋅W≤L⋅W=areaW\cdot W\le L\cdot W=\textit{area}W⋅W≤L⋅W=area，从而有 W≤⌊area⌋W\le\lfloor\sqrt\textit{area}\rfloorW≤⌊area
     *
     * ​⌋；
     * 这意味着 WWW 应取满足 area\textit{area}area 可以被 WWW 整除且 W≤⌊area⌋W\le\lfloor\sqrt\textit{area}\rfloorW≤⌊area
     *
     *     ​⌋ 的最大值。
     *
     * 我们可以初始化 W=⌊area⌋W=\lfloor\sqrt\textit{area}\rfloorW=⌊area
     *
     * ​⌋，不断循环判断 area\textit{area}area 能否被 WWW 整除，如果可以则跳出循环，否则将 WWW 减一后继续循环。
     *
     * 循环结束后我们就找到了答案，长为 areaW\dfrac{\textit{area}}{W}Warea​，宽为 WWW。
     *
     * class Solution {
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/construct-the-rectangle/solution/gou-zao-ju-xing-by-leetcode-solution-dest/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param area
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了66.84% 的用户
     * 通过测试用例：52 / 52
     */
    public int[] constructRectangle1(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            --w;
        }
        return new int[]{area / w, w};
    }
    public static void main(String[] args) {
        ConstructRectangle demo = new ConstructRectangle();
        System.out.println(Arrays.toString(demo.constructRectangle(37)));
    }
}
