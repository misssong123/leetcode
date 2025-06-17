package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class RemoveDuplicateLetters316 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了31.95% 的Java用户
     * 	内存消耗:41.5 MB,击败了61.24% 的Java用户
     * @param s
     * @return
     */
    public String removeDuplicateLetters316(String s) {
        int[] cnt = new int[26];
        List<Character> list = new ArrayList<>();
        for(char c : s.toCharArray()){
            cnt[c - 'a']++;
        }
        for(char c : s.toCharArray()){
            cnt[c - 'a']--;
            if(!list.contains(c)){
                if (list.isEmpty()||list.get(list.size()-1)<c){
                    list.add(c);
                }else {
                    while (!list.isEmpty()&&list.get(list.size()-1)>c&&cnt[list.get(list.size()-1)-'a']>0){
                        list.remove(list.size()-1);
                    }
                    list.add(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : list){
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了70.86% 的Java用户
     * 	内存消耗:41.5 MB,击败了56.18% 的Java用户
     * @param S
     * @return
     */
    public String removeDuplicateLetters(String S) {
        char[] s = S.toCharArray();
        int[] left = new int[26];
        for (char c : s) {
            left[c - 'a']++; // 统计每个字母的出现次数
        }
        StringBuilder ans = new StringBuilder(26);
        boolean[] inAns = new boolean[26];
        for (char c : s) {
            left[c - 'a']--;
            if (inAns[c - 'a']) { // ans 中不能有重复字母
                continue;
            }
            // 设 x = ans.charAt(ans.length() - 1)，
            // 如果 c < x，且右边还有 x，那么可以把 x 去掉，因为后面可以重新把 x 加到 ans 中
            while (ans.length()>0 && c < ans.charAt(ans.length() - 1) && left[ans.charAt(ans.length() - 1) - 'a'] > 0) {
                inAns[ans.charAt(ans.length() - 1) - 'a'] = false; // 标记 x 不在 ans 中
                ans.deleteCharAt(ans.length() - 1);
            }
            ans.append(c); // 把 c 加到 ans 的末尾
            inAns[c - 'a'] = true; // 标记 c 在 ans 中
        }
        return ans.toString();
    }

}
