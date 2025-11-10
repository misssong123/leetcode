package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MinOperations3542 {
    /**
     * 解答成功:
     * 	执行耗时:1506 ms,击败了5.49% 的Java用户
     * 	内存消耗:274.6 MB,击败了5.49% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations3542(int[] nums) {
        int res = 0;
        TreeSet<Integer> minSet = new TreeSet<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0 ; i < nums.length ; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i],new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()){
            int key = entry.getKey();
            if (minSet.isEmpty()){
                minSet.addAll(entry.getValue());
                if (key != 0){
                    res++;
                }
            }else{
                List<Integer> list = entry.getValue();
                for(int i = 0 ; i < list.size() - 1 ; i++){
                    if(!minSet.subSet(list.get(i),true,list.get(i + 1),true).isEmpty()){
                        res++;
                    }
                }
                res++;
                minSet.addAll(list);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:15 ms,击败了86.81% 的Java用户
     * 	内存消耗:130.4 MB,击败了5.49% 的Java用户
     * @param nums
     * @return
     */
    public int minOperationsOther(int[] nums) {
        int ans = 0;
        int top = -1; // 栈顶下标（把 nums 当作栈）
        for (int x : nums) {
            while (top >= 0 && x < nums[top]) {
                top--; // 出栈
                ans++;
            }
            // 如果 x 与栈顶相同，那么 x 与栈顶可以在同一次操作中都变成 0，x 无需入栈
            if (top < 0 || x != nums[top]) {
                nums[++top] = x; // 入栈
            }
        }
        return ans + top + (nums[0] > 0 ? 1 : 0);
    }

    /**
     * 解答成功:
     * 	执行耗时:36 ms,击败了51.65% 的Java用户
     * 	内存消耗:120.5 MB,击败了6.59% 的Java用户
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        List<Integer> s = new ArrayList<>();
        int res = 0;
        for (int a : nums) {
            while (!s.isEmpty() && s.get(s.size() - 1) > a) {
                s.remove(s.size() - 1);
            }
            if (a == 0) continue;
            if (s.isEmpty() || s.get(s.size() - 1) < a) {
                res++;
                s.add(a);
            }
        }
        return res;
    }


}
