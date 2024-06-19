package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxIncreasingCells2713 {
    int res = 0;
    int[][] temp;
    Map<Integer,Integer> cache;
    Map<String,int[][]>dp= new HashMap<>();
    int n ,m;

    /**
     * 超时
     * @param mat
     * @return
     */
    public int maxIncreasingCellsMy(int[][] mat) {
        cache = new HashMap<>();
        temp = mat;
        n = mat.length;
        m=mat[0].length;
        for(int i = 0 ; i < n ; i ++ ){
            for (int j = 0 ; j < m ; j++){
                int num = dfs(i,j);
                res = Math.max(res,num);
                if (res == n * m){
                    return res;
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j) {
        if (cache.containsKey(i*m+j)){
            return cache.get(i*m+j);
        }
        //寻找比当前下标大的元素
        List<int[]> nums = findBigNum(i,j);
        if (nums.size() == 0){
            cache.put(i*m+j,1);
            return 1;
        }
        int t = 0;
        for(int[] num : nums){
            t = Math.max(t,dfs(num[0],num[1]));
        }
        cache.put(i*m+j,t+1);
        return t+1;
    }

    private List<int[]> findBigNum(int x ,int y) {
        List<int[]> list = new ArrayList<>();
        //寻找x轴比当前下标大的元素
        int[][] dpX = dp.get("x:"+x);
        if (dpX==null){
            int[][] tempNum = new int[m][2];
            for (int i = 0 ; i < m ; i ++){
                tempNum[i][0] = temp[x][i];
                tempNum[i][1] = i;
            }
            Arrays.sort(tempNum, Comparator.comparingInt(o -> o[0]));
            dpX = tempNum;
            dp.put("x:"+x,dpX);
        }
        int xIndex = highBound(dpX, temp[x][y]);
        if (xIndex != -1){
            for(int i = xIndex ; i < m ; i ++){
                list.add(new int[]{x,dpX[i][1]});
            }
        }
        //寻找y轴比当前下标大的元素
        if (n>1){
            int[][] dpY = dp.get("y:"+y);
            if (dpY==null){
                int[][] tempNum = new int[n][2];
                for (int i = 0 ; i < n ; i ++){
                    tempNum[i][0] = temp[i][y];
                    tempNum[i][1] = i;
                }
                Arrays.sort(tempNum, Comparator.comparingInt(o -> o[0]));
                dpY = tempNum;
                dp.put("y:"+y,dpY);
            }
            int yIndex = highBound(dpY, temp[x][y]);
            if (yIndex != -1){
                for(int i = yIndex ; i < n ; i ++){
                    list.add(new int[]{dpY[i][1],y});
                }
            }
        }
        return list;
    }
    public static int highBound(int[][] nums, int val) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right - left)/ 2;
            if (nums[mid][0]>val){
                right = mid - 1;
            }else {
                left = mid+1;
            }
        }
        return left==nums.length?-1:left;
    }

    /**
     * 解答成功:
     * 	执行耗时:189 ms,击败了80.00% 的Java用户
     * 	内存消耗:93.9 MB,击败了36.67% 的Java用户
     * @param mat
     * @return
     */
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        TreeMap<Integer, List<int[]>> g = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 相同元素放在同一组，统计位置
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        int ans = 0;
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        for (List<int[]> pos : g.values()) {
            // 先把所有 f 值都算出来，再更新 rowMax 和 colMax
            int[] fs = new int[pos.size()];
            for (int k = 0; k < pos.size(); k++) {
                int[] p = pos.get(k);
                int i = p[0];
                int j = p[1];
                fs[k] = Math.max(rowMax[i], colMax[j]) + 1;
                ans = Math.max(ans, fs[k]);
            }
            for (int k = 0; k < pos.size(); k++) {
                int[] p = pos.get(k);
                int i = p[0];
                int j = p[1];
                rowMax[i] = Math.max(rowMax[i], fs[k]); // 更新第 i 行的最大 f 值
                colMax[j] = Math.max(colMax[j], fs[k]); // 更新第 j 列的最大 f 值
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
