package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumMountainRemovals1671 {
    /**
     * 解答成功:
     * 	执行耗时:1721 ms,击败了5.00% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.00% 的Java用户
     * @param nums
     * @return
     */
    public int minimumMountainRemovalsMy(int[] nums) {
        int len = nums.length;
        int[] preNum = new int[len];
        int res = len;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]){
                return b[1] - a[1];
            }
            return a[0] - b[0];}
        );
        queue.add(new int[]{nums[0],0});
        List<int[]> temp = new ArrayList<>();
        for(int i = 1 ; i < len -1 ; i++){
            //查询第一个小于当前数字的最大长度
            while (!queue.isEmpty() && queue.peek()[0] >= nums[i]){
                temp.add(queue.poll());
            }
            //设置当前坐标最大值
            if (!queue.isEmpty()){
                preNum[i] = queue.peek()[1]+1;
            }
            //将当前坐标加入队列
            queue.add(new int[]{nums[i], preNum[i]});
            //将之前大于当前数字的坐标加入队列
            if (!temp.isEmpty()){
                queue.addAll(temp);
            }
            temp.clear();
        }
        queue.clear();
        queue.add(new int[]{nums[len-1],0});
        for(int i = len-2 ; i >=1 ; i--){
            //查询第一个小于当前数字的最大长度
            while (!queue.isEmpty() && queue.peek()[0] >= nums[i]){
                temp.add(queue.poll());
            }
            //设置当前坐标最大值
            int num = 0;
            if (!queue.isEmpty()){
                num = queue.peek()[1]+1;
            }
            //将当前坐标加入队列
            queue.add(new int[]{nums[i], num});
            //将之前大于当前数字的坐标加入队列
            if (!temp.isEmpty()){
                queue.addAll(temp);
            }
            temp.clear();
            if (preNum[i]>0&&num>0){
                res = Math.min(res, len -(num + preNum[i]+1));
            }
        }
        return res;
    }

    /**
     * 时间
     * 详情
     * 12ms
     * 击败 74.38%使用 Java 的用户
     * 内存
     * 详情
     * 43.01MB
     * 击败 5.00%使用 Java 的用户
     * @param nums
     * @return
     */
    public int minimumMountainRemovals1(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        List<Integer> g = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            suf[i] = j + 1; // 从 nums[i] 开始的最长严格递减子序列的长度
        }

        int mx = 0;
        g.clear();
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            int pre = j + 1; // 在 nums[i] 结束的最长严格递增子序列的长度
            if (pre >= 2 && suf[i] >= 2) {
                mx = Math.max(mx, pre + suf[i] - 1); // 减去重复的 nums[i]
            }
        }
        return n - mx;
    }

    // 请看 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }

    /**
     * 动态规划
     * 时间
     * 详情
     * 61ms
     * 击败 11.88%使用 Java 的用户
     * 内存
     * 详情
     * 43.04MB
     * 击败 5.00%使用 Java 的用户
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray(nums);
        int[] reversed = reverse(nums);
        int[] suf = getLISArray(reversed);
        suf = reverse(suf);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (pre[i] > 1 && suf[i] > 1) {
                ans = Math.max(ans, pre[i] + suf[i] - 1);
            }
        }

        return n - ans;
    }

    public int[] getLISArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    public int[] reverse(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }

    /**
     * 二分查找
     * 时间
     * 详情
     * 52ms
     * 击败 35.00%使用 Java 的用户
     * 内存
     * 详情
     * 43.34MB
     * 击败 5.00%使用 Java 的用户
     * @param nums
     * @return
     */
    public int minimumMountainRemovals3(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray3(nums);
        int[] reversed = reverse3(nums);
        int[] suf = getLISArray3(reversed);
        suf = reverse3(suf);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (pre[i] > 1 && suf[i] > 1) {
                ans = Math.max(ans, pre[i] + suf[i] - 1);
            }
        }

        return n - ans;
    }

    public int[] getLISArray3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        List<Integer> seq = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            int index = binarySearch(seq, nums[i]);
            if (index == seq.size()) {
                seq.add(nums[i]);
                dp[i] = seq.size();
            } else {
                seq.set(index, nums[i]);
                dp[i] = index + 1;
            }
        }
        return dp;
    }

    public int[] reverse3(int[] nums) {
        int n = nums.length;
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }
        return reversed;
    }

    public int binarySearch(List<Integer> seq, int target) {
        int low = 0, high = seq.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (seq.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
