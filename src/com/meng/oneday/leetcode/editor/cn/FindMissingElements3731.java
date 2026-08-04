package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class FindMissingElements3731 {

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了47.37% 的Java用户
     * 	内存消耗:46.3 MB,击败了44.74% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findMissingElements3731(int[] nums) {
        List<Integer> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        int index = 0;
        for (int i = nums[0] ; i <= nums[nums.length-1]; i++){
            if(nums[index] == i){
                index++;
            }else{
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了89.47% 的Java用户
     * 	内存消耗:46.2 MB,击败了44.74% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findMissingElements(int[] nums) {
        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        Set<Integer> st = new HashSet<>();
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            st.add(x);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = mn + 1; i < mx; i++) {
            if (!st.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

}
