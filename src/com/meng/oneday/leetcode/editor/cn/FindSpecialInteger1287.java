package com.meng.oneday.leetcode.editor.cn;

class FindSpecialInteger1287 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了64.57% 的Java用户
     * @param arr
     * @return
     */
    public int findSpecialIntegerMy(int[] arr) {
        int len = arr.length;
        if (len < 4){
            return arr[0];
        }
        int diff = len /4;
        for(int i = 0 ; i < len-diff ; i++){
            if(arr[i] == arr[i+diff]){
                return arr[i];
            }
        }
        return arr[len-1];
    }
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.9 MB,击败了65.71% 的Java用户
     */
    public int findSpecialInteger(int[] arr) {
        int m = arr.length / 4;
        for (int i : new int[]{m, m * 2 + 1}) {
            int x = arr[i];
            int j = lowerBound(arr, x);
            if (arr[j + m] == x) {
                return x;
            }
        }
        return arr[m * 3 + 2];
    }

    // lowerBound 返回最小的满足 nums[i] >= target 的下标 i
    // 如果数组为空，或者所有数都 < target，则返回 nums.length
    // 要求 nums 是非递减的，即 nums[i] <= nums[i + 1]
    // 原理见 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(int[] nums, int target) {
        int left = -1;
        int right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        // 循环结束后 left+1 = right
        // 此时 nums[left] < target 而 nums[right] >= target
        // 所以 right 就是第一个 >= target 的元素下标
        return right;
    }

}
