package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Interview028ThreeSum {
    /**
     * 解答成功:
     * 	执行耗时:34 ms,击败了50.46% 的Java用户
     * 	内存消耗:50.4 MB,击败了49.94% 的Java用户
     * 排序+双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumMy(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < len ; i++){
            //排除重复元素
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1,right = len-1, target = -nums[i];
            while(left < right){
                int mid = nums[left] + nums[right];
                if(mid == target){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //找到下个不等于当前元素的数据
                    do {
                        left++;
                    } while (left < right && nums[left] == nums[left - 1]);
                }else if(mid < target){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:31 ms,击败了72.29% 的Java用户
     * 	内存消耗:51.4 MB,击败了23.46% 的Java用户
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
