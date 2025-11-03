package com.meng.top100.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

class SnakesAndLadders_909 {
    /**
     * 有误
     * @param board
     * @return
     */
    public int snakesAndLadders909(int[][] board) {
        int n = board.length;
        int target = n * n ;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        int step = 0;
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()){
            step++;
            int size = queue.size();
            while (size > 0){
                Integer index = queue.poll();
                for(int i = index + 1 ; i <= Math.min(index + 6,target) ; i++){
                    if (!visited[i]){
                        int num = i;
                        visited[num] = true;
                        int[] pos = findJudge(i , board);
                        if (board[pos[0]][pos[1]] == -1){
                            queue.add(num);
                        }
                        while (board[pos[0]][pos[1]] != -1){
                            num = board[pos[0]][pos[1]];
                            if (visited[num]){
                                break;
                            }
                            visited[num] = true;
                            pos = findJudge(num , board);
                            if (board[pos[0]][pos[1]] == -1){
                                queue.add(num);
                            }
                        }
                    }
                    if (visited[target]){
                        return step;
                    }
                }
                size--;
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
