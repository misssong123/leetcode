package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MaximumOr2680 {
    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public long maximumOrMy(int[] nums, int k) {
        List<Integer> maxIndex = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Integer.toBinaryString(nums[i]).length();
            if( cur > max){
                max = cur;
                maxIndex.clear();
                maxIndex.add(i);
            }else if (cur == max){
                maxIndex.add(i);
            }
        }
        long pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!maxIndex.contains(i)){
                pre |= nums[i];
            }
        }
        long res = 0;
        for (int i = 0 ; i < maxIndex.size(); i++){
            long temp = pre;
            for(int j = 0 ; j < maxIndex.size() ; j++){
                if (i == j){
                   temp |= ((long) nums[maxIndex.get(i)] << k);
                }else {
                    temp |= nums[maxIndex.get(j)];
                }
            }
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了20.24% 的Java用户
     * 	内存消耗:54.4 MB,击败了78.57% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maximumOrProve(int[] nums, int k) {
        int len = nums.length;
        long[] suf = new long[len + 1];
        for(int i = len - 2 ; i >= 0 ; i--){
            suf[i] = suf[i + 1] | nums[i + 1];
        }
        long pre = 0,ans = 0;
        for(int i = 0; i < len ; i++){
            ans = Math.max(ans,pre | ((long) nums[i] << k) | suf[i]);
            pre |= nums[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:55.1 MB,击败了8.33% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maximumOrOther(int[] nums, int k) {
        int allOr = 0;
        int fixed = 0;
        for (int x : nums) {
            // 如果在计算 allOr |= x 之前，allOr 和 x 有公共的 1
            // 那就意味着有多个 nums[i] 在这些比特位上都是 1
            fixed |= allOr & x; // 把公共的 1 记录到 fixed 中
            allOr |= x; // 所有数的 OR
        }

        long ans = 0;
        for (int x : nums) {
            ans = Math.max(ans, (allOr ^ x) | fixed | ((long) x << k));
        }
        return ans;
    }
}
