package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinFlipsMonoIncr926 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了43.17% 的Java用户
     * 	内存消耗:44.5 MB,击败了58.99% 的Java用户
     * 枚举所有为0的位置，一次计算当前0为左边界翻转的次数
     * @param s
     * @return
     */
    public int minFlipsMonoIncr926(String s) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < s.length();i++){
            if (s.charAt(i) == '0'){
                list.add(i);
            }
        }
        if (list.isEmpty()){
            return 0;
        }
        //依次计算翻转次数
        int size = list.size();
        int res = size;
        for(int i = 0 ; i < list.size() ; i++){
            res = Math.min(res,size - 1-i + list.get(i)-i);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了38.85% 的Java用户
     * 	内存消耗:44.3 MB,击败了85.61% 的Java用户
     * @param s
     * @return
     */
    public int minFlipsMonoIncrOther1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] g = new int[n + 10];
        Arrays.fill(g, n + 10);
        for (int i = 0; i < n; i++) {
            int t = s.charAt(i) - '0';
            int l = 1, r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (g[mid] > t) r = mid;
                else l = mid + 1;
            }
            g[r] = t;
            ans = Math.max(ans, r);
        }
        return n - ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了95.68% 的Java用户
     * 	内存消耗:44.3 MB,击败了82.73% 的Java用户
     * @param s
     * @return
     */
    public int minFlipsMonoIncrOther2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = n;
        int[] sum = new int[n + 10];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + (cs[i - 1] - '0');
        for (int i = 1; i <= n; i++) {
            int l = sum[i - 1], r = (n - i) - (sum[n] - sum[i]);
            ans = Math.min(ans, l + r);
        }
        return ans;
    }

}
