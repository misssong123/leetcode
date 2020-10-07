package com.meng;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 * 提示：
 *     0 <= n <= 100
 * 来源：力扣（LeetCode）
 */

/**
 * 思路:
 * 青蛙的跳上n阶台阶的跳法等于条n-1和n-2阶台阶解法的和
 */
public class NumWays {
    public static void main(String[] args) {
        NumWays demo = new NumWays();
        System.out.println(demo.numWaysNoRecursion(44));
    }
    /**
     * @param n
     * @return
     */
    public int numWaysNoRecursion(int n) {
        if (n<=1)
            return 1;
        else if (n==2)
            return 2;
        else{
            int a=1,b=2,result=0;
            for(int i=3;i<=n;i++){
                result = (a+ b)%1000000007;
                a=b;
                b=result;
            }
            return result;
        }
    }
}
