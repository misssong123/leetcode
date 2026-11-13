package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LetterCasePermutation784 {
    List<String> res = null;

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.8 MB,击败了9.23% 的Java用户
     * @param s
     * @return
     */
    public List<String> letterCasePermutation784(String s) {
        res = new ArrayList<>();
        dfs(s.toCharArray(),0);
        return res;
    }

    private void dfs(char[] charArray, int index) {
        if(index == charArray.length){
            res.add(new String(charArray));
            return;
        }
        if(charArray[index] >= 'a' && charArray[index] <= 'z'){
            //不转换
            dfs(charArray,index+1);
            //转换
            char origin = charArray[index];
            charArray[index] = (char) (charArray[index] - 'a' + 'A');
            dfs(charArray,index+1);
            charArray[index] = origin;

        }else if(charArray[index] >= 'A' && charArray[index] <= 'Z'){
            //不转换
            dfs(charArray,index+1);
            //转换
            char origin = charArray[index];
            charArray[index] = (char) (charArray[index] - 'A' + 'a');
            dfs(charArray,index+1);
            charArray[index] = origin;
        }else {
            dfs(charArray,index+1);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.7 MB,击败了27.18% 的Java用户
     * @param S
     * @return
     */
    public List<String> letterCasePermutationOther(String S) {
        List<String> res = new ArrayList<>();
        char[] charArray = S.toCharArray();
        dfs(charArray, 0, res);
        return res;
    }

    private void dfs(char[] charArray, int index, List<String> res) {
        if (index == charArray.length) {
            res.add(new String(charArray));
            return;
        }

        dfs(charArray, index + 1, res);
        if (Character.isLetter(charArray[index])) {
            charArray[index] ^= 1 << 5;
            dfs(charArray, index + 1, res);
        }
    }

}
