package com.meng.oneday.leetcode.editor.cn;

import java.util.List;
import java.util.TreeMap;

class MinimumSumSubarray3364 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.6 MB,击败了83.69% 的Java用户
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int minimumSumSubarray3364(List<Integer> nums, int l, int r) {
        int len = nums.size();
        int[] pre = new int[len+1];
        for(int i = 0 ; i < len;i++){
            pre[i+1] = pre[i] + nums.get(i);
        }
        int res = Integer.MAX_VALUE;
        for(int i = l-1 ; i < len ; i++){
            for(int j = l ; j <= r ; j++){
                int index = i - j + 1;
                if (index < 0) {
                    break;
                }
                int val = pre[i+1] - pre[index];
                if (val >0){
                    res = Math.min(res,val);
                    if (res ==  1){
                        return res;
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了32.62% 的Java用户
     * 	内存消耗:46.4 MB,击败了5.68% 的Java用户
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        Integer[] a = nums.toArray(Integer[]::new);
        int ans = Integer.MAX_VALUE;
        int n = a.length;
        int[] s = new int[n + 1];
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int j = 1; j <= n; j++) {
            s[j] = s[j - 1] + a[j - 1];
            if (j < l) {
                continue;
            }
            cnt.merge(s[j - l], 1, Integer::sum); // cnt[s[j-l]]++
            Integer lower = cnt.lowerKey(s[j]);
            if (lower != null) {
                ans = Math.min(ans, s[j] - lower);
            }
            if (j >= r) {
                int v = s[j - r];
                int c = cnt.get(v);
                if (c == 1) {
                    cnt.remove(v);
                } else {
                    cnt.put(v, c - 1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
