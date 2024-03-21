package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:63 ms,击败了23.81% 的Java用户
 * 	内存消耗:113 MB,击败了26.19% 的Java用户
 */
class FrequencyTracker2671 {
    int[] nums = null;
    Map<Integer, List<String>> freqMap = null;
    public FrequencyTracker2671() {
        nums = new int[100000];
        freqMap = new HashMap<>(1024);
    }
    
    public void add(int number) {
        int preFreq = nums[number-1];
        nums[number-1]++;
        if(freqMap.containsKey(preFreq)){
            freqMap.get(preFreq).remove(String.valueOf(number-1));
            if(freqMap.get(preFreq).size() == 0) {
                freqMap.remove(preFreq);
            }
        }
        freqMap.putIfAbsent(preFreq+1, new ArrayList<>());
        freqMap.get(preFreq+1).add(String.valueOf(number-1));
    }
    
    public void deleteOne(int number) {
        if (nums[number-1]<=0){
            return;
        }
        int preFreq = nums[number-1];
        if(freqMap.containsKey(preFreq)){
            freqMap.get(preFreq).remove(String.valueOf(number-1));
            if(freqMap.get(preFreq).size() == 0) {
                freqMap.remove(preFreq);
            }
        }
        nums[number-1]--;
        if (preFreq - 1 >=0){
            freqMap.putIfAbsent(preFreq-1, new ArrayList<>());
            freqMap.get(preFreq-1).add(String.valueOf(number-1));
        }
    }
    
    public boolean hasFrequency(int frequency) {
        return  freqMap.containsKey(frequency);
    }
}

/**
 * 解答成功:
 * 	执行耗时:58 ms,击败了30.95% 的Java用户
 * 	内存消耗:101.8 MB,击败了83.33% 的Java用户
 */
class FrequencyTracker {
    private static final int N = 100001;
    private int[] freq;
    private int[] freqCnt;

    public FrequencyTracker() {
        freq = new int[N];
        freqCnt = new int[N];
    }

    public void add(int number) {
        --freqCnt[freq[number]];
        ++freq[number];
        ++freqCnt[freq[number]];
    }

    public void deleteOne(int number) {
        if (freq[number] == 0) {
            return;
        }
        --freqCnt[freq[number]];
        --freq[number];
        ++freqCnt[freq[number]];
    }

    public boolean hasFrequency(int frequency) {
        return freqCnt[frequency] > 0;
    }
}

