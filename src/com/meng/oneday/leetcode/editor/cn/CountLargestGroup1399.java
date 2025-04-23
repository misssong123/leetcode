package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class CountLargestGroup1399 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了34.12% 的Java用户
     * 	内存消耗:43 MB,击败了11.76% 的Java用户
     * @param n
     * @return
     */
    public int countLargestGroup1399(int n) {
        int res = 0;
        int max = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 1 ; i <= n ; i++){
            int temp = i ;
            int num = 0;
            while(temp != 0){
                num += temp % 10;
                temp /= 10;
            }
            if (!map.containsKey(num)){
                map.put(num, new ArrayList<>());
            }
            map.get(num).add(i);
            int size = map.get(num).size();
            if (size > max){
                max = size;
                res = 1;
            }else if (size == max){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.7 MB,击败了74.12% 的Java用户
     * @param n
     * @return
     */
    public int countLargestGroup(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int m = s.length;
        int[][] memo = new int[m][m * 9 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int maxCnt = 0;
        int ans = 0;
        for (int target = 1; target <= m * 9; target++) { // 枚举目标数位和
            int cnt = dfs(0, target, true, s, memo);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                ans = 1;
            } else if (cnt == maxCnt) {
                ans++;
            }
        }
        return ans;
    }

    private int dfs(int i, int left, boolean limitHigh, char[] s, int[][] memo) {
        if (i == s.length) {
            return left == 0 ? 1 : 0;
        }
        if (!limitHigh && memo[i][left] != -1) {
            return memo[i][left];
        }

        int hi = limitHigh ? s[i] - '0' : 9; // 当前数位至多填 hi
        int res = 0;
        for (int d = 0; d <= Math.min(hi, left); d++) { // 枚举当前数位填 d
            res += dfs(i + 1, left - d, limitHigh && d == hi, s, memo);
        }

        if (!limitHigh) {
            memo[i][left] = res;
        }
        return res;
    }

}
