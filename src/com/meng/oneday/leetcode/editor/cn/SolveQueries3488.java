package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class SolveQueries3488 {
    /**
     * 解答成功:
     * 	执行耗时:120 ms,击败了84.54% 的Java用户
     * 	内存消耗:182 MB,击败了81.64% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public List<Integer> solveQueries3488(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = nums.length;
        for(int i = 0 ; i < len ; i++ ){
            if (!map.containsKey(nums[i])){
                map.put(nums[i],new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int query : queries) {
            res.add(find(map.get(nums[query]), query, len));
        }
        return res;
    }
    private int find(List<Integer> list , int target,int len){
        if (list.size() == 1){
            return -1;
        }
        //获取当前下标
        int l = 0 , r = list.size() - 1,index = 0;
        while (l <= r){
            int mid = (l + r) >> 1;
            if (list.get(mid) == target){
                index = mid;
                break;
            }else  if( list.get(mid) < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        if (index == 0){
            int res = list.get(index + 1) - target;
            return Math.min(res,len - (list.get(list.size() - 1) - target));
        }else if (index == list.size() - 1){
            int res = target - list.get(index - 1) ;
            return Math.min(res,len - (target - list.get(0)));
        }else{
            return Math.min(target - list.get(index - 1),list.get(index + 1) - target);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:169 ms,击败了25.12% 的Java用户
     * 	内存消耗:191.4 MB,击败了26.09% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int n = nums.length;
        for (List<Integer> p : indices.values()) {
            // 前后各加一个哨兵
            int i0 = p.get(0);
            p.add(0, p.get(p.size() - 1) - n);
            p.add(i0 + n);
        }

        List<Integer> ans = new ArrayList<>(queries.length); // 预分配空间
        for (int i : queries) {
            List<Integer> p = indices.get(nums[i]);
            if (p.size() == 3) {
                ans.add(-1);
            } else {
                int j = Collections.binarySearch(p, i);
                ans.add(Math.min(i - p.get(j - 1), p.get(j + 1) - i));
            }
        }
        return ans;
    }

}
