package com.meng.top100.leetcode.editor.cn;

import java.util.PriorityQueue;

class FindXSum3318 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了87.50% 的Java用户
     * 	内存消耗:46.1 MB,击败了6.25% 的Java用户
     * @param nums
     * @param k
     * @param x
     * @return
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int len = k - 1;
        for(int i = 0 ; i < ans.length ;i++){
            ans[i] = cal(nums,i,i+len,x);
        }
        return ans;
    }

    private int cal(int[] nums, int start, int end, int x) {
        int[] cnt = new int[51];
        for(int i = start ; i <= end ;i++){
            cnt[nums[i]]++;
        }
        PriorityQueue<int[]> po = new PriorityQueue<>((a,b)->{
            if (a[1] != b[1]){
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        for(int i = 0 ; i < 51 ;i++){
            if(cnt[i] > 0){
                po.offer(new int[]{i,cnt[i]});
            }
        }
        int sum = 0;
        while (!po.isEmpty() && x > 0){
            int[] cur = po.poll();
            sum += cur[0] * cur[1];
            x--;
        }
        return sum;
    }
}
