package com.meng.oneday.leetcode.editor.cn;

class ProcessStr3612 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.3 MB,击败了27.27% 的Java用户
     * @param s
     * @return
     */
    public String processStr3612(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*'){
                if (sb.length() > 0){
                    sb.deleteCharAt(sb.length()-1);
                }
            }else if (c == '#'){
                sb.append(sb);
            }else if (c == '%'){
                sb.reverse();
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.4 MB,击败了27.27% 的Java用户
     * @param s
     * @return
     */
    String processStr(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                if (ans.length() > 0) {
                    ans.setLength(ans.length() - 1);
                }
            } else if (c == '#') {
                ans.append(ans);
            } else if (c == '%') {
                ans.reverse();
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }

}
