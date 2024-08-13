package com.meng.interview150.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview005MajorityElementMy {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了61.93% 的Java用户
     * 	内存消耗:52 MB,击败了24.83% 的Java用户
     * @param nums
     * @return
     */
    public int majorityElementMy(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了61.93% 的Java用户
     * 	内存消耗:51.9 MB,击败了29.67% 的Java用户
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
