package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;
import java.util.PriorityQueue;

class Interview015Candy {
    /**
     * 解答成功:
     * 	执行耗时:33 ms,击败了5.97% 的Java用户
     * 	内存消耗:44.7 MB,击败了9.32% 的Java用户
     * @param ratings
     * @return
     */
    public int candyMy(int[] ratings) {
        int len = ratings.length;
        int[] ans = new int[len];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->{
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        for(int i = 0; i < len; i++) {
            queue.add(new int[]{ratings[i],i});
        }
        while (!queue.isEmpty()) {
            int index = queue.poll()[1];
            int temp = 1;
            if (index > 0 && ratings[index] > ratings[index -1]) {
                temp = Math.max(temp, ans[index -1]+1);
            }
            if (index < len -1 && ratings[index] > ratings[index + 1]) {
                temp = Math.max(temp, ans[index +1]+1);
            }
            ans[index] = temp;
        }
        return Arrays.stream(ans).sum();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了97.87% 的Java用户
     * 	内存消耗:44.9 MB,击败了5.02% 的Java用户
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}
