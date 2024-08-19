package com.meng.interview150.leetcode.editor.cn;

class Interview039CanConstruct {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.73% 的Java用户
     * 	内存消耗:43.6 MB,击败了80.84% 的Java用户
     */
    public boolean canConstructMy(String ransomNote, String magazine) {
       int[] cache = new int[26];
       for(char c : magazine.toCharArray()){
           cache[c-'a']++;
       }
       for(char c : ransomNote.toCharArray()){
           if(cache[c-'a']-- <= 0){
               return false;
           }
       }
       return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了50.24% 的Java用户
     * 	内存消耗:43.7 MB,击败了63.91% 的Java用户
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


}
