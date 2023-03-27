package com.meng.oneQuestionPerDay.leetcode.editor.cn;
//给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
//
// 比方说，如果 nums = [6,1,7,4,1] ，那么： 
//
// 
// 选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。 
// 选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。 
// 选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。 
// 
//
// 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。 
//
// 请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,1,6,4]
//输出：1
//解释：
//删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
//删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
//删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
//删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
//只有一种让剩余数组成为平衡数组的方案。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1]
//输出：3
//解释：你可以删除任意元素，剩余数组都是平衡数组。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3]
//输出：0
//解释：不管删除哪个元素，剩下数组都不是平衡数组。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁴ 


import java.util.Arrays;

class WaysToMakeFair1664 {
    //1.暴力解法，
    // 计算目标数据，
    // 依次遍历缺失的下标，
    // 查看偶数下标的总和是否为目标数据
    //超时
    public int waysToMakeFair(int[] nums) {
        int len = nums.length;
        if (len <=2){
            return len%2;
        }
        int sum = Arrays.stream(nums).sum();
        int num = 0 ;
        for(int i = 0 ; i < len ; i++){
            if ((sum - nums[i])%2!=0){
                continue;
            }
            int target = (sum - nums[i])/2;
            for (int j = 0 ; j < len ; j++){
                if (j == i){
                    continue;
                }
                //计算偶数位总和
                if (j < i && j % 2 == 0){
                    target -= nums[j];
                }else if(j > i && j % 2 ==1){
                    target -= nums[j];
                }
                if (target < 0){
                    continue;
                }
            }
            if (target == 0){
                num++;
            }
        }
        return num;
    }
    //2.记录
    // 记录当前数据左右相隔一位累计的数据
    //依次移除当前下标
    //计算当前数据前一位的左累计和当前数据后两位的右累计和，是否等于目标数据
    //执行用时：
    //16 ms
    //, 在所有 Java 提交中击败了
    //6.01%
    //的用户
    //内存消耗：
    //49.6 MB
    //, 在所有 Java 提交中击败了
    //85.71%
    //的用户
    //通过测试用例：
    //105 / 105
    public int waysToMakeFair1(int[] nums) {
        int len = nums.length;
        if (len <=2){
            return len%2;
        }
        int sum = Arrays.stream(nums).sum();
        int[] rights = new int[len];
        int[] lefts = new int[len];
        for(int i = 0 ; i < len ; i++){
            if (i >= 2){
                lefts[i] = nums[i] + lefts[i-2];
            }else {
                lefts[i] = nums[i];
            }
        }
        for(int i = len - 1 ; i >=0 ; i--){
            if (i <=len-3){
                rights[i] = nums[i] + rights[i+2];
            }else {
                rights[i] = nums[i];
            }
        }
        int num = 0;
        for(int i = 0 ; i < len ; i++) {
            if ((sum - nums[i]) % 2 != 0) {
                continue;
            }
            int target = (sum - nums[i])/2;
            //当前下标左侧第一位
            int left = i > 0 ? lefts[i-1] : 0;
            //当前下标右侧第二位
            int right = i < len - 2 ? rights[i+2]:0;
            if (target - left - right == 0){
                num++;
            }
        }
        return num;
    }
    public static void main(String[] args) {

    }

    /**
     * 官方解法
     * 动态规划
     * @param nums
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 98.50%
     * 的用户
     * 内存消耗：
     * 51.9 MB
     * , 在所有 Java 提交中击败了
     * 23.31%
     * 的用户
     * 通过测试用例：
     * 105 / 105
     */
    public int waysToMakeFair3(int[] nums) {
        int odd1 = 0, even1 = 0;
        int odd2 = 0, even2 = 0;
        for (int i = 0; i < nums.length; ++i) {
            if ((i & 1) != 0) {
                odd2 += nums[i];
            } else {
                even2 += nums[i];
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if ((i & 1) != 0) {
                odd2 -= nums[i];
            } else {
                even2 -= nums[i];
            }
            if (odd1 + even2 == odd2 + even1) {
                ++res;
            }
            if ((i & 1) != 0) {
                odd1 += nums[i];
            } else {
                even1 += nums[i];
            }
        }
        return res;
    }

}
