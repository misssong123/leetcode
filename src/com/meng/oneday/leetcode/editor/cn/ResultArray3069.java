package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class ResultArray3069 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.9 MB,击败了82.35% 的Java用户
     * @param nums
     * @return
     */
    public int[] resultArray3069(int[] nums) {
        int len = nums.length;
        int[] arr1 =  new int[len];
        int[] arr2 =  new int[len];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < len; i++) {
            if(i == 0){
                arr1[index1++] = nums[i];
            }else if(i == 1){
                arr2[index2++] = nums[i];
            }else{
                if (arr1[index1-1] > arr2[index2-1]){
                    arr1[index1++] = nums[i];
                }else{
                    arr2[index2++] = nums[i];

                }
            }
        }
        int[] res = new int[len];
        int index = 0;
        for (int i = 0; i < index1; i++) {
            res[index++] = arr1[i];
        }
        for (int i = 0; i < index2; i++) {
            res[index++] = arr2[i];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了76.47% 的Java用户
     * 	内存消耗:46.1 MB,击败了50.00% 的Java用户
     * @param nums
     * @return
     */
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        a.add(nums[0]);
        b.add(nums[1]);
        for (int i = 2; i < n; i++) {
            if (a.get(a.size() - 1) > b.get(b.size() - 1)) {
                a.add(nums[i]);
            } else {
                b.add(nums[i]);
            }
        }
        a.addAll(b);
        for (int i = 0; i < n; i++) {
            nums[i] = a.get(i);
        }
        return nums;
    }

}
