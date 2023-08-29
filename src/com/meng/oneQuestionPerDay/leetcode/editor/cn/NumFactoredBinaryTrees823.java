package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

class NumFactoredBinaryTrees823 {
    /**
     * 1.计算每个数的因子
     * 2.计算每个数的因子的组合
     * 3.计算每个数的因子的组合的二叉树
     * @param arr
     * @return
     * 时间
     * 详情
     * 21ms
     * 击败 33.33%使用 Java 的用户
     * 内存
     * 详情
     * 41.84MB
     * 击败 5.98%使用 Java 的用户
     */
    Map<Integer, List<Integer>> factor = new HashMap<>();
    Map<Integer,Long> count = new HashMap<>();
    Map<Integer,Integer> index = new HashMap<>();
    int NUM = 1000000007;
    public int numFactoredBinaryTrees(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        index.put(arr[0],0);
        for(int i = 1 ; i < len ; i++){
            int avg = arr[i] / 2;
            for(int j = 0 ; j < i && arr[j] <= avg ; j++){
                if(arr[i] % arr[j] == 0 && index.containsKey(arr[i] / arr[j])){
                    factor.putIfAbsent(arr[i],new ArrayList<>());
                    factor.get(arr[i]).add(arr[j]);
                }
            }
            index.put(arr[i],i);
        }
        //计算每个数的因子的组合
        long res = 1;
        count.put(arr[0],1L);
        for(int i = 1 ; i < len ; i++){
            long temp = 1;
            if (factor.containsKey(arr[i])){
                long t = 0;
                for(int num : factor.get(arr[i])){
                    int other = arr[i] / num;
                    long left = count.get(num);
                    long right = count.get(other);
                    if(num > other){
                        break;
                    }
                    if (num == other){
                        t = (t+left*right)%NUM;
                    }else {
                        t = (t+left*right*2)%NUM;
                    }
                }
                temp += t%NUM;
            }
            count.put(arr[i],temp);
            res = (res + temp)%NUM;
        }
        return (int)(res%NUM);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{45,42,2,18,23,1170,12,41,40,9,47,24,33,28,10,32,29,17,46,11,759,37,6,26,21,49,31,14,19,8,13,7,27,22,3,36,34,38,39,30,43,15,4,16,35,25,20,44,5,48};
        NumFactoredBinaryTrees823 demo = new NumFactoredBinaryTrees823();
        System.out.println(demo.numFactoredBinaryTrees(arr));
        System.out.println(demo.numFactoredBinaryTrees1(arr));
    }

    /**
     * 时间
     * 详情
     * 16ms
     * 击败 92.31%使用 Java 的用户
     * 内存
     * 详情
     * 39.15MB
     * 击败 97.44%使用 Java 的用户
     * @param arr
     * @return
     */
    public int numFactoredBinaryTrees1(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        long[] dp = new long[n];
        long res = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int left = 0, right = i - 1; left <= right; left++) {
                while (right >= left && (long) arr[left] * arr[right] > arr[i]) {
                    right--;
                }
                if (right >= left && (long) arr[left] * arr[right] == arr[i]) {
                    if (right != left) {
                        dp[i] = (dp[i] + dp[left] * dp[right] * 2) % mod;
                    } else {
                        dp[i] = (dp[i] + dp[left] * dp[right]) % mod;
                    }
                }
            }
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
    /**
     *
     * @param arr
     * @return
     */
    public int numFactoredBinaryTrees2(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }
        long ans = 0;
        long[] f = new long[n];
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                int x = arr[j];
                if ((long) x * x > val) { // 防止乘法溢出
                    break;
                }
                if (x * x == val) {
                    f[i] += f[j] * f[j];
                    break;
                }
                if (val % x == 0 && idx.containsKey(val / x)) {
                    f[i] += f[j] * f[idx.get(val / x)] * 2;
                }
            }
            ans += f[i];
        }
        return (int) (ans % MOD);
    }

}

