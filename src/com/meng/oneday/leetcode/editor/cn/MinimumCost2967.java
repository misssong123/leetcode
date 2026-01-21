package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinimumCost2967 {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了80.00% 的Java用户
     * 	内存消耗:63.2 MB,击败了8.00% 的Java用户
     * @param nums
     * @return
     */
    public long minimumCost2967(int[] nums) {
        //排序
        Arrays.sort(nums);
        long sum = 0L;
        for (int num : nums){
            sum += num;
        }
        int len = nums.length;
        //前缀和
        long[] pre = new long[len + 1];
        for (int i = 0; i < len; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        //范围内
        List<Integer> vals = new ArrayList<>();
        int midValue = nums[nums.length / 2];
        if (isPalindrome(midValue)){
            vals.add(midValue);
        }
        //寻找前缀和后缀
        vals.add(findPalindromes(midValue,-1));
        vals.add(findPalindromes(midValue,1));
        //遍历可能大小
        long res = Long.MAX_VALUE;
        for(int i : vals){
            //寻找第一个大于当前元素的下标
            int index = search(nums,i);
            if (index == -1){
               res = Math.min( (long)i * len - sum,res);
            }else{
                long sufMax = pre[len] - pre[index];
                long preSum = sum - sufMax;
                long diff = sufMax - (long) (len - index) * i + (long) index * i - preSum;
                res = Math.min(res,diff);
            }
        }
        return res;
    }

    private int search(int[] nums, int i) {
        int res = -1;
        int l = 0 , r = nums.length - 1;
        while (l <= r){
            int mid = (l + r) >> 1;
            if (nums[mid] > i){
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }
    public static Integer findPalindromes(int val,int step) {
        while (!isPalindrome(val)) {
            val += step;
        }
        return val;
    }
    /**
     * 判断一个整数是否是回文数
     */
    public static boolean isPalindrome(int num) {
        // 负数通常不被视为回文数
        if (num < 10) return true;

        int original = num;
        int reversed = 0;

        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        return original == reversed;
    }

    private static final int[] pal = new int[109999];

    static {
        // 严格按顺序从小到大生成所有回文数（不用字符串转换）
        int palIdx = 0;
        for (int base = 1; base <= 10000; base *= 10) {
            // 生成奇数长度回文数
            for (int i = base; i < base * 10; i++) {
                int x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                pal[palIdx++] = x;
            }
            // 生成偶数长度回文数
            if (base <= 1000) {
                for (int i = base; i < base * 10; i++) {
                    int x = i;
                    for (int t = i; t > 0; t /= 10) {
                        x = x * 10 + t % 10;
                    }
                    pal[palIdx++] = x;
                }
            }
        }
        pal[palIdx++] = 1_000_000_001; // 哨兵，防止下面代码中的 i 下标越界
    }

    /**
     * 解答成功:
     * 	执行耗时:23 ms,击败了60.00% 的Java用户
     * 	内存消耗:59.6 MB,击败了32.00% 的Java用户
     * @param nums
     * @return
     */
    public long minimumCost(int[] nums) {
        // 注：排序只是为了找中位数，如果用快速选择算法，可以做到 O(n)
        Arrays.sort(nums);
        int n = nums.length;

        // 二分找中位数右侧最近的回文数
        int i = lowerBound(nums[(n - 1) / 2]);

        // 回文数在中位数范围内
        if (pal[i] <= nums[n / 2]) {
            return cost(nums, i); // 直接变成 pal[i]
        }

        // 枚举离中位数最近的两个回文数 pal[i-1] 和 pal[i]
        return Math.min(cost(nums, i - 1), cost(nums, i));
    }

    // 返回 nums 中的所有数变成 pal[i] 的总代价
    private long cost(int[] nums, int i) {
        int target = pal[i];
        long sum = 0;
        for (int x : nums) {
            sum += Math.abs(x - target);
        }
        return sum;
    }

    // 开区间写法
    private int lowerBound(int target) {
        int left = -1, right = pal.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // pal[left] < target
            // pal[right] >= target
            int mid = left + (right - left) / 2;
            if (pal[mid] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right; // 或者 left+1
    }
}
