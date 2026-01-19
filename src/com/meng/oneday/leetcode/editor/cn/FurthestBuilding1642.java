package com.meng.oneday.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

class FurthestBuilding1642 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了60.96% 的Java用户
     * 	内存消耗:72 MB,击败了38.50% 的Java用户
     * @param heights
     * @param bricks
     * @param ladders
     * @return
     */
    public int furthestBuilding1642(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        for(int i = 1 ; i < heights.length ; i++){
            int diff = heights[i] - heights[i-1];
            if (diff > 0){
                queue.add(diff);
                if (queue.size() > ladders){
                    bricks -= queue.poll();
                    if (bricks < 0){
                        return i - 1;
                    }
                }
            }
        }
        return heights.length -1;
    }




    int res = 0;
    boolean[][][] visited;
    public int furthestBuildingTimeOut(int[] heights, int bricks, int ladders) {
        visited = new boolean[heights.length][bricks+1][ladders+1];
        dfs(heights,bricks,ladders,0);
        return res;
    }

    private void dfs(int[] heights, int bricks, int ladders, int index) {
        res = Math.max(index,res);
        if (res == heights.length-1){
            return;
        }
        if (visited[index][bricks][ladders]){
            return;
        }
        visited[index][bricks][ladders] = true;
        if (heights[index] >= heights[index+1]){
            dfs(heights,bricks,ladders,index+1);
        }else{
            //使用梯子
            if (ladders > 0){
                dfs(heights,bricks,ladders-1,index+1);
            }
            //使用砖块
            if (bricks >= heights[index+1] - heights[index]){
                dfs(heights,bricks-(heights[index+1] - heights[index]),ladders,index+1);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了41.80% 的Java用户
     * 	内存消耗:72.2 MB,击败了25.93% 的Java用户
     * @param heights
     * @param bricks
     * @param ladders
     * @return
     */
    public int furthestBuildingOther(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int ans = 0;
        for(int i = 1;i < heights.length;i++){
            int diff = heights[i] - heights[i-1];
            if(diff <= 0) continue;
            else{
                pq.offer(diff);
                bricks -= diff;
                if(bricks < 0){
                    bricks += pq.poll();
                    ladders--;
                }
                if(ladders < 0) return i-1;
            }
        }
        return heights.length-1;
    }

}
