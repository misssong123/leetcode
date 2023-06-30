package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ShortestCommonSupersequence1092 {
    /**
     * 1.选择两者中较小的字符串
     * 2.计算较短字符串的所有字串
     * 3.计算两者最大的公共字串
     * 4.将原来两个字符串依次填充到公共字串的特定位置
     * @param str1
     * @param str2
     * @return
     * 超时
     * "bcaaacbbbcbdcaddadcacbdddcdcccdadadcbabaccbccdcdcbcaccacbbdcbabb"
     * "dddbbdcbccaccbababaacbcbacdddcdabadcacddbacadabdabcdbaaabaccbdaa"
     */
    Set<String> cache = new HashSet<>();
    List<Character> temp = new ArrayList<>();
    StringBuffer sb = new StringBuffer();
    String res = "";
    public String shortestCommonSupersequence(String str1, String str2) {
        if (str1.length() > str2.length()){
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        dfs(str1,0,str1.length(),str1.length(),false);
        dfs(str2,0,str2.length(),str1.length(),true);
        if (res.equals("")){
            return str1+str2;
        }
        sb = new StringBuffer();
        int index1 = 0;
        int index2 = 0;
        for(int i = 0 ; i < res.length() ; i++){
            char c = res.charAt(i);
            int i1 ;
            int i2;
            if (i==0){
                i1 = str1.indexOf(c);
                i2 = str2.indexOf(c);
            }else {
                i1 = str1.indexOf(c,index1);
                i2 = str2.indexOf(c,index2);

            }
            sb.append(str1, index1, i1);
            sb.append(str2, index2, i2);
            sb.append(c);
            index1 = i1+1;
            index2 = i2+1;
        }
        sb.append(str1,index1,str1.length());
        sb.append(str2,index2,str2.length());
        return  sb.toString();
    }
    public void dfs(String str,int index,int len , int limit,boolean flag){
        if (limit == 0 || index >= len){
            temp.stream().forEach(item->sb.append(item));
            if (flag){
                if (cache.contains(sb.toString())&&sb.length()>res.length()){
                    res = sb.toString();
                }
            }else {
                cache.add(sb.toString());
            }
            sb.delete(0,sb.length());
            return;
        }
        //不存在当前位置数据
        dfs(str,index+1,len,limit,flag);
        //添加当前位置数据
        temp.add(str.charAt(index));
        dfs(str,index+1,len,limit-1,flag);
        temp.remove(temp.size()-1);
    }

    public static void main(String[] args) {
        ShortestCommonSupersequence1092 demo = new ShortestCommonSupersequence1092();
        String str1 = "bcaaacbbbcbdcaddadcacbdddcdcccdadadcbabaccbccdcdcbcaccacbbdcbabb";
        String str2 = "dddbbdcbccaccbababaacbcbacdddcdabadcacddbacadabdabcdbaaabaccbdaa";
        System.out.println(demo.shortestCommonSupersequence(str1,str2));

    }

    /**
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 50.44%
     * 的用户
     * 内存消耗：
     * 45.1 MB
     * , 在所有 Java 提交中击败了
     * 53.98%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     * @param str1
     * @param str2
     * @return
     */
    public String shortestCommonSupersequence1(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][m] = n - i;
        }
        for (int i = 0; i < m; ++i) {
            dp[n][i] = m - i;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        int t1 = 0, t2 = 0;
        while (t1 < n && t2 < m) {
            if (str1.charAt(t1) == str2.charAt(t2)) {
                res.append(str1.charAt(t1));
                ++t1;
                ++t2;
            } else if (dp[t1 + 1][t2] == dp[t1][t2] - 1) {
                res.append(str1.charAt(t1));
                ++t1;
            } else if (dp[t1][t2 + 1] == dp[t1][t2] - 1) {
                res.append(str2.charAt(t2));
                ++t2;
            }
        }
        if (t1 < n) {
            res.append(str1.substring(t1));
        } else if (t2 < m) {
            res.append(str2.substring(t2));
        }
        return res.toString();
    }

}

