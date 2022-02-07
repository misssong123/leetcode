package com.meng.origin;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbStairs {
    /**
     * 递归
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n<=1)
            return 1;
        else if(n==2)
            return 2;
        else
            return climbStairs(n-1)+climbStairs(n-2);
    }
    /**
     * 递归
     * @param n
     * @return
     */
    public int climbStairsNoRecursion(int n) {
        if (n<=1)
            return 1;
        else if(n==2)
            return 2;
        else{
            int a =1,b=2,result=0;
            for(int i =3;i<=n;i++){
                result = a+b;
                a=b;
                b=result;
            }
            return result;
        }
    }
}
