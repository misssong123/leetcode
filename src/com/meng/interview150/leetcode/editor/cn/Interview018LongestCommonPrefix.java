package com.meng.interview150.leetcode.editor.cn;

class Interview018LongestCommonPrefix {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了71.12% 的Java用户
     * 	内存消耗:40.4 MB,击败了82.81% 的Java用户
     * @param strs
     * @return
     */
    public String longestCommonPrefixMy(String[] strs) {
        if(strs[0].length()==0){
            return "";
        }
        char[] chars = strs[0].toCharArray();
        int max = chars.length;
        for (int i = 1; i < strs.length; i++) {
            max = Math.min(max, strs[i].length());
            for (int j = 0; j < max; j++) {
                if (chars[j] != strs[i].charAt(j)) {
                    max = j;
                    break;
                }
            }
            if (max ==0){
                return "";
            }
        }
        return strs[0].substring(0,max);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了54.07% 的Java用户
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
