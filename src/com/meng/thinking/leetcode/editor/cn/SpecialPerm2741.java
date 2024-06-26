package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SpecialPerm2741 {
    int N = 1000000007;
    int[] nums;
    Map<Integer, List<Integer>> cache;
    Map<String,Integer> numCache;

    /**
     * 1862
     * ms
     * 击败
     * 5.00%
     * 复杂度分析
     * 消耗内存分布
     * 64.71
     * MB
     * 击败
     * 5.00%
     * @param num
     * @return
     */
    public int specialPermMy(int[] num) {
        cache = new HashMap<>();
        numCache = new HashMap<>();
        nums = num;
        for(int i = 0; i < num.length; i++){
            for (int j= i+1; j < num.length; j++){
                if (nums[i] % nums[j]==0||nums[j] % nums[i] == 0){
                    cache.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                    cache.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                }
            }
        }
        boolean[] flags =  new boolean[num.length];
        int ans = 0;
        for (int i = 0; i < num.length; i++){
            flags[i] = true;
            ans += dfs(flags,i,1,0) % N;
            ans = ans % N;
            flags[i] = false;
        }
        return ans;
    }

    private int dfs(boolean[] flags, int index,int num, int pre) {
        //遍历过当前组合
        if (numCache.containsKey(pre+"#"+index)){
            return numCache.get(pre+"#"+index);
        }
        if (num == nums.length){
            return 1;
        }
        int ans = 0;
        if (cache.containsKey(index)){
            for(int i : cache.get(index)){
                if (!flags[i]){
                    flags[i] = true;
                    ans = (ans + dfs(flags,i,num+1,pre|1<<index) % N)% N;
                    flags[i] = false;
                }
            }
        }
        numCache.put(pre+"#"+index,ans);
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:159 ms,击败了78.75% 的Java用户
     * 	内存消耗:53.8 MB,击败了35.00% 的Java用户
     * @param nums
     * @return
     */
    public int specialPerm(int[] nums) {
        int n = nums.length;
        int u = (1 << n) - 1;
        long[][] memo = new long[u][n];
        for (long[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dfs(u ^ (1 << i), i, nums, memo);
        }
        return (int) (ans % 1_000_000_007);
    }

    private long dfs(int s, int i, int[] nums, long[][] memo) {
        if (s == 0) {
            return 1; // 找到一个特别排列
        }
        if (memo[s][i] != -1) { // 之前计算过
            return memo[s][i];
        }
        long res = 0;
        for (int j = 0; j < nums.length; j++) {
            if ((s >> j & 1) > 0 && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                res += dfs(s ^ (1 << j), j, nums, memo);
            }
        }
        return memo[s][i] = res; // 记忆化
    }


}
//leetcode submit region end(Prohibit modification and deletion)
