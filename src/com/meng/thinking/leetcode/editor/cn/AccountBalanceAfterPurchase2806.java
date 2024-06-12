package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class AccountBalanceAfterPurchase2806 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了20.44% 的Java用户
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchaseMy(int purchaseAmount) {
        int n = purchaseAmount / 10 ;
        int pre = n * 10 , suf = (n+1)*10;
        if (purchaseAmount-pre<suf-purchaseAmount){
            return 100 - pre;
        }else {
            return 100 - suf;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.6 MB,击败了8.76% 的Java用户
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        return 100 - (purchaseAmount + 5) / 10 * 10;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
