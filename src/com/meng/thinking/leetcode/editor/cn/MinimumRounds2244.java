package com.meng.thinking.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumRounds2244 {
    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了82.50% 的Java用户
     * 	内存消耗:54.9 MB,击败了96.25% 的Java用户
     * @param tasks
     * @return
     */
    public int minimumRoundsMy(int[] tasks) {
        Map<Integer,Integer> cache = new HashMap<>();
        for (int task : tasks) {
            cache.put(task,cache.getOrDefault(task, 0) + 1);
        }
        int ans = 0;
        for(int val : cache.values()){
            if (val == 1){
                return -1;
            }else {
                ans += val / 3 + (val % 3 == 0 ? 0 : 1);
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了82.50% 的Java用户
     * 	内存消耗:56.5 MB,击败了46.25% 的Java用户
     * @param tasks
     * @return
     */
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int task : tasks) {
            cnt.put(task, cnt.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (int v : cnt.values()) {
            if (v == 1) {
                return -1;
            }
            if (v % 3 == 0) {
                res += v / 3;
            } else {
                res += 1 + v / 3;
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
