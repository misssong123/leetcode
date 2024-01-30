package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

class FindDisappearedNumbers448 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了52.21% 的Java用户
     * 	内存消耗:52.2 MB,击败了60.05% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbersMy1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < nums.length ; i++){
            while (nums[i] != i+1 && nums[i] != nums[nums[i] - 1]){
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }
        for(int i = 0 ;i < nums.length ; i++){
            if (nums[i] != i+1){
                res.add(i+1);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了25.64% 的Java用户
     * 	内存消耗:54.3 MB,击败了5.03% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbersMy2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 1 ; i <= nums.length ; i++){
            if (!set.contains(i)){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了37.71% 的Java用户
     * 	内存消耗:52.4 MB,击败了41.48% 的Java用户
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
