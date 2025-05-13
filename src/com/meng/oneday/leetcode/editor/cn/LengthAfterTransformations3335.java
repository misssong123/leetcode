package com.meng.oneday.leetcode.editor.cn;
class LengthAfterTransformations3335 {
    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了51.61% 的Java用户
     * 	内存消耗:44.6 MB,击败了77.42% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public int lengthAfterTransformations3335(String s, int t) {
        long[] nums = new long[26];
        for(char c : s.toCharArray()){
            nums[c - 'a']++;
        }
        int mod = 1000000007;
        for(int i = 0 ; i < t ; i++){
            long zNum = nums[25];
            for (int j = 25 ; j > 0 ; j-- ){
                nums[j] = nums[j - 1];
            }
            nums[0] = zNum;
            nums[1]=(nums[1]+zNum)%mod;
        }

        long ans=0;
        for (int i = 0 ; i < 26 ; i++){
            ans = (ans + nums[i]) % mod;
        }
        return (int)ans;
    }
    private static final int MOD = 1000000007;

    /**
     * 解答成功:
     * 	执行耗时:67 ms,击败了51.61% 的Java用户
     * 	内存消耗:44.6 MB,击败了74.19% 的Java用户
     * @param s
     * @param t
     * @return
     */
    public int lengthAfterTransformationsOfficial(String s, int t) {
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) {
            ++cnt[ch - 'a'];
        }
        for (int round = 0; round < t; ++round) {
            int[] nxt = new int[26];
            nxt[0] = cnt[25];
            nxt[1] = (cnt[25] + cnt[0]) % MOD;
            for (int i = 2; i < 26; ++i) {
                nxt[i] = cnt[i - 1];
            }
            cnt = nxt;
        }
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans = (ans + cnt[i]) % MOD;
        }
        return ans;
    }


}
