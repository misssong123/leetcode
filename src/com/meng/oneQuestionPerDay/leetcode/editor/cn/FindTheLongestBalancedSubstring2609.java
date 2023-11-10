package com.meng.oneQuestionPerDay.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class FindTheLongestBalancedSubstring2609 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了62.21% 的Java用户
     * @param s
     * @return
     */
    public int findTheLongestBalancedSubstringMy(String s) {
        int res = 0 ;
        int zeroIndex = s.indexOf("0");
        while (zeroIndex != -1){
            int firstIndex = s.indexOf("1",zeroIndex);
            if (firstIndex == -1){
                break;
            }
            int nextZeroIndex = s.indexOf("0",firstIndex);
            res = Math.max(res,Math.min(firstIndex -zeroIndex,
                    (nextZeroIndex==-1?s.length():nextZeroIndex)-firstIndex)*2);
            zeroIndex = nextZeroIndex;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.7 MB,击败了78.42% 的Java用户
     * @param s
     * @return
     */
    public int findTheLongestBalancedSubstring(String s) {
        int res = 0;
        int n = s.length();
        int[] count = new int[2];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                count[1]++;
                res = Math.max(res, 2 * Math.min(count[0], count[1]));
            } else if (i == 0 || s.charAt(i - 1) == '1') {
                count[0] = 1;
                count[1] = 0;
            } else {
                count[0]++;
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
