package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class LargestSubMatrix1727 {
    /*
    * 思路有误
    * 此方法实现有误，未完成正确的逻辑实现
    * */
    public int largestSubmatrixError(int[][] matrix) {
        //记录从0到尾，从尾到0连续一的个数
        PriorityQueue<Integer> sQueue = new PriorityQueue<>();
        PriorityQueue<Integer> bQueue = new PriorityQueue<>();
        int m = matrix.length , n = matrix[0].length;
        //初始化
        for(int i = 0 ; i < n ; i++){
            int cnt = 0;
            for (int[] ints : matrix) {
                if (ints[i] == 0) {
                    sQueue.add(cnt);
                    break;
                }
                cnt++;
            }
            if (cnt == m) {
                bQueue.add(cnt);
                continue;
            }
            cnt = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[j][i] == 0) {
                    bQueue.add(cnt);
                    break;
                }
                cnt++;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:86 ms,击败了14.29% 的Java用户 输入的二维矩阵
     * 	内存消耗:116.3 MB,击败了9.52% 的Java用户 返回最大子矩阵的面积
     * @param matrix
     * @return
     */
    public int largestSubmatrix1727(int[][] matrix) {
        //按照起始点/长度排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{ // 按长度降序排序
            if (a[0] == b[0]){
                return b[1] - a[1]; // 按起始点升序排序
            }
            return a[0] - b[0];
        });
        int m = matrix.length , n = matrix[0].length;
        //初始化
        for(int i = 0 ; i < n ; i++){
            int cnt = 0;
            for (int j = 0; j < m ; j++) {
                if (matrix[j][i] == 0 ) { // 添加连续1的子数组
                    if (cnt != 0){
                        queue.add(new int[]{j - cnt,cnt});
                    }
                    cnt = 0;
                    continue;
                }
                cnt++;
            } // 添加到末尾的连续1
            if (cnt != 0){
                queue.add(new int[]{m - cnt,cnt});
            }
        }
        int res = 0;
        for(int i = 0 ; i < m ; i++){
            int width = 1;
            int temp  = 0;
            while (!queue.isEmpty() && queue.peek()[0] <= i){ // 计算当前宽度下的最大面积
                int[] poll = queue.poll();
                temp = Math.max(temp,poll[1] * width);
                width++; // 减少连续1的长度
                if (poll[1] > 1){
                    queue.add(new int[]{poll[0] + 1,poll[1] - 1});
                } // 更新最大面积
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了23.81% 的Java用户 输入的二维矩阵
     * 	内存消耗:111.5 MB,击败了66.67% 的Java用户 返回最大子矩阵的面积
     * @param matrix
     * @return
     */
    public int largestSubmatrixOther(int[][] matrix) { // 记录每列的高度
        int n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;

        for (int[] row : matrix) { // 枚举子矩形的底边
            for (int j = 0; j < n; j++) { // 遇到0重置高度
                if (row[j] == 0) {
                    heights[j] = 0; // 高度增加
                } else {
                    heights[j]++;
                }
            } // 复制高度数组
 // 排序高度数组
            int[] hs = heights.clone();
            Arrays.sort(hs);
            for (int i = 0; i < n; i++) { // 把 [i,n-1] 作为子数组
                // 子数组长为 n-i，最小值为 hs[i]，对应的子矩形面积为 (n-i)*hs[i]
                ans = Math.max(ans, (n - i) * hs[i]);
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:110.7 MB,击败了100.00% 的Java用户
     * @param matrix
     * @return
     */
    public int largestSubmatrix(int[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n];
        int[] idx = new int[n]; // 按照高度排序后的列号
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        int[] nonZeros = new int[n]; // 避免在循环内反复申请内存
        int ans = 0;

        for (int[] row : matrix) {
            int p = 0;
            int q = 0;
            for (int j : idx) {
                if (row[j] == 0) {
                    heights[j] = 0;
                    idx[p++] = j; // 高度 0 排在前面
                } else {
                    heights[j]++;
                    nonZeros[q++] = j;
                }
            }

            // heights[idx[i]] 是递增的
            for (int i = p; i < n; i++) { // 高度 0 无需计算
                idx[i] = nonZeros[i - p]; // 把 nonZeros 复制到 idx 的 [p,n-1] 中
                ans = Math.max(ans, (n - i) * heights[idx[i]]);
            }
        }

        return ans;
    }
}
