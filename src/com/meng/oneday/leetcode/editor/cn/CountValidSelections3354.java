package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class CountValidSelections3354 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了33.33% 的Java用户
     * 	内存消耗:41.5 MB,击败了25.00% 的Java用户
     * @param nums
     * @return
     */
    public int countValidSelections3354(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int l = 0,res = 0;
        for (int num : nums) {
            if (num == 0) {
                int r = sum - l;
                if (l == r) {
                    res += 2;
                } else if (l - r == 1 || r - l == 1) {
                    res += 1;
                }
            }
            l += num;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了60.42% 的Java用户
     * @param nums
     * @return
     */
    public int countValidSelectionsOther(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }

        int ans = 0;
        int pre = 0;
        for (int x : nums) {
            if (x > 0) {
                pre += x;
            } else if (pre * 2 == total) {
                ans += 2;
            } else if (Math.abs(pre * 2 - total) == 1) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了50.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了95.83% 的Java用户
     * @param nums
     * @return
     */
    public int countValidSelections(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }

        int ans = 0;
        int pre = 0;
        for (int x : nums) {
            if (x > 0) {
                pre += x;
            } else {
                int diff = Math.abs(pre * 2 - total);
                ans += Math.max(2 - diff, 0);
            }
        }
        return ans;
    }

}
