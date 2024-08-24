package com.meng.weeklycompetition.week360;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2835. 使子序列的和等于目标的最少操作次数
 * 提示
 * 困难
 * 28
 * 相关企业
 * 给你一个下标从 0 开始的数组 nums ，它包含 非负 整数，且全部为 2 的幂，同时给你一个整数 target 。
 *
 * 一次操作中，你必须对数组做以下修改：
 *
 * 选择数组中一个元素 nums[i] ，满足 nums[i] > 1 。
 * 将 nums[i] 从数组中删除。
 * 在 nums 的 末尾 添加 两个 数，值都为 nums[i] / 2 。
 * 你的目标是让 nums 的一个 子序列 的元素和等于 target ，请你返回达成这一目标的 最少操作次数 。如果无法得到这样的子序列，请你返回 -1 。
 *
 * 数组中一个 子序列 是通过删除原数组中一些元素，并且不改变剩余元素顺序得到的剩余数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,8], target = 7
 * 输出：1
 * 解释：第一次操作中，我们选择元素 nums[2] 。数组变为 nums = [1,2,4,4] 。
 * 这时候，nums 包含子序列 [1,2,4] ，和为 7 。
 * 无法通过更少的操作得到和为 7 的子序列。
 * 示例 2：
 *
 * 输入：nums = [1,32,1,2], target = 12
 * 输出：2
 * 解释：第一次操作中，我们选择元素 nums[1] 。数组变为 nums = [1,1,2,16,16] 。
 * 第二次操作中，我们选择元素 nums[3] 。数组变为 nums = [1,1,2,16,8,8] 。
 * 这时候，nums 包含子序列 [1,1,2,8] ，和为 12 。
 * 无法通过更少的操作得到和为 12 的子序列。
 * 示例 3：
 *
 * 输入：nums = [1,32,1], target = 35
 * 输出：-1
 * 解释：无法得到和为 35 的子序列。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 230
 * nums 只包含非负整数，且均为 2 的幂。
 * 1 <= target < 231
 */
public class MinOperations2835 {
    public int minOperations(List<Integer> nums, int target) {
        return -1;
    }

    public static void main(String[] args) {
        MinOperations2835 demo = new MinOperations2835();
        List<Integer> list = Arrays.asList(1, 2,3,6,8);
        for(int num : list){
            System.out.print(Integer.toBinaryString(num)+",");
            System.out.print(Integer.bitCount(num)+",");
            System.out.println(Integer.numberOfTrailingZeros(num));
        }
    }
    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int minOperations1(List<Integer> nums, int target) {
        long s = 0;
        long[] cnt = new long[31];
        for (int x : nums) {
            s += x;
            cnt[Integer.numberOfTrailingZeros(x)]++;
        }
        if (s < target)
            return -1;
        int ans = 0, i = 0;
        s = 0;
        while ((1L << i) <= target) {
            s += cnt[i] << i;
            int mask = (int) ((1L << ++i) - 1);
            if (s >= (target & mask)){
                continue;
            }
            ans++; // 一定要找更大的数操作
            for (; cnt[i] == 0; i++){
                ans++; // 还没找到，继续找更大的数
            }
        }
        return ans;
    }
    public int minOperations2(List<Integer> nums, int target) {
        int ans = 0;
        long sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum =sum +nums.get(i);
        }
        if(sum<target) return -1;
        Collections.sort(nums);
        while(target>0){
            int last = nums.get(nums.size()-1);
            nums.remove(nums.size()-1);
            if(sum - last>=target){
                sum = sum -last;
            }else if(last>target){
                ans++;
                nums.add(last/2);
                nums.add(last/2);
            }else{
                sum = sum-last;
                target = target -last;
            }
        }
        return ans;
    }
}
