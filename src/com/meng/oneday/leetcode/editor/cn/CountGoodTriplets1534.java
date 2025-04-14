package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountGoodTriplets1534 {
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了72.15% 的Java用户
     * 	内存消耗:40.4 MB,击败了79.71% 的Java用户
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTriplets1534(int[] arr, int a, int b, int c) {
        int res = 0;
        for(int i = 0 ; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                for(int k = j+1; k < arr.length; k++){
                    if (Math.abs(arr[i] - arr[j]) <= a &&
                            Math.abs(arr[j] - arr[k]) <= b &&
                            Math.abs(arr[i] - arr[k]) <= c){
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 前缀和
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了49.25% 的Java用户
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTripletsPre(int[] arr, int a, int b, int c) {
        int mx = 0;
        for (int x : arr) {
            mx = Math.max(mx, x);
        }
        int[] s = new int[mx + 2]; // cnt 数组的前缀和
        int ans = 0;
        for (int j = 0; j < arr.length; j++) {
            int y = arr[j];
            for (int k = j + 1; k < arr.length; k++) {
                int z = arr[k];
                if (Math.abs(y - z) > b) {
                    continue;
                }
                int l = Math.max(Math.max(y - a, z - c), 0);
                int r = Math.min(Math.min(y + a, z + c), mx);
                ans += Math.max(s[r + 1] - s[l], 0); // 如果 l > r + 1，s[r + 1] - s[l] 可能是负数
            }
            for (int v = y + 1; v < s.length; v++) {
                s[v]++; // 把 y 加到 cnt 数组中，更新所有受到影响的前缀和
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了72.15% 的Java用户
     * 	内存消耗:43.4 MB,击败了5.45% 的Java用户
     * @param arr
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        Integer[] idx = new Integer[arr.length];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);

        int ans = 0;
        for (int j : idx) {
            int y = arr[j];
            List<Integer> left = new ArrayList<>();
            for (int i : idx) {
                if (i < j && Math.abs(arr[i] - y) <= a) {
                    left.add(arr[i]);
                }
            }

            List<Integer> right = new ArrayList<>();
            for (int k : idx) {
                if (k > j && Math.abs(arr[k] - y) <= b) {
                    right.add(arr[k]);
                }
            }
            int k1 = 0;
            int k2 = 0;
            for (int x : left) {
                while (k2 < right.size() && right.get(k2) <= x + c) {
                    k2++;
                }
                while (k1 < right.size() && right.get(k1) < x - c) {
                    k1++;
                }
                ans += k2 - k1;
            }
        }
        return ans;
    }
}
