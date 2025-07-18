package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class MinimumDifference2163 {
    /**
     * 解答成功:
     * 	执行耗时:186 ms,击败了30.44% 的Java用户
     * 	内存消耗:96.2 MB,击败了8.70% 的Java用户
     * @param nums
     * @return
     */
    public long minimumDifference2163(int[] nums) {
        int n = nums.length/3;
        long[] min = new long[n+1];
        long[] max = new long[n+1];
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        //计算初始值
        for(int i = 0 ; i < n ; i++){
            maxQueue.offer(nums[i]);
            min[0] += nums[i];
        }
        for(int i = n*2 ; i < n*3 ; i++){
            minQueue.offer(nums[i]);
            max[0] += nums[i];
        }
        System.out.println(Arrays.toString(min));
        System.out.println(Arrays.toString(max));
        //计算最小值
        for(int i = n,j=1 ; i < n*2 ;j++, i++){
            min[j] = min[j-1];
            if (!maxQueue.isEmpty()&&nums[i] < maxQueue.peek()){
                min[j] -= (maxQueue.poll() - nums[i]);
                maxQueue.offer(nums[i]);
            }
        }
        //计算最大值
        for(int i = n*2 -1,j=1 ; i >=n ;j++, i--){
            max[j] = max[j-1];
            if (!minQueue.isEmpty()&&nums[i] > minQueue.peek()){
                max[j] += nums[i] -minQueue.poll() ;
                minQueue.offer(nums[i]);
            }
        }
        long res = min[0]-max[0];
        for(int i = 0 ; i <= n ; i++){
            res = Math.min(res,min[i]-max[n-i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:154 ms,击败了73.91% 的Java用户
     * 	内存消耗:78.1 MB,击败了69.57% 的Java用户
     * @param nums
     * @return
     */
    public long minimumDifference(int[] nums) {
        int m = nums.length;
        int n = m / 3;
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        long sum = 0;
        for (int i = m - 1; i >= m - n; i--) {
            minPQ.offer(nums[i]);
            sum += nums[i];
        }

        long[] sufMax = new long[m - n + 1]; // 后缀最大和
        sufMax[m - n] = sum;
        for (int i = m - n - 1; i >= n; i--) {
            int v = nums[i];
            if (v > minPQ.peek()) {
                sum += v - minPQ.poll();
                minPQ.offer(v);
            }
            sufMax[i] = sum;
        }

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        long preMin = 0; // 前缀最小和
        for (int i = 0; i < n; ++i) {
            maxPQ.offer(nums[i]);
            preMin += nums[i];
        }

        long ans = preMin - sufMax[n]; // i=n-1 时的答案
        for (int i = n; i < m - n; i++) {
            int v = nums[i];
            if (v < maxPQ.peek()) {
                preMin += v - maxPQ.poll();
                maxPQ.offer(v);
            }
            ans = Math.min(ans, preMin - sufMax[i + 1]);
        }
        return ans;
    }

}
