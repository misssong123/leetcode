package com.meng.oneday.leetcode.editor.cn;

class CheckZeroOnes1869 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了75.40% 的Java用户
     * @param s
     * @return
     */
    public boolean checkZeroOnes(String s) {
        int maxZero = 0 , maxOne = 0;
        int tempZero = 0 , tempOne = 0;
        for(char c : s.toCharArray()){
            if(c == '0'){
                if (tempOne == 0){
                    tempZero++;
                }else{
                    maxOne = Math.max(maxOne,tempOne);
                    tempOne = 0;
                    tempZero = 1;
                }
            }else{
                if (tempZero == 0){
                    tempOne++;
                }else{
                    maxZero = Math.max(maxZero,tempZero);
                    tempZero = 0;
                    tempOne = 1;
                }
            }
        }
        maxOne = Math.max(maxOne,tempOne);
        maxZero = Math.max(maxZero,tempZero);
        return maxOne > maxZero;
    }
}
