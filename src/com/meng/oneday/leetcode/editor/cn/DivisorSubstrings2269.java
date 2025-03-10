package com.meng.oneday.leetcode.editor.cn;

class DivisorSubstrings2269 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了45.00% 的Java用户
     * @param num
     * @param k
     * @return
     */
    public int divisorSubstringsMy(int num, int k) {
        String str = String.valueOf(num);
        int res = 0;
        for(int i = 0 ; i <= str.length() -k; i++){
            int n = Integer.parseInt(str.substring(i,i+k));
            if(n != 0 && num % n == 0){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了51.00% 的Java用户
     * @param num
     * @param k
     * @return
     */
    public int divisorSubstrings(int num, int k) {
        long m = (long) Math.pow(10, k);
        int ans = 0;
        for (int n = num; n >= m / 10; n /= 10) {
            int x = (int) (n % m);
            if (x > 0 && num % x == 0) {
                ans++;
            }
        }
        return ans;
    }

}
