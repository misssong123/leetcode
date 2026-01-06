package com.meng.oneday.leetcode.editor.cn;

class MinDeletionSize960 {
    /**
     * 无思路
     * @param strs
     * @return
     */
    public int minDeletionSizeNoThinking(String[] strs) {
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了95.06% 的Java用户
     * 	内存消耗:43.7 MB,击败了37.26% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize960(String[] strs) {
       int m = strs[0].length();
       int[] f = new int[m];
       for (int i = 0 ; i < m ; i++){
           for (int j = 0 ; j < i ; j++){
               if(f[j] > f[i] && allLess(strs,j,i)){
                   f[i] = f[j];
               }
           }
           f[i]++;
       }
       int max = 0;
       for(int num : f){
           max = Math.max(max,num);
       }
       return m - max;
    }
    private boolean allLess(String[] strs,int i,int j){
        for (String str : strs){
            if (str.charAt(i) > str.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
