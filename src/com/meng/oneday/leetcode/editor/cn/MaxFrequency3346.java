package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MaxFrequency3346 {
    /**
     * 思路错误
     * @param nums
     * @param k
     * @param numOperations
     * @return
     */
    public int maxFrequency3346Err(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int len = nums.length;
        int max = nums[len-1];
        int[] cnt = new int[max+1];
        int res = 0;
        for(int num : nums){
            cnt[num]++;
            res = Math.max(res,cnt[num]);
        }
        if (numOperations == 0 || res == len) {
            return res;
        }
        //滑动窗口+差分
        int[] diff = new int[max+1];
        for(int i = 0 ; i < len ; i++){
            int num = nums[i];
            cnt[num]--;
            int l = Math.max(0,num - k);
            int r = Math.min(max,num + k);
            diff[l]++;
            if (r < max){
                diff[r+1]--;
            }
            if (i +1 > numOperations){
                num = nums[i - numOperations];
                cnt[num]++;
                l = Math.max(0,num - k);
                r = Math.min(max,num + k);
                diff[l]--;
                if (r < max){
                    diff[r+1]++;
                }
            }
            //获取最大值
            int pre = 0;
            for(int j = 0 ; j <= max ; j++){
                pre += diff[j];
                res = Math.max(res,pre + cnt[j]);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了86.96% 的Java用户
     * 	内存消耗:57 MB,击败了82.61% 的Java用户
     * @param nums
     * @param k
     * @param numOperations
     * @return
     */
    public int maxFrequency3346(int[] nums, int k, int numOperations) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] cnt = new int[max + 1];
        int min = max;
        int res = 0;
        for (int num : nums) {
            cnt[num]++;
            min = Math.min(min, num);
            res = Math.max(res,cnt[num]);
        }
        if (min == max || numOperations == 0){
            return res;
        }
        //前缀和
        int[] pre = new int[max + 1];
        for (int i = 1; i <= max; i++) {
            pre[i] = pre[i - 1] + cnt[i];
        }
        for(int i = 1 ; i <= max ; i++){
            int l = Math.max(min, i - k);
            int r = Math.min(max, i+k);
            res = Math.max(res, Math.min(pre[r] - pre[l - 1] - cnt[i],numOperations)+cnt[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:193 ms,击败了56.52% 的Java用户
     * 	内存消耗:61.2 MB,击败了47.83% 的Java用户
     * @param nums
     * @param k
     * @param numOperations
     * @return
     */
    public int maxFrequencyOfficial(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int ans = 0;
        Map<Integer, Integer> numCount = new HashMap<>();
        int lastNumIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != nums[lastNumIndex]) {
                numCount.put(nums[lastNumIndex], i - lastNumIndex);
                ans = Math.max(ans, i - lastNumIndex);
                lastNumIndex = i;
            }
        }

        numCount.put(nums[lastNumIndex], nums.length - lastNumIndex);
        ans = Math.max(ans, nums.length - lastNumIndex);

        for (int i = nums[0]; i <= nums[nums.length - 1]; i++) {
            int l = leftBound(nums, i - k);
            int r = rightBound(nums, i + k);
            int tempAns;
            if (numCount.containsKey(i)) {
                tempAns = Math.min(r - l + 1, numCount.get(i) + numOperations);
            } else {
                tempAns = Math.min(r - l + 1, numOperations);
            }
            ans = Math.max(ans, tempAns);
        }

        return ans;
    }

    private int leftBound(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int rightBound(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] > value) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
