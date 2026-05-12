package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class BagOfTokensScore948 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了75.96% 的Java用户
     * 	内存消耗:44.7 MB,击败了16.35% 的Java用户
     * @param tokens
     * @param power
     * @return
     */
    public int bagOfTokensScore948(int[] tokens, int power) {
        //从小到大排序
        Arrays.sort(tokens);
        int res = 0;
        int l = 0 , r = tokens.length - 1;
        while (l <= r){
            if (power >= tokens[l]){
                power -= tokens[l];
                res++;
                l++;
            }else if (res > 0){
                power += (tokens[r--] - tokens[l++]);
            }else{
                break;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了86.54% 的Java用户
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int lo = 0, hi = tokens.length - 1;
        int points = 0, ans = 0;
        while (lo <= hi && (P >= tokens[lo] || points > 0)) {
            while (lo <= hi && P >= tokens[lo]) {
                P -= tokens[lo++];
                points++;
            }

            ans = Math.max(ans, points);
            if (lo <= hi && points > 0) {
                P += tokens[hi--];
                points--;
            }
        }

        return ans;
    }

}
