package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class Find132pattern456 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了43.46% 的Java用户
     * 	内存消耗:95.3 MB,击败了80.39% 的Java用户
     * @param nums 输入的整数数组
     * @return 如果数组中存在132模式则返回true，否则返回false
     */
    public boolean find132pattern456(int[] nums) {
        // 如果数组长度小于3，无法形成132模式，直接返回false
        if (nums.length < 3){
            return false;
        }
        // 初始化k为最小整数值，用于存储中间值
        int k = Integer.MIN_VALUE;
        // 使用双端队列作为辅助数据结构
        Deque<Integer> d = new ArrayDeque<>();
        // 从数组末尾开始向前遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果当前元素小于k，说明找到了132模式中的1，返回true
            if (nums[i] < k){
                return true;
            }
            while(!d.isEmpty() && d.peekLast() < nums[i]){
                k = Math.max(k,d.pollLast());
                // 更新k为队列尾部元素和当前k的最大值
            }
            d.addLast(nums[i]);
            // 将当前元素添加到队列尾部
        }
        return false;
        // 遍历结束后未找到132模式，返回false
    }
    /**
     * 思路错误
     * @param nums
     * @return 输入的整数数组
     */
    public boolean find132patternError(int[] nums) {
        int len = nums.length;
        if (len < 3){
            return false;
        // 如果数组长度小于3，无法形成132模式，直接返回false
        }
        int[] suf = new int[len];
        suf[len - 1] = nums[len - 1];
        // 创建一个后缀数组，存储每个位置右侧的最小值
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = Math.min(suf[i + 1], nums[i]);
        }
        // 从倒数第二个元素开始，填充后缀数组
        int pre = nums[0];
        for (int i = 1 ; i < len - 1 ; i++){
            if (nums[i] > pre && nums[i] > suf[i + 1]){
        // 初始化前缀最小值
                return true;
        // 遍历数组，寻找132模式
            }
            // 如果当前元素大于前缀最小值且大于后缀最小值，则找到132模式
            pre = Math.min(pre, nums[i]);
        }
        return false;
            // 更新前缀最小值
    }

        // 未找到132模式，返回false
    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了70.00% 的Java用户
     * 	内存消耗:95.7 MB,击败了73.46% 的Java用户
     * @param nums
     * @return
     */
    public boolean find132patternOther(int[] nums) {
        int n = nums.length;
        Deque<Integer> d = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        // 获取数组长度
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) return true;
            while (!d.isEmpty() && d.peekLast() < nums[i]) {
                // 事实上，k 的变化也具有单调性，直接使用 k = pollLast() 也是可以的
                k = Math.max(k, d.pollLast());
            }
            d.addLast(nums[i]);
        }
        return false;
    }

}
