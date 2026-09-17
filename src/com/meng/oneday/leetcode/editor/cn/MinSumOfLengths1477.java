package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinSumOfLengths1477 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了43.33% 的Java用户
     * 	内存消耗:86.3 MB,击败了98.89% 的Java用户
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengths1477(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int len = arr.length;
        int[] lensArr = new int[len];
        int[] rightMin = new int[len];
        Arrays.fill(rightMin,Integer.MAX_VALUE);
        for (int i = 0 ; i < len; i++) {
            sum += arr[i];
            while (left <= i && sum > target) {
                sum -= arr[left++];
            }
            //记录
            if (sum == target) {
                lensArr[left] = i - left + 1;
            }
        }
        rightMin[len - 1] = lensArr[len - 1] > 0 ? lensArr[len - 1] : Integer.MAX_VALUE;
        //记录右侧最小值
        for (int i = len - 2; i >= 0; i--) {
            rightMin[i] = Math.min(lensArr[i] > 0 ?lensArr[i]:Integer.MAX_VALUE, rightMin[i + 1]);
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++) {
            if (lensArr[i] > 0 && i + lensArr[i] < len && rightMin[i + lensArr[i]] < Integer.MAX_VALUE) {
                ans = Math.min(ans, lensArr[i] + rightMin[i + lensArr[i]]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了47.78% 的Java用户
     * 	内存消耗:89.1 MB,击败了82.22% 的Java用户
     * @param arr
     * @param target
     * @return
     */
    public int minSumOfLengthsOther(int[] arr, int target) {
        int n = arr.length;
        // sufMin[i] 表示左端点 >= i 的和为 target 的最短子数组长度
        // 不存在子数组时，长度设为 n+1
        int[] sufMin = new int[n];
        int minLen = n + 1;
        int sum = 0;
        int r = n - 1;
        for (int l = n - 1; l > 0; l--) {
            sum += arr[l];
            while (sum > target) {
                sum -= arr[r];
                r--;
            }
            if (sum == target) {
                minLen = Math.min(minLen, r - l + 1); // 维护遍历过的和为 target 的子数组的最短长度
            }
            sufMin[l] = minLen; // 保存此时此刻的 minLen
        }

        int ans = n + 1;
        sum = 0;
        int l = 0;
        for (r = 0; r < n - 1; r++) {
            sum += arr[r];
            while (sum > target) {
                sum -= arr[l];
                l++;
            }
            if (sum == target) {
                ans = Math.min(ans, r - l + 1 + sufMin[r + 1]);
            }
        }

        return ans > n ? -1 : ans;
    }

}
