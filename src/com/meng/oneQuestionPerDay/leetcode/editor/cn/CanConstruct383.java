package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class CanConstruct383 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.82% 的Java用户
     * 	内存消耗:43.7 MB,击败了9.12% 的Java用户
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()){
            return false;
        }
        int[] nums = new int[26];
        for (char c : magazine.toCharArray()){
            nums[c-'a']++;
        }
        for (char c : ransomNote.toCharArray()){
            nums[c-'a']--;
            if (nums[c-'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
