package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumNumberOfStringPairs2744 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了24.26% 的Java用户
     * 	内存消耗:42.1 MB,击败了38.61% 的Java用户
     * @param words
     * @return
     */
    public int maximumNumberOfStringPairsMy(String[] words) {
        int res = 0;
        Map<String,Integer> cache = new HashMap<>();
        for(String word : words){
            String str = reversalStr(word);
            if (cache.getOrDefault(str,0)>0){
                cache.put(str,cache.get(str)-1);
                res++;
            }else {
                cache.put(word,cache.getOrDefault(word,0)+1);
            }
        }
        return res;
    }
    public String reversalStr(String s){
        StringBuffer sb = new StringBuffer(s.length());
        for (int i = s.length() - 1 ; i >=0 ; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了78.31% 的Java用户
     * 	内存消耗:41.9 MB,击败了50.00% 的Java用户
     * @param words
     * @return
     */
    public int maximumNumberOfStringPairs1(String[] words) {
        int n = words.length;
        int ans = 0;
        Set<Integer> seen = new HashSet<Integer>();
        for (int i = 0; i < n; ++i) {
            if (seen.contains(words[i].charAt(1) * 100 + words[i].charAt(0))) {
                ++ans;
            }
            seen.add(words[i].charAt(0) * 100 + words[i].charAt(1));
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.4 MB,击败了70.59% 的Java用户
     * @param words
     * @return
     */
    public int maximumNumberOfStringPairs(String[] words) {
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (words[i].charAt(0) == words[j].charAt(1) && words[i].charAt(1) == words[j].charAt(0)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
