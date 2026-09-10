package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MaxSum2815 {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了7.39% 的Java用户
     * 	内存消耗:46.6 MB,击败了5.11% 的Java用户
     * @param nums
     * @return
     */
    public int maxSum2815(int[] nums) {
        Arrays.sort(nums);
        List<Integer>[] list = new List[10];
        for(int i = 0; i < list.length; i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int num : nums){
            int max = 0;
            int origin = num;
            while (num > 0){
                int temp = num % 10;
                max = Math.max(max,temp);
                num /= 10;
            }
            list[max].add(origin);
        }
        int res = -1;
        for(List<Integer> l : list){
            if (l.size() <= 1){
                continue;
            }
            res = Math.max(res,l.get(l.size()-1) + l.get(l.size()-2));
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:45.9 MB,击败了47.16% 的Java用户
     * @param nums
     * @return
     */
    public int maxSum(int[] nums) {
        int ans = -1;
        int[] maxVal = new int[10];
        Arrays.fill(maxVal, Integer.MIN_VALUE); // 表示不存在最大值
        for (int v : nums) {
            int maxD = 0;
            for (int x = v; x > 0; x /= 10) {
                maxD = Math.max(maxD, x % 10);
            }
            ans = Math.max(ans, v + maxVal[maxD]);
            maxVal[maxD] = Math.max(maxVal[maxD], v);
        }
        return ans;
    }


}
