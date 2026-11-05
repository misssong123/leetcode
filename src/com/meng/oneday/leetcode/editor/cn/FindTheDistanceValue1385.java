package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class FindTheDistanceValue1385 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.4 MB,击败了95.07% 的Java用户
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValue1385(int[] arr1, int[] arr2, int d) {
        int[] cnts = new int[2001];
        for (int arr : arr2){
            cnts[getIndex(arr)]++;
        }
        //前缀和
        int[] pre = new int[2002];
        for (int i = 0; i < 2001; i++){
            pre[i+1] = pre[i] + cnts[i];
        }
        //计算符合条件的数据
        int res = 0;
        for (int arr : arr1){
            int left = Math.max(-1000, arr - d);
            int right = Math.min(1000, arr + d);
            int cnt = pre[getIndex(right)+1] - pre[getIndex(left)];
            if (cnt == 0){
                res++;
            }
        }
        return res;
    }
    private int getIndex(int num){
        return num + 1000;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了86.00% 的Java用户
     * 	内存消耗:46.1 MB,击败了27.42% 的Java用户
     * @param arr1
     * @param arr2
     * @param d
     * @return
     */
    public int findTheDistanceValueOther(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x - d);
            if (i < 0) {
                i = ~i; // -i - 1
            }
            if (i == arr2.length || arr2[i] > x + d) {
                ans++;
            }
        }
        return ans;
    }
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int ans = 0;
        int j = 0;
        for (int x : arr1) {
            while (j < arr2.length && arr2[j] < x - d) {
                j++;
            }
            if (j == arr2.length || arr2[j] > x + d) {
                ans++;
            }
        }
        return ans;
    }

}
