package com.meng.oneday.leetcode.editor.cn;

class GetCommon2540 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:79.1 MB,击败了11.26% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int getCommon2540(int[] nums1, int[] nums2) {
        int l1 = nums1.length , l2 = nums2.length;
        int i = 0 , j = 0;
        while (i < l1 && j < l2){
            if (nums1[i] == nums2[j]){
                return nums1[i];
            }else if (nums1[i] < nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        return -1;
    }
}
