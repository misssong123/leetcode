package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SmallestString2734 {
    /**
     * 解答成功:
     * 	执行耗时:94 ms,击败了5.80% 的Java用户
     * 	内存消耗:48.9 MB,击败了11.59% 的Java用户
     * @param s
     * @return
     */
    public String smallestStringMy(String s) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        //找到第一个不为a的下标
        while (index < s.length() && s.charAt(index) == 'a'){
            sb.append(s.charAt(index));
            index++;
        }
        if (index == s.length()){
            sb.deleteCharAt(sb.length()-1);
            sb.append('z');
        }
        //找到下一个为a的下标
        while (index < s.length() && s.charAt(index) != 'a'){
            sb.append((char)(s.charAt(index)-1));
            index++;
        }
        while (index < s.length()){
            sb.append(s.charAt(index));
            index++;
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了89.86% 的Java用户
     * 	内存消耗:48.3 MB,击败了86.96% 的Java用户
     * @param S
     * @return
     */
    public String smallestString(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) {
            if (s[i] > 'a') {
                // 继续向后遍历
                for (; i < n && s[i] > 'a'; i++) {
                    s[i]--;
                }
                return new String(s);
            }
        }
        // 所有字母均为 a
        s[n - 1] = 'z';
        return new String(s);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
