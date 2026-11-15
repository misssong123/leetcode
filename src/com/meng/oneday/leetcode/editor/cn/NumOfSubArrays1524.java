package com.meng.oneday.leetcode.editor.cn;

class NumOfSubArrays1524 {
    private int mod = 1000000007;

    /**
     * 思路错误
     * @param arr
     * @return
     */
    public int numOfSubarraysError(int[] arr) {
        int odd = 0, even = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) == 1) {
                odd = even + 1;
                even = odd;
                res += odd;
            }else {
                even = even + 1;
                res += even;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了26.03% 的Java用户
     * 	内存消耗:80.4 MB,击败了89.04% 的Java用户
     * @param arr
     * @return
     */
    public int numOfSubarrays1524(int[] arr) {
        int odd = 0, even = 0, res = 0;
        for(int num : arr){
            if (num % 2 == 1){
                int temp = odd;
                odd = even + 1;
                even = temp;
            }else {
                even = even + 1;
            }
            res += odd;
            odd %= mod;
            even %= mod;
            res %= mod;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了99.32% 的Java用户
     * 	内存消耗:80.2 MB,击败了92.47% 的Java用户
     * @param arr
     * @return
     */
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int[] cnt = new int[2];
        int s = 0;
        long ans = 0;
        for (int x : arr) {
            cnt[s]++;
            s = (s + x) & 1; // &1 比 %2 快
            ans += cnt[s ^ 1];
        }
        return (int) (ans % MOD);
    }

}
