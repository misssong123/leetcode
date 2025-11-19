package com.meng.top100.leetcode.editor.cn;

class RangeBitwiseAnd201 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了6.43% 的Java用户
     * 	内存消耗:45.1 MB,击败了5.27% 的Java用户
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd201(int left, int right) {
        if (left == 0){
            return 0;
        }
        String leftStr = Integer.toBinaryString(left);
        String rightStr = Integer.toBinaryString(right);
        if (leftStr.length() != rightStr.length()){
            return 0;
        }
        int num = leftStr.length() -1;
        int res = 0;
        for (int i = 0 ; i < leftStr.length() ; i++){
            if (leftStr.charAt(i) == rightStr.charAt(i)){
                if(leftStr.charAt(i) == '1'){
                    res += 1 << num;
                }
            }else {
                break;
            }
            num --;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了6.43% 的Java用户
     * 	内存消耗:45.1 MB,击败了8.09% 的Java用户
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }
}
