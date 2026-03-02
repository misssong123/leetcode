package com.meng.oneday.leetcode.editor.cn;

class MinPartitions1689 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了4.89% 的Java用户
     * 	内存消耗:47 MB,击败了15.22% 的Java用户
     * @param n
     * @return
     */
    public int minPartitions1689(String n) {
        int ans = 0;
        for (char c : n.toCharArray()) {
            ans = Math.max(ans, c - '0');
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了32.61% 的Java用户
     * 	内存消耗:46.8 MB,击败了68.48% 的Java用户
     * @param n
     * @return
     */
    public int minPartitions(String n) {
        int mx = 0;
        for (char ch : n.toCharArray()) {
            mx = Math.max(mx, ch);
        }
        return mx - '0';
    }

}
