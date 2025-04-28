package com.meng.oneday.leetcode.editor.cn;

class CountSubArrays2302 {
    /**
     * 解答成功:
     * 	执行耗时:59 ms,击败了6.48% 的Java用户
     * 	内存消耗:56.3 MB,击败了98.31% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countSubarrays2302(int[] nums, long k) {
        int n = nums.length;
        //前缀和数组
        long res = 0;
        long[] cache = new long[n+1];
        for(int i = 1; i <= n; i++){
            int num = nums[i-1];
            cache[i] = cache[i-1] + num;
            if (num < k){
                //计算符合条件下标
                int right = i-1,left = 0;
                while(left < right){
                    int mid = (left+right)/2;
                    int len = i - mid;
                    if((cache[i] - cache[mid])*len < k){
                        right = mid;
                    }else {
                        left = mid+1;
                    }
                }
                res += (i -right);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:60.6 MB,击败了18.31% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countSubarrays(int[] nums, long k) {
        long ans = 0;
        long sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }

}
