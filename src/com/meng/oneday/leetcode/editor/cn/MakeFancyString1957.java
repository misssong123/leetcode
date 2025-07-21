package com.meng.oneday.leetcode.editor.cn;

class MakeFancyString1957 {
    /**
     * 解答成功:
     * 	执行耗时:63 ms,击败了5.78% 的Java用户
     * 	内存消耗:44.9 MB,击败了72.25% 的Java用户
     * @param s
     * @return
     */
    public String makeFancyString1957(String s) {
        StringBuffer sb =  new StringBuffer();
        for(int i = 0 ; i < s.length(); i++){
            if (i >= 2 && s.charAt(i) == s.charAt(i-1) && s.charAt(i) == s.charAt(i-2)){
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:39 ms,击败了47.98% 的Java用户
     * 	内存消耗:44.8 MB,击败了85.55% 的Java用户
     * @param s
     * @return
     */
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt++;
            if (cnt < 3) {
                ans.append(s.charAt(i));
            }
            if (i < s.length() - 1 && s.charAt(i) != s.charAt(i + 1)) {
                cnt = 0; // 当前字母和下个字母不同，重置计数器
            }
        }
        return ans.toString();
    }

}
