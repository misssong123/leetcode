package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class Merge88 {
    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 39.56mb
     * 击败 7.31%使用 Java 的用户
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n -1;
        m--;
        n--;
        while (m >= 0 && n >= 0){
            if (nums1[m] > nums2[n]){
                nums1[index--] = nums1[m--];
            }else {
                nums1[index--] = nums2[n--];
            }
        }
        while (m >= 0){
            nums1[index--] = nums1[m--];
        }
        while (n >= 0){
            nums1[index--] = nums2[n--];
        }
    }

    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 39.47mb
     * 击败 17.02%使用 Java 的用户
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }


}

