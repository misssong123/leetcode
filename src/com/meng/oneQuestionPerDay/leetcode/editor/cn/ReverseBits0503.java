package com.meng.oneQuestionPerDay.leetcode.editor.cn;


class ReverseBits0503 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了8.90% 的Java用户
     * @param num
     * @return
     */
    public int reverseBitsMy(int num) {
        String s = Integer.toBinaryString(num);
        int res = 1;
        int pre = 0,next = 0;
        boolean flag = false;
        for(int i = 0;i < s.length();i++){
            if (s.charAt(i)=='0'){
                if (flag){
                    res = Math.max(pre + next +1,res);
                    pre = next;
                    next = 0;
                }else {
                    flag = true;
                }
            }else if (s.charAt(i)=='1'){
                if (flag){
                    next++;
                }else {
                    pre++;
                }
            }
        }
        res = Math.max(pre + next +1,res);
        return Math.min(res,32);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.2 MB,击败了21.71% 的Java用户
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        int max = 0;
        int reverse = 0;
        int current = 0;
        for(int i=0;i<32;i++){
            if((num&1)==1){
                current++;
                reverse++;
            }else{
                reverse = current+1;
                current = 0;
            }
            if(reverse>max) max = reverse;
            num >>= 1;
        }
        return max;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
