package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinDeletionSize955 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.57% 的Java用户
     * 	内存消耗:43.2 MB,击败了28.57% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize955(String[] strs) {
        int row = strs.length , col = strs[0].length();
        int res = 0;
        int[] dp = new int[row];
        int[] temp = new int[row];
        for (int i = 0 ; i < col ; i++){
            Arrays.fill(temp,0);
            int num = row * (col - i);
            temp[0] = num;
            boolean moreFlag = true;
            boolean lessFlag = false;
            for (int j = 1 ; j < row ; j++){
                if(strs[j].charAt(i) > strs[j-1].charAt(i)|| dp[j] > dp[j - 1]){
                    temp[j] = num + j;
                }else if (strs[j].charAt(i) == strs[j-1].charAt(i)){
                    temp[j] = temp[j-1];
                    moreFlag = false;
                }else{
                    res++;
                    lessFlag = true;
                    break;
                }
            }
            if (lessFlag){
                continue;
            }
            if (moreFlag){
                break;
            }
            for (int j = 0 ; j < row ; j++){
                dp[j] += temp[j];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了52.38% 的Java用户
     * @param strs
     * @return
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int size = n - 1;
        int[] checkList = new int[size];
        for (int i = 0; i < size; i++) {
            checkList[i] = i;
        }

        int ans = 0;
        next:
        for (int j = 0; j < m; j++) {
            for (int t = 0; t < size; t++) {
                int i = checkList[t];
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    // j 列不是升序，必须删
                    ans++;
                    continue next;
                }
            }
            // j 列是升序，不删更好
            int newSize = 0;
            for (int t = 0; t < size; t++) {
                int i = checkList[t];
                if (strs[i].charAt(j) == strs[i + 1].charAt(j)) {
                    // 相邻字母相等，下一列 i 和 i+1 需要继续比大小
                    checkList[newSize++] = i; // 原地覆盖
                }
            }
            size = newSize;
        }
        return ans;
    }

}
