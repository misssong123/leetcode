package com.meng.oneday.leetcode.editor.cn;

class MinMoves1674 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了100.00% 的Java用户
     * 	内存消耗:75 MB,击败了33.33% 的Java用户
     * @param nums
     * @param limit
     * @return
     */
    public int minMoves1674(int[] nums, int limit) {
        int [] cache = new int[limit*2+2];
        int n = nums.length;
        for(int i = 0 ; i < n / 2 ; i++){
            int min = Math.min(nums[i],nums[n-i-1])+1;
            int max = Math.max(nums[i],nums[n-i-1]) + limit;
            int val = nums[i] + nums[n-i-1];
            cache[2]+=2;
            cache[min] -= 1;
            cache[val] -= 1;
            cache[val+1] += 1;
            cache[max+1] += 1;
        }
        int ans = cache[2];
        int temp = ans;
        for(int i = 3 ; i < cache.length ; i++){
            temp += cache[i];
            ans = Math.min(ans,temp);
        }
        return ans;
    }
}
