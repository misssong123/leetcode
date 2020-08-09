package com.meng;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums ={1,3,5,7,8,3,11};
        int target = 10;
        TwoSum demo1 = new TwoSum();
        int[] ints = demo1.method1(nums, target);
        for (int i : ints)
            System.out.println(i);
    }

    /**
     * 暴力查找
     * @param nums
     * @param target
     * @return
     */
    public int[] method1(int[] nums, int target){
        int[] result = {-1,-1};
        for (int i=0;i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                if (i!=j && nums[i]+nums[j]==target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
    /**
     * 排序+双端查找
     * @param nums
     * @param target
     * @return
     */
        public int[] twoSum(int[] nums, int target) {
            int [] result = {-1,-1};
            int[] copy = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copy);
            //记录下标位置
            int begin = -1;
            int end = -1;
            //记录排序后的双端
            int first = 0;
            int last = copy.length-1;
            while(first<last){
                if (copy[first] + copy[last] > target)
                    last --;
                else if (copy[first] + copy[last] < target)
                    first ++;
                else
                    break;
            }
            for(int i = 0;i<nums.length;i++){
                if (nums[i] == copy[first] && begin==-1)
                    begin = i;
                if (nums[i]==copy[last] && end == -1 && i!=begin)
                    end=i;
                if (begin != -1 && end != -1)
                    break;

            }
            if (begin<end){
                result[0]=begin;
                result[1]=end;
            }else {
                result[1]=begin;
                result[0]=end;
            }
            return result;
        }
}
