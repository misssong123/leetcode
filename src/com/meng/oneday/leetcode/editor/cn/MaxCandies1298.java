package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MaxCandies1298 {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了11.48% 的Java用户
     * 	内存消耗:56.4 MB,击败了32.79% 的Java用户
     * @param status
     * @param candies
     * @param keys
     * @param containedBoxes
     * @param initialBoxes
     * @return
     */
    public int maxCandies1298(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        //钥匙集合
        Set<Integer> keysSet = new HashSet<>();
        //盒子集合
        Set<Integer> boxSet = new HashSet<>();
        int res = 0;
        //可以打开的盒子
        List<Integer> openList = new ArrayList<>();
        for (int initialBox : initialBoxes) {
            if (status[initialBox] == 1) {
                openList.add(initialBox);
            } else {
                boxSet.add(initialBox);
            }
        }
        while (!openList.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for(Integer box : openList){
                //添加糖果数量
                res += candies[box];
                //添加钥匙
                for (int key : keys[box]) {
                   keysSet.add(key);
                   //添加可打开的盒子
                   if (boxSet.contains(key)){
                       temp.add(key);
                       boxSet.remove(key);
                   }
                }
                //添加盒子
                for (int containedBox : containedBoxes[box]) {
                    if (keysSet.contains(containedBox)||status[containedBox] == 1){
                        temp.add(containedBox);
                    }else {
                        boxSet.add(containedBox);
                    }
                }
            }
            openList.clear();
            openList.addAll(temp);
            temp.clear();
        }
        return res;
    }
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了87.70% 的Java用户
     * 	内存消耗:56 MB,击败了44.26% 的Java用户
     * @param status
     * @param candies
     * @param keys
     * @param containedBoxes
     * @param initialBoxes
     * @return
     */
    private int ans = 0;
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int[] hasKey = status; // 把开着的盒子当作有钥匙
        boolean[] hasBox = new boolean[status.length];
        for (int x : initialBoxes) {
            hasBox[x] = true;
        }

        for (int x : initialBoxes) {
            if (hasBox[x] && hasKey[x] == 1) { // 注意 dfs 中会修改 hasBox
                dfs(x, candies, keys, containedBoxes, hasKey, hasBox);
            }
        }
        return ans;
    }

    private void dfs(int x, int[] candies, int[][] keys, int[][] containedBoxes, int[] hasKey, boolean[] hasBox) {
        ans += candies[x];
        hasBox[x] = false; // 避免找到钥匙后重新访问开着的盒子

        // 找到钥匙，打开盒子（说明我们先找到盒子，然后找到钥匙）
        for (int y : keys[x]) {
            hasKey[y] = 1;
            if (hasBox[y]) {
                dfs(y, candies, keys, containedBoxes, hasKey, hasBox);
            }
        }

        // 找到盒子，使用钥匙（说明我们先找到钥匙，然后找到盒子）
        for (int y : containedBoxes[x]) {
            hasBox[y] = true;
            if (hasKey[y] == 1) {
                dfs(y, candies, keys, containedBoxes, hasKey, hasBox);
            }
        }
    }

}
