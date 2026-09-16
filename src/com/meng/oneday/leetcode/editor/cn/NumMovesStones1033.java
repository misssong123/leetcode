package com.meng.oneday.leetcode.editor.cn;

class NumMovesStones1033 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了35.00% 的Java用户
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int[] numMovesStones1033(int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int mid = a + b + c - min - max;
        if(min + 1 == mid && mid + 1 == max){
            return new int[]{0,0};
        }
        int maxStep = Math.abs(mid - min) + Math.abs(max - mid) - 2;
        if(min + 1 == mid || mid + 1 == max || min + 2 == mid || mid + 2 == max){
            return new int[]{1,maxStep};
        }
        return new int[]{2,maxStep};
    }
}
