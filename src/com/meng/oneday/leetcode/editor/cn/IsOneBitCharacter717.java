package com.meng.oneday.leetcode.editor.cn;

class IsOneBitCharacter717 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.41% 的Java用户
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter717(int[] bits) {
        for(int i = 0; i < bits.length ; i++){
            if(i == bits.length - 1){
                return true;
            }
            if(bits[i] == 1){
                i++;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.41% 的Java用户
     * @param bits
     * @return
     */
    public boolean isOneBitCharacterOther(int[] bits) {
        int n = bits.length;
        int i = 0;
        while (i < n - 1) { // 循环直到剩下至多一个数字
            i += bits[i] + 1; // 如果 bits[i] == 1 则跳过下一位
        }
        return i == n - 1; // 注意题目保证 bits[n-1] == 0，无需判断
    }

}
