package com.meng.oneday.leetcode.editor.cn;

class MinInsertions1541 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.9 MB,击败了51.35% 的Java用户
     * @param s
     * @return
     */
    public int minInsertions1541(String s) {
        int l = 0 , r = 0,res = 0;
        for(char c : s.toCharArray()){
            //左括号 '(' 必须在对应的连续两个右括号 '))' 之前
            if(c == '('){
                l++;
                if (r % 2 != 0){
                    r++;
                    res++;
                }
            }else {
                //任何左括号 '(' 必须对应两个连续的右括号 '))'
                r++;
                if(r > l * 2){
                    res++;
                    l++;
                }
            }
        }
        return res + (l * 2 - r);
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:47 MB,击败了20.27% 的Java用户
     * @param S
     * @return
     */
    public int minInsertions(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int left = 0; // 未配对的左括号个数
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (s[i] == '(') {
                left++; // 未配对的左括号
                continue;
            }

            if (left > 0) {
                left--; // 左右括号配对
            } else {
                ans++; // 右括号太多了，补一个左括号
            }

            // 必须有两个连续的右括号，也就是 s[i+1] 必须也是右括号
            if (i < n - 1 && s[i + 1] == ')') {
                i++;
            } else {
                ans++; // 补一个右括号
            }
        }

        // 左括号太多了，补上缺失的右括号
        return ans + left * 2;
    }
}
