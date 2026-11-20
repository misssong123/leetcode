package com.meng.oneday.leetcode.editor.cn;

class NearestDrone4024 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了69.09% 的Java用户
     * @param drones
     * @param target
     * @return
     */
    public int nearestDrone(int[][] drones, int[] target) {
        int res = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < drones.length; i++) {
            int dis = Math.abs(target[0] - drones[i][0]) + Math.abs(target[1] - drones[i][1]);
            if(dis < min && dis <= drones[i][2]){
                min = dis;
                res = i;
            }
        }
        return res;
    }
}
