package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MatchPlayersAndTrainers2410 {
    /**
     * 解答成功:
     * 	执行耗时:32 ms,击败了16.38% 的Java用户
     * 	内存消耗:89 MB,击败了16.38% 的Java用户
     * @param players
     * @param trainers
     * @return
     */
    public int matchPlayersAndTrainers2410(int[] players, int[] trainers) {
        // 1. 安全检查
        if (players == null || trainers == null || players.length == 0 || trainers.length == 0) {
            return 0;
        }
        //排序
        Arrays.sort(players);
        Arrays.sort(trainers);
        int pIndex = 0 , tIndex = 0 ;
        while (pIndex < players.length && tIndex < trainers.length){
            if (players[pIndex] <= trainers[tIndex]){
                pIndex++;
            }
            tIndex++;
        }
        return pIndex;
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了76.27% 的Java用户
     * 	内存消耗:88.8 MB,击败了35.03% 的Java用户
     * @param players
     * @param trainers
     * @return
     */
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int n = players.length;
        int m = trainers.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            int p = players[i];
            while (j < m && trainers[j] < p) {
                j++;
            }
            if (j == m) { // 无法找到匹配的训练师
                return i;
            }
            j++; // 匹配一位训练师
        }
        return n; // 所有运动员都有匹配的训练师
    }

}
