package com.meng.oneday.leetcode.editor.cn;

class MinimumPushes3014 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了27.78% 的Java用户
     * @param word
     * @return
     */
    public int minimumPushes3014(String word) {
        int ans = 0 ;
        int len = word.length();
        int weight = 1;
        while (len > 0){
            if(len >= 8){
                ans += 8 * weight;
                len -= 8;
            }else{
                ans += len * weight;
                len = 0;
            }
            weight++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了33.33% 的Java用户
     * @param word
     * @return
     */
    public int minimumPushes(String word) {
        int n = word.length();
        int k = n / 8;
        return (k * 4 + n % 8) * (k + 1);
    }
}
