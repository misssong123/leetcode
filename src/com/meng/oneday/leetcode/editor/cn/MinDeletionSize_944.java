package com.meng.oneday.leetcode.editor.cn;

class MinDeletionSize_944 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了84.34% 的Java用户
     * 	内存消耗:46.6 MB,击败了6.03% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize944(String[] strs) {
        int row = strs.length,col = strs[0].length();
        int res = 0;
        for (int i = 0 ; i < col ; i++){
            for(int j = 1 ; j < row ; j++){
                if(strs[j].charAt(i) < strs[j-1].charAt(i)){
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了84.34% 的Java用户
     * 	内存消耗:46.2 MB,击败了24.10% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int ans = 0;
        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) { // 遍历 j 列的字母
                if (strs[i - 1].charAt(j) > strs[i].charAt(j)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

}
