package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumSeconds2808 {
    /**
     * 解答成功:
     * 	执行耗时:67 ms,击败了82.81% 的Java用户
     * 	内存消耗:79 MB,击败了48.44% 的Java用户
     * @param nums
     * @return
     */
    public int minimumSecondsMy(List<Integer> nums) {
        Map<Integer,List<Integer>> cache = new HashMap<>();
        for (int i = 0 ; i < nums.size() ; i++){
            cache.computeIfAbsent(nums.get(i),k->new ArrayList<>()).add(i);
        }
        int ans = nums.size();
        //计算元素之间的最大距离
        for (List<Integer> positions : cache.values()){
            int temp = positions.get(0) - positions.get(positions.size()-1) + nums.size();
            for (int i = 1 ; i < positions.size() ; i++){
                temp = Math.max(temp,positions.get(i) - positions.get(i-1));
            }
            ans = Math.min(ans,temp/2);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:67 ms,击败了82.81% 的Java用户
     * 	内存消耗:79.1 MB,击败了46.88% 的Java用户
     * @param nums
     * @return
     */
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        int ans = 1 << 30;
        for (List<Integer> idx : d.values()) {
            int m = idx.size();
            int t = idx.get(0) + n - idx.get(m - 1);
            for (int i = 1; i < m; ++i) {
                t = Math.max(t, idx.get(i) - idx.get(i - 1));
            }
            ans = Math.min(ans, t / 2);
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
