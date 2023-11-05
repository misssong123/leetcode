package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class FindRepeatedDnaSequences187 {
    /**
     * 解答成功:
     * 	执行耗时:21 ms,击败了60.50% 的Java用户
     * 	内存消耗:50.7 MB,击败了33.14% 的Java用户
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequencesMy(String s) {
        int len  = s.length();
        List<String> res = new ArrayList<>();
        if (len > 10){
            Map<String,Integer> cache = new HashMap<>();
            for (int i = 0 ; i <= len -10 ; i++){
                String temp = s.substring(i,i+10);
                cache.put(temp,cache.getOrDefault(temp,0)+1);
            }
            for (Map.Entry<String,Integer> entry : cache.entrySet()){
                if (entry.getValue()>1){
                    res.add(entry.getKey());
                }
            }
        }
        return res;
    }
    static final int L = 10;
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了17.73% 的Java用户
     * 	内存消耗:48 MB,击败了94.00% 的Java用户
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n - L; ++i) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
