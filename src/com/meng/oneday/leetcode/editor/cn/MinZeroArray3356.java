package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinZeroArray3356 {
    /**
     * 解答成功:
     * 	执行耗时:35 ms,击败了23.53% 的Java用户
     * 	内存消耗:119.9 MB,击败了5.23% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int minZeroArray3356(int[] nums, int[][] queries) {
        boolean allMath = Arrays.stream(nums).allMatch(item -> item == 0);
        if (allMath){
            return 0;
        }
        int left = 0 ,right = queries.length - 1;
        int ans = -1;
        while (left <= right){
            int mid = (left + right) >> 1;
            if (isZeroArray(mid,nums,queries)){
                ans = mid+1;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
    public boolean isZeroArray(int right,int[] nums, int[][] queries) {
        int len = nums.length;
        int[] diff = new int[len];
        for(int i = 0 ; i <= right ; i++){
            int[] query = queries[i];
            diff[query[0]]-= query[2];
            if (query[1]  < len - 1){
                diff[query[1]+1]+= query[2];
            }
        }
        int sum = 0;
        for(int i = 0 ; i < len ; i++){
            sum += diff[i];
            if (nums[i] + sum > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了45.75% 的Java用户
     * 	内存消耗:116.6 MB,击败了55.56% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int minZeroArrayOther1(int[] nums, int[][] queries) {
        int q = queries.length;
        int left = -1, right = q + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(mid, nums, queries)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right <= q ? right : -1;
    }

    // 3355. 零数组变换 I
    private boolean check(int k, int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < k; i++) { // 前 k 个询问
            int[] q = queries[i];
            int l = q[0], r = q[1], val = q[2];
            diff[l] += val;
            diff[r + 1] -= val;
        }

        int sumD = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i];
            if (nums[i] > sumD) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:155 ms,击败了8.50% 的Java用户
     * 	内存消耗:98.8 MB,击败了90.20% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int minZeroArrayOther2(int[] nums, int[][] queries) {
        SegmentTree tree = new SegmentTree(nums);
        if (tree.queryAll() <= 0) {
            return 0;
        }
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            tree.update(1, 0, nums.length - 1, q[0], q[1], q[2]);
            if (tree.queryAll() <= 0) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了79.09% 的Java用户
     * 	内存消耗:116.8 MB,击败了44.44% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        int sumD = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            sumD += diff[i];
            while (k < queries.length && sumD < x) { // 需要添加询问，把 x 减小
                int[] q = queries[k];
                int l = q[0], r = q[1], val = q[2];
                diff[l] += val;
                diff[r + 1] -= val;
                if (l <= i && i <= r) { // x 在更新范围中
                    sumD += val;
                }
                k++;
            }
            if (sumD < x) { // 无法更新
                return -1;
            }
        }
        return k;
    }

}
class SegmentTree {
    private final int[] mx;
    private final int[] todo;

    public SegmentTree(int[] nums) {
        int n = nums.length;
        int m = 2 << (32 - Integer.numberOfLeadingZeros(n));
        mx = new int[m];
        todo = new int[m];
        build(1, 0, n - 1, nums);
    }

    private void do_(int o, int v) {
        mx[o] -= v;
        todo[o] += v;
    }

    private void spread(int o) {
        if (todo[o] != 0) {
            do_(o * 2, todo[o]);
            do_(o * 2 + 1, todo[o]);
            todo[o] = 0;
        }
    }

    private void maintain(int o) {
        mx[o] = Math.max(mx[o * 2], mx[o * 2 + 1]);
    }

    private void build(int o, int l, int r, int[] nums) {
        if (l == r) {
            mx[o] = nums[l];
            return;
        }
        int m = (l + r) / 2;
        build(o * 2, l, m, nums);
        build(o * 2 + 1, m + 1, r, nums);
        maintain(o);
    }

    public void update(int o, int l, int r, int ql, int qr, int v) {
        if (ql <= l && r <= qr) {
            do_(o, v);
            return;
        }
        spread(o);
        int m = (l + r) / 2;
        if (ql <= m) {
            update(o * 2, l, m, ql, qr, v);
        }
        if (m < qr) {
            update(o * 2 + 1, m + 1, r, ql, qr, v);
        }
        maintain(o);
    }

    public int queryAll() {
        return mx[1];
    }
}
