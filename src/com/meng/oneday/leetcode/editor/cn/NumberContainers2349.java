package com.meng.oneday.leetcode.editor.cn;

import java.util.*;
/**
 * 解答成功:
 * 	执行耗时:79 ms,击败了82.47% 的Java用户
 * 	内存消耗:99.2 MB,击败了49.49% 的Java用户
 */
class NumberContainers2349 {
    Map<Integer,Integer> position ;
    Map<Integer, PriorityQueue<Integer>> val ;
    public NumberContainers2349() {
        position = new HashMap<>();
        val = new HashMap<>();
    }
    
    public void change(int index, int number) {
        if (position.containsKey(index)){
            Integer num = position.get(index);
            if (num != number){
                val.get(num).remove(index);
                position.put(index,number);
                if (!val.containsKey(number)){
                    val.put(number,new PriorityQueue<>(Integer::compareTo));
                }
                val.get(number).add(index);
            }

        }else {
            position.put(index,number);
            if (!val.containsKey(number)){
                val.put(number,new PriorityQueue<>(Integer::compareTo));
            }
            val.get(number).add(index);
        }
    }

    public int find(int number) {
        if (val.containsKey(number)){
            return val.get(number).isEmpty() ?  -1 : val.get(number).peek();
        }
        return -1;
    }
}

/**
 * 解答成功:
 * 	执行耗时:142 ms,击败了13.40% 的Java用户
 * 	内存消耗:98.3 MB,击败了58.76% 的Java用户
 */
class NumberContainersOther {
    private final Map<Integer, Integer> indexToNumber = new HashMap<>();
    private final Map<Integer, TreeSet<Integer>> numberToIndices = new HashMap<>();

    public void change(int index, int number) {
        // 移除旧数据
        Integer oldNumber = indexToNumber.get(index);
        if (oldNumber != null) {
            numberToIndices.get(oldNumber).remove(index);
        }

        // 添加新数据
        indexToNumber.put(index, number);
        numberToIndices.computeIfAbsent(number, item -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        TreeSet<Integer> indices = numberToIndices.get(number);
        return indices == null || indices.isEmpty() ? -1 : indices.first();
    }
}

/**
 * 解答成功:
 * 	执行耗时:120 ms,击败了45.36% 的Java用户
 * 	内存消耗:100.8 MB,击败了37.12% 的Java用户
 */
class NumberContainers {
    private final Map<Integer, Integer> indexToNumber = new HashMap<>();
    private final Map<Integer, PriorityQueue<Integer>> numberToIndices = new HashMap<>();

    public void change(int index, int number) {
        // 添加新数据
        indexToNumber.put(index, number);
        numberToIndices.computeIfAbsent(number, item -> new PriorityQueue<>()).offer(index);
    }

    public int find(int number) {
        PriorityQueue<Integer> indices = numberToIndices.get(number);
        if (indices == null) {
            return -1;
        }
        while (!indices.isEmpty() && indexToNumber.get(indices.peek()) != number) {
            indices.poll(); // 堆顶货不对板，说明是旧数据，删除
        }
        return indices.isEmpty() ? -1 : indices.peek();
    }
}

