package com.meng.oneday.leetcode.editor.cn;

class MaxSumOfSquares3723 {
    /**
     * 解答成功:
     * 	执行耗时:113 ms,击败了20.00% 的Java用户
     * 	内存消耗:47.4 MB,击败了30.00% 的Java用户
     * @param num
     * @param sum
     * @return
     */
    public String maxSumOfSquares3723(int num, int sum) {
        if (9 * num < sum ){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (sum >= 9){
                sb.append(9);
                sum -= 9;
            }else {
                sb.append(sum);
                sum = 0;
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了85.00% 的Java用户
     * 	内存消耗:46.8 MB,击败了70.00% 的Java用户
     * @param n
     * @param sum
     * @return
     */
    public String maxSumOfSquares(int n, int sum) {
        if (n * 9 < sum) {
            return "";
        }
        StringBuilder ans = new StringBuilder(n).repeat('9', sum / 9);
        if (sum % 9 > 0) {
            ans.append((char) ('0' + sum % 9));
        }
        ans.repeat('0', n - ans.length());
        return ans.toString();
    }

}
