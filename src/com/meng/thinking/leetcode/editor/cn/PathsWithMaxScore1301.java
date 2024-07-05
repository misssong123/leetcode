package com.meng.thinking.leetcode.editor.cn;
import java.util.List;
class PathsWithMaxScore1301 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了80.17% 的Java用户
     * 	内存消耗:43.6 MB,击败了52.07% 的Java用户
     * @param board
     * @return
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][][] dp = new int[n][n][2];
        int[][] dirs = {{0,1},{1,0},{1,1}};
        dp[n-1][n-1][0] = 0;
        dp[n-1][n-1][1] = 1;
        int N = 1000000007;
        for (int i = n-1; i >=0;--i) {
            char[] chars = board.get(i).toCharArray();
            for (int j= n-1; j >=0;--j){
                //障碍不计算
                if (chars[j]=='X'||chars[j]=='S'){
                    continue;
                }
                int num = 0;
                if (i!=0||j!=0){
                    num = chars[j]-'0';
                }
                int maxNum =0,maxStep = 0;
                for (int k = 0; k < 3; ++k) {
                    int x =  i + dirs[k][0];
                    int y =  j + dirs[k][1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        if(dp[x][y][0]>=maxNum){
                            maxStep = ((dp[x][y][0] == maxNum)?maxStep + dp[x][y][1]:dp[x][y][1])%N;
                            maxNum = dp[x][y][0];

                        }
                    }
                }
                if (maxStep>0){
                    dp[i][j][0] = maxNum+num;
                    dp[i][j][1] = maxStep;
                }
            }
        }
        return dp[0][0];
    }
}
