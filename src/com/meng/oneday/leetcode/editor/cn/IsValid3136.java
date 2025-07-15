package com.meng.oneday.leetcode.editor.cn;

class IsValid3136 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了79.31% 的Java用户
     * @param word
     * @return
     */
    public boolean isValid3136(String word) {
        if (word.length() < 3){
            return false;
        }
        //元音标识
        boolean isVowel = false;
        //辅音标识
        boolean isConsonant = false;
        for(int i = 0 ; i < word.length(); i++){
            char c = word.charAt(i);
            if(c >='a' && c <='z'){
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                    isVowel = true;
                }else {
                    isConsonant = true;
                }
            }else if (c >='A' && c <='Z'){
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                    isVowel = true;
                }else {
                    isConsonant = true;
                }
            }else if(c >='0' && c <='9'){
                continue;
            }else {
                return false;
            }
        }
        return isVowel && isConsonant;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.10% 的Java用户
     * 	内存消耗:41.3 MB,击败了58.62% 的Java用户
     * @param word
     * @return
     */
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        boolean[] f = new boolean[2];
        for (char c : word.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                c = Character.toLowerCase(c);
                f[c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ? 1 : 0] = true;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return f[0] && f[1];
    }

}
