package com.meng.oneday.leetcode.editor.cn;

class CheckValidString678 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了81.25% 的Java用户
     * @param s
     * @return
     */
    public boolean checkValidString678(String s) {
        int l = 0 , r = 0 , x = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            }else if (c == ')') {
                r++;
                if (r > l + x) {
                    return false;
                }
            }else {
                x++;
            }
        }
        l = 0 ;r = 0 ; x = 0;
        for (int i = s.length() -1 ; i >= 0 ; i--) {
            if (s.charAt(i) == ')') {
                r++;
            }else if (s.charAt(i) == '(') {
                l++;
                if (l > r + x) {
                    return false;
                }
            }else {
                x++;
            }
        }
        return true;
    }
    /**
     * 未思考*在左括号前面的问题
     * @param s
     * @return
     */
    public boolean checkValidStringError(String s) {
        int l = 0 , r = 0 , x = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            }else if (c == ')') {
                r++;
                if (r > l + x) {
                    return false;
                }
            }else {
                x++;
            }
        }
        return l <= r + x;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了78.85% 的Java用户
     * @param s
     * @return
     */
    public boolean checkValidStringOther(String s) {
        int mn = 0; // 未匹配的左括号的个数的最小值
        int mx = 0; // 未匹配的左括号的个数的最大值

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                mn++;
                mx++;
            } else if (ch == ')') {
                mn--;
                mx--;
                if (mx < 0) { // 右括号太多了
                    return false;
                }
            } else { // '*'
                mn--; // '*' 改成右括号
                mx++; // '*' 改成左括号
            }
            mn = Math.max(mn, 0); // 未匹配的左括号的个数不能为负
        }

        return mn == 0; // 最终未匹配的左括号的个数能是 0
    }
}
