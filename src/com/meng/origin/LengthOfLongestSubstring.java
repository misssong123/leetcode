package com.meng.origin;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s ="pwwkew";
        LengthOfLongestSubstring demo = new LengthOfLongestSubstring();
        System.out.println(demo.lengthOfLongestSubstring2(s));
    }
    int max =0;
    public int lengthOfLongestSubstring1(String s) {
        if (s.length()<=1)
            return s.length();
        Set<String> set = new HashSet<>();
        int length = s.length();
        for(int i = 0 ;i<length-max;i++){
            set.add(s.substring(i,i+1));
            for (int j = i+1;j<length;j++){
                //表示存在相同的子串
                if (!set.add(s.substring(j,j+1))){
                    if(set.size()>max)
                        max=set.size();
                    set.clear();
                    break;
                }
            }
            //表示从i开始到结束不存在相同的字母
                if(set.size()>max)
                    max=set.size();
                set.clear();
            }

        return max;
    }
    public int lengthOfLongestSubstring2(String s) {
        if (s.length()<=1)
            return s.length();
        int ans = 0,head=0,last=0;
        int length = s.length();
        Set<String> set = new HashSet<>();
        while (head<length-ans){
            while (last<length &&set.add(s.substring(last,last+1))){
                last++;
            }
            //如果last = length 表示结束
            if (last == length){
                ans = Math.max(ans,set.size());
                break;
            }
            //表示当前字符存在
            String temp = s.substring(last,last+1);
            ans = Math.max(ans,set.size());
            while (set.contains(temp)){
                set.remove(s.substring(head,head+1));
                head++;
            }
            set.add(temp);
            last++;
        }
        return ans;
    }
    /**
     * 官方解法
     *滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
