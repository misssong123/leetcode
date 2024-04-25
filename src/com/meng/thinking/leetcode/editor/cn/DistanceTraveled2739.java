package com.meng.thinking.leetcode.editor.cn;
class DistanceTraveled2739 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了53.62% 的Java用户
     * @param mainTank
     * @param additionalTank
     * @return
     */
    public int distanceTraveledMy(int mainTank, int additionalTank) {
        int res = 0;
        while (mainTank >= 5){
            mainTank-=5;
            if (additionalTank>=1){
                mainTank++;
                additionalTank--;
            }
            res+=50;
        }
        res+= mainTank * 10;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了95.18% 的Java用户
     * @param mainTank
     * @param additionalTank
     * @return
     */
    public int distanceTraveled(int mainTank, int additionalTank) {
        return 10 * (mainTank + Math.min((mainTank - 1) / 4, additionalTank));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
