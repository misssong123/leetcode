package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

class AdvantageCount870 {
    /**
     * 解答成功:
     * 	执行耗时:70 ms,击败了52.03% 的Java用户
     * 	内存消耗:75.9 MB,击败了7.32% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount870(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int [] res = new int[len];
        Arrays.sort(nums1);
        Integer[] indexs = new Integer[len];
        for (int i = 0; i < len; i++) {
            indexs[i] = i;
        }
        Arrays.sort(indexs, Comparator.comparingInt(a -> nums2[a]));
        int left = 0, right = len - 1;
        for(int i = len - 1 ; i >= 0 ; i--){
            if(nums1[right] > nums2[indexs[i]]){
                res[indexs[i]] = nums1[right--];
            }else{
                res[indexs[i]] = nums1[left++];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:68 ms,击败了54.47% 的Java用户
     * 	内存消耗:72.1 MB,击败了13.82% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        int n = nums1.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> nums2[i] - nums2[j]);

        int[] ans = new int[n];
        int left = 0;
        int right = n - 1;
        for (int x : nums1) {
            int i = x > nums2[idx[left]] ? idx[left++] : idx[right--];
            ans[i] = x;
        }
        return ans;
    }

}
