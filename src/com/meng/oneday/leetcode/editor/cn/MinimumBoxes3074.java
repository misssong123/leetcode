package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class MinimumBoxes3074 {

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了39.52% 的Java用户
     * 	内存消耗:44.2 MB,击败了5.24% 的Java用户
     * @param apple
     * @param capacity
     * @return
     */
    public int minimumBoxes3074(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int sum  = 0 ;
        for(int a : apple){
            sum += a;
        }
        int res = 0,index = capacity.length-1;
        while (sum >0){
            res++;
            sum -= capacity[index--];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了39.52% 的Java用户
     * 	内存消耗:43.9 MB,击败了20.97% 的Java用户
     * @param apple
     * @param capacity
     * @return
     */
    public int minimumBoxes(int[] apple, int[] capacity) {
        int s = 0;
        for (int x : apple) {
            s += x; // 把所有苹果堆在一起
        }

        Arrays.sort(capacity);

        int m = capacity.length;
        int i = m - 1; // 先装大箱子，再装小箱子
        while (s > 0) { // 还有剩余苹果
            s -= capacity[i--]; // 使用一个箱子
        }
        return m - 1 - i;
    }

}
