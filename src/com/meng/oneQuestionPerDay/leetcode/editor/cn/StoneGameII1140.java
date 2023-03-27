package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class StoneGameII1140 {
    /**
     * 没有思路，变量太多，无法进行动态规划的求解思路
     * @param piles
     * @return
     */
    public int stoneGameII(int[] piles) {
        return 0;
    }

    /**
     * 思考1
     * s[]为后缀和数组
     * 先手拿走[i,i+x-1]对石子
     * 对手最优解为[i+x,len-1]所有可能性中的最大值
     * 因此先手拿走的数据应该为对手所有可能性[i+x,len-1]中的最小值，
     * 先手能拿到的最优数据为s[i]-Min(dfs(i+x,Math.max(M,X)))
     * @param piles
     * @return
     * 超时
     */
    int [] res ;
    public int stoneGameIIThink01(int[] piles) {
        res = piles;
        //计算后缀和
        for(int i = piles.length - 2 ; i >=0 ; i--){
            res[i] += res[i+1];
        }
        return dfs(0,1);
    }

    private int dfs(int i, int m) {
        if (i + m*2 >= res.length){//如果当前下标能够拿走所有的棋子就直接拿走
            return res[i];
        }
        int mn = Integer.MAX_VALUE;
        for(int j = 1 ; j <= m * 2 ; j++){
            mn = Math.min(mn,dfs(i+j,Math.max(j,m)));
        }
        return res[i] - mn;
    }

    /**
     * 执行用时：
     * 36 ms
     * , 在所有 Java 提交中击败了
     * 5.11%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 5.47%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     * 利用存储中间结果做数据优化
     * @param piles
     * @return
     */
    int [] cache ;
    Map<String,Integer> middle = new HashMap<>();
    public int stoneGameIIThink02(int[] piles) {
        cache = piles;
        //计算后缀和
        for(int i = piles.length - 2 ; i >=0 ; i--){
            cache[i] += cache[i+1];
        }
        return dfs2(0,1);
    }
    private int dfs2(int i, int m) {
        if (i + m*2 >= cache.length){//如果当前下标能够拿走所有的棋子就直接拿走
            return cache[i];
        }
        if (middle.get(i+","+m)!=null){
            return middle.get(i+","+m);
        }
        int mn = Integer.MAX_VALUE;
        for(int j = 1 ; j <= m * 2 ; j++){
            mn = Math.min(mn,dfs2(i+j,Math.max(j,m)));
        }
        middle.put(i+","+m,cache[i]-mn);
        return cache[i] - mn;
    }
}

