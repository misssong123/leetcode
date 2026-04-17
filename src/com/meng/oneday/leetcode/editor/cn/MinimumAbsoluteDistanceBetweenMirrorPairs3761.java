package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinMirrorPairDistance3761 {
    /**
     * 解答成功:
     * 	执行耗时:57 ms,击败了48.52% 的Java用户
     * 	内存消耗:93.2 MB,击败了70.16% 的Java用户
     * @param nums
     * @return
     */
    public int minMirrorPairDistance3761(int[] nums) {
        int len = nums.length;
        //计算反转后的下标
        Map<Integer, Integer> map = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for (int i = len - 1  ; i >=0  ; i--){
            int revNum = reverse(nums[i]);
            if (map.containsKey(revNum)){
                res = Math.min(res, map.get(revNum) - i);
            }
            map.put(nums[i], i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private int reverse(int num){
        int res = 0;
        while(num > 0){
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:45 ms,击败了87.21% 的Java用户
     * 	内存消耗:100.7 MB,击败了30.82% 的Java用户
     * @param nums
     * @return
     */
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        int ans = n;
        Map<Integer, Integer> lastIndex = new HashMap<>(n, 1); // 预分配空间

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            Integer i = lastIndex.get(x);
            if (i != null) {
                ans = Math.min(ans, j - i);
            }

            // 计算 reverse(x)，不用字符串
            int rev = 0;
            for (; x > 0; x /= 10) {
                rev = rev * 10 + x % 10;
            }
            lastIndex.put(rev, j);
        }

        return ans < n ? ans : -1;
    }

}
