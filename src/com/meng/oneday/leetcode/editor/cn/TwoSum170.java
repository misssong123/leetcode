package com.meng.oneday.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 解答成功:
 * 	执行耗时:94 ms,击败了55.40% 的Java用户
 * 	内存消耗:51.3 MB,击败了58.11% 的Java用户
 */
class TwoSum170 {
    Map<Integer,Integer> cache;
    public TwoSum170() {
        cache = new HashMap<>();
    }
    
    public void add(int number) {
        cache.put(number,cache.getOrDefault(number,0)+1);
    }
    public boolean find(int value) {
        for(Map.Entry<Integer,Integer> entry:cache.entrySet()){
            int key = entry.getKey();
            int target = value-key;
            if (target == key){
                if (entry.getValue() > 1){
                    return true;
                }
            }else if (cache.containsKey(target)){
                return true;
            }
        }
        return false;
    }
}

/**
 * 解答成功:
 * 	执行耗时:97 ms,击败了48.65% 的Java用户
 * 	内存消耗:51.4 MB,击败了41.89% 的Java用户
 */
class TwoSumOfficial {
    private HashMap<Integer, Integer> num_counts;

    /** 在这里初始化你的数据结构。 */
    public TwoSumOfficial() {
        this.num_counts = new HashMap<Integer, Integer>();
    }

    /** 将数字添加到内部数据结构中。 */
    public void add(int number) {
        if (this.num_counts.containsKey(number))
            this.num_counts.replace(number, this.num_counts.get(number) + 1);
        else
            this.num_counts.put(number, 1);
    }

    /** 查看是否存在任何一对数字，其总和等于该值。 */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : this.num_counts.entrySet()) {
            int complement = value - entry.getKey();
            if (complement != entry.getKey()) {
                if (this.num_counts.containsKey(complement))
                    return true;
            } else {
                if (entry.getValue() > 1)
                    return true;
            }
        }
        return false;
    }
}
