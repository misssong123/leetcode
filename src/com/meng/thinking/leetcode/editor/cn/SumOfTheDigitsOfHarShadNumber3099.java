package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class SumOfTheDigitsOfHarShadNumber3099 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了9.96% 的Java用户
     * @param x
     * @return
     */
    public int sumOfTheDigitsOfHarshadNumberMy(int x) {
        int sum = 0;
        int temp = x;
        while (temp != 0){
            sum += temp % 10;
            temp /= 10;
        }
        return x % sum == 0 ? sum : -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了27.00% 的Java用户
     * @param x
     * @return
     */
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int s = 0;
        for (int y = x; y != 0; y /= 10) {
            s += y % 10;
        }
        return x % s != 0 ? -1 : s;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
