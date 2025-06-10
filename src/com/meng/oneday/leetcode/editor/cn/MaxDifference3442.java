package com.meng.oneday.leetcode.editor.cn;

class MaxDifference3442 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了89.57% 的Java用户
     * @param s
     * @return
     */
    public int maxDifference3442(String s) {
        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        int oddMin = 101,oddMax = 0,evenMin = 101,evenMax = 0;
        for (int num : nums) {
            if (num > 0){
                if (num % 2 == 0){
                    evenMin = Math.min(evenMin,num);
                    evenMax = Math.max(evenMax,num);
                }else {
                    oddMin = Math.min(oddMin,num);
                    oddMax = Math.max(oddMax,num);
                }
            }
        }
        int res = 0;
        if (oddMin < 101 && evenMax > 0){
            res =  oddMax - evenMin;
        }
        if (oddMax > 0 && evenMin < 101){
            res = Math.max(res,oddMin - evenMax);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了82.21% 的Java用户
     * @param s
     * @return
     */
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char b : s.toCharArray()) {
            cnt[b - 'a']++;
        }

        int max1 = 0;
        int min0 = Integer.MAX_VALUE;
        for (int c : cnt) {
            if (c % 2 > 0) {
                max1 = Math.max(max1, c);
            } else if (c > 0) {
                min0 = Math.min(min0, c);
            }
        }
        return max1 - min0;
    }

}
