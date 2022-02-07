package com.meng.origin;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 *
 *     必须在原数组上操作，不能拷贝额外的数组。
 *     尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MoveZeroes {
    /**
     * 找到不为0的数字，从下标0开始，依次排放
     * @param nums
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了96.47% 的用户
     */
    public void moveZeroes(int[] nums) {
        int m = 0,index = 0 ,len = nums.length;
        for(int i = 0 ; i < len ; i++){
            if (nums[i]!=0){
                if (i!=m){
                    nums[m] = nums[i];
                    nums[i] = 0;
                }
                m++;
            }
        }
    }

    /**
     * 逆序
     * @param nums
     */
    public void moveZeroesRev(int[] nums) {
        int len = nums.length,m = len-1,index = len-1 ;
        for(int i = len-1 ; i >=0 ; i--){
            if (nums[i]!=0){
                if (i!=m){
                    nums[m] = nums[i];
                    nums[i] = 0;
                }
                m--;
            }
        }
    }

    /**
     * 官方解法
     * 方法一：双指针
     *
     * 思路及解法
     *
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     *
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     *
     *     左指针左边均为非零数；
     *
     *     右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了86.84% 的用户
     */
    public void moveZeroes1(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
    public static void main(String[] args) {
        MoveZeroes demo = new MoveZeroes();
        int [] nums = {1,0,2,0,0,3,0,5,0,0};
        demo.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        demo.moveZeroesRev(nums);
        System.out.println(Arrays.toString(nums));
    }
}
