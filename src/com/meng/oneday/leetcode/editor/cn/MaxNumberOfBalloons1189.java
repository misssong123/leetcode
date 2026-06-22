package com.meng.oneday.leetcode.editor.cn;

class MaxNumberOfBalloons1189 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.5 MB,击败了76.39% 的Java用户
     * @param text
     * @return
     */
    public int maxNumberOfBalloons1189(String text) {
        int[] cnt = new int[26];
        for (char c : text.toCharArray()) {
            cnt[c - 'a']++;
        }
        int res = text.length();
        for (char c : "balon".toCharArray()) {
            if (c == 'l' || c == 'o'){
                res = Math.min(res, cnt[c - 'a'] / 2);
            }else{
                res = Math.min(res, cnt[c - 'a']);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了25.00% 的Java用户
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int['z' + 1];
        for (char ch : text.toCharArray()) {
            cnt[ch]++;
        }
        return min(cnt['a'], cnt['b'], cnt['l'] / 2, cnt['n'], cnt['o'] / 2);
    }

    private int min(int... nums) {
        int res = nums[0];
        for (int x : nums) {
            res = Math.min(res, x);
        }
        return res;
    }

}
