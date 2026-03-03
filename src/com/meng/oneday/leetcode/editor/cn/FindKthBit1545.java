package com.meng.oneday.leetcode.editor.cn;

class FindKthBit1545 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了33.63% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public char findKthBit1545(int n, int k) {
        //第一层
        if (n == 1){
            return '0';
        }
        //获取当前高度
        int len = (int)Math.pow(2,n) - 1;
        if (k == len/2 + 1){
            return '1';
        }
        int reverse = 0;
        char base = '0';
        //获取层级
        while (--n > 0){
            //计算上一层级长度
            len = len / 2;
            //判断是否在中间位置
            if(k == len + 1){
                base = '1';
                break;
            }
            //判断k是否在反转区域
            boolean isReverse = k > len;
            //计算当前k在上一层级的位置
            k = isReverse ? 2 * len + 2 - k : k ;
            //记录反转次数
            reverse += isReverse ? 1 : 0;
        }
        return reverse % 2 == 0 ? base : base == '0' ? '1' : '0' ;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了92.92% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public char findKthBit(int n, int k) {
        int rev = 0; // 翻转次数的奇偶性
        while (true) {
            if (n == 1) {
                return (char) ('0' ^ rev);
            }
            if (k == 1 << (n - 1)) {
                return (char) ('1' ^ rev);
            }
            if (k > 1 << (n - 1)) {
                k = (1 << n) - k;
                rev ^= 1;
            }
            n--;
        }
    }


}
