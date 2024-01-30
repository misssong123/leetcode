package com.meng.thinking.leetcode.editor.cn;

import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class FindDuplicate287 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了39.20% 的Java用户
     * 	内存消耗:54.5 MB,击败了96.41% 的Java用户
     * @param nums
     * @return
     */
    public int findDuplicateMy(int[] nums) {
        Set<Integer> set = new java.util.HashSet<>();
        for (int num : nums){
            if (!set.add(num)){
                return num;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了25.47% 的Java用户
     * 	内存消耗:56.9 MB,击败了26.12% 的Java用户
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length; // n + 1 = len, n = len - 1

        // 在 [1..n] 查找 nums 中重复的元素
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            // nums 中小于等于 mid 的元素的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                // 下一轮搜索的区间 [left..mid]
                right = mid;
            } else {
                // 下一轮搜索的区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        return left;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
