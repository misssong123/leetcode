package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinSetSize1338 {
    /**
     * 解答成功:
     * 	执行耗时:20 ms,击败了96.55% 的Java用户
     * 	内存消耗:68.6 MB,击败了98.85% 的Java用户
     * @param arr
     * @return
     */
    public int minSetSize1338(int[] arr) {
        int[] nums = new int[100001];
        for (int num : arr) {
            nums[num]++;
        }
        Arrays.sort(nums);
        int ans = 0;
        int num = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans += nums[i];
            num++;
            if (ans >= arr.length / 2) {
                return num;
            }
        }
        return num;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了93.10% 的Java用户
     * 	内存消耗:85.4 MB,击败了63.22% 的Java用户
     * @param arr
     * @return
     */
    public int minSetSizeOther(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) {
            freq.merge(x, 1, Integer::sum); // freq[x]++
        }
        List<Integer> cnt = new ArrayList<>(freq.values());
        cnt.sort((a, b) -> b - a);
        int s = 0;
        for (int i = 0; ; i++) {
            s += cnt.get(i);
            if (s >= arr.length / 2) {
                return i + 1;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了96.55% 的Java用户
     * 	内存消耗:71.5 MB,击败了96.55% 的Java用户
     * @param arr
     * @return
     */
    public int minSetSize(int[] arr) {
        int mx = 0;
        for (int x : arr) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : arr) {
            cnt[x]++;
        }
        Arrays.sort(cnt);
        int s = 0;
        for (int i = mx; ; i--) {
            s += cnt[i];
            if (s >= arr.length / 2) {
                return mx + 1 - i;
            }
        }
    }

}
