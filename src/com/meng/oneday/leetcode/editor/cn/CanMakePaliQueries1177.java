package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CanMakePaliQueries1177 {
    /**
     * 内存受限
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueriesError1(String s, int[][] queries) {
        int len = s.length();
        int[][] dp = new int[len][len];
        //初始化
        for (int i = 0; i < len; i++) {
            for(int j = i ; j >=0 ; j--){
                int num = s.charAt(i) == s.charAt(j) ? 0 : 1;
                dp[i][j] = num;
                if(i - j > 1){
                    dp[i][j] += dp[i-1][j+1];
                }
            }
        }
        //计算
        List<Boolean> res = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            if (dp[query[1]][query[0]] <= query[2]
            || isSample(s.substring(query[0], query[1] + 1), query[2])) {
                res.add(true);
            }else {
                res.add(false);
            }
        }
        return res;
    }

    /**
     * 超时
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueriesError2(String s, int[][] queries) {
        //计算
        List<Boolean> res = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            if ( isSample(s.substring(query[0], query[1] + 1), query[2])) {
                res.add(true);
            }else {
                res.add(false);
            }
        }
        return res;
    }

    private boolean isSample(String substring, int cnt) {
        int[] arr = new int[26];
        for (char c : substring.toCharArray()) {
            arr[c - 'a']++;
        }
        int num = 0;
        for (int i : arr) {
            if (i % 2 == 1) {
                num++;
            }
        }
        return num / 2  <= cnt;
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了25.00% 的Java用户
     * 	内存消耗:165.6 MB,击败了27.00% 的Java用户
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueriesOther(String s, int[][] queries) {
        int n = s.length();
        int[][] sum = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i].clone();
            sum[i + 1][s.charAt(i) - 'a']++;
        }

        List<Boolean> ans = new ArrayList<>(queries.length); // 预分配空间
        for (int[] q : queries) {
            int left = q[0], right = q[1], k = q[2], m = 0;
            for (int j = 0; j < 26; j++)
                m += (sum[right + 1][j] - sum[left][j]) % 2; // 奇数+1，偶数+0
            ans.add(m / 2 <= k);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:49 ms,击败了19.00% 的Java用户
     * 	内存消耗:165.8 MB,击败了21.00% 的Java用户
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueriesOther2(String s, int[][] queries) {
        int n = s.length();
        int[][] sum = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i].clone();
            sum[i + 1][s.charAt(i) - 'a']++;
            sum[i + 1][s.charAt(i) - 'a'] %= 2; // 偶数是 0
        }

        List<Boolean> ans = new ArrayList<Boolean>(queries.length); // 预分配空间
        for (int[] q : queries) {
            int left = q[0], right = q[1], k = q[2], m = 0;
            for (int j = 0; j < 26; j++)
                m += (sum[right + 1][j] != sum[left][j] ? 1 : 0);
            ans.add(m / 2 <= k);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了57.00% 的Java用户
     * 	内存消耗:118.8 MB,击败了98.00% 的Java用户
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int bit = 1 << (s.charAt(i) - 'a');
            sum[i + 1] = sum[i] ^ bit; // 该比特对应字母的奇偶性：奇数变偶数，偶数变奇数
        }

        List<Boolean> ans = new ArrayList<Boolean>(queries.length); // 预分配空间
        for (int[] q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int m = Integer.bitCount(sum[right + 1] ^ sum[left]);
            ans.add(m / 2 <= k);
        }
        return ans;
    }


}
