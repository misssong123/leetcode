package com.meng.top100.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveLCR119 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了88.15% 的Java用户
     * 	内存消耗:56 MB,击败了18.01% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutiveLCR119(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums) {
            cache.add(num);
        }
        int res = 0;
        for (int num : cache){
            if(cache.contains(num-1)){
                continue;
            }
            int cur = 1;
            while (cache.contains(num+1)){
                num++;
                cur++;
            }
            res = Math.max(res,cur);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.2 MB,击败了9.71% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            st.add(num); // 把 nums 转成哈希集合
        }
        int m = st.size();

        int ans = 0;
        for (int x : st) { // 遍历哈希集合
            if (st.contains(x - 1)) { // 如果 x 不是序列的起点，直接跳过
                continue;
            }
            // x 是序列的起点
            int y = x + 1;
            while (st.contains(y)) { // 不断查找下一个数是否在哈希集合中
                y++;
            }
            // 循环结束后，y-1 是最后一个在哈希集合中的数
            ans = Math.max(ans, y - x); // 从 x 到 y-1 一共 y-x 个数
            if (ans * 2 >= m) {
                break;
            }
        }
        return ans;
    }
}
