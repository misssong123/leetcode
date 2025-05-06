package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CountSubArrays2962 {
    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了7.52% 的Java用户
     * 	内存消耗:58.9 MB,击败了84.17% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countSubarrays2962(int[] nums, int k) {
        //获取最大值
        int max = Arrays.stream(nums).max().getAsInt();
        //获取每个最大值的下标
        List<Integer> index = new ArrayList<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == max){
                index.add(i);
            }
        }
        long res = 0;
        //遍历最大值的下标
        for(int i = 0 ; i <= index.size() - k ; i++){
            //获取右侧下标
            int right = i + k - 1;
            //获取左侧元素个数
            int leftNum = i> 0 ?index.get(i) - index.get(i-1) : index.get(i)+1;
            //获取右侧元素个数
            int rightNum = nums.length - index.get(right) ;
            //计算
            res += (long)leftNum * rightNum;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了21.43% 的Java用户
     * 	内存消耗:61.6 MB,击败了73.16% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countSubarrays(int[] nums, int k) {
        //获取最大值
        int max = Arrays.stream(nums).max().getAsInt();
        int left = 0,cnt =0;
        long res = 0;
        for(int num : nums){
            if(num == max){
                cnt++;
            }
            while (cnt == k){
                if (nums[left] == max){
                    cnt--;
                }
                left++;
            }
            res += left;
        }
        return res;
    }
}
