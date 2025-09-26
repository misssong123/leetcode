package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class TriangleNumber611 {
    /**
     * 解答成功:
     * 	执行耗时:897 ms,击败了5.59% 的Java用户
     * 	内存消耗:42.5 MB,击败了48.84% 的Java用户
     * @param nums
     * @return
     */
    public int triangleNumber611(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 0;
        for(int i = 0 ; i < len ; i++){
            for(int j = i + 1 ; j < len ; j++){
                for(int k = j + 1 ; k < len ; k++){
                    if(nums[i] + nums[j] <= nums[k]){
                        break;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了99.57% 的Java用户
     * 	内存消耗:42.3 MB,击败了99.35% 的Java用户
     * @param nums
     * @return
     */
    public int triangleNumberOther1(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int k = 2; k < nums.length; k++) {
            int c = nums[k];
            int i = 0; // a=nums[i]
            int j = k - 1; // b=nums[j]
            while (i < j) {
                if (nums[i] + nums[j] > c) {
                    ans += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了99.57% 的Java用户
     * 	内存消耗:42.3 MB,击败了98.33% 的Java用户
     * @param nums
     * @return
     */
    public int triangleNumberOther2(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int k = nums.length - 1; k > 1; k--) {
            int c = nums[k];
            if (nums[0] + nums[1] > c) { // 优化一
                ans += (k + 1) * k * (k - 1) / 6;
                break;
            }
            if (nums[k - 2] + nums[k - 1] <= c) { // 优化二
                continue;
            }
            int i = 0; // a=nums[i]
            int j = k - 1; // b=nums[j]
            while (i < j) {
                if (nums[i] + nums[j] > c) {
                    ans += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了98.60% 的Java用户
     * 	内存消耗:42.3 MB,击败了99.35% 的Java用户
     * @param nums
     * @return
     */
    public int triangleNumberOther3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int a = nums[i];
            if (a == 0) { // 三角形的边不能是 0
                continue;
            }
            int j = i + 1;
            for (int k = i + 2; k < n; k++) {
                while (nums[k] - nums[j] >= a) {
                    j++;
                }
                // 如果 a=nums[i] 和 c=nums[k] 固定不变
                // 那么 b 可以是 nums[j],nums[j+1],...,nums[k-1]，一共有 k-j 个
                ans += k - j;
            }
        }
        return ans;
    }

}
