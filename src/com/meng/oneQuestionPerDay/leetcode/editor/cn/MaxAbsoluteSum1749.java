package com.meng.oneQuestionPerDay.leetcode.editor.cn;


class MaxAbsoluteSum1749 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 62.25%
     * 的用户
     * 内存消耗：
     * 53.7 MB
     * , 在所有 Java 提交中击败了
     * 27.55%
     * 的用户
     * 通过测试用例：
     * 66 / 66
     * @param nums
     * @return
     */
    public int maxAbsoluteSum(int[] nums) {
        //最大正数
        int maxPositive = 0;
        //最大负数
        int maxNegative = 0;
        int max = Integer.MIN_VALUE;
        for(int num : nums){
            if (num > 0){
                max = Math.max(num + maxPositive ,max);
                maxPositive += num;
                maxNegative = Math.min(maxNegative + num , 0);
            }else {
                max = Math.max(Math.abs(num + maxNegative) ,max);
                maxNegative += num;
                maxPositive = Math.max(maxPositive + num , 0);
            }
        }
        return max;
    }

    /**
     *方法一：动态规划 + 分情况讨论
     *
     * 思路
     *
     * 一个变量绝对值的最大值，可能是这个变量的最大值的绝对值，也可能是这个变量的最小值的绝对值。题目要求任意子数组和的绝对值的最大值，可以分别求出子数组和的最大值
     * positiveMax
     * positiveMax 和子数组和的最小值
     * negativeMin
     * negativeMin，因为子数组可以为空，所以 $\textit{positiveMax} \geq 0
     * ，
     * ，\textit{negativeMin} \leq 0 $。最后返回
     * max
     * ⁡
     * (
     * positiveMax
     * ,
     * −
     * negativeMin
     * )
     * max(positiveMax,−negativeMin) 即为任意子数组和的绝对值的最大值。
     *
     * 而求子数组和的最大值，可以参照「53. 最大子数组和」的解法，运用动态规划求解。而求子数组和的最小值，也是类似的思路，遍历时记录全局最小值
     * negativeMin
     * negativeMin 和当前子数组负数和并更新，遍历完即可得到子数组和的最小值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/solution/ren-yi-zi-shu-zu-he-de-jue-dui-zhi-de-zu-qerr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 62.25%
     * 的用户
     * 内存消耗：
     * 53.8 MB
     * , 在所有 Java 提交中击败了
     * 14.29%
     * 的用户
     * 通过测试用例：
     * 66 / 66
     */
    public int maxAbsoluteSum1(int[] nums) {
        int positiveMax = 0, negativeMin = 0;
        int positiveSum = 0, negativeSum = 0;
        for (int num : nums) {
            positiveSum += num;
            positiveMax = Math.max(positiveMax, positiveSum);
            positiveSum = Math.max(0, positiveSum);
            negativeSum += num;
            negativeMin = Math.min(negativeMin, negativeSum);
            negativeSum = Math.min(0, negativeSum);
        }
        return Math.max(positiveMax, -negativeMin);
    }

}

