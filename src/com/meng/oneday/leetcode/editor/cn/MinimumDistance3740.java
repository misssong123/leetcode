package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinimumDistance3740 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了64.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了50.00% 的Java用户
     * @param nums
     * @return
     */
    public int minimumDistance3740(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 3){
                continue;
            }
            for (int i = 0 ; i < entry.getValue().size() - 2; i++) {
                res = Math.min(res, (entry.getValue().get(i + 1) - entry.getValue().get(i))
                        + (entry.getValue().get(i + 2) - entry.getValue().get(i + 1))
                        + (entry.getValue().get(i + 2) - entry.getValue().get(i)));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了94.00% 的Java用户
     * 	内存消耗:43.8 MB,击败了74.00% 的Java用户
     * @param nums
     * @return
     */
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] last = new int[n + 1];
        int[] last2 = new int[n + 1];
        Arrays.fill(last, -n);
        Arrays.fill(last2, -n); // i-(-n) >= n，不会把 ans 变小

        int ans = n;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            ans = Math.min(ans, i - last2[x]);
            last2[x] = last[x];
            last[x] = i;
        }

        return ans == n ? -1 : ans * 2;
    }


}
