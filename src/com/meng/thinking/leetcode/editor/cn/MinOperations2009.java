package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MinOperations2009 {
    /**
     * 解答成功:
     * 	执行耗时:60 ms,击败了58.70% 的Java用户
     * 	内存消耗:57.2 MB,击败了52.17% 的Java用户
     * @param nums
     * @return
     */
    public int minOperationsMy(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int right = 0;
        int ans = len;
        Set<Integer> cache = new HashSet<>();
        for (int i = 0 ; i < len ; i++){
            //寻找已当前元素为起点的最长连续子序列
            while (right<len&&nums[right] - nums[i] <= len-1){
                cache.add(nums[right]);
                right++;
            }
            ans = Math.min(ans,len-cache.size());
            if (right == len){
                break;
            }
            cache.remove(nums[i]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:109 ms,击败了8.70% 的Java用户
     * 	内存消耗:62.2 MB,击败了15.22% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> sortedUniqueNums = new ArrayList<Integer>(set);
        Collections.sort(sortedUniqueNums);
        int res = n;
        int j = 0;
        for (int i = 0; i < sortedUniqueNums.size(); i++) {
            int left = sortedUniqueNums.get(i);
            int right = left + n - 1;
            while (j < sortedUniqueNums.size() && sortedUniqueNums.get(j) <= right) {
                res = Math.min(res, n - (j - i + 1));
                j++;
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
