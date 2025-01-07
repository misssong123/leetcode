package com.meng.oneday.leetcode.editor.cn;

class CountKeyChanges3019 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了84.00% 的Java用户
     * 	内存消耗:41.3 MB,击败了44.54% 的Java用户
     * @param s
     * @return
     */
    public int countKeyChangesMy(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        for(int i = 1 ; i < chars.length ; i++){
            int distinct = Math.abs(chars[i] - chars[i-1]);
            if ( distinct!= 32 && distinct != 0){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了68.18% 的Java用户
     * @param s
     * @return
     */
    public int countKeyChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if ((s.charAt(i - 1) & 31) != (s.charAt(i) & 31)) {
                ans++;
            }
        }
        return ans;
    }

}
