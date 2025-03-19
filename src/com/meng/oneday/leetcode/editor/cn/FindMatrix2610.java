package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FindMatrix2610 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了86.96% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> findMatrix2610(int[] nums) {
        int len = nums.length;
        int[] count = new int[len+1];
        for(int num : nums){
            count[num]++;
        }
        List<List<Integer>> res = new ArrayList<>();
        while(true){
            boolean flag = false;
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i <= len ; i ++){
                if (count[i] > 0){
                    flag = true;
                    temp.add(i);
                    count[i]--;
                }
            }
            if (!flag){
                break;
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了6.52% 的Java用户
     * 	内存消耗:44.3 MB,击败了19.57% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> findMatrix(int[] nums) {
        // 统计每个元素的出现次数
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (!cnt.isEmpty()) { // 还有剩余元素
            List<Integer> row = new ArrayList<>(cnt.keySet());
            ans.add(row);
            // cnt 中的每个元素的出现次数都减一
            for (Integer x : row) {
                int c = cnt.get(x) - 1;
                if (c == 0) {
                    cnt.remove(x); // 去掉出现次数为 0 的元素
                } else {
                    cnt.put(x, c); // 更新出现次数
                }
            }
        }
        return ans;
    }
}
