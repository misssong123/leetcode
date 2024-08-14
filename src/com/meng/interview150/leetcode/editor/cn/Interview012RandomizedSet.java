package com.meng.interview150.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Set+List
 * 解答成功:
 * 	执行耗时:76 ms,击败了15.50% 的Java用户
 * 	内存消耗:90.8 MB,击败了52.75% 的Java用户
 */
class Interview012RandomizedSet {
    Set<Integer> set;
    List<String> list;
    Random random;
    public Interview012RandomizedSet() {
        set = new HashSet<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (set.add(val)){
            list.add(String.valueOf(val));
            return true;
        }
        return false;
    }
    
    public boolean remove(int val) {
        if (set.contains(val)){
            set.remove(val);
            list.remove(String.valueOf(val));
            return true;
        }
        return false;
    }
    
    public int getRandom() {
        int index = random.nextInt(list.size());
        return Integer.parseInt(list.get(index));
    }
}

/**
 * 解答成功:
 * 	执行耗时:25 ms,击败了35.93% 的Java用户
 * 	内存消耗:91.1 MB,击败了46.70% 的Java用户
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
