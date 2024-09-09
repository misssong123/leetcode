package com.meng.interview150.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class Interview095SnakesAndLadders {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了89.34% 的Java用户
     * 	内存消耗:43.8 MB,击败了30.79% 的Java用户
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        //记录长度
        int n = board.length;
        boolean[] visited = new boolean[n*n+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1,0});
        visited[1] = true;
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            for(int i = 1 ; i <= 6 ; i++){
                int next = poll[0] + i;
                //大于最大值，跳出
                if(next > n*n){
                    break;
                }
                int[] nextPos = getPos(next,n);
                if (board[nextPos[0]][nextPos[1]] != -1){
                    next = board[nextPos[0]][nextPos[1]];
                }
                if(next == n*n){
                    return poll[1] + 1;
                }
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(new int[]{next,poll[1]+1});
                }
            }
        }
        return -1;
    }

    private int[] getPos(int next, int n) {
        int newX = (next - 1)/n ,newY = (next - 1)%n;
        //颠倒位置
        if (newX % 2 == 1){
            newY = n - 1 - newY;
        }
        return new int[]{n - 1 - newX, newY};
    }
}
