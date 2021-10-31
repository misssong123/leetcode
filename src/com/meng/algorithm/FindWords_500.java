package com.meng.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 500. 键盘行
 * 难度
 * 简单
 *
 * 178
 *
 *
 *
 *
 *
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * American keyboard
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 */
public class FindWords_500 {
    public String[] findWords(String[] words) {
        int[] cache = new int[26];
        for(char c : "qwertyuiop".toCharArray()){
            cache[c-'a'] = 1;
        }
        for(char c : "asdfghjkl".toCharArray()){
            cache[c-'a'] = 2;
        }
        for(char c : "zxcvbnm".toCharArray()){
            cache[c-'a'] = 3;
        }
        List<String> temp = new ArrayList<>();
        for (String s : words){
            int pre = -1;
            boolean flag = true;
            for(char c : s.toLowerCase().toCharArray()){
                if (pre == -1){
                    pre = cache[c-'a'];
                }
                if (cache[c-'a']==pre){
                    continue;
                }else {
                    flag =  false;
                    break;
                }
            }
            if (flag)
            temp.add(s);
        }
        String [] res =  new String[temp.size()];
        int index = 0;
        for(String s : temp){
            res[index++] = s;
        }
        return res;
    }

    /**
     * 方法一：遍历
     *
     * 我们为每一个英文字母标记其对应键盘上的行号，然后检测字符串中所有字符对应的行号是否相同。
     *
     * 我们可以预处理计算出每个字符对应的行号。
     * 遍历字符串时，统一将大写字母转化为小写字母方便计算。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/keyboard-row/solution/jian-pan-xing-by-leetcode-solution-bj5e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param words
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 36.2 MB
     * , 在所有 Java 提交中击败了
     * 77.97%
     * 的用户
     * 通过测试用例：
     * 22 / 22
     */
    public String[] findWords1(String[] words) {
        List<String> list = new ArrayList<String>();
        String rowIdx = "12210111011122000010020202";
        for (String word : words) {
            boolean isValid = true;
            char idx = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
            for (int i = 1; i < word.length(); ++i) {
                if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != idx) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                list.add(word);
            }
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }



}
