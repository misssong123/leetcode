package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KMirror2081 {
    private static final int MAX_N = 30;
    private static final List<Long>[] ans = new ArrayList[10];
    private static boolean initialized = false;
    // 这样写比 static block 快
    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(ans, i -> new ArrayList<>());
        for (int base = 1; ; base *= 10) {
            // 生成奇数长度回文数，例如 base = 10，生成的范围是 101 ~ 999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
            // 生成偶数长度回文数，例如 base = 10，生成的范围是 1001 ~ 9999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
        }
    }

    private boolean doPalindrome(long x) {
        boolean done = true;
        for (int k = 2; k < 10; k++) {
            if (ans[k].size() < MAX_N && isKPalindrome(x, k)) {
                ans[k].add(x);
            }
            if (ans[k].size() < MAX_N) {
                done = false;
            }
        }
        if (!done) {
            return false;
        }

        for (int k = 2; k < 10; k++) {
            // 原地求前缀和
            List<Long> s = ans[k];
            for (int i = 1; i < MAX_N; i++) {
                s.set(i, s.get(i) + s.get(i - 1));
            }
        }
        return true;
    }

    // 力扣 9. 回文数
    private boolean isKPalindrome(long x, int k) {
        if (x % k == 0) {
            return false;
        }
        int rev = 0;
        while (rev < x / k) {
            rev = rev * k + (int) (x % k);
            x /= k;
        }
        return rev == x || rev == x / k;
    }

    /**
     * 解答成功:
     * 	执行耗时:208 ms,击败了25.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了33.33% 的Java用户
     * @param k
     * @param n
     * @return
     */
    public long kMirrorOther(int k, int n) {
        init();
        return ans[k].get(n - 1);
    }
    //十进制回文数
    final static List<Integer> decimal = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    final static List<Integer>[] other = new ArrayList[10];

    /**
     * 超时
     * @param k
     * @param n
     * @return
     */
    public long kMirror2081(int k, int n) {
        if (other[k] == null){
            other[k] = new ArrayList<>();
        }
        int res = 0;
        //计算已有的k进制回文数
        for(int num : other[k]){
            res += num;
            n--;
            if (n == 0){
                return res;
            }
        }
        int pre = other[k].isEmpty()?0:other[k].get(other[k].size() - 1);
        //计算已经计算的10进制回文个数
        for(int num : decimal){
            if(n == 0){
                return res;
            }
            if (num > pre &&isPalindrome(num,k)) {
                res += num;
                n--;
                other[k].add(num);
            }
        }
        for(int i = decimal.get(decimal.size() - 1) + 1;n > 0;i++){
            if (isPalindrome(i,10)){
                decimal.add(i);
                if (isPalindrome(i,k)){
                    res += i;
                    n--;
                    other[k].add(i);
                }
            }
        }
        return res;
    }
    private boolean isPalindrome(int x , int k){
        if (x % k == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        while (x > 0){
            sb.append(x % k);
            x /= k;
        }
        int left = 0,right = sb.length() - 1;
        while (left < right){
            if (sb.charAt(left++) != sb.charAt(right--)){
                return false;
            }
        }
        return true;
    }
}
