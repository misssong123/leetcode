package com.meng.oneday.leetcode.editor.cn;

class NumEquivDominoPairs1128 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了98.44% 的Java用户
     * 	内存消耗:51.5 MB,击败了88.28% 的Java用户
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs1128(int[][] dominoes) {
        int[][] cache = new int[10][10];
        int res = 0;
        for(int [] dominoe : dominoes){
            int x = dominoe[0],y = dominoe[1];
            if(x > y){
                int temp = x;
                x = y;
                y = temp;
            }
            res += cache[x][y];
            cache[x][y]++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了98.44% 的Java用户
     * 	内存消耗:51.8 MB,击败了57.03% 的Java用户
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

}
