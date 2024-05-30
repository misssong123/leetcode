package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumLength2982 {
    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了90.74% 的Java用户
     * 	内存消耗:47.7 MB,击败了38.89% 的Java用户
     * @param s
     * @return
     */
    public int maximumLength(String s) {
        int n = s.length();
        int[][] cnt = new int[26][3];

        for (int i = 0, j = 0; i < s.length(); i = j) {
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int index = s.charAt(i) - 'a';
            int len = j - i;
            if (len > cnt[index][0]) {
                cnt[index][2] = cnt[index][1];
                cnt[index][1] = cnt[index][0];
                cnt[index][0] = len;
            } else if (len > cnt[index][1]) {
                cnt[index][2] = cnt[index][1];
                cnt[index][1] = len;
            } else if (len > cnt[index][2]) {
                cnt[index][2] = len;
            }
        }

        int res = 0;
        for (int[] vec : cnt) {
            res = Math.max(res, Math.max(vec[0] - 2, Math.min(vec[0] - 1, vec[1])));
            res = Math.max(res, vec[2]);
        }
        return res != 0 ? res : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
