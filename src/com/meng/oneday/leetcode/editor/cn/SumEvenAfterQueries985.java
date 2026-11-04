package com.meng.oneday.leetcode.editor.cn;

class SumEvenAfterQueries985 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:51.7 MB,击败了81.82% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries985(int[] nums, int[][] queries) {
        int evenSum = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                evenSum += num;
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int val = queries[i][0], index = queries[i][1];
            if ((nums[index] & 1) == 0) {
                evenSum -= nums[index];
            }
            nums[index] += val;
            if ((nums[index] & 1) == 0) {
                evenSum += nums[index];
            }
            res[i] = evenSum;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了86.36% 的Java用户
     * 	内存消耗:51.9 MB,击败了22.73% 的Java用户
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int S = 0;
        for (int x: A)
            if (x % 2 == 0)
                S += x;

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0], index = queries[i][1];
            if (A[index] % 2 == 0) S -= A[index];
            A[index] += val;
            if (A[index] % 2 == 0) S += A[index];
            ans[i] = S;
        }

        return ans;
    }
}
