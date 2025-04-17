class Solution {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了57.89% 的Java用户
     * 	内存消耗:54.9 MB,击败了34.74% 的Java用户
     * @param S
     * @return
     */
    public int minSwaps(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int c = 0;
        int j = s.length - 1;
        for (char b : s) {
            if (b == '[') {
                c++;
            } else if (c > 0) {
                c--;
            } else { // c == 0
                // 找最右边的左括号交换
                while (s[j] == ']') {
                    j--;
                }
                s[j] = ']'; // s[i] = '[' 可以省略
                ans++;
                c++; // s[i] 变成左括号，c 加一
            }
        }
        return ans;
    }


}
