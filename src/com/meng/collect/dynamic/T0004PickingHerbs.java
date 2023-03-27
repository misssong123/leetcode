package com.meng.collect.dynamic;

/**
 * # [NOIP2005 普及组] 采药
 *
 * ## 题目描述
 *
 * 辰辰是个天资聪颖的孩子，他的梦想是成为世界上最伟大的医师。为此，他想拜附近最有威望的医师为师。医师为了判断他的资质，给他出了一个难题。医师把他带到一个到处都是草药的山洞里对他说：“孩子，这个山洞里有一些不同的草药，采每一株都需要一些时间，每一株也有它自身的价值。我会给你一段时间，在这段时间里，你可以采到一些草药。如果你是一个聪明的孩子，你应该可以让采到的草药的总价值最大。”
 *
 *
 * 如果你是辰辰，你能完成这个任务吗？
 *
 * ## 输入格式
 *
 * 第一行有 $2$ 个整数 $T$（$1 \le T \le 1000$）和 $M$（$1 \le  M \le 100$），用一个空格隔开，$T$ 代表总共能够用来采药的时间，$M$ 代表山洞里的草药的数目。
 *
 * 接下来的 $M$ 行每行包括两个在 $1$ 到 $100$ 之间（包括 $1$ 和 $100$）的整数，分别表示采摘某株草药的时间和这株草药的价值。
 *
 * ## 输出格式
 *
 * 输出在规定的时间内可以采到的草药的最大总价值。
 *
 * ## 样例 #1
 *
 * ### 样例输入 #1
 *
 * ```
 * 70 3
 * 71 100
 * 69 1
 * 1 2
 * ```
 *
 * ### 样例输出 #1
 *
 * ```
 * 3
 * ```
 *
 * ## 提示
 *
 * **【数据范围】**
 *
 * - 对于 $30\%$ 的数据，$M \le 10$；
 * - 对于全部的数据，$M \le 100$。
 *
 * **【题目来源】**
 *
 * NOIP 2005 普及组第三题
 */
public class T0004PickingHerbs {

    public int pickingHerbs(int time,int num,int[][] herbs){
        int[] dp = new int[time+1];
        for(int i = 1 ; i <= num ; i++){
            int t = herbs[i-1][0];
            int v = herbs[i-1][1];
            for (int j = time ; j >= t ; j--){
                dp[j] = Math.max(dp[j],dp[j-t]+v);
            }
        }
        return dp[time];
    }


    public static int maxValue(int[] time, int[] value, int T) {
        int n = time.length;
        int[] dp = new int[T+1];
        for (int i = 1; i <= n; i++) {
            for (int j = T; j >= time[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j-time[i-1]] + value[i-1]);
            }
        }
        return dp[T];
    }

}
