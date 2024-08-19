package com.meng.interview150.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class Interview045IsHappy {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了81.55% 的Java用户
     * 	内存消耗:39.8 MB,击败了64.66% 的Java用户
     * @param n
     * @return
     */
    public boolean isHappyMy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            int next = 0;
            while (n !=0){
                int num = n % 10;
                next += num * num;
                n /= 10;
            }
            n = next;
        }
        return true;
    }


    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.7 MB,击败了84.44% 的Java用户
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }
}
