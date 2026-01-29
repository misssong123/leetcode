package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class MinimumCost2976 {
    public long minimumCostErr(String source, String target,
                            char[] original, char[] changed, int[] cost) {
        int[][] dp = new int[26][26];
        for(int[] d : dp){
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0 ; i < original.length ; i++){
            dp[original[i]-'a'][changed[i]-'a'] = Math.min(cost[i], dp[original[i]-'a'][changed[i]-'a']);
        }
        boolean[][] visited = new boolean[26][26];
        for(int i = 0 ; i < 26 ; i++){
            fill(dp,visited[i],i);
        }
        int res = 0;
        for(int i = 0 ; i < source.length() ; i++){
            if (source.charAt(i) != target.charAt(i)){
                int x = source.charAt(i) - 'a';
                int y = target.charAt(i) - 'a';
                if (dp[x][y] == Integer.MAX_VALUE){
                    return -1;
                }else{
                    res += dp[x][y];
                }
            }
        }
        return res;
    }

    private void fill(int[][] dp, boolean[] flags, int i) {
        flags[i] = true;
        dp[i][i] = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0 ; j < 26 ; j++){
            if (dp[i][j] != Integer.MAX_VALUE){
                queue.offer(j);
                flags[j] = true;
            }
        }
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (int j = 0 ; j < 26 ; j++){
                if (dp[cur][j] != Integer.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j],dp[cur][j] + dp[i][cur]);
                    if (!flags[j]){
                        queue.offer(j);
                        flags[j] = true;
                    }
                }
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了50.00% 的Java用户
     * 	内存消耗:47.2 MB,击败了17.39% 的Java用户
     * @param source
     * @param target
     * @param original
     * @param changed
     * @param cost
     * @return
     */
    public long minimumCost2976(String source, String target,
                            char[] original, char[] changed, int[] cost) {
        long[][] dp = new long[26][26];
        for(int i = 0 ; i < 26 ; i++ ){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        for (int i = 0 ; i < original.length ; i++){
            int x = original[i]-'a';
            int y = changed[i]-'a';
            dp[x][y] = Math.min(cost[i], dp[x][y]);
        }

        for(int j = 0 ; j < 26 ; j++){
            for(int i = 0 ; i < 26 ; i++){
                if (dp[i][j] == Integer.MAX_VALUE){
                    continue;
                }
                for (int k = 0 ; k < 26 ; k++){
                    if(dp[j][k] == Integer.MAX_VALUE){
                        continue;
                    }
                    dp[i][k] = Math.min(dp[i][k], dp[i][j]+dp[j][k]);
                }
            }
        }

        long res = 0;
        for(int i = 0 ; i < source.length() ; i++){
            if (source.charAt(i) != target.charAt(i)){
                int x = source.charAt(i) - 'a';
                int y = target.charAt(i) - 'a';
                if (dp[x][y] == Integer.MAX_VALUE){
                    return -1;
                }else{
                    res += dp[x][y];
                }
            }
        }
        return res;
    }

    private static final int INF = Integer.MAX_VALUE / 2;

    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了47.83% 的Java用户
     * 	内存消耗:47.3 MB,击败了13.04% 的Java用户
     * @param source
     * @param target
     * @param original
     * @param changed
     * @param cost
     * @return
     */
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] G = new int[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(G[i], INF);
            G[i][i] = 0;
        }

        int m = original.length;
        for (int i = 0; i < m; i++) {
            int idx = original[i] - 'a';
            int idy = changed[i] - 'a';
            G[idx][idy] = Math.min(G[idx][idy], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (G[i][k] != INF && G[k][j] != INF) {
                        G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]);
                    }
                }
            }
        }

        int n = source.length();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int idx = source.charAt(i) - 'a';
            int idy = target.charAt(i) - 'a';
            if (G[idx][idy] == INF) {
                return -1;
            }
            ans += G[idx][idy];
        }

        return ans;
    }

}
