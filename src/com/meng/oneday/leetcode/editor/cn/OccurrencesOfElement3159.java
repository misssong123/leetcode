package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class OccurrencesOfElement3159 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了61.40% 的Java用户
     * 	内存消耗:64.3 MB,击败了16.66% 的Java用户
     * @param nums
     * @param queries
     * @param x
     * @return
     */
    public int[] occurrencesOfElementMy(int[] nums, int[] queries, int x) {
        List<Integer> cache = new ArrayList<>();
        for (int i = 0 ; i < nums.length; i++){
            if (nums[i] == x){
                cache.add(i);
            }
        }
        int[] res = new int[queries.length];
        int index = 0;
        for(int num : queries){
            if (num > cache.size()){
                res[index++] = -1;
            }else {
                res[index++] = cache.get(num-1);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了87.72% 的Java用户
     * 	内存消耗:62.4 MB,击败了70.67% 的Java用户
     * @param nums
     * @param queries
     * @param x
     * @return
     */
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                pos.add(i);
            }
        }
        for (int i = 0; i < queries.length; i++) {
            queries[i] = queries[i] > pos.size() ? -1 : pos.get(queries[i] - 1);
        }
        return queries;
    }

}
