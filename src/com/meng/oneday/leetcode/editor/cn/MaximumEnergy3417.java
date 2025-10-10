package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MaximumEnergy3417 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了6.90% 的Java用户
     * 	内存消耗:59 MB,击败了55.17% 的Java用户
     * @param energy
     * @param k
     * @return
     */
    public int maximumEnergy3417(int[] energy, int k) {
        int n = energy.length;
        int[][] pre = new int[k][n/k+1];
        int res = energy[n-1];
        //计算每个分组前缀和
        for(int i = n-1 ; i >= 0 ; i--){
            int x = i % k;
            int y = i / k;
            pre[x][y] = energy[i];
            if (i < n - k){
                pre[x][y] += pre[x][y+1];
            }
            res = Math.max(res,pre[x][y]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了55.17% 的Java用户
     * 	内存消耗:59.2 MB,击败了55.17% 的Java用户
     * @param energy
     * @param k
     * @return
     */
    public int maximumEnergyProve(int[] energy, int k) {
        int n = energy.length;
        int[] pre = new int[k];
        int res = energy[n-1];
        //计算每个分组前缀和
        for(int i = n-1 ; i >= 0 ; i--){
            int x = i % k;
            pre[x] += energy[i];
            res = Math.max(res,pre[x]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了55.17% 的Java用户
     * 	内存消耗:58.7 MB,击败了65.52% 的Java用户
     * @param energy
     * @param k
     * @return
     */
    public int maximumEnergyOther(int[] energy, int k) {
        int n = energy.length;
        int ans = Integer.MIN_VALUE;
        for (int i = n - k; i < n; i++) { // 枚举终点 i
            int sufSum = 0;
            for (int j = i; j >= 0; j -= k) {
                sufSum += energy[j]; // 计算后缀和
                ans = Math.max(ans, sufSum);
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了6.90% 的Java用户
     * 	内存消耗:55.9 MB,击败了100.00% 的Java用户
     * @param energy
     * @param k
     * @return
     */
    public int maximumEnergy(int[] energy, int k) {
        for (int i = energy.length - k - 1; i >= 0; i--) {
            energy[i] += energy[i + k];
        }
        return Arrays.stream(energy).max().getAsInt();
    }
}
