package com.meng.oneday.leetcode.editor.cn;

class ShortestBeautifulSubstring2904 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了89.91% 的Java用户
     * 	内存消耗:43.4 MB,击败了73.85% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String shortestBeautifulSubstring2904(String s, int k) {
        int l = 0 ;
        String ans = s + s;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1'){
                cnt++;
            }
            //满足条件
            if (cnt == k){
                //选取最短字符
                while (s.charAt(l) != '1'){
                    l++;
                }
                if(ans.length() > i - l + 1){
                    ans = s.substring(l,i+1);
                }else if (ans.length() == i - l + 1){
                    ans = ans.compareTo(s.substring(l,i+1)) < 0 ? ans : s.substring(l,i+1);
                }
                //移除导致不符合条件
                cnt--;
                l++;
            }
        }
        return ans.length() > s.length() ? "" : ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了34.40% 的Java用户
     * 	内存消耗:43.3 MB,击败了81.19% 的Java用户
     * @param S
     * @param k
     * @return
     */
    public String shortestBeautifulSubstring(String S, int k) {
        if (S.replace("0", "").length() < k) {
            return "";
        }
        char[] s = S.toCharArray();
        String ans = S;
        int cnt1 = 0, left = 0;
        for (int right = 0; right < s.length; right++) {
            cnt1 += s[right] - '0';
            while (cnt1 > k || s[left] == '0') {
                cnt1 -= s[left++] - '0';
            }
            if (cnt1 == k) {
                String t = S.substring(left, right + 1);
                if (t.length() < ans.length() || t.length() == ans.length() && t.compareTo(ans) < 0) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
