package com.meng.oneday.leetcode.editor.cn;

class MinDeletionSize944 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了35.58% 的Java用户
     * 	内存消耗:43.4 MB,击败了87.50% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize944(String[] strs) {
        int n = strs.length ,m = strs[0].length();
        int res = 0;
        for(int i = 0 ; i < m ; i ++){
            for(int j = 1 ; j < n ; j++){
                if (strs[j].charAt(i) < strs[j-1].charAt(i)){
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了35.58% 的Java用户
     * 	内存消耗:43.4 MB,击败了91.35% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int row = strs.length;
        int col = strs[0].length();
        int ans = 0;
        for (int j = 0; j < col; ++j) {
            for (int i = 1; i < row; ++i) {
                if (strs[i - 1].charAt(j) > strs[i].charAt(j)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

}
