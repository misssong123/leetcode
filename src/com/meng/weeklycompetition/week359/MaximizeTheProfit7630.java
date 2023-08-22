package com.meng.weeklycompetition.week359;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 中等
 * 13
 * 相关企业
 * 给你一个整数 n 表示数轴上的房屋数量，编号从 0 到 n - 1 。
 *
 * 另给你一个二维整数数组 offers ，其中 offers[i] = [starti, endi, goldi] 表示第 i 个买家想要以 goldi 枚金币的价格购买从 starti 到 endi 的所有房屋。
 *
 * 作为一名销售，你需要有策略地选择并销售房屋使自己的收入最大化。
 *
 * 返回你可以赚取的金币的最大数目。
 *
 * 注意 同一所房屋不能卖给不同的买家，并且允许保留一些房屋不进行出售。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
 * 输出：3
 * 解释：
 * 有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
 * 将位于 [0,0] 范围内的房屋以 1 金币的价格出售给第 1 位买家，并将位于 [1,3] 范围内的房屋以 2 金币的价格出售给第 3 位买家。
 * 可以证明我们最多只能获得 3 枚金币。
 * 示例 2：
 *
 * 输入：n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
 * 输出：10
 * 解释：有 5 所房屋，编号从 0 到 4 ，共有 3 个购买要约。
 * 将位于 [0,2] 范围内的房屋以 10 金币的价格出售给第 2 位买家。
 * 可以证明我们最多只能获得 10 枚金币。
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * 1 <= offers.length <= 105
 * offers[i].length == 3
 * 0 <= starti <= endi <= n - 1
 * 1 <= goldi <= 103
 */
public class MaximizeTheProfit7630 {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        List<int[]>[] groups = new ArrayList[n];
        Arrays.setAll(groups, e -> new ArrayList<>());
        for (List<Integer> offer : offers){
            groups[offer.get(1)].add(new int[]{offer.get(0), offer.get(2)});
        }
        int[] f = new int[n + 1];
        for (int end = 0; end < n; end++) {
            f[end + 1] = f[end];
            for (int[] p : groups[end]){
                f[end + 1] = Math.max(f[end + 1], f[p[0]] + p[1]);
            }
        }
        return f[n];
    }

    public static void main(String[] args) {
        MaximizeTheProfit7630 demo = new MaximizeTheProfit7630();
        int n = 5;
        List<List<Integer>> offers = new ArrayList<>();
        offers.add(Arrays.asList(0,0,1));
        offers.add(Arrays.asList(0,2,2));
        offers.add(Arrays.asList(1,3,2));
        System.out.println(demo.maximizeTheProfit(n, offers));
    }
}
