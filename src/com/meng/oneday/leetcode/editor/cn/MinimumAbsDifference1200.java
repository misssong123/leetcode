package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinimumAbsDifference1200 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了78.39% 的Java用户
     * 	内存消耗:62.8 MB,击败了45.34% 的Java用户
     * @param arr
     * @return
     */
    public List<List<Integer>> minimumAbsDifference1200(int[] arr) {
        //排序
        Arrays.sort(arr);
        //寻找最小差值
        int min = Integer.MAX_VALUE;
        for (int i = 1 ; i < arr.length; i++) {
            min = Math.min(min,arr[i] - arr[i-1]);
        }
        List<List<Integer>> res = new ArrayList<>();
        //寻找最小差值的数对
        for (int i = 1 ; i < arr.length; i++) {
            if (arr[i] - arr[i-1] == min){
                res.add(Arrays.asList(arr[i-1],arr[i]));
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了8.05% 的Java用户
     * 	内存消耗:62.6 MB,击败了67.80% 的Java用户
     * @param arr
     * @return
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            int x = arr[i - 1];
            int y = arr[i];
            int diff = y - x;
            if (diff < minDiff) {
                minDiff = diff;
                ans.clear();
                ans.add(Arrays.asList(x, y));
            } else if (diff == minDiff) {
                ans.add(Arrays.asList(x, y));
            }
        }
        return ans;
    }

}
