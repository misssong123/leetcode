package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MaximumPrimeDifference3115 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了85.36% 的Java用户
     * 	内存消耗:77.6 MB,击败了38.16% 的Java用户
     * @param nums
     * @return
     */
    public int maximumPrimeDifferenceMy(int[] nums) {
        //寻找第一个质数
        int first = -1;
        for(int i = 0; i < nums.length; i++){
            if (isPrime(nums[i])){
                first = i;
                break;
            }
        }
        //寻找第二个质数
        int second = -1;
        for (int i = nums.length-1;i>=first;i--){
            if (isPrime(nums[i])){
                second = i;
                break;
            }
        }
        return  second-first;
    }
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了30.64% 的Java用户
     * 	内存消耗:75.5 MB,击败了94.24% 的Java用户
     * @param nums
     * @return
     */
    public int maximumPrimeDifferenceOfficial(int[] nums) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(
                2, 3, 5, 7, 11,
                13, 17, 19, 23, 29,
                31, 37, 41, 43, 47,
                53, 59, 61, 67, 71,
                73, 79, 83, 89, 97
        ));

        int n = nums.length;
        int first = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (primes.contains(nums[i])) {
                if (first != -1) {
                    ans = Math.max(ans, i - first);
                } else {
                    first = i;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了85.36% 的Java用户
     * 	内存消耗:77.6 MB,击败了59.13% 的Java用户
     * @param nums
     * @return
     */
    public int maximumPrimeDifference(int[] nums) {
        boolean[] cache = generatePrimeArray(100);
        //寻找第一个质数
        int first = -1;
        for(int i = 0; i < nums.length; i++){
            if (cache[nums[i]]){
                first = i;
                break;
            }
        }
        //寻找第二个质数
        int second = -1;
        for (int i = nums.length-1;i>=first;i--){
            if (cache[nums[i]]){
                second = i;
                break;
            }
        }
        return  second-first;
    }
    public boolean[] generatePrimeArray(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true); // 初始化全部数为质数
        isPrime[0] = false; // 0不是质数
        isPrime[1] = false; // 1不是质数

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) { // i是质数，则将i的倍数标记为非质数
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }
}
