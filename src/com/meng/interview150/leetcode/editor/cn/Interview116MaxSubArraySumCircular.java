package com.meng.interview150.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class Interview116MaxSubArraySumCircular {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了64.45% 的Java用户
     * 	内存消耗:46.5 MB,击败了88.67% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircularMy(int[] nums) {
        int n = nums.length;
        int[] preMax = new int[n];
        int[] pre = new int[n];
        preMax[0] = nums[0];
        pre[0] = nums[0];
        int max = nums[0];
        int temp = nums[0];
        //记录前缀最大和
        for(int i = 1 ; i < n ; i++){
            pre[i] = Math.max(pre[i-1],0) + nums[i];
            max = Math.max(max,pre[i]);
            temp+=nums[i];
            preMax[i] = Math.max(preMax[i-1],temp);
        }
        int suf = nums[n-1];
        for(int i = n -2 ; i >=0 ; i--){
            max = Math.max(max,suf + Math.max(preMax[i],pre[i]));
            suf+=nums[i];
        }
        return max;
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了86.76% 的Java用户
     * 	内存消耗:48.8 MB,击败了7.40% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular1(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }

    /**
     *解答成功:
     * 	执行耗时:7 ms,击败了24.26% 的Java用户
     * 	内存消耗:48 MB,击败了46.94% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular2(int[] nums) {
        int n = nums.length;
        int preMax = nums[0], maxRes = nums[0];
        int preMin = nums[0], minRes = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]);
            maxRes = Math.max(maxRes, preMax);
            preMin = Math.min(preMin + nums[i], nums[i]);
            minRes = Math.min(minRes, preMin);
            sum += nums[i];
        }
        if (maxRes < 0) {
            return maxRes;
        } else {
            return Math.max(maxRes, sum - minRes);
        }
    }

    /**
     *解答成功:
     * 	执行耗时:30 ms,击败了5.18% 的Java用户
     * 	内存消耗:46 MB,击败了98.69% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        Deque<int[]> queue = new ArrayDeque<int[]>();
        int pre = nums[0], res = nums[0];
        queue.offerLast(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.pollFirst();
            }
            pre += nums[i % n];
            res = Math.max(res, pre - queue.peekFirst()[1]);
            while (!queue.isEmpty() && queue.peekLast()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }
}
