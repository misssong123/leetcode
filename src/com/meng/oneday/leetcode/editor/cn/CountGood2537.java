package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class CountGood2537 {
    /**
     * 解答成功:
     * 	执行耗时:49 ms,击败了53.39% 的Java用户
     * 	内存消耗:59.1 MB,击败了65.87% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countGood2537(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int left = 0,right = 0,len = nums.length,temp = 0;
        long res = 0;
        while (right < len){
            int num = nums[right];
            //查找目前出现的相同个数
            int cnt = map.getOrDefault(num,0);
            temp += cnt;
            map.put(num,cnt+1);
            //当前满足要求
            if (temp >= k){
                res += len -right;
                //移动左指针，缩小范围
                while(left < right){
                    int leftNum = nums[left++];
                    int leftCnt = map.get(leftNum)-1;
                    map.put(leftNum,leftCnt);
                    temp -= leftCnt;
                    //如果左指针移动后，满足要求
                    if (temp < k){
                        break;
                    }
                    //左指针移动后，不满足要求
                    res += len - right;
                }
            }
            right++;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:49 ms,击败了53.39% 的Java用户
     * 	内存消耗:59.3 MB,击败了51.01% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countGood(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length,temp = 0,left = 0;
        long res = 0;
        for (int right = 0 ; right < len ; right++ ){
            int num = nums[right];
            //查找目前出现的相同个数
            int cnt = map.getOrDefault(num,0);
            temp += cnt;
            map.put(num,cnt+1);
            //当前满足要求
            if (temp >= k){
                res += len -right;
                //移动左指针，缩小范围
                while(left < right){
                    int leftNum = nums[left++];
                    int leftCnt = map.get(leftNum)-1;
                    map.put(leftNum,leftCnt);
                    temp -= leftCnt;
                    //如果左指针移动后，满足要求
                    if (temp < k){
                        break;
                    }
                    //左指针移动后，不满足要求
                    res += len - right;
                }
            }
        }

        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:57 ms,击败了35.23% 的Java用户
     * 	内存消耗:59.4 MB,击败了45.13% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long countGoodOther(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int pairs = 0;
        int left = 0;
        for (int x : nums) {
            int c = cnt.getOrDefault(x, 0);
            pairs += c; // 进
            cnt.put(x, c + 1);
            while (pairs >= k) {
                x = nums[left];
                c = cnt.get(x);
                pairs -= c - 1; // 出
                cnt.put(x, c - 1);
                left++;
            }
            ans += left;
        }
        return ans;
    }
}
