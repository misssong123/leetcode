package com.meng.enterprise.day02;

/**
 * 70. 爬楼梯(简单)
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 *
 * 1 <= n <= 45
 */
public class ClimbStairs {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 69.29%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n < 3){
            return n;
        }
        int first = 1 , second = 2,index = 3;
        while (index <= n){
            second = first + second;
            first = second - first;
            index++;
        }
        return second;
    }
}
