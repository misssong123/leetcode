package com.meng.interview150.leetcode.editor.cn;

import java.util.PriorityQueue;
import java.util.TreeMap;

class Interview139LengthOfLIS {
    /**
     * 解答成功:
     * 	执行耗时:29 ms,击败了84.91% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.07% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
        for(int num : nums){
            //寻找比num小的最大数
            Integer findKey = map.floorKey(num - 1);
            int[] numArr = {num,1};
            if(findKey != null){
                numArr[1] = map.get(findKey) + 1;
            }
            while (!pq.isEmpty()){
                if (numArr[0] <= pq.peek()[0] && numArr[1] >= pq.peek()[1]){
                    map.remove(pq.peek()[0]);
                    pq.poll();
                }else {
                    break;
                }
            }
            //移除比num大但是值小于numArr[1]的数
            while (true){
                Integer maxKey = map.higherKey(num);
                if (maxKey != null && map.get(maxKey) <= numArr[1]){
                    map.remove(maxKey);
                }else {
                    break;
                }
            }
            pq.add(numArr);
            map.put(numArr[0],numArr[1]);
        }
        return pq.isEmpty()?0:pq.peek()[1];
    }

    /**
     * 解答成功:
     * 	执行耗时:62 ms,击败了49.05% 的Java用户
     * 	内存消耗:43 MB,击败了67.97% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLISOfficial1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了99.69% 的Java用户
     * 	内存消耗:43.1 MB,击败了43.91% 的Java用户
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
