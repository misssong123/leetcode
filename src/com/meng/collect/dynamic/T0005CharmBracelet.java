package com.meng.collect.dynamic;

/**
 * # [USACO07DEC]Charm Bracelet S
 *
 * ## 题目描述
 *
 * Bessie has gone to the mall's jewelry store and spies a charm bracelet. Of course, she'd like to fill it with the best charms possible from the N (1 ≤ N ≤ 3,402) available charms. Each charm i in the supplied list has a weight Wi (1 ≤ Wi ≤ 400), a 'desirability' factor Di (1 ≤ Di ≤ 100), and can be used at most once. Bessie can only support a charm bracelet whose weight is no more than M (1 ≤ M ≤ 12,880).
 *
 * Given that weight limit as a constraint and a list of the charms with their weights and desirability rating, deduce the maximum possible sum of ratings.
 *
 * 有 $N$ 件物品和一个容量为 $M$ 的背包。第 $i$ 件物品的重量是 $W_i$，价值是 $D_i$。求解将哪些物品装入背包可使这些物品的重量总和不超过背包容量，且价值总和最大。
 *
 * ## 输入格式
 *
 * \* Line 1: Two space-separated integers: N and M
 *
 * \* Lines 2..N+1: Line i+1 describes charm i with two space-separated integers: Wi and Di
 *
 * 第一行：物品个数 $N$ 和背包大小 $M$。
 *
 * 第二行至第 $N+1$ 行：第 $i$ 个物品的重量 $W_i$ 和价值 $D_i$。
 *
 * ## 输出格式
 *
 * \* Line 1: A single integer that is the greatest sum of charm desirabilities that can be achieved given the weight constraints
 *
 * 输出一行最大价值。
 *
 * ## 样例 #1
 *
 * ### 样例输入 #1
 *
 * ```
 * 4 6
 * 1 4
 * 2 6
 * 3 12
 * 2 7
 * ```
 *
 * ### 样例输出 #1
 *
 * ```
 * 23
 * ```
 */
public class T0005CharmBracelet {
    public int maxValue(int T,int[] time,  int[] value) {
        int len = value.length;
       int[][] dp = new int[len+1][T+1];
       for(int i = 1 ; i <= len ; i++){
           int t = time[i-1];
           int v = value[i-1];
           for(int j = T ; j >= t ; j--){
                dp[i][j] = dp[i-1][j-t] + v;
           }
       }
       return dp[len][T];
    }

    public static void main(String[] args) {
        int T = 60;
        int[] time = {61,59,1};
        int[] value = {15,3,1};
        T0005CharmBracelet demo = new T0005CharmBracelet();
        System.out.println(demo.maxValue(T,time,value));
    }
}
