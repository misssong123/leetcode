package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class Solution {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.9 MB,击败了8.46% 的Java用户
     * @param money
     * @param children
     * @return
     */
    public int distMoney1(int money, int children) {
        if (money < children){
            return -1;
        }
        //计算每人分配一元剩余的钱数
        money -= children;
        //计算能拿到8元的人数
        int res = money / 7;
        money = money % 7;
        //大于全部人数直接返回
        if (res >= children ){
            return (res >children || money != 0)?children-1:children;
        }
        //剩下人数大于1且剩余钱数不为3
        if (children - res > 1 || money != 3){
            return res;
        }
        return res -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了80.06% 的Java用户
     * 	内存消耗:39.7 MB,击败了29.31% 的Java用户
     * @param money
     * @param children
     * @return
     */
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        money -= children;
        int cnt = Math.min(money / 7, children);
        money -= cnt * 7;
        children -= cnt;
        if ((children == 0 && money > 0) || (children == 1 && money == 3)) {
            cnt--;
        }
        return cnt;
    }
}
