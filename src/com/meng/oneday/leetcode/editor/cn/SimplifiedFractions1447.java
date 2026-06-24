package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SimplifiedFractions1447 {
    /**
     * 解答成功:
     * 	执行耗时:19 ms,击败了53.33% 的Java用户
     * 	内存消耗:52.3 MB,击败了13.33% 的Java用户
     * @param n
     * @return
     */
    public List<String> simplifiedFractions1447(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 2 ; i <= n ; i++){
            list.add("1/"+i);
            for (int j = 2 ; j < i ; j++ ){
                if (gcd1447(j,i) > 1){
                    continue;
                }
                list.add(j+"/"+i);
            }
        }
        return list;
    }

    public static int gcd1447(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b; // 取余数
            a = temp;
        }
        return Math.abs(a); // 返回绝对值，确保负数也能正确处理
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了90.00% 的Java用户
     * 	内存消耗:52.3 MB,击败了13.33% 的Java用户
     * @param n
     * @return
     */
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<String>();
        for (int denominator = 2; denominator <= n; ++denominator) {
            for (int numerator = 1; numerator < denominator; ++numerator) {
                if (gcd(numerator, denominator) == 1) {
                    ans.add(numerator + "/" + denominator);
                }
            }
        }
        return ans;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

}
