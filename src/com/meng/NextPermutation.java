package com.meng;

import java.util.Arrays;

public class NextPermutation {
    /**
     * 从后面找到第一个降序的数字x
     * 在x后面的元素中找到第一个比x大的数字y，将y与x交换
     * 并将原x坐标后的
     * @param nums
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.72% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了64.55% 的用户
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length-1,x = len;
        while (x-1>=0 && nums[x-1]>=nums[x])
            x--;
        if (x!=0){
            int y = len;
            while (y>=x && nums[y]<=nums[x-1])
                y--;
            int temp = nums[x-1];
            nums[x-1] = nums[y];
            nums[y] = temp;
        }
        int num = (len-x)/2;
        for(int i = 0 ; i<=num ; i++){
            int temp = nums[x+i];
            nums[x+i] = nums[len-i];
            nums[len-i] = temp;
        }
    }

    public static void main(String[] args) {
        NextPermutation demo = new NextPermutation();
        int [] nums = {3, 2, 1};
        demo.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
    /**
     * 官方解法
     * 方法一：两遍扫描
     *
     * 思路及解法
     *
     * 注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：
     *
     *     我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
     *
     *     同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
     *
     * 以排列 [4,5,2,6,3,1][4,5,2,6,3,1][4,5,2,6,3,1] 为例：
     *
     *     我们能找到的符合条件的一对「较小数」与「较大数」的组合为 222 与 333，满足「较小数」尽量靠右，而「较大数」尽可能小。
     *
     *     当我们完成交换后排列变为 [4,5,3,6,2,1][4,5,3,6,2,1][4,5,3,6,2,1]，此时我们可以重排「较小数」右边的序列，序列变为 [4,5,3,1,2,6][4,5,3,1,2,6][4,5,3,1,2,6]。
     *
     * 具体地，我们这样描述该算法，对于长度为 nnn 的排列 aaa：
     *
     *     首先从后向前查找第一个顺序对 (i,i+1)(i,i+1)(i,i+1)，满足 a[i]<a[i+1]a[i] < a[i+1]a[i]<a[i+1]。这样「较小数」即为 a[i]a[i]a[i]。此时 [i+1,n)[i+1,n)[i+1,n) 必然是下降序列。
     *
     *     如果找到了顺序对，那么在区间 [i+1,n)[i+1,n)[i+1,n) 中从后向前查找第一个元素 jjj 满足 a[i]<a[j]a[i] < a[j]a[i]<a[j]。这样「较大数」即为 a[j]a[j]a[j]。
     *
     *     交换 a[i]a[i]a[i] 与 a[j]a[j]a[j]，此时可以证明区间 [i+1,n)[i+1,n)[i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n)[i+1,n)[i+1,n) 使其变为升序，而无需对该区间进行排序。
     *注意
     *
     * 如果在步骤 1 找不到顺序对，说明当前序列已经是一个降序序列，即最大的序列，我们直接跳过步骤 2 执行步骤 3，即可得到最小的升序序列。
     *
     * 该方法支持序列中存在重复元素，且在 C++ 的标准库函数 next_permutation 中被采用
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.72% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了93.43% 的用户
     */
    public void nextPermutation1(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
