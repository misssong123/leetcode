package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinOperations2033 {
    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了80.46% 的Java用户
     * 	内存消耗:109.2 MB,击败了45.98% 的Java用户
     * @param grid
     * @param x
     * @return
     */
    public int minOperations2033(int[][] grid, int x) {
        int m = grid.length , n = grid[0].length;
        int[] arr = new int[m*n];
        int index = 0;
        int mod = grid[0][0] % x;
        for (int[] g : grid){
            for(int num : g){
                if (num % x != mod){
                    return -1;
                }
                arr[index++] = num;
            }
        }
        Arrays.sort(arr);
        int sum = 0;
        int mid = arr[(m*n)/2];
        for (int num : arr){
            sum += Math.abs(num - mid);
        }
        return sum / x;
    }
    public int minOperationsError(int[][] grid, int x) {
        int m = grid.length , n = grid[0].length;
        if (m == 1 && n == 1){
            return 0;
        }
        List<Integer> list = new ArrayList<>(m*n);
        for (int[] ints : grid) {
            for (int j : ints) {
                list.add(j);
            }
        }
        list.sort(Integer::compareTo);
        long sum = list.get(0);
        for(int i = 1 ; i < m*n ; i++){
            sum += list.get(i);
            if ((list.get(i) - list.get(0))%x != 0){
                return -1;
            }
        }
        long res = Integer.MAX_VALUE;
        int target =(int) (sum / (m*n));
        int[] pos = findMore(list,target);
        System.out.println(target);
        System.out.println(Arrays.toString(pos));
        System.out.println(sum);
        System.out.println(list);
        long pre = 0;
        for (int i = 0 ; i < pos[0] ; i++){
            pre += list.get(i);
        }
        long suf = sum - pre;
        for (int index : pos){
            //基准
            int prior = list.get(index);
            suf -= prior;
            res = Math.min(res,(int)((prior * index - pre) / x + (suf - prior * (m*n - index - 1)) / x));
            pre += prior;
        }
        return (int)res;
    }

    public int[] findMore(List<Integer> list, int target) {
        int l = 0 , r = list.size() - 1,res = list.size()-1;
        while (l <= r){
            int mid = (l + r) >> 1;
            if (list.get(mid) >= target){
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        if (res == 0){
            return new int[]{res,res+1};
        }else if (res == list.size()-1){
            return new int[]{res-1,res};
        }
        return new int[]{res - 1,res,res+1};
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了86.67% 的Java用户
     * 	内存消耗:108.8 MB,击败了80.00% 的Java用户
     * @param grid
     * @param x
     * @return
     */
    public int minOperationsOther(int[][] grid, int x) {
        int k = grid.length * grid[0].length;
        int[] a = new int[k];
        int idx = 0;
        int target = grid[0][0] % x;

        // 1. 判断是否无解
        for (int[] row : grid) {
            for (int v : row) {
                if (v % x != target) { // 每个数模 x 都必须相等
                    return -1;
                }
                a[idx++] = v;
            }
        }

        // 2. 计算 grid 的中位数 median
        Arrays.sort(a);
        int median = a[k / 2];

        // 3. 计算操作次数
        int ans = 0;
        for (int v : a) {
            ans += Math.abs(v - median);
        }
        return ans / x;
    }

}
