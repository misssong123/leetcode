package com.meng.oneday.leetcode.editor.cn;

class MinOperations1758 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了68.63% 的Java用户
     * 	内存消耗:43.1 MB,击败了49.02% 的Java用户
     * @param s
     * @return
     */
    public int minOperations1758(String s) {
        int firstZero = 0, firstOne = 0 ;
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '0'){
                if (i % 2 == 0){
                    firstOne++;
                }else {
                    firstZero++;
                }
            }else{
                if (i % 2 == 0){
                    firstZero++;
                }else {
                    firstOne++;
                }
            }
        }
        return Math.min(firstZero,firstOne);
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了86.27% 的Java用户
     * 	内存消耗:43.1 MB,击败了49.02% 的Java用户
     * @param s
     * @return
     */
    public int minOperations(String s) {
        int n = s.length();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            // 如果 i 是偶数，把 s[i] 变成 0，否则变成 1
            if (s.charAt(i) - '0' != i % 2) {
                diff++;
            }
        }
        return Math.min(diff, n - diff);
    }

}
