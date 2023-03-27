package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class MinSubarray1590 {
    /**
     * 执行用时：
     * 1998 ms
     * , 在所有 Java 提交中击败了
     * 5.22%
     * 的用户
     * 内存消耗：
     * 59.3 MB
     * , 在所有 Java 提交中击败了
     * 57.46%
     * 的用户
     * 通过测试用例：
     * 142 / 142
     * @param nums
     * @param p
     * @return
     * 前缀和
     */
    public int minSubarray(int[] nums, int p) {
        long sum = 0l ;
        for(int num : nums){
            sum += num;
        }
        if (sum % p == 0){
            return 0;
        }
        int len  = nums.length;
        if (len > 1){
            //移除一位
            for(int num : nums){
                if ((sum - num)%p==0){
                    return 1;
                }
            }
            long[] cache = new long[nums.length];
            cache[0] = nums[0];
            for(int i = 1 ; i < len ; i++){
                cache[i] = cache[i-1] + nums[i];
            }
            //移除多位
            for(int i = 2 ; i < len ; i++){
                for(int j = i - 1 ; j < len ; j++){
                   long temp = sum - cache[j] + (j > i -1 ? cache[j-i]:0);
                   if (temp % p == 0){
                       return i;
                   }
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
       int [] nums = {1000000000,1000000000,1000000000};
       int p = 3;
       long sum = 0l;
       for(int num : nums){
           sum += num;
           System.out.println(sum);
       }
       MinSubarray1590 demo = new MinSubarray1590();
        System.out.println(demo.minSubarray(nums,p));
    }

    /**
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray1(int[] nums, int p) {
        int x = 0;
        for (int num : nums) {
            x = (x + num) % p;
        }
        if (x == 0) {
            return 0;
        }
        Map<Integer, Integer> index = new HashMap<>();
        int y = 0, res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            index.put(y, i); // f[i] mod p = y，因此哈希表记录 y 对应的下标为 i
            y = (y + nums[i]) % p;
            if (index.containsKey((y - x + p) % p)) {
                res = Math.min(res, i - index.get((y - x + p) % p) + 1);
            }
        }
        return res == nums.length ? -1 : res;
    }


}
