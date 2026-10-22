package com.meng.oneday.leetcode.editor.cn;

class MinOperations3942 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:93.4 MB,击败了19.05% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations3942(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        //排段是否已经满足
        boolean flag = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return 0;
        }
        flag = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return 1;
        }
        //最大值在首位
        if(nums[0] == n-1){
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != i-1) {
                    return -1;
                }
            }
            return 1;
        }
        if (nums[0] == 0){
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != n - i) {
                    return -1;
                }
            }
            return 2;
        }
        //两端连续
        boolean addFlag = nums[1] - nums[0] >= 0;
        int index = -1;
        for (int i = 1; i < nums.length; i++) {
            if (addFlag){
                if (nums[i] - nums[i -1] != 1){
                    if (index == -1){
                        index = i;
                    }else {
                        return -1;
                    }
                }
            }else {
                if (nums[i-1] - nums[i ] != 1){
                    if (index == -1){
                        index = i;
                    }else {
                        return -1;
                    }
                }
            }
        }
        return addFlag ? Math.min(index,n -index + 2) : Math.min(index+1,n -index + 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:93.3 MB,击败了42.86% 的Java用户
     * @param nums
     * @return
     */
    public int minOperationsOther1(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int p = 0;
        for (int i = 1; i < n && cnt < 2; i++) {
            if (nums[i - 1] > nums[i]) {
                cnt++;
                p = i;
            }
        }

        if (cnt == 0) { // 已是递增
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        if (cnt == 1 && nums[0] > nums[n - 1]) { // 两个递增段
            ans = Math.min(p, n - p + 2);
        }

        cnt = p = 0;
        for (int i = 1; i < n && cnt < 2; i++) {
            if (nums[i - 1] < nums[i]) {
                cnt++;
                p = i;
            }
        }

        if (cnt == 0) { // 已是递减
            return 1;
        }
        if (cnt == 1 && nums[0] < nums[n - 1]) { // 两个递减段
            ans = Math.min(ans, Math.min(p, n - p) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:93.2 MB,击败了61.90% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int d = nums[1] - nums[0];
        if (Math.abs(d) != 1) {
            d = nums[2] - nums[1];
            if (Math.abs(d) != 1) { // 不能有多个跳变位置
                return -1;
            }
        }

        int p = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] != d) {
                // i 是跳变位置
                if (p > 0) { // 不能有多个跳变位置
                    return -1;
                }
                p = i;
            }
        }

        if (d == 1) {
            return Math.min(p, n - p + 2); // 递增
        }
        return Math.min(p, n - p) + 1; // 递减
    }


}
