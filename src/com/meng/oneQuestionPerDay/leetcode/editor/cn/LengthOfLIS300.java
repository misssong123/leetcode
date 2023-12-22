package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class LengthOfLIS300 {
    /**
     * 解答成功:
     * 执行耗时:6 ms,击败了84.88% 的Java用户
     * 内存消耗:43 MB,击败了5.03% 的Java用户
     *
     * @param nums
     * @return
     */
    public int lengthOfLISMy(int[] nums) {
        int res = 1;
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int index = binarySearch(temp, nums[i]);
            if (index == temp.size()) {
                temp.add(nums[i]);
                res = temp.size();
            } else {
                temp.set(index, nums[i]);
                res = Math.max(res, index + 1);
            }
        }
        return res;
    }

    private int binarySearch(List<Integer> temp, int target) {
        int low = 0, high = temp.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (temp.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 动态规划
     * 解答成功:
     * 执行耗时:62 ms,击败了46.42% 的Java用户
     * 内存消耗:43.1 MB,击败了5.03% 的Java用户
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 二分查找
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.61% 的Java用户
     * 	内存消耗:42.9 MB,击败了5.03% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
