package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class SumFourDivisors1390 {
    /**
     * 解答成功:
     * 	执行耗时:41 ms,击败了30.23% 的Java用户
     * 	内存消耗:46.3 MB,击败了30.23% 的Java用户
     * @param nums
     * @return
     */
    public int sumFourDivisors1390(int[] nums) {
        Set<Integer> set = new HashSet<>(8);
        int res = 0;
        for (int num : nums){
            for(int i = 1 ; i <= Math.sqrt(num); i++){
                if (num % i == 0){
                    set.add(i);
                    set.add(num/i);
                }
                if(set.size() > 4){
                    break;
                }
            }
            if(set.size() == 4){
                res +=set.stream().mapToInt(Integer::intValue).sum();
            }
            set.clear();
        }

        return res;
    }
    private static final int MX = 100_001;
    private static final int[] divisorNum = new int[MX];
    private static final int[] divisorSum = new int[MX];
    private static boolean initialized = false;

    // 这样写比 static block 快
    public SumFourDivisors1390() {
        if (initialized) {
            return;
        }
        initialized = true;

        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) { // 枚举 i 的倍数 j
                divisorNum[j]++; // i 是 j 的因子
                divisorSum[j] += i;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.5 MB,击败了27.91% 的Java用户
     * @param nums
     * @return
     */
    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            if (divisorNum[x] == 4) {
                ans += divisorSum[x];
            }
        }
        return ans;
    }
}
