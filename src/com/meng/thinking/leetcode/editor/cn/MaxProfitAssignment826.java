package com.meng.thinking.leetcode.editor.cn;

import javafx.util.Pair;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxProfitAssignment826 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了71.89% 的Java用户
     * 	内存消耗:45.1 MB,击败了5.07% 的Java用户
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignmentMy(int[] difficulty, int[] profit, int[] worker) {
        int[] maxProfit = new int[100001];
        int n = difficulty.length;
        for (int i = 0; i < n; i++) {
            maxProfit[difficulty[i]] = Math.max(profit[i], maxProfit[difficulty[i]]);
        }
        for (int i = 1; i < 100001; i++){
            maxProfit[i] = Math.max(maxProfit[i-1], maxProfit[i]);
        }
        int ans = 0;
        for (int work : worker){
            ans += maxProfit[work];
        }
        return  ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了31.34% 的Java用户
     * 	内存消耗:45 MB,击败了7.37% 的Java用户
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        List<Pair<Integer, Integer>> jobs = new ArrayList<>();
        int N = profit.length, res = 0, i = 0, best = 0;
        for (int j = 0; j < N; ++j) {
            jobs.add(new Pair<>(difficulty[j], profit[j]));
        }
        Collections.sort(jobs, Comparator.comparing(Pair::getKey));
        Arrays.sort(worker);
        for (int w : worker) {
            while (i < N && w >= jobs.get(i).getKey()) {
                best = Math.max(best, jobs.get(i).getValue());
                i++;
            }
            res += best;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
