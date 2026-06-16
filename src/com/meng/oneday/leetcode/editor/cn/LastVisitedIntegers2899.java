package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LastVisitedIntegers2899 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.9 MB,击败了50.00% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> lastVisitedIntegers2899(int[] nums) {
        List<Integer> res =  new ArrayList<>();
        int len = nums.length;
        int[] temp = new int[len];
        int index = 0;
        int k = 0;
        for (int num : nums) {
            if(num == -1){
                k++;
                if (k > index){
                    res.add(-1);
                }else{
                    res.add(temp[index - k]);
                }
            }else{
                temp[index++] = num;
                k = 0;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.8 MB,击败了66.67% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> seen = new ArrayList<>();
        int k = 0;
        for (int x : nums) {
            if (x > 0) {
                seen.add(x);
                k = 0;
            } else {
                ans.add(++k > seen.size() ? -1 : seen.get(seen.size() - k)); // 倒数第 k 个
            }
        }
        return ans;
    }

}
