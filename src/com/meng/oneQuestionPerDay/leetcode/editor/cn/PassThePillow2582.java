package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionPassThePillow2582 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.2 MB,击败了28.88% 的Java用户
     */
    public int passThePillow(int n, int time) {
        int num = time / (n-1);
        int index = time % (n-1);
        return num % 2 == 0 ? 1 + index : n -index;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.3 MB,击败了18.77% 的Java用户
     * @param n
     * @param time
     * @return
     */
    public int passThePillow1(int n, int time) {
        time %= (n - 1) * 2;
        return time < n ? time + 1 : n * 2 - time - 1;
    }

}
