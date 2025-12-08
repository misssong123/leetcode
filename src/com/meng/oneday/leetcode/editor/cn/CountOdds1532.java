package com.meng.oneday.leetcode.editor.cn;//给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
//
// 
//
// 示例 1： 
//
// 输入：low = 3, high = 7
//输出：3
//解释：3 到 7 之间奇数数字为 [3,5,7] 。 
//
// 示例 2： 
//
// 输入：low = 8, high = 10
//输出：1
//解释：8 到 10 之间奇数数字为 [9] 。 
//
// 
//
// 提示： 
//
// 
// 0 <= low <= high <= 10^9 
// 
//
// Related Topics 数学 👍 143 👎 0


class CountOdds1532 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.5 MB,击败了20.84% 的Java用户
     * @param low
     * @param high
     * @return
     */
    public int countOdds1532(int low, int high) {
        int num = high - low + 1;
        return  num / 2 +  (num % 2 ==1 && low % 2 == 1 ? 1 : 0) ;
    }
    public int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }
}

