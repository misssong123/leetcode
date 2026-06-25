package com.meng.oneday.leetcode.editor.cn;

class CountMajoritySubArrays3737 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了39.13% 的Java用户
     * 	内存消耗:46.3 MB,击败了43.48% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int countMajoritySubArrays3737(int[] nums, int target) {
        int res = 0;
        for (int i = 0 ; i < nums.length ; i++){
            int sum = 0;
            for (int j = i ; j < nums.length ; j++){
                sum += nums[j] == target ? 1 : -1;
                if (sum >= 1){
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.2 MB,击败了47.83% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] cnt = new int[n * 2 + 1];
        cnt[n] = 1;
        int ans = 0;
        int s = n;
        int f = 0;
        for (int x : nums) {
            if (x == target) {
                f += cnt[s];
                s++;
            } else {
                s--;
                f -= cnt[s];
            }
            ans += f;
            cnt[s]++;
        }
        return ans;
    }

}
