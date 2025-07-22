package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class MaximumUniqueSubarray1695 {
    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了79.79% 的Java用户
     * 	内存消耗:57.2 MB,击败了77.60% 的Java用户
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray1695(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        int l = 0,res = 0,temp =0;
        for (int num : nums) {
            if (cache.add(num)) {
                temp += num;
            } else {
                while (nums[l] != num) {
                    cache.remove(nums[l]);
                    temp -= nums[l];
                    l++;
                }
                l++;
            }
            res = Math.max(res, temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:56 ms,击败了40.80% 的Java用户
     * 	内存消耗:60 MB,击败了10.71% 的Java用户
     * @param nums
     * @return
     */
    public int maximumUniqueSubarrayOfficial(int[] nums) {
        int n = nums.length;
        Set<Integer> seen = new HashSet<>();
        int ans = 0, psum = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            psum += nums[i];
            while (seen.contains(nums[i])) {
                seen.remove(nums[j]);
                psum -= nums[j];
                j++;
            }
            seen.add(nums[i]);
            ans = Math.max(ans, psum);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:67 ms,击败了34.16% 的Java用户
     * 	内存消耗:59.4 MB,击败了27.03% 的Java用户
     * @param nums
     * @return
     */
    public int maximumUniqueSubarrayOfficial2(int[] nums) {
        int n = nums.length;
        int[] psum = new int[n + 1];
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            psum[i + 1] = psum[i] + nums[i];
            pre = Math.max(pre, cnt.getOrDefault(nums[i], 0));
            ans = Math.max(ans, psum[i + 1] - psum[pre]);
            cnt.put(nums[i], i + 1);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:64 ms,击败了35.58% 的Java用户
     * 	内存消耗:59 MB,击败了35.09% 的Java用户
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0, s = 0, left = 0;
        for (int x : nums) {
            while (set.contains(x)) {
                set.remove(nums[left]);
                s -= nums[left];
                left++;
            }
            set.add(x);
            s += x;
            ans = Math.max(ans, s);
        }
        return ans;
    }

}
