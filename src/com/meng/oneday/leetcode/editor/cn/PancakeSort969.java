package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class PancakeSort969 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了25.00% 的Java用户
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort969(int[] arr) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        for(int i = n ; i > 1 ; i--){
            //寻找i的下标
            int index = find(arr,i);
            if (index == i -1){
                continue;
            }
            //反转0-i的下标
            reverse (arr,index);
            ans.add(index+1);
            //反转0-i
            reverse(arr,i-1);
            ans.add(i);
        }
        return ans;
    }

    private void reverse(int[] arr, int end) {
        int start = 0;
        while (start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    private int find(int[] arr, int i) {
        for (int j = i -1; j >= 0; j--) {
            if(arr[j] == i){
                return j;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了28.13% 的Java用户
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        int[] idxs = new int[n + 10];
        for (int i = 0; i < n; i++) idxs[arr[i]] = i;
        List<Integer> ans = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            int idx = idxs[i];
            if (idx == i - 1) continue;
            if (idx != 0) {
                ans.add(idx + 1);
                reverse(arr, 0, idx, idxs);
            }
            ans.add(i);
            reverse(arr, 0, i - 1, idxs);
        }
        return ans;
    }
    void reverse(int[] arr, int i, int j, int[] idxs) {
        while (i < j) {
            idxs[arr[i]] = j; idxs[arr[j]] = i;
            int c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }
    }
}
