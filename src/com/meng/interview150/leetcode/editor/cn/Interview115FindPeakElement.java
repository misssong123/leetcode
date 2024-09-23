package com.meng.interview150.leetcode.editor.cn;

class Interview105FindPeakElement {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了61.71% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElementMy(int[] nums) {
        if (nums.length == 1||nums[0] > nums[1]) {
            return 0;
        }
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                return i;
            }
        }
        return nums.length-1;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了2.03% 的Java用户
     * 	内存消耗:41.9 MB,击败了5.55% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        int idx = (int) (Math.random() * n);

        while (!(compare1(nums, idx - 1, idx) < 0 && compare1(nums, idx, idx + 1) > 0)) {
            if (compare1(nums, idx, idx + 1) < 0) {
                idx += 1;
            } else {
                idx -= 1;
            }
        }

        return idx;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get1(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare1(int[] nums, int idx1, int idx2) {
        int[] num1 = get1(nums, idx1);
        int[] num2 = get1(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了57.74% 的Java用户
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }
}
