package com.meng.oneday.leetcode.editor.cn;

class ShiftingLetters848 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了66.29% 的Java用户
     * 	内存消耗:70.2 MB,击败了98.88% 的Java用户
     * @param s
     * @param shifts
     * @return
     */
    public String shiftingLetters848(String s, int[] shifts) {
        char[] chars = s.toCharArray();
        int n = shifts.length;
        int pre = 0;
        for (int i = n - 1; i >= 0; i--) {
            pre += shifts[i];
            pre %= 26;
            chars[i]= (char) (((chars[i] - 'a' + pre) % 26) + 'a');
        }
        return new String(chars);
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了26.97% 的Java用户
     * 	内存消耗:72 MB,击败了46.07% 的Java用户
     * @param S
     * @param shifts
     * @return
     */
    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder ans = new StringBuilder();
        int X = 0;
        for (int shift: shifts)
            X = (X + shift) % 26;

        for (int i = 0; i < S.length(); ++i) {
            int index = S.charAt(i) - 'a';
            ans.append((char) ((index + X) % 26 + 97));
            X = Math.floorMod(X - shifts[i], 26);
        }

        return ans.toString();
    }

}
