package com.meng.weeklycompetition.week359;

import java.util.HashSet;
import java.util.Set;

/**
 * 6450. k-avoiding 数组的最小总和
 * 提示
 * 中等
 * 3
 * 相关企业
 * 给你两个整数 n 和 k 。
 *
 * 对于一个由 不同 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 k-avoiding 数组。
 *
 * 返回长度为 n 的 k-avoiding 数组的可能的最小总和。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5, k = 4
 * 输出：18
 * 解释：设若 k-avoiding 数组为 [1,2,4,5,6] ，其元素总和为 18 。
 * 可以证明不存在总和小于 18 的 k-avoiding 数组。
 * 示例 2：
 *
 * 输入：n = 2, k = 6
 * 输出：3
 * 解释：可以构造数组 [1,2] ，其元素总和为 3 。
 * 可以证明不存在总和小于 3 的 k-avoiding 数组。
 *
 *
 * 提示：
 *
 * 1 <= n, k <= 50
 */
public class MinimumSum6450 {
    /**
     * 详情
     * 4ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 40.58MB
     * 击败 100.00%使用 Java 的用户
     * @param n
     * @param k
     * @return
     */
    public int minimumSum(int n, int k) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        int num = 1;
        while (set.size() < n){
            if (!set.contains(k-num)){
                res += num;
                set.add(num);
            }
            num++;
        }
        return res;
    }
}
