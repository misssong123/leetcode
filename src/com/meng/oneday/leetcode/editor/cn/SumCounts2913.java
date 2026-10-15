package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SumCounts2913 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了53.57% 的Java用户
     * 	内存消耗:45.5 MB,击败了60.71% 的Java用户
     * @param nums
     * @return
     */
    public int sumCounts2913(List<Integer> nums) {
        int n = nums.size();
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i < n ; i++){
            set.clear();
            for(int j = i ; j >=0 ; j--){
                set.add(nums.get(j));
                res += set.size() * set.size();
            }
        }
        return res;
    }
    private long[] sum;
    private int[] todo;

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了96.43% 的Java用户
     * 	内存消耗:44.5 MB,击败了75.00% 的Java用户
     * @param nums
     * @return
     */
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        sum = new long[n * 4];
        todo = new int[n * 4];

        long ans = 0, s = 0;
        var last = new HashMap<Integer, Integer>();
        for (int i = 1; i <= n; i++) {
            int x = nums.get(i - 1);
            int j = last.getOrDefault(x, 0);
            s += queryAndAdd1(1, 1, n, j + 1, i) * 2 + i - j;
            ans = (ans + s) % 1_000_000_007;
            last.put(x, i);
        }
        return (int) ans;
    }

    private void do_(int o, int l, int r, int add) {
        sum[o] += (long) add * (r - l + 1);
        todo[o] += add;
    }

    // o=1  [l,r] 1<=l<=r<=n
    // 把 [L,R] 加一，同时返回加一之前的区间和
    private long queryAndAdd1(int o, int l, int r, int L, int R) {
        if (L <= l && r <= R) {
            long res = sum[o];
            do_(o, l, r, 1);
            return res;
        }

        int m = (l + r) / 2;
        int add = todo[o];
        if (add != 0) {
            do_(o * 2, l, m, add);
            do_(o * 2 + 1, m + 1, r, add);
            todo[o] = 0;
        }

        long res = 0;
        if (L <= m) res += queryAndAdd1(o * 2, l, m, L, R);
        if (m < R)  res += queryAndAdd1(o * 2 + 1, m + 1, r, L, R);
        sum[o] = sum[o * 2] + sum[o * 2 + 1];
        return res;
    }

}
