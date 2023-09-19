package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionGiveGemLCP50 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.6 MB,击败了44.56% 的Java用户
     * @param gem
     * @param operations
     * @return
     */
    public int giveGem1(int[] gem, int[][] operations) {
        for(int[] operation : operations){
            int n = gem[operation[0]] / 2;
            gem[operation[0]] -= n;
            gem[operation[1]] += n;
        }
        int max = gem[0] ,min = gem[0];
        for(int g : gem){
            if (g > max){
                max = g;
            }else if (g < min){
                min = g;
            }
        }
        return max - min;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了91.30% 的Java用户
     * 	内存消耗:42.3 MB,击败了92.39% 的Java用户
     * @param gem
     * @param operations
     * @return
     */
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int x = operation[0], y = operation[1];
            int number = gem[x] / 2;
            gem[x] -= number;
            gem[y] += number;
        }
        int mn = gem[0], mx = gem[0];
        for (int number : gem) {
            mn = Math.min(number, mn);
            mx = Math.max(number, mx);
        }
        return mx - mn;
    }
}

