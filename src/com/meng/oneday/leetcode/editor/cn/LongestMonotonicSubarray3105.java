package com.meng.oneday.leetcode.editor.cn;

class LongestMonotonicSubarray3105 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.9 MB,击败了36.36% 的Java用户
     * @param nums
     * @return
     */
    public int longestMonotonicSubarray3105(int[] nums) {
        int res = 1,temp = 1;
        int flag = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]){
                temp = 1;
                flag = 0;
            }else if (nums[i] > nums[i-1]){
                if (flag == 1){
                    temp++;
                }else {
                    temp = 2;
                    flag = 1;
                }
            }else{
                if (flag == -1){
                    temp++;
                }else {
                    temp = 2;
                    flag = -1;
                }
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了42.42% 的Java用户
     * @param a
     * @return
     */
    public int longestMonotonicSubarray(int[] a) {
        int ans = 1;
        int i = 0, n = a.length;
        while (i < n - 1) {
            if (a[i + 1] == a[i]) {
                i++; // 直接跳过
                continue;
            }
            int start = i; // 记录这一组的开始位置
            boolean inc = a[i] < a[i + 1]; // 定下基调：是严格递增还是严格递减
            i += 2; // i 和 i+1 已经满足要求，从 i+2 开始判断
            while (i < n && a[i] != a[i - 1] && (a[i] > a[i - 1]) == inc) {
                i++;
            }
            // 从 start 到 i-1 是满足题目要求的（并且无法再延长的）子数组
            ans = Math.max(ans, i - start);
            i--;
        }
        return ans;
    }
}
