package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class IsAcronym2828 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.22% 的Java用户
     * @param words
     * @param s
     * @return
     */
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()){
            return false;
        }
        for(int i = 0; i <words.size(); i++){
            if (words.get(i).charAt(0)!=s.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
