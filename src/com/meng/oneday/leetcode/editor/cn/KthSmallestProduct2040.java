package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class KthSmallestProduct2040 {
    /**
     * 超时
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long kthSmallestProduct2040(int[] nums1, int[] nums2, long k) {
        List<Long> list = new ArrayList<>();
        for(int num1 : nums1){
            for(int num2 : nums2){
                list.add((long)num1 * num2);
            }
        }
        Collections.sort(list);
        return list.get((int)k - 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:60 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.2 MB,击败了76.00% 的Java用户
     * @param a
     * @param b
     * @param k
     * @return
     */
    public long kthSmallestProduct(int[] a, int[] b, long k) {
        int i0 = lowerBound(a, 0); // 四个区域的水平分界线
        int j0 = lowerBound(b, 0); // 四个区域的垂直分界线

        int n = a.length;
        int m = b.length;
        List<Long> corners = Arrays.asList((long) a[0] * b[0], (long) a[0] * b[m - 1], (long) a[n - 1] * b[0], (long) a[n - 1] * b[m - 1]);
        long left = Collections.min(corners) - 1;
        long right = Collections.max(corners);

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (check(a, b, i0, j0, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] a, int[] b, int i0, int j0, long k, long mx) {
        int n = a.length;
        int m = b.length;
        long cnt = 0;

        if (mx < 0) {
            // 右上区域
            int i = 0;
            int j = j0;
            while (i < i0 && j < m) { // 不判断 cnt < k 更快
                if ((long) a[i] * b[j] > mx) {
                    j++;
                } else {
                    cnt += m - j;
                    i++;
                }
            }

            // 左下区域
            i = i0;
            j = 0;
            while (i < n && j < j0) {
                if ((long) a[i] * b[j] > mx) {
                    i++;
                } else {
                    cnt += n - i;
                    j++;
                }
            }
        } else {
            // 右上区域和左下区域的所有数都 <= 0 <= mx
            cnt = (long) i0 * (m - j0) + (long) (n - i0) * j0;

            // 左上区域
            int i = 0;
            int j = j0 - 1;
            while (i < i0 && j >= 0) {
                if ((long) a[i] * b[j] > mx) {
                    i++;
                } else {
                    cnt += i0 - i;
                    j--;
                }
            }

            // 右下区域
            i = i0;
            j = m - 1;
            while (i < n && j >= j0) {
                if ((long) a[i] * b[j] > mx) {
                    j--;
                } else {
                    cnt += j - j0 + 1;
                    i++;
                }
            }
        }

        return cnt >= k;
    }

    // 见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
