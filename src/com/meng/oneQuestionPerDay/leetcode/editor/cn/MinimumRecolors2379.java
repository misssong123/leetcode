package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MinimumRecolors2379 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 58.27%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 61.42%
     * 的用户
     * 通过测试用例：
     * 122 / 122
     * @param blocks
     * @param k
     * @return
     */
    public int minimumRecolors(String blocks, int k) {
        int res = 0,temp = 0,len = blocks.length();
        for(int i = 0 ; i < k ; i++){
            if (blocks.charAt(i)=='W'){
                res++;
            }
        }
        if (res != 0){
            temp = res;
            for(int i = k ; i < len ; i ++){
                if (blocks.charAt(i-k) == 'W'){
                    temp--;
                }
                if (blocks.charAt(i) == 'W'){
                    temp++;
                }
                res = Math.min(res,temp);
                if (res == 0){
                    return 0;
                }
            }
        }
        return res;
    }
}

