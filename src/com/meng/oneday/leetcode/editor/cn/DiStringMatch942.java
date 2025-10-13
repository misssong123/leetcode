package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class DiStringMatch942 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了39.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了46.50% 的Java用户
     * @param s
     * @return
     */
    public int[] diStringMatch942(String s) {
        int n = s.length();
        int[] res = new int[n+1];
        Arrays.fill(res,-1);
        int l = 0 ,r = n;
        for(int i = 0 ; i < n ; i++){
            if(s.charAt(i) == 'I'){
                res[i] = l++;
            }else{
                res[i] = r--;
            }
        }
        res[n] = l;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了39.00% 的Java用户
     * 	内存消耗:44.2 MB,击败了77.50% 的Java用户
     * @param s
     * @return
     */
    public int[] diStringMatch(String s) {
        int n = s.length(), lo = 0, hi = n;
        int[] perm = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            perm[i] = s.charAt(i) == 'I' ? lo++ : hi--;
        }
        perm[n] = lo; // 最后剩下一个数，此时 lo == hi
        return perm;
    }
}
