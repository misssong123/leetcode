package com.meng.oneday.leetcode.editor.cn;

class MinSwaps2134 {
    /**
     * 想法有误
     * 寻找第一个‘1’的位置，最后一个‘1’的位置
     * 寻找出现‘1’的最后一个位置，第二次出现‘1’的位置
     * @param nums
     * @return
     */
    public int minSwapsMyError(int[] nums) {
        int len = nums.length;
        int firstOne = -1, lastOne = -1;
        int firstFindLast = -1 ,secondFindFirst = -1;
        int num = 0;
        for(int i = 0 ; i  < len; i++){
            if(nums[i] == 1){
                if(firstOne == -1){
                    firstOne = i;
                }
                lastOne = i;
                if(firstFindLast != -1 && secondFindFirst == -1){
                    secondFindFirst = i;
                }
                num++;
            }else {
                if(firstOne != -1&&firstFindLast == -1){
                    firstFindLast = i;
                }
            }
        }
        if (firstOne == -1){
            return 0;
        }
        return Math.min(lastOne-firstOne+1-num,len-secondFindFirst+firstFindLast-num);
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了67.20% 的Java用户
     * 	内存消耗:58.3 MB,击败了21.25% 的Java用户
     * @param nums
     * @return
     */
    public int minSwaps2134(int[] nums) {
        //计算‘1’的个数
        int count = 0;
        for(int num : nums){
            if (num == 1){
                count++;
            }
        }
        if (count == 0){
            return 0;
        }
        //计算第一个窗口‘1’的个数
        int temp  = 0;
        for(int i = 0 ; i < count ; i++){
            if (nums[i] == 1){
                temp++;
            }
        }
        if (count == temp){
            return 0;
        }
        //滑动窗口
        int res = count - temp;
        for(int i = count ; i < nums.length + count ; i++){
            temp += nums[i - count] == 1? -1 :0;
            temp += nums[i % nums.length] == 1? 1 :0;
            res = Math.min(res,count - temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了33.05% 的Java用户
     * 	内存消耗:57.9 MB,击败了82.18% 的Java用户
     * @param nums
     * @return
     */
    public int minSwaps(int[] nums) {
        //因为两个数位的数字可以跨顺序任意交换 我们可以统计出数组中 1 的数目
        //然后将它作为滑动窗口的长度 统计0的最小值
        int sum = 0;
        int n = nums.length;
        int count = 0;
        //定义min暂时为整数的最大值
        int min = Integer.MAX_VALUE;
        //先统计出数组中 1 的数目
        for(int i = 0;i < n;i++) if(nums[i] == 1) sum++;
        //提前退出
        if(sum == 0) return 0;
        // 要保证 最后sum -1 个数组有答案 我们扩充循环  增加 sum - 1
        for(int i = 0;i < sum + n - 1;i++){
            //进窗口
            if(nums[i%n] == 0) count++;
            //在i == sum-1时 才开始第一个窗口
            if(i < sum-1) continue;
            //更新答案
            min = Math.min(min,count);
            //出窗口
            if(nums[(i-sum+1)%n] == 0) count--;
        }
        return min;
    }
}
