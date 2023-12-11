package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class ClimbStairs70 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.6 MB,击败了5.02% 的Java用户
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2){
            return n;
        }
        int first = 1 ,second = 2;
        for(int i = 3; i <= n ; i++ ){
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
