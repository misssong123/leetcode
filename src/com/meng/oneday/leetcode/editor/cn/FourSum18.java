package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FourSum18 {
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了51.15% 的Java用户
     * 	内存消耗:43.3 MB,击败了23.30% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum18(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        if ((nums[0] > 0 && target < 0) || (target > 0 && nums[nums.length - 1] < 0)){
            return list;
        }

        int len = nums.length;
        for(int  i = 0 ; i < len - 3 ; i++){
            //去重
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            //剪枝
            if (target > 0 && nums[i] > target){
                break;
            }
            long temp = target - nums[i];
            for(int j = i + 1 ; j < len - 2 ; j++){
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                //剪枝
                if (temp > 0 && nums[j] > temp){
                    break;
                }
                long val = temp - nums[j];
                int left = j + 1,right = len - 1;
                while (left < right){
                    if (nums[left] + nums[right] == val){
                        list.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]){
                            left++;
                        }
                        while (right > left && nums[right] == nums[right + 1]){
                            right--;
                        }
                    }else if (nums[left] + nums[right] > val){
                        right--;
                    }else {
                        left++;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.79% 的Java用户
     * 	内存消耗:43.1 MB,击败了70.78% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int a = 0; a < n - 3; a++) { // 枚举第一个数
            long x = nums[a]; // 使用 long 避免溢出
            if (a > 0 && x == nums[a - 1]) continue; // 跳过重复数字
            if (x + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) break; // 优化一
            if (x + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue; // 优化二
            for (int b = a + 1; b < n - 2; b++) { // 枚举第二个数
                long y = nums[b];
                if (b > a + 1 && y == nums[b - 1]) continue; // 跳过重复数字
                if (x + y + nums[b + 1] + nums[b + 2] > target) break; // 优化一
                if (x + y + nums[n - 2] + nums[n - 1] < target) continue; // 优化二
                int c = b + 1;
                int d = n - 1;
                while (c < d) { // 双指针枚举第三个数和第四个数
                    long s = x + y + nums[c] + nums[d]; // 四数之和
                    if (s > target) d--;
                    else if (s < target) c++;
                    else { // s == target
                        ans.add(Arrays.asList((int) x, (int) y, nums[c], nums[d]));
                        for (c++; c < d && nums[c] == nums[c - 1]; c++) ; // 跳过重复数字
                        for (d--; d > c && nums[d] == nums[d + 1]; d--) ; // 跳过重复数字
                    }
                }
            }
        }
        return ans;
    }

}
