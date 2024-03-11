package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class CapitalizeTitle2129 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.87% 的Java用户
     * 	内存消耗:40.9 MB,击败了68.10% 的Java用户
     * @param title
     * @return
     */
    public String capitalizeTitleMy(String title) {
        int len = title.length();
        title = title.toLowerCase();
        int pre = 0;
        char[] chars = title.toCharArray();
        for (int i = 0 ; i < len ; i++){
            if (chars[i] == ' '){
                if (i - pre > 2){
                    chars[pre] = (char)(chars[pre] - 32);
                }
                pre = i + 1;
            }
        }
        if (len -pre>2){
            chars[pre] = (char)(chars[pre] - 32);
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了93.87% 的Java用户
     * 	内存消耗:40.9 MB,击败了72.39% 的Java用户
     * @param title
     * @return
     */
    public String capitalizeTitle(String title) {
        StringBuilder sb = new StringBuilder(title);
        int n = title.length();
        int l = 0, r = 0;   // 单词左右边界（左闭右开）
        while (r < n) {
            while (r < n && sb.charAt(r) != ' ') {
                ++r;
            }
            // 对于每个单词按要求处理
            if (r - l > 2) {
                sb.setCharAt(l, Character.toUpperCase(sb.charAt(l)));
                ++l;
            }
            while (l < r) {
                sb.setCharAt(l, Character.toLowerCase(sb.charAt(l)));
                ++l;
            }
            l = r + 1;
            ++r;
        }
        return sb.toString();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
