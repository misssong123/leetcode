package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class ThreeSumClosest16 {
    /**
     * 1.排序
     * 2.遍历选取最接近的值
     * @param nums
     * @param target
     * @return
     * 执行用时：
     * 22 ms
     * , 在所有 Java 提交中击败了
     * 14.72%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 33.93%
     * 的用户
     * 通过测试用例：
     * 99 / 99
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = nums[0] + nums[1]+nums[2];
        for(int i = 0 ; i < len ; i++ ){
            //重复数据直接返回
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i + 1 ,k = len -1;
            while (j < k){
                //与目标数相同直接返回，
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target){
                    return target;
                }
                //选取最接近值
                if (Math.abs(sum - target) < Math.abs(res-target)){
                    res = sum;
                }
                //寻找下一个不同的值
                if (sum > target){
                    int nextK = k - 1;
                    while (nextK > j && nums[k] == nums[nextK]){
                        nextK--;
                    }
                    k = nextK;
                }else {
                    int nextJ = j + 1;
                    while (nextJ < k && nums[j] == nums[nextJ]){
                        nextJ++;
                    }
                    j = nextJ;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumClosest16 demo = new ThreeSumClosest16();
        System.out.println(demo.threeSumClosest1(new int[]{-1,2,1,-4},1));
    }
    /**
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 31.51%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 22.57%
     * 的用户
     * 通过测试用例：
     * 99 / 99
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

}
