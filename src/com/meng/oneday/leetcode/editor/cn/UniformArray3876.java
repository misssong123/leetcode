package com.meng.oneday.leetcode.editor.cn;

class UniformArray3876 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了63.33% 的Java用户
     * 	内存消耗:119.2 MB,击败了50.00% 的Java用户
     * @param nums1
     * @return
     */
    public boolean uniformArray3876(int[] nums1) {
        //如果最小数为偶数，并且存在奇数，则为false，否则为true
        int min = nums1[0];
        boolean hasOdd = false;
        for (int num : nums1) {
            if (num % 2 == 1) {
                hasOdd = true;
            }
            min = Math.min(min, num);
        }
        return !hasOdd || min % 2 != 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了86.67% 的Java用户
     * 	内存消耗:119.3 MB,击败了16.67% 的Java用户
     * @param nums1
     * @return
     */
    public boolean uniformArray(int[] nums1) {
        // 计算最小偶数、最小奇数
        int[] mn = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int x : nums1) {
            mn[x & 1] = Math.min(mn[x & 1], x); // &1 比 %2 好，nums1 有负数也适用
        }

        // 只有偶数，或者偶数 >= 最小的偶数 > 最小的奇数
        // 只有奇数的情况蕴含在 mn[0] > mn[1] 中
        return mn[1] == Integer.MAX_VALUE || mn[0] > mn[1];
    }

}
