package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:223 ms,击败了5.43% 的Java用户
 * 	内存消耗:90.5 MB,击败了51.16% 的Java用户
 */
class RandomizedSetLCR030 {
    Set<Integer> cache;
    Random random ;
    public RandomizedSetLCR030() {
        cache = new HashSet<>();
        random = new Random();
    }
    public boolean insert(int val) {
        return cache.add(val);
    }
    
    public boolean remove(int val) {
        return cache.remove(val);
    }
    
    public int getRandom() {
        int size = cache.size();
        int randomNum = random.nextInt(size);
        return cache.stream().skip(randomNum).findFirst().get();
    }
}

/**
 * 解答成功:
 * 	执行耗时:24 ms,击败了59.69% 的Java用户
 * 	内存消耗:91.5 MB,击败了13.18% 的Java用户
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


