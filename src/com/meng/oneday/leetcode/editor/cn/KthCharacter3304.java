package com.meng.oneday.leetcode.editor.cn;

class KthCharacter3304 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了18.18% 的Java用户
     * 	内存消耗:41.1 MB,击败了51.82% 的Java用户
     * @param k
     * @return
     */
    public char kthCharacter3304(int k) {
        if (k == 1){
            return 'a';
        }
        StringBuilder sb = new StringBuilder("a");
        while (true){
            int len = sb.length();
            for(int i = 0 ; i < len ; i++){
                if (sb.charAt(i) == 'z'){
                    sb.append('a');
                }else {
                    sb.append((char)(sb.charAt(i) + 1));
                }
            }
            if (sb.length() >= k){
                return sb.charAt(k - 1);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.4 MB,击败了100.00% 的Java用户
     * @param k
     * @return
     */
    public char kthCharacter(int k) {
        return (char) ('a' + Integer.bitCount(k - 1));
    }
}
