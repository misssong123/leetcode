package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class GetSmallestString3106 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.8 MB,击败了39.41% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String getSmallestString(String s, int k) {
        char[] chars = s.toCharArray();
        for(int i = 0 ; i < chars.length ; i++){
            int dis = Math.min(chars[i]-'a','z'-chars[i]+1);
            if (dis > k){
                chars[i] -= k;
                break;
            }
            chars[i] = 'a';
            k -= dis;
        }
        return new String(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
