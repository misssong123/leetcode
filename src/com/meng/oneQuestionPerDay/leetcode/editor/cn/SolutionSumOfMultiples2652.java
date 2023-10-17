package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;


class SolutionSumOfMultiples2652 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了5.12% 的Java用户
     * 	内存消耗:42.2 MB,击败了5.12% 的Java用户
     * @param n
     * @return
     */
    public int sumOfMultiplesMy(int n) {
        if (n < 3){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int res = 0;
        int first = 3 , second = 5 ,third = 7;
        while (first <= n || second <=n || third <= n){
            if (set.add(first)&&first <= n){
                res+=first;
            }
            if (set.add(second)&&second <=n){
                res+=second;
            }
            if (set.add(third)&& third <= n){
                res+=third;
            }
            first+=3;
            second+=5;
            third+=7;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了84.30% 的Java用户
     * 	内存消耗:38.4 MB,击败了74.40% 的Java用户
     * @param n
     * @return
     */
    public int sumOfMultiples1(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                res += i;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.2 MB,击败了97.95% 的Java用户
     * @param n
     * @return
     */
    public int sumOfMultiples(int n) {
        return f(n, 3) + f(n, 5) + f(n, 7) - f(n, 3 * 5) - f(n, 3 * 7) - f(n, 5 * 7) + f(n, 3 * 5 * 7);
    }
    public int f(int n, int m) {
        return (m + n / m * m) * (n / m) / 2;
    }

}

