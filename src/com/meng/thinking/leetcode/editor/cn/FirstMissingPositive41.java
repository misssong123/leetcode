package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class FirstMissingPositive41 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了15.37% 的Java用户
     * 	内存消耗:53.7 MB,击败了81.52% 的Java用户
     * @param nums
     * @return
     */
    public int firstMissingPositiveMy(int[] nums) {
        Arrays.sort(nums);
        int index = 1;
        for(int num : nums){
            if(num < index ){

            }else if(num == index) {
                index++;
            }else{
                return index;
            }
        }
        return index;
    }

    /**
     * 原地哈希
     * 解答成功:
     * 	执行耗时:1 ms,击败了98.44% 的Java用户
     * 	内存消耗:53.8 MB,击败了78.70% 的Java用户
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }
        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
