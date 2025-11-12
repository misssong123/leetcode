package com.meng.oneday.leetcode.editor.cn;

class MinOperations2654 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了6.00% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations2654(int[] nums) {
        int cnt = 1;
        while (cnt < nums.length){
            for(int i = 0 ; i < nums.length -1 ; i++){
                int gcd = gcd(nums[i],nums[i+1]);
                if (gcd == 1){
                    if (cnt == 1){
                        int n = 0;
                        for (int num : nums){
                            if (num == 1){
                                n++;
                            }
                        }
                        return nums.length - n;
                    }else{
                        return nums.length + cnt -1 ;
                    }
                }
                nums[i] = gcd;
            }
            cnt++;
        }
        return -1;
    }
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了8.00% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length, gcdAll = 0, cnt1 = 0;
        for (int x : nums) {
            gcdAll = gcd(gcdAll, x);
            if (x == 1) ++cnt1;
        }
        if (gcdAll > 1) return -1;
        if (cnt1 > 0) return n - cnt1;

        int minSize = n;
        for (int i = 0; i < n; i++) {
            int g = 0;
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    // 这里本来是 j-i+1，把 +1 提出来合并到 return 中
                    minSize = Math.min(minSize, j - i);
                    break;
                }
            }
        }
        return minSize + n - 1;
    }
}
