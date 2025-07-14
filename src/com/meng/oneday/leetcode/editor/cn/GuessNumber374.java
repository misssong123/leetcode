package com.meng.oneday.leetcode.editor.cn;

/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class GuessNumber374 extends GuessGame {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了86.45% 的Java用户
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int l = 1,r = n;
        while (l<=r){
            int mid = l + (r-l)/2;
            int guess = guess(mid);
            if(guess == 0){
                return mid;
            }else if(guess == -1){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return -1;
    }
}
class GuessGame{
    public int guess(int num){
        return 0;
    }
}