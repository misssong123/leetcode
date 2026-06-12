package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class IsGood2784 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了6.53% 的Java用户
     * 	内存消耗:44.4 MB,击败了24.18% 的Java用户
     * @param nums
     * @return
     */
    public boolean isGood2784(int[] nums) {
        Arrays.sort(nums);
        int max = nums.length - 1;
        if (nums[max] != max){
            return false;
        }
        for (int i = 1; i <= max ; i++){
            if (nums[i-1] != i){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.96% 的Java用户
     * 	内存消耗:43.7 MB,击败了88.88% 的Java用户
     * @param nums
     * @return
     */
    public boolean isGoodOther(int[] nums) {
        int n = nums.length - 1;
        int[] cnt = new int[n + 1];
        for (int x : nums) {
            if (x > n ||
                    x == n && cnt[x] > 1 || // cnt[x] 加一之前 > 1，加一之后 > 2
                    x < n && cnt[x] > 0) {  // cnt[x] 加一之前 > 0，加一之后 > 1
                return false;
            }
            cnt[x]++;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.96% 的Java用户
     * 	内存消耗:44 MB,击败了64.75% 的Java用户
     * @param nums
     * @return
     */
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        int cntN = 0;
        for (int x : nums) {
            x = Math.abs(x);
            if (x > n ||
                    x == n && cntN > 1 ||
                    x < n && nums[x] < 0) { // x 之前遇到过，现在又遇到了，所以 x 的出现次数至少是 2
                return false;
            }
            if (x == n) {
                cntN++;
            } else {
                nums[x] = -nums[x]; // 标记 x 遇到过
            }
        }
        return true;
    }


}
