package com.meng.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 740. 删除并获得点数
 * 难度
 * 中等
 *
 * 423
 *
 *
 *
 *
 *
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < len ; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+nums[i]);
        }
        if (map.size() == 0){
            return map.get(nums[0]);
        }
        int [] keys = new int[map.size()];
        int index = 0;
        for(int key : map.keySet()){
            keys[index++] = key;
        }
        Arrays.sort(keys);
        int[] cache = new int[index];
        cache[0] = 0;
        for(int i = 1 ; i < index ; i++){
            int num = i -1;
            if (num == 0 || nums[num] > nums[num-1] + 1){
                cache[i] = map.get(nums[num]) + cache[num];
            }else {
                cache[i] = Math.max(cache[i-1],cache[i-2]+map.get(nums[num]));
            }
        }
        return cache[index-1];
    }
}
