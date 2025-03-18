package com.meng.oneday.leetcode.editor.cn;

class DiagonalPrime2614 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:58.1 MB,击败了24.38% 的Java用户
     * @param nums
     * @return
     */
    public int diagonalPrime2614(int[][] nums) {
        int len = nums.length;
        int res = 0;
        for(int i = 0 ; i < len ; i++){
            if(nums[i][i] > res && isPrime2614(nums[i][i])){
                res = nums[i][i];
            }
            if (nums[i][len - i - 1]!= nums[i][i]
                    &&nums[i][len - i - 1] > res
                    && isPrime2614(nums[i][len - i - 1])){
                res = nums[i][len - i - 1];
            }
        }
        return res;
    }
    private boolean isPrime2614(int num){
        if (num < 2){
            return false;
        }
        for (int i = 2 ; i <= Math.sqrt(num) ; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:57.6 MB,击败了75.62% 的Java用户
     * @param nums
     * @return
     */
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i][i];
            if (x > ans && isPrime(x)) {
                ans = x;
            }
            x = nums[i][n - 1 - i];
            if (x > ans && isPrime(x)) {
                ans = x;
            }
        }
        return ans;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2; // 1 不是质数
    }

}
