package com.meng.top100.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:80 ms,击败了11.60% 的Java用户
 * 	内存消耗:89.9 MB,击败了73.30% 的Java用户
 */
class RandomizedSet380 {
    List<String> list;
    Set<Integer> set;
    Random random;
    public RandomizedSet380() {
        list =  new ArrayList<>();
        set = new HashSet<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (!set.add(val)){
            return false;
        }
        list.add(String.valueOf(val));
        return true;
    }
    
    public boolean remove(int val) {
        if (!set.remove(val)){
            return false;
        }
        list.remove(String.valueOf(val));
        return true;
    }
    
    public int getRandom() {
        return Integer.parseInt(list.get(random.nextInt(list.size())));
    }
}

/**
 * 解答成功:
 * 	执行耗时:23 ms,击败了100.00% 的Java用户
 * 	内存消耗:88.2 MB,击败了90.20% 的Java用户
 */
class RandomizedSet {
    static int[] nums = new int[200010];
    Random random = new Random();
    Map<Integer, Integer> map = new HashMap<>();
    int idx = -1;
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        nums[++idx] = val;
        map.put(val, idx);
        return true;
    }
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int loc = map.remove(val);
        if (loc != idx) map.put(nums[idx], loc);
        nums[loc] = nums[idx--];
        return true;
    }
    public int getRandom() {
        return nums[random.nextInt(idx + 1)];
    }
}

