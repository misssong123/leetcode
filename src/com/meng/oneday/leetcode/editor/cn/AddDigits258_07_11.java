package com.meng.oneday.leetcode.editor.cn;

class AddDigits258_07_11 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.1 MB,击败了67.42% 的Java用户
     * @param num
     * @return
     */
    public int addDigits258(int num) {
        int temp = 0;
        while(num >= 10){
            while(num > 0){
                temp += num % 10;
                num /= 10;
            }
            num = temp;
            temp = 0;
        }
        return num;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了96.63% 的Java用户
     * @param num
     * @return
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
