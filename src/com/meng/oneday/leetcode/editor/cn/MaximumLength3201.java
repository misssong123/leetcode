package com.meng.oneday.leetcode.editor.cn;

class MaximumLength3201 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了60.00% 的Java用户
     * 	内存消耗:60.9 MB,击败了80.00% 的Java用户
     * @param nums
     * @return
     */
    public int maximumLength3201(int[] nums) {
        //分别记录奇数/偶数/奇偶/偶奇
        int odd = 0,even = 0, oddEven = 0, evenOdd = 0;
        for(int num : nums){
            if (num % 2 == 0){
                odd++;
                oddEven += oddEven%2;
                evenOdd += (evenOdd+1)%2;
            }else{
                even++;
                oddEven += (oddEven+1)%2;
                evenOdd += evenOdd%2;
            }
        }
        return Math.max(Math.max(oddEven,evenOdd),Math.max(odd,even));
    }

    /**
     * 解答成功:
     * 	执行耗时:22 ms,击败了15.00% 的Java用户
     * 	内存消耗:62.8 MB,击败了30.00% 的Java用户
     * @param nums
     * @return
     */
    public int maximumLengthOfficial(int[] nums) {
        int res = 0;
        int[][] patterns = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        for (int[] pattern : patterns) {
            int cnt = 0;
            for (int num : nums) {
                if (num % 2 == pattern[cnt % 2]) {
                    cnt++;
                }
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
}
