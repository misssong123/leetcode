package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interview101LetterCombinations {
    char[][] map = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
    List<String> res = new ArrayList<>();

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.2 MB,击败了19.87% 的Java用户
     * @param digits
     * @return
     */
    public List<String> letterCombinationsMy(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        StringBuffer sb = new StringBuffer();
        dfs(digits,0,sb);
        return res;
    }

    private void dfs(String digits, int index, StringBuffer sb) {
        if (index == digits.length()){
            res.add(sb.toString());
            return;
        }
        int n = digits.charAt(index) - '2';
        for(char c : map[n]){
            sb.append(c);
            dfs(digits,index + 1,sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了50.64% 的Java用户
     * 	内存消耗:41.1 MB,击败了63.59% 的Java用户
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

}
