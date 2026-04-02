package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaximumAmount3418 {
    /**
     * 解答成功:
     * 	执行耗时:80 ms,击败了52.69% 的Java用户
     * 	内存消耗:232.7 MB,击败了68.86% 的Java用户
     * @param coins 二维数组，表示金币和强盗的分布
     * @return 返回可以获得的最大金额
     */
    public int maximumAmount3418(int[][] coins) {
        int m = coins.length ,n = coins[0].length ; // 获取网格的行数和列数
    // 创建一个三维dp数组，dp[i][j][k]表示到达位置(i,j)时，k种状态下的最大金额
    // 第0维表示正常收集金币的状态
    // 第1维表示已经触发过一次强盗的状态
    // 第2维表示已经触发过两次强盗的状态
        int[][][] dp = new int[m+1][n+1][3];
    // 初始化dp数组，所有值设为负无穷大的一半，防止溢出
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
            }
        }
    // 设置起始位置的状态
        dp[0][1][0] = 0;
        dp[0][1][1] = 0;
        dp[0][1][2] = 0;
    // 遍历网格中的每个位置
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int coin = coins[i-1][j-1]; // 获取当前位置的值
            // 获取从上方或左侧到达当前位置的前一个位置的最大值
                int maxPrev0 = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
                int maxPrev1 = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]);
                int maxPrev2 = Math.max(dp[i - 1][j][2], dp[i][j - 1][2]);
                if(coin >= 0){//金币
                    dp[i][j][0] = maxPrev0 + coin;
                    dp[i][j][1] = maxPrev1 + coin;
                    dp[i][j][2] = maxPrev2 + coin;
                }else{//强盗
                    dp[i][j][0] = maxPrev0 + coin;
                    dp[i][j][1] = Math.max(maxPrev1 + coin , maxPrev0 );
                    dp[i][j][2] = Math.max(maxPrev2 + coin , maxPrev1 );
                }
            }
        }
        return Math.max(Math.max(dp[m][n][0],dp[m][n][1]),dp[m][n][2]);
    }
}
