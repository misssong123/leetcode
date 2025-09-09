package com.meng.oneday.leetcode.editor.cn;

class PeopleAwareOfSecret2327 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了29.17% 的Java用户
     * 	内存消耗:39.6 MB,击败了100.00% 的Java用户
     * @param n
     * @param delay
     * @param forget
     * @return
     */
    public int peopleAwareOfSecret2327(int n, int delay, int forget) {
        int mod = 1000000007;
        int[] delayArr = new int[delay+1];
        int[] forgetArr = new int[forget+1];
        delayArr[1] = 1;
        forgetArr[1] = 1;
        for(int i = 1 ; i < n ; i++){
            //移除忘记秘密的人
            delayArr[delay] -= forgetArr[forget];
            if (delayArr[delay] < 0){
                delayArr[delay] += mod;
            }
            //新增秘密的人
            int share = delayArr[delay];
            for(int j = delay ; j >= 2; j--){
                if (j == delay){
                    delayArr[j] += delayArr[j-1];
                }else {
                    delayArr[j] = delayArr[j-1];
                }
            }
            if (delay == 1){
                delayArr[1] += share;
            }else {
                delayArr[1] = share;
            }
            //遗忘秘密的人
            for(int j = forget ; j >= 2; j--){
                forgetArr[j] = forgetArr[j-1];
            }
            forgetArr[1] = share;
            //精简
            forgetArr[forget] %= mod;
            delayArr[delay] %= mod;
        }
        long res = 0;
        for(int i = 1 ; i <= delay; i++){
            res += delayArr[i] ;
            res %= mod;
        }
        return (int)res % mod;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了75.00% 的Java用户
     * 	内存消耗:39.7 MB,击败了88.54% 的Java用户
     * @param n
     * @param delay
     * @param forget
     * @return
     */
    public int peopleAwareOfSecretOther(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;
        int[] sum = new int[n + 1]; // known 数组的前缀和
        sum[1] = 1;
        for (int j = 2; j <= n; j++) {
            int known = (sum[Math.max(j - delay, 0)] - sum[Math.max(j - forget, 0)]) % MOD;
            sum[j] = (sum[j - 1] + known) % MOD;
        }

        int ans = sum[n] - sum[Math.max(n - forget, 0)];
        return (ans % MOD + MOD) % MOD; // 保证答案非负
    }

}
