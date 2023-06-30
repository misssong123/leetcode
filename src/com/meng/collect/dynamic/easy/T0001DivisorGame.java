package com.meng.collect.dynamic.easy;

/**
 * 1025. 除数博弈
 * 难度
 * 简单
 *
 * 396
 *
 *
 *
 *
 *
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字 n 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < n 且 n % x == 0 。
 * 用 n - x 替换黑板上的数字 n 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 true 。假设两个玩家都以最佳状态参与游戏。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1000
 */
public class T0001DivisorGame {
    /**
     * 超出时间限制
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        return win(n,true);
    }

    private boolean win(int n, boolean flag) {
        if (n==1){//n==1时，当前人员失败
            return !flag;
        }
        for(int i = 1 ; i <= n ; i++){
            if (i % 2 == 0){
                boolean canWin = win(n-1,!flag);//当前人员能都赢

                if (canWin && flag){
                    return true;
                }
                if (!canWin && !flag){
                    return false;
                }
            }
        }
        //不存在符合条件数据，当前人员失败
        return !flag;
    }

    public static void main(String[] args) {
        T0001DivisorGame demo = new T0001DivisorGame();
        System.out.println(demo.divisorGame(2));
    }
}
