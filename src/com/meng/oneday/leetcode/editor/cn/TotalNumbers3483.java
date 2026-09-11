package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class TotalNumbers3483 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了53.57% 的Java用户
     * 	内存消耗:43.2 MB,击败了96.43% 的Java用户
     * @param digits
     * @return
     */
    public int totalNumbers3483(int[] digits) {
        //统计数字个数
        int[] cnts = new int[10];
        for (int digit : digits) {
            cnts[digit]++;
        }
        System.out.println(Arrays.toString(cnts));
        int res = 0;
        for (int i = 0 ; i < 10 ; i+= 2){
            if (cnts[i] == 0){
                continue;
            }
            for (int j = 0 ; j < 10 ; j++){
                if (cnts[j] == 0){
                    continue;
                }
                if(j == i && cnts[j] < 2){
                    continue;
                }
                for (int k = 1 ; k < 10 ; k++){
                    if (cnts[k] == 0){
                        continue;
                    }
                    if (k == i && k == j && cnts[k] < 3){
                        continue;
                    }
                    if ((k == i || k == j) && cnts[k] < 2){
                        continue;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了85.71% 的Java用户
     * 	内存消耗:45.7 MB,击败了39.29% 的Java用户
     * @param digits
     * @return
     */
    public int totalNumbersOther(int[] digits) {
        Set<Integer> set = new HashSet<>();
        int n = digits.length;
        for (int i = 0; i < n; i++) { // 个位数
            int a = digits[i];
            if (a % 2 > 0) {
                continue;
            }
            for (int j = 0; j < n; j++) { // 十位数
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; k++) { // 百位数
                    int c = digits[k];
                    if (c == 0 || k == i || k == j) {
                        continue;
                    }
                    set.add(c * 100 + digits[j] * 10 + a);
                }
            }
        }
        return set.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了96.43% 的Java用户
     * @param digits
     * @return
     */
    public int totalNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }

        int nonZeros = 0;
        int kinds = 0;
        int singles = 0;
        for (int d = 0; d < 10; d++) {
            if (cnt[d] == 0) {
                continue;
            }
            kinds++;
            if (d > 0) {
                nonZeros++;
                if (cnt[d] == 1) {
                    singles++;
                }
            }
        }

        int ans = 0;

        // 枚举个位填偶数 d
        for (int d = 0; d < 10; d += 2) {
            int c = cnt[d];
            if (c == 0) {
                continue;
            }

            // 十位填任意数字
            int k = kinds;
            if (c == 1) {
                k--;
            }

            // 百位填任意非零数字
            int nz = nonZeros;
            if (d > 0 && c == 1) {
                nz--;
            }

            // 恰好出现一次的非零数字，不能同时填入十位和百位
            int s = singles;
            if (d > 0) {
                if (c == 1) {
                    s--;
                } else if (c == 2) {
                    s++; // 个位数填入 d 后，d 恰好出现一次
                }
            }

            ans += k * nz - s;
        }

        return ans;
    }

}
