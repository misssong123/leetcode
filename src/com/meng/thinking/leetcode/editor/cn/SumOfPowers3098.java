package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SumOfPowers3098 {
    static final int N = 1000000007;

    /**
     * 未考虑负数的情况
     * @param nums
     * @param k
     * @return
     */
    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = i +1 ; j < n - k + 2 ; j++){
                if (i == j){
                    continue;
                }
                int count = count(n - j -1 ,2);
                int temp = count * (nums[j] - nums[i]) % N;
                sum += temp;
                sum %= N;
            }
        }
        return sum;
    }
    Map<String,Integer> cache = new HashMap<>();
    //计算组合数字
    private int count(int sum,int count){
        if (sum <= count){
            return 1;
        }
        if (cache.containsKey(sum+","+count)){
            return  cache.get(sum+","+count);
        }
        int res = 1;
        for(int i = 1 ; i <= count ; i++){
            res = res * sum / i;
            sum--;
        }
        cache.put(sum+","+count,res);
        return res;
    }

    static final int MOD = 1000000007, INF = 0x3f3f3f3f;

    /**
     * 解答成功:
     * 	执行耗时:429 ms,击败了17.74% 的Java用户
     * 	内存消耗:55.7 MB,击败了53.23% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int sumOfPowersOfficial(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer>[][] d = new Map[n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                d[i][j] = new HashMap<Integer, Integer>();
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            d[i][1].put(INF, 1);
            for (int j = 0; j < i; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                for (int p = 2; p <= k; p++) {
                    for (Map.Entry<Integer, Integer> entry : d[j][p - 1].entrySet()) {
                        int v = entry.getKey(), cnt = entry.getValue();
                        int currKey = Math.min(diff, v);
                        d[i][p].put(currKey, (d[i][p].getOrDefault(currKey, 0) + cnt) % MOD);
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : d[i][k].entrySet()) {
                int v = entry.getKey(), cnt = entry.getValue();
                res = (int) ((res + 1L * v * cnt % MOD) % MOD);
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
