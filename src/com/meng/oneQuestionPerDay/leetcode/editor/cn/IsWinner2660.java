package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class IsWinner2660 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.00% 的Java用户
     * @param player1
     * @param player2
     * @return
     */
    public int isWinnerMy(int[] player1, int[] player2) {
        int n = player1.length;
        int num1 = 0,num2 = 0,index1 = -10,index2 = -10;
        for(int i = 0 ; i < n ;i++){
            num1 +=(index1+2>=i)?player1[i]*2:player1[i];
            num2 +=(index2+2>=i)?player2[i]*2:player2[i];
            if (player1[i]==10){
                index1 = i;
            }
            if (player2[i]==10){
                index2 = i;
            }
        }
        return num1 == num2 ?0 :num1 > num2?1:2;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.00% 的Java用户
     * @param player1
     * @param player2
     * @return
     */
    public int isWinner(int[] player1, int[] player2) {
        int s1 = score(player1);
        int s2 = score(player2);
        return s1 == s2 ? 0 : s1 > s2 ? 1 : 2;
    }

    public int score(int[] player) {
        int res = 0;
        for (int i = 0; i < player.length; i++) {
            if ((i > 0 && player[i - 1] == 10) || (i > 1 && player[i - 2] >= 10)) {
                res += 2 * player[i];
            } else {
                res += player[i];
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
