package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FindLHS594 {
    /**
     * map实现
     * 解答成功:
     * 	执行耗时:17 ms,击败了38.11% 的Java用户
     * 	内存消耗:44.9 MB,击败了49.13% 的Java用户
     * @param nums
     * @return
     */
    public int findLHS594(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int res = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int val = entry.getValue();
            res = Math.max(val+map.getOrDefault(num-1,-val),res);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了98.95% 的Java用户
     * 	内存消耗:44.8 MB,击败了58.57% 的Java用户
     * @param nums
     * @return
     */
    public int findLHSMy2(int[] nums) {
        Arrays.sort(nums);
        int cur = 0,last =0,res=0;
        for(int i = 0 ;i < nums.length;i++){
            if(nums[i] == nums[cur]){
                continue;
            }
            if (nums[i] == nums[cur] + 1){
                //记录第一次出现的位置
                if (last == cur){
                    last = i;
                }
                res = Math.max(res,i-cur+1);
            }else {//不符合统计条件
                if (nums[i] - nums[i-1] > 1){
                    cur = last = i;
                }else {
                    cur = last;
                    last = i;
                    res = Math.max(res,i-cur+1);
                }
            }
        }
        return res;
    }
    public int findLHSOther(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) map.put(i, map.getOrDefault(i, 0) + 1);
        int ans = 0;
        for (int i : nums) {
            if (map.containsKey(i - 1)) {
                ans = Math.max(ans, map.get(i) + map.get(i - 1));
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了98.95% 的Java用户
     * 	内存消耗:45 MB,击败了12.59% 的Java用户
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                res = Math.max(res, end - begin + 1);
            }
        }
        return res;
    }
}
