package com.meng.origin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 *     所有数字（包括 target）都是正整数。
 *     解集不能包含重复的组合。
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 *
 * 提示：
 *
 *     1 <= candidates.length <= 30
 *     1 <= candidates[i] <= 200
 *     candidate 中的每个元素都是独一无二的。
 *     1 <= target <= 500
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        CombinationSum demo = new CombinationSum();
        List<List<Integer>> lists = demo.combinationSum(candidates, target);
        System.out.println(lists);
    }
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum(candidates,target,0);
        return result;
    }

    /**
     *
     * @param sort 经过排序后的数组
     * @param target 已存在的目标数
     * @param index 当前正在尝试填充的数组下标
     */
    private void combinationSum(int[] sort, int target ,int index) {
        //表示无法进行数组填充
        if (index == sort.length){
            return;
        }
        //表示当前组合符合条件
        if (target == 0){
            //需要填入新的list
            result.add(new ArrayList<>(temp));
            return;
        }
        //填入当前值
        if (target >=sort[index]){
            temp.add(sort[index]);
            //继续尝试填入该值
            combinationSum(sort,target-sort[index],index);
            //移除最后一位填入的数据
            temp.remove(temp.size()-1);
        }
        //填入下一个值
        if (index+1<sort.length && target>=sort[index+1]){
            combinationSum(sort,target,index+1);
        }
    }
}
