package com.meng.interview150.leetcode.editor.cn;

class Interview147RangeBitwiseAnd {
    /**
     * 超时
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAndMy(int left, int right) {
        if (left == right){
            return left;
        }
        int num1 = Integer.highestOneBit(left) ;
        int num2 = Integer.highestOneBit(right) ;
        if (num1 == num2 && num1 != 0){
            int temp = left ;
            for(int i = left+1; i>0&&i <= right; i++){
                temp &= i ;
            }
            return temp;
        }
        return 0;
    }

    /**
     * 执行用时分布
     * 3
     * ms
     * 击败
     * 100.00%
     * 复杂度分析
     * 消耗内存分布
     * 42.90
     * MB
     * 击败
     * 21.06%
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right){
            right = right & (right - 1);
        }
        return right;
    }
}
