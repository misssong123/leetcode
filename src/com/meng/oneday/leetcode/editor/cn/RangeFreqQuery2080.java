package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:261 ms,击败了5.12% 的Java用户
 * 	内存消耗:166.9 MB,击败了6.04% 的Java用户
 */
class RangeFreqQuery2080 {
    Map<Integer, TreeMap<Integer,Integer>> map = new HashMap<>();
    public RangeFreqQuery2080(int[] arr) {
       for(int i = 0 ; i < arr.length; i++){
           if (!map.containsKey(arr[i])){
               map.put(arr[i], new TreeMap<>());
           }
           map.get(arr[i]).put(i,map.get(arr[i]).size());
       }
    }
    
    public int query(int left, int right, int value) {
        if (map.containsKey(value)){
            TreeMap<Integer,Integer> set = map.get(value);
            Integer l = set.ceilingKey(left);
            if (l == null || l > right){
                return 0;
            }
            Integer r = set.floorKey(right);
            return set.get(r) - set.get(l) + 1;
        }
        return 0;
    }

}

/**
 * 解答成功:
 * 	执行耗时:139 ms,击败了49.77% 的Java用户
 * 	内存消耗:129.4 MB,击败了58.84% 的Java用户
 */
class RangeFreqQuery {
    private final Map<Integer, List<Integer>> pos = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            pos.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> a = pos.get(value);
        if (a == null) {
            return 0;
        }
        // > right 等价于 >= right+1
        return lowerBound(a, right + 1) - lowerBound(a, left);
    }

    // 开区间写法
    // 请看 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(List<Integer> a, int target) {
        // 开区间 (left, right)
        int left = -1;
        int right = a.size();
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // a[left] < target
            // a[right] >= target
            int mid = (left + right) >>> 1;
            if (a.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // right 是最小的满足 a[right] >= target 的下标
    }
}

