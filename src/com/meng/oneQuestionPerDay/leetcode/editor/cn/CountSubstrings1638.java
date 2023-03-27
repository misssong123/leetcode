package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class CountSubstrings1638 {
    /**
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 53.61%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 69.07%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     * @param s
     * @param t
     * @return
     */
    public int countSubstrings(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int ans = 0;
        for(int i = 0 ; i < sLen ; i++){
            for(int j = 0 ; j < tLen ; j++){
                int diff = 0;
                for(int k = 0 ; i + k < sLen && j + k < tLen ; k++){
                    diff += s.charAt(i+k) == t.charAt(j+k)?0:1;
                    if (diff > 1){
                        break;
                    }else if (diff == 1){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSubstrings1638 demo = new CountSubstrings1638();
        String s = "a";
        String t = "bb";
        System.out.println(demo.countSubstrings1(s,t));
    }

    /**
     * 方法一：枚举
     *
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 53.61%
     * 的用户
     * 内存消耗：
     * 39.3 MB
     * , 在所有 Java 提交中击败了
     * 82.47%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public int countSubstrings1(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = 0;
                for (int k = 0; i + k < m && j + k < n; k++) {
                    diff += s.charAt(i + k) == t.charAt(j + k) ? 0 : 1;
                    if (diff > 1) {
                        break;
                    } else if (diff == 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：动态规划
     * @param s
     * @param t
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 53.61%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 40.21%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public int countSubstrings2(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dpl = new int[m + 1][n + 1];
        int[][] dpr = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dpl[i + 1][j + 1] = s.charAt(i) == t.charAt(j) ? (dpl[i][j] + 1) : 0;
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dpr[i][j] = s.charAt(i) == t.charAt(j) ? (dpr[i + 1][j + 1] + 1) : 0;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    ans += (dpl[i][j] + 1) * (dpr[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }

}

