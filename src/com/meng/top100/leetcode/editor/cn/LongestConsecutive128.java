package com.meng.top100.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class LongestConsecutive128 {
    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了99.98% 的Java用户
     * 	内存消耗:55.4 MB,击败了95.79% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutive128(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int max = 0,cnt = 1;
        for(int i = 1 ; i < nums.length ; i++){
            if(nums[i] == nums[i-1]){
                continue;
            }else if (nums[i] == nums[i-1] + 1){
                cnt++;
            }else{
                max = Math.max(max,cnt);
                cnt = 1;
            }
        }
        return Math.max(max,cnt);
    }

    /**
     * 解答成功:
     * 	执行耗时:24 ms,击败了90.23% 的Java用户
     * 	内存消耗:64.7 MB,击败了74.98% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutiveOther(int[] nums) {
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

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了59.95% 的Java用户
     * 	内存消耗:66.5 MB,击败了21.67% 的Java用户
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums){
            cache.add(num);
        }
        int max = 0;
        for(int num : cache){
            if (cache.contains(num-1)){
                continue;
            }
            int cnt = 1;
            while (cache.contains(num + cnt)){
                cnt++;
            }
            max = Math.max(max,cnt);
        }
        return max;
    }
}
