package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CountSeniors2678 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了75.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了23.77% 的Java用户
     * @param details
     * @return
     */
    public int countSeniors(String[] details) {
        int res = 0;
        for (String str : details){
            if (Integer.parseInt(str.substring(11,13))>60){
                res++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
