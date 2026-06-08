package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PivotArray2161 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了58.82% 的Java用户
     * 	内存消耗:156.3 MB,击败了82.35% 的Java用户
     * @param nums
     * @param pivot
     * @return
     */
    public int[] pivotArray2161(int[] nums, int pivot) {
       int len = nums.length;
       int[] res = new int[len];
       int l = 0, r = len - 1;
       int indexL = 0 , indexR = len -1;
       while (l < len || r >= 0){
           if (nums[l] < pivot){
               res[indexL++] = nums[l];
           }
           if (nums[r] > pivot){
               res[indexR--] = nums[r];
           }
           l++;
           r--;
       }
       while (indexL <= indexR){
           res[indexL++] = pivot;
       }
       return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了17.65% 的Java用户
     * 	内存消耗:157.4 MB,击败了17.65% 的Java用户
     * @param nums
     * @param pivot
     * @return
     */
    public int[] pivotArrayOther(int[] nums, int pivot) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        for (int x : nums) {
            if (x < pivot) {
                a.add(x);
            } else if (x == pivot) {
                b.add(x);
            } else {
                c.add(x);
            }
        }

        int[] ans = new int[nums.length];
        int idx = 0;
        for (int x : a) ans[idx++] = x;
        for (int x : b) ans[idx++] = x;
        for (int x : c) ans[idx++] = x;
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:156.7 MB,击败了29.41% 的Java用户
     * @param nums
     * @param pivot
     * @return
     */
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, pivot);
        int left = 0, right = n - 1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < pivot) {
                ans[left] = nums[i];
                ++left;
            } else if (nums[i] > pivot) {
                ans[right] = nums[i];
                --right;
            }
        }
        reverse(ans, right + 1, n - 1);
        return ans;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
