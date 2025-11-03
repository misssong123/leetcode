package com.meng.top100.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class SnakesAndLadders_909 {
    /**
     * 解答成功:
     * 执行耗时:4 ms,击败了89.43% 的Java用户
     * 内存消耗:46.04 MB,击败了5.03% 的Java用户
     * @param board
     * @return
     */
    public int snakesAndLadders909(int[][] board) {
        int n = board.length;
        int target = n * n;
        boolean[] visited = new boolean[target + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1,0});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            visited[cur[0]] = true;
            for(int i = cur[0] + 1 ;i <= Math.min(cur[0] + 6,target);i++){
                int[] next = findJudge(i,board);
                int num = board[next[0]][next[1]];
                if (num == -1){
                    num = i;
                }
                if (num == target){
                    return cur[1] + 1;
                }
                if (!visited[num]){
                    queue.offer(new int[]{num,cur[1] + 1});
                }
            }
        }
        return -1;
    }
    public int[]  findJudge(int num ,int[][] board){
        int n = board.length;
        int x = n -(num - 1) / n - 1 ;
        int y = (num - 1 ) % n;
        if ((n -1 - x) % 2 != 0){
            y = n - 1 - y;
        }
        return new int[]{x,y};
    }

    /**
     * 解答成功:
     * 执行耗时:3 ms,击败了98.91% 的Java用户
     * 内存消耗:45.70 MB,击败了5.03% 的Java用户
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true;
        int[] q = new int[n * n];
        int head = 0;
        int tail = 0;
        q[tail++] = 1; // 起点
        for (int step = 0; head < tail; step++) {
            for (int size = tail - head; size > 0; size--) {
                int x = q[head++];
                if (x == n * n) {
                    return step; // 终点
                }
                for (int y = x + 1; y <= Math.min(x + 6, n * n); y++) {
                    int r = (y - 1) / n;
                    int c = (y - 1) % n;
                    if (r % 2 > 0) {
                        c = n - 1 - c; // 奇数行从右到左
                    }
                    int nxt = board[n - 1 - r][c];
                    if (nxt < 0) {
                        nxt = y;
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true; // 有环的情况下，避免死循环
                        q[tail++] = nxt;
                    }
                }
            }
        }
        return -1; // 无法到达终点
    }
}
