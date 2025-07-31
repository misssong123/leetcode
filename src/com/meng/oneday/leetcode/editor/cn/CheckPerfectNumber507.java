package com.meng.oneday.leetcode.editor.cn;

class CheckPerfectNumber507 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.01% 的Java用户
     * 	内存消耗:39.5 MB,击败了82.79% 的Java用户
     * @param num
     * @return
     */
    public boolean checkPerfectNumber507(int num) {
        if(num <=3){
            return false;
        }
        int sum = 1;
        for(int i = 2 ;i <= (int)Math.sqrt(num);i++){
            if(num % i == 0){
                sum += i;
                if (num / i != i){
                    sum += num / i;
                }
            }
        }
        return sum == num;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.01% 的Java用户
     * 	内存消耗:39.6 MB,击败了67.58% 的Java用户
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num == 1)
            return false;
        int ans = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                ans += i;
                if (i * i != num)
                    ans += num / i;
            }
        }
        return ans == num;
    }
}
