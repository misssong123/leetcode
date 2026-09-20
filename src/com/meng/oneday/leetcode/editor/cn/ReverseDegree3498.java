package com.meng.oneday.leetcode.editor.cn;

class ReverseDegree3498 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了77.78% 的Java用户
     * 	内存消耗:43.7 MB,击败了16.67% 的Java用户
     * @param s
     * @return
     */
    public int reverseDegree3498(String s) {
        char zChar = 'z';
        int ans = 0;
        int index = 1;
        for (char c : s.toCharArray()) {
            ans += (index * (zChar - c + 1));
            index ++;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了25.00% 的Java用户
     * 	内存消耗:43.2 MB,击败了80.56% 的Java用户
     * @param s
     * @return
     */
    public int reverseDegree(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += ('{' - s.charAt(i)) * (i + 1); // 下标从 1 开始
        }
        return ans;
    }
}
