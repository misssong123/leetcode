package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
class FindDuplicates442 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了83.55% 的Java用户
     * 	内存消耗:52.4 MB,击败了30.20% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDuplicatesMy(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < nums.length; i++){
            while (nums[i] != nums[nums[i]-1]){
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] != i+1){
                res.add(nums[i]);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了20.91% 的Java用户
     * 	内存消耗:54.2 MB,击败了5.08% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDuplicatesMy2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if (!set.add(num)){
                res.add(num);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了46.26% 的Java用户
     * 	内存消耗:52.4 MB,击败了29.89% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(nums[i]);
            if (nums[x - 1] > 0) {
                nums[x - 1] = -nums[x - 1];
            } else {
                ans.add(x);
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
