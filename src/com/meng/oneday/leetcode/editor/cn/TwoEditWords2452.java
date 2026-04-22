package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class TwoEditWords2452 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了53.57% 的Java用户
     * 	内存消耗:43.6 MB,击败了71.43% 的Java用户
     * @param queries
     * @param dictionary
     * @return
     */
    public List<String> twoEditWords2452(String[] queries, String[] dictionary) {
        //构建字典集合
        List<String> ans = new ArrayList<>();
        for (String query : queries) {
            for (String s : dictionary) {
                int cnt = 0;
                for (int i = 0 ; i < s.length() ; i++){
                    if (query.charAt(i) != s.charAt(i)){
                        cnt++;
                    }
                    if (cnt > 2){
                        break;
                    }
                }
                if (cnt <= 2){
                    ans.add(query);
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了67.86% 的Java用户
     * 	内存消耗:43.8 MB,击败了32.14% 的Java用户
     * @param queries
     * @param dictionary
     * @return
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        for (String q : queries) {
            for (String s : dictionary) {
                int cnt = 0;
                for (int i = 0; i < s.length() && cnt <= 2; i++) {
                    if (q.charAt(i) != s.charAt(i)) {
                        cnt++;
                    }
                }
                if (cnt <= 2) {
                    ans.add(q);
                    break;
                }
            }
        }
        return ans;
    }
}
