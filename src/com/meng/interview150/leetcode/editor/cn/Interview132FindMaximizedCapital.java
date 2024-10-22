package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Interview132FindMaximizedCapital {
    /**
     * 1.构造基础数据，按照起始花费进行排序
     * 2.生成构建当前可以完成的数据，并且按照利润进行排序
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     * 解答成功:
     * 	执行耗时:164 ms,击败了5.39% 的Java用户
     * 	内存消耗:60.2 MB,击败了37.95% 的Java用户
     */
    public int findMaximizedCapitalMy(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> baseData = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<int[]> maxProfit = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int len = profits.length;
        for(int i = 0 ; i < len ; i++){
            baseData.add(new int[]{profits[i],capital[i]});
        }
        while (k > 0){
            //构建当前所能完成的项目
            while(!baseData.isEmpty()&&baseData.peek()[1] <= w){
                maxProfit.add(baseData.poll());
            }
            if(!maxProfit.isEmpty()&&maxProfit.peek()[0] > 0){
                w += maxProfit.poll()[0];
            }else {
                break;
            }
            k--;
        }
        return w;
    }

    /**
     * 解答成功:
     * 	执行耗时:114 ms,击败了30.70% 的Java用户
     * 	内存消耗:57.5 MB,击败了53.05% 的Java用户
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }

        return w;
    }

}
