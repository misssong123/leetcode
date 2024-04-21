package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CombinationSum3216 {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了13.57% 的Java用户
     * 	内存消耗:39.9 MB,击败了91.19% 的Java用户
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum31(int k, int n) {
        for (int mask = 0; mask < (1 << 9); ++mask) {
            if (check(mask, k, n)) {
                ans.add(new ArrayList<Integer>(temp));
            }
        }
        return ans;
    }

    public boolean check(int mask, int k, int n) {
        temp.clear();
        for (int i = 0; i < 9; ++i) {
            if (((1 << i) & mask) != 0) {
                temp.add(i + 1);
            }
        }
        if (temp.size() != k) {
            return false;
        }
        int sum = 0;
        for (int num : temp) {
            sum += num;
        }
        return sum == n;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了13.57% 的Java用户
     * 	内存消耗:40.1 MB,击败了46.17% 的Java用户
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, 9, k, n);
        return ans;
    }

    public void dfs(int cur, int n, int k, int sum) {
        if (temp.size() + (n - cur + 1) < k || temp.size() > k) {
            return;
        }
        if (temp.size() == k) {
            int tempSum = 0;
            for (int num : temp) {
                tempSum += num;
            }
            if (tempSum == sum) {
                ans.add(new ArrayList<Integer>(temp));
                return;
            }
        }
        temp.add(cur);
        dfs(cur + 1, n, k, sum);
        temp.remove(temp.size() - 1);
        dfs(cur + 1, n, k, sum);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
