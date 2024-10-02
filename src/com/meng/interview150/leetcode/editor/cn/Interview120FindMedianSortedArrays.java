package com.meng.interview150.leetcode.editor.cn;
class Interview120FindMedianSortedArrays {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了24.18% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0){
            return 0.0;
        }
        //确定目标下标和目标值
        int leftIndex = 0,rightIndex = 0,leftNum = 0,rightNum = 0;
        int n1 = nums1.length,n2 = nums2.length;
        int index1 = 0,index2 = 0;
        int len = nums1.length + nums2.length;
        if (len % 2 == 0){
            leftIndex = len / 2 ;
            rightIndex = leftIndex +1;
        }else {
            leftIndex = rightIndex = (len +1) /2;
        }
        while (index1 < n1 && index2 < n2 && (leftIndex >0 || rightIndex > 0)){
            leftIndex--;
            rightIndex--;
            int num = 0;
            if (nums1[index1] < nums2[index2]){
                num = nums1[index1];
                index1++;
            }else {
                num = nums2[index2];
                index2++;
            }
            if (leftIndex == 0){
                leftNum = num;
            }
            if (rightIndex == 0){
                rightNum = num;
            }
        }
        if (leftIndex <= 0 && rightIndex <= 0){
            return (leftNum + rightNum) / 2.0;
        }
        while (index1 < n1&& (leftIndex >0 || rightIndex >0)){
            leftIndex--;
            rightIndex--;
            if (leftIndex == 0){
                leftNum = nums1[index1];
            }
            if (rightIndex == 0){
                rightNum = nums1[index1];
            }
            index1++;
        }
        while (index2 < n2&& (leftIndex >0 || rightIndex >0)){
            leftIndex--;
            rightIndex--;
            if (leftIndex == 0){
                leftNum = nums2[index2];
            }
            if (rightIndex == 0){
                rightNum = nums2[index2];
            }
            index2++;
        }
        return (leftNum + rightNum) / 2.0;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.7 MB,击败了93.99% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

}
