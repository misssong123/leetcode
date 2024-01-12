package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class CountWords2086 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了18.13% 的Java用户
     * 	内存消耗:43.2 MB,击败了12.50% 的Java用户
     * @param words1
     * @param words2
     * @return
     */
    public int countWordsMy(String[] words1, String[] words2) {
        Map<String,Integer> cache = new HashMap<>();
        for (String str : words1){
            cache.put(str,cache.getOrDefault(str,0)+1);
        }
        for (String str : words2){
            Integer orDefault = cache.getOrDefault(str, 0);
            if (orDefault < 2){
                cache.put(str,orDefault-1);
            }
        }
        return  (int)cache.values().stream().filter(i->i==0).count();
    }

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了8.13% 的Java用户
     * 	内存消耗:43.1 MB,击败了14.38% 的Java用户
     * @param words1
     * @param words2
     * @return
     */
    public int countWords(String[] words1, String[] words2) {
        // 统计字符串出现频率
        Map<String, Integer> freq1 = new HashMap<>();
        Map<String, Integer> freq2 = new HashMap<>();
        for (String w : words1) {
            freq1.put(w, freq1.getOrDefault(w, 0) + 1);
        }
        for (String w : words2) {
            freq2.put(w, freq2.getOrDefault(w, 0) + 1);
        }

        // 遍历 words1 出现的字符并判断是否满足要求
        int res = 0;
        for (String w : freq1.keySet()) {
            if (freq1.get(w) == 1 && freq2.getOrDefault(w, 0) == 1) {
                res++;
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
