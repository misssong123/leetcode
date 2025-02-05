package com.meng.oneday.leetcode.editor.cn;

class GenerateKey3270 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了56.39% 的Java用户
     * @param num1
     * @param num2
     * @param num3
     * @return
     */
    public int generateKey(int num1, int num2, int num3) {
        int res = 0;
        for(int i = 1 ; num1 > 0 && num2 > 0 && num3 > 0 ; i *= 10){
            res+=Math.min(Math.min(num1%10,num2%10),num3%10)*i;
            num1/=10;
            num2/=10;
            num3/=10;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了89.00% 的Java用户
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int generateKeyOther(int x, int y, int z) {
        int ans = 0;
        for (int pow10 = 1; x > 0 && y > 0 && z > 0; pow10 *= 10) {
            ans += Math.min(Math.min(x % 10, y % 10), z % 10) * pow10;
            x /= 10;
            y /= 10;
            z /= 10;
        }
        return ans;
    }

}
