package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class BuyChoco2706 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43 MB,击败了10.88% 的Java用户
     * @param prices
     * @param money
     * @return
     */
    public int buyChocoMy(int[] prices, int money) {
        int first = Math.min(prices[0],prices[1]), second = Math.max(prices[0],prices[1]);
        for(int i = 2 ; i < prices.length ; i++){
            if (prices[i] < second){
                if (prices[i] < first){
                    second = first;
                    first = prices[i];
                }else {
                    second = prices[i];
                }
            }
        }
        return (first + second) > money ?money : money-first-second;
    }

    /**
     *解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.1 MB,击败了9.63% 的Java用户
     * @param prices
     * @param money
     * @return
     */
    public int buyChoco(int[] prices, int money) {
        int fi = Integer.MAX_VALUE, se = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < fi) {
                se = fi;
                fi = price;
            } else if (price < se) {
                se = price;
            }
        }
        return money < fi + se ? money : money - fi - se;
    }

}
