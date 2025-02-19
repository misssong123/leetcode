package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class MaxDistance624 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了51.41% 的Java用户
     * 	内存消耗:63.3 MB,击败了56.84% 的Java用户
     * @param arrays
     * @return
     */
    public int maxDistanceMy(List<List<Integer>> arrays) {
        int[][] min = new int[2][2];
        int[][] max = new int[2][2];
        //初始化
        min[0][0] = arrays.get(0).get(0);
        min[1][0] = Integer.MAX_VALUE;
        max[0][0] = arrays.get(0).get(arrays.get(0).size()-1);
        max[1][0] = Integer.MIN_VALUE;
        for(int i = 1 ; i < arrays.size();i++){
            int curMix = arrays.get(i).get(0);
            int curMax = arrays.get(i).get(arrays.get(i).size()-1);
            //更新最小值
            if(curMix <= min[0][0]){
                min[1][0] = min[0][0];
                min[1][1] = min[0][1];
                min[0][0] = curMix;
                min[0][1] = i;
            }else if(curMix <= min[1][0]){
                min[1][0] = curMix;
                min[1][1] = i;
            }
            //更新最大值
            if(curMax >= max[0][0]){
                max[1][0] = max[0][0];
                max[1][1] = max[0][1];
                max[0][0] = curMax;
                max[0][1] = i;
            }else if(curMax >= max[1][0]){
                max[1][0] = curMax;
                max[1][1] = i;
            }
        }
        //计算最大距离
        if(min[0][1] != max[0][1]){
            return max[0][0] - min[0][0];
        }
        return Math.max(max[0][0] - min[1][0],max[1][0] - min[0][0]);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了73.97% 的Java用户
     * 	内存消耗:62.8 MB,击败了87.42% 的Java用户
     * @param arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int mn = Integer.MAX_VALUE / 2; // 防止减法溢出
        int mx = Integer.MIN_VALUE / 2;
        for (List<Integer> a : arrays) {
            int x = a.get(0);
            int y = a.get(a.size() - 1);
            ans = Math.max(ans, Math.max(y - mn, mx - x));
            mn = Math.min(mn, x);
            mx = Math.max(mx, y);
        }
        return ans;
    }
}
