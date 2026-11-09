package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinIncrementForUnique945 {
    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了28.89% 的Java用户
     * 	内存消耗:78.7 MB,击败了20.00% 的Java用户
     * @param nums
     * @return
     */
    public int minIncrementForUnique945(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] <= nums[i-1]){
                 res += (nums[i-1] - nums[i] + 1);
                 nums[i] = nums[i-1] + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了97.78% 的Java用户
     * 	内存消耗:75.6 MB,击败了95.56% 的Java用户
     * @param nums
     * @return
     */
    public int minIncrementForUniqueOfficial1(int[] nums) {
        int[] count = new int[200000];
        for (int x : nums) {
            count[x]++;
        }

        int ans = 0, taken = 0;

        for (int x = 0; x < 200000; ++x) {
            if (count[x] >= 2) {
                taken += count[x] - 1;
                ans -= x * (count[x] - 1);
            } else if (taken > 0 && count[x] == 0) {
                taken--;
                ans += x;
            }
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:47 ms,击败了11.11% 的Java用户
     * 	内存消耗:78.4 MB,击败了71.11% 的Java用户
     * @param nums
     * @return
     */
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, taken = 0;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] == nums[i]) {
                taken++;
                ans -= nums[i];
            } else {
                int give = Math.min(taken, nums[i] - nums[i - 1] - 1);
                ans += give * (give + 1) / 2 + give * nums[i - 1];
                taken -= give;
            }
        }

        if (nums.length > 0) {
            ans += taken * (taken + 1) / 2 + taken * nums[nums.length - 1];
        }

        return ans;
    }

}
