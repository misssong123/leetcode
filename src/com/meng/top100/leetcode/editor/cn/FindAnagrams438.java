package com.meng.top100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindAnagrams438 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了65.22% 的Java用户
     * 	内存消耗:44 MB,击败了56.88% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams438(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()){
            return res;
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        //创建基准
        for(char c : p.toCharArray()){
            pCount[c-'a']++;
        }
        //初始化
        int len = p.length();
        for(int i = 0 ; i < len ; i++){
            sCount[s.charAt(i)-'a']++;
        }
        if(equals(sCount,pCount)){
            res.add(0);
        }
        //滑动窗口
        for(int i = 1 ; i <= s.length() - len ; i++){
            //移除上一个节点
            sCount[s.charAt(i-1)-'a']--;
            //新增下一个节点
            sCount[s.charAt(i+len-1)-'a']++;
            if(equals(sCount,pCount)){
                res.add(i);
            }
        }
        return res;
    }

    private boolean equals(int[] sCount, int[] pCount) {
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != pCount[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了55.66% 的Java用户
     * 	内存消耗:44 MB,击败了54.30% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsOther1(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] cntP = new int[26]; // 统计 p 的每种字母的出现次数
        int[] cntS = new int[26]; // 统计 s 的长为 p.length() 的子串 s' 的每种字母的出现次数
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++; // 统计 p 的字母
        }
        for (int right = 0; right < s.length(); right++) {
            cntS[s.charAt(right) - 'a']++; // 右端点字母进入窗口
            int left = right - p.length() + 1;
            if (left < 0) { // 窗口长度不足 p.length()
                continue;
            }
            if (Arrays.equals(cntS, cntP)) { // s' 和 p 的每种字母的出现次数都相同
                ans.add(left); // s' 左端点下标加入答案
            }
            cntS[s.charAt(left) - 'a']--; // 左端点字母离开窗口
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了97.19% 的Java用户
     * 	内存消耗:43.9 MB,击败了63.02% 的Java用户
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[26]; // 统计 p 的每种字母的出现次数
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'a';
            cnt[c]--; // 右端点字母进入窗口
            while (cnt[c] < 0) { // 字母 c 太多了
                cnt[s.charAt(left) - 'a']++; // 左端点字母离开窗口
                left++;
            }
            if (right - left + 1 == p.length()) { // s' 和 p 的每种字母的出现次数都相同
                ans.add(left); // s' 左端点下标加入答案
            }
        }
        return ans;
    }

}
