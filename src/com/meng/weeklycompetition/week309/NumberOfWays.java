package com.meng.weeklycompetition.week309;

import java.util.HashMap;
import java.util.Map;

/**
 * 2400. 恰好移动 k 步到达某一位置的方法数目(中等)
 * 给你两个 正 整数 startPos 和 endPos 。最初，你站在 无限 数轴上位置 startPos 处。在一步移动中，你可以向左或者向右移动一个位置。
 *
 * 给你一个正整数 k ，返回从 startPos 出发、恰好 移动 k 步并到达 endPos 的 不同 方法数目。由于答案可能会很大，返回对 109 + 7 取余 的结果。
 *
 * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
 *
 * 注意：数轴包含负整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：startPos = 1, endPos = 2, k = 3
 * 输出：3
 * 解释：存在 3 种从 1 到 2 且恰好移动 3 步的方法：
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * 可以证明不存在其他方法，所以返回 3 。
 * 示例 2：
 *
 * 输入：startPos = 2, endPos = 5, k = 10
 * 输出：0
 * 解释：不存在从 2 到 5 且恰好移动 10 步的方法。
 *
 *
 * 提示：
 *
 * 1 <= startPos, endPos, k <= 1000
 */
public class NumberOfWays {
    /**
     * 思路错误
     * @param startPos
     * @param endPos
     * @param k
     * @return
     */
    Map<String,Integer> cache = new HashMap<>();
    public int numberOfWays(int startPos, int endPos, int k) {
        int diff = k - endPos + startPos;
        if (diff % 2 != 0){
            return 0;
        }
        int left = diff / 2;
        int right = k - left;
        return dfs(left,right);
    }
    private int dfs(int left, int right) {
        if (left <0 || right < 0){
            return 0;
        }
        if (left == 0 || right == 0){
            return 1;
        }
        if (cache.get(left+""+right) != null){
            return cache.get(left+""+right);
        }
        String key = left+""+right;
        int value = (dfs(left-1,right)+dfs(left,right-1))%MOD;
        cache.put(key,value);
        return value;
    }
    public static void main(String[] args) {
        NumberOfWays demo = new NumberOfWays();
        int start = 888;
        int end = 900;
        int k = 42;
        System.out.println(demo.numberOfWays(start,end,k));
        System.out.println(demo.numberOfWays2(start,end,k));
    }
    /**
     * 动态规划
     * @param startPos
     * @param endPos
     * @param k
     * @return
     * 执行用时：
     * 211 ms
     * , 在所有 Java 提交中击败了
     * 24.93%
     * 的用户
     * 内存消耗：
     * 108.5 MB
     * , 在所有 Java 提交中击败了
     * 13.24%
     * 的用户
     * 通过测试用例：
     * 35 / 35
     */
    int mod = (int) 1E9 + 7;
    public int numberOfWays1(int startPos, int endPos, int k) {
        long[][] dp = new long[3005][1005];
        dp[startPos + 1 + 1000][1] = 1;
        dp[startPos - 1 + 1000][1] = 1;
        for (int i = 2; i <= k; i++) {
            for (int j = 1000 + startPos - k; j <= 1000 + startPos + k; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
                dp[j][i] %= mod;
            }
        }
        return (int) dp[1000 + endPos][k];
    }

    /**
     *记忆搜索优化
     * @param startPos
     * @param endPos
     * @param k
     * @return
     * 执行用时：
     * 152 ms
     * , 在所有 Java 提交中击败了
     * 35.42%
     * 的用户
     * 内存消耗：
     * 84 MB
     * , 在所有 Java 提交中击败了
     * 35.67%
     * 的用户
     * 通过测试用例：
     * 35 / 35
     */
    int s, e, MOD = (int)1e9 + 7;
    Map<Integer, Long> map = new HashMap<>();
    public int numberOfWays2(int startPos, int endPos, int k) {
        s = startPos; e = endPos;
        return (int)dfs1(s, k);
    }
    public long dfs1(int idx, int rest) {
        if (Math.abs(idx - e) > rest) return 0; // 剪枝
        if (rest == 0) return 1; // 此时rest = 0 并且因为上一个if的过滤，所以idx肯定到达了终点
        int key = idx * 1005 + rest;
        if (map.containsKey(key)){
            return map.get(key);
        }
        long value = (dfs1(idx + 1, rest - 1) + dfs1(idx - 1, rest - 1)) % MOD;
        map.put(key, value);
        return value;
    }
    /**
     *动态规划
     * @param startPos
     * @param endPos
     * @param k
     * @return
     * 执行用时：
     * 103 ms
     * , 在所有 Java 提交中击败了
     * 49.59%
     * 的用户
     * 内存消耗：
     * 85.7 MB
     * , 在所有 Java 提交中击败了
     * 29.67%
     * 的用户
     * 通过测试用例：
     * 35 / 35
     */
    int N = 2010, M = 1010;
    long[][] dp = new long[M][N];
    public int numberOfWays3(int startPos, int endPos, int k) {
        int diff = Math.abs(startPos - endPos);
        int s = 1001, e = 1001 + diff;
        dp[0][s] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < N - 1; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
            }
        }
        return (int)dp[k][e];
    }

}
