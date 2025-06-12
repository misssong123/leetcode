package com.meng.weeklycompetition.week360;

import java.util.List;

/**
 * 2836. 在传球游戏中最大化函数值
 * 提示
 * 困难
 * 15
 * 相关企业
 * 给你一个长度为 n 下标从 0 开始的整数数组 receiver 和一个整数 k 。
 *
 * 总共有 n 名玩家，玩家 编号 互不相同，且为 [0, n - 1] 中的整数。这些玩家玩一个传球游戏，receiver[i] 表示编号为 i 的玩家会传球给编号为 receiver[i] 的玩家。玩家可以传球给自己，也就是说 receiver[i] 可能等于 i 。
 *
 * 你需要从 n 名玩家中选择一名玩家作为游戏开始时唯一手中有球的玩家，球会被传 恰好 k 次。
 *
 * 如果选择编号为 x 的玩家作为开始玩家，定义函数 f(x) 表示从编号为 x 的玩家开始，k 次传球内所有接触过球玩家的编号之 和 ，如果有玩家多次触球，则 累加多次 。换句话说， f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver(k)[x] 。
 *
 * 你的任务时选择开始玩家 x ，目的是 最大化 f(x) 。
 *
 * 请你返回函数的 最大值 。
 *
 * 注意：receiver 可能含有重复元素。
 *
 *
 *
 * 示例 1：
 *
 * 传递次数	传球者编号	接球者编号	x + 所有接球者编号
 *  	 	 	2
 * 1	2	1	3
 * 2	1	0	3
 * 3	0	2	5
 * 4	2	1	6
 *
 *
 * 输入：receiver = [2,0,1], k = 4
 * 输出：6
 * 解释：上表展示了从编号为 x = 2 开始的游戏过程。
 * 从表中可知，f(2) 等于 6 。
 * 6 是能得到最大的函数值。
 * 所以输出为 6 。
 * 示例 2：
 *
 * 传递次数	传球者编号	接球者编号	x + 所有接球者编号
 *  	 	 	4
 * 1	4	3	7
 * 2	3	2	9
 * 3	2	1	10
 *
 *
 * 输入：receiver = [1,1,1,2,3], k = 3
 * 输出：10
 * 解释：上表展示了从编号为 x = 4 开始的游戏过程。
 * 从表中可知，f(4) 等于 10 。
 * 10 是能得到最大的函数值。
 * 所以输出为 10 。
 *
 *
 * 提示：
 *
 * 1 <= receiver.length == n <= 105
 * 0 <= receiver[i] <= n - 1
 * 1 <= k <= 1010
 */
public class GetMaxFunctionValue2836 {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        return -1;
    }

    /**
     *
     * @param receiver
     * @param K
     * @return
     */
    public long getMaxFunctionValue1(List<Integer> receiver, long K) {
        int n = receiver.size();
        int m = 64 - Long.numberOfLeadingZeros(K); // K 的二进制长度
        int[][] pa = new int[m][n];
        long[][] sum = new long[m][n];
        for (int i = 0; i < n; i++) {
            pa[0][i] = receiver.get(i);
            sum[0][i] = receiver.get(i);
        }
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[i][x];
                pa[i + 1][x] = pa[i][p];
                sum[i + 1][x] = sum[i][x] + sum[i][p]; // 合并节点值之和
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long s = i;
            int x = i;
            for (long k = K; k > 0; k &= k - 1) {
                int ctz = Long.numberOfTrailingZeros(k);
                s += sum[ctz][x];
                x = pa[ctz][x];
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }


}
