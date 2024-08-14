package com.meng.interview150.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview017RomanToInt {
    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了5.10% 的Java用户
     * 	内存消耗:44.3 MB,击败了7.02% 的Java用户
     * @param s
     * @return
     */
    public int romanToIntMy(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);
        int ans = 0;
        for(int i = 0;i<s.length();i++){
            if (i+1<s.length() && map.containsKey(s.substring(i,i+2))){
                ans+= map.get(s.substring(i,i+2));
                i++;
            }else {
                ans+= map.get(s.substring(i,i+1));
            }
        }
        return ans;
    }
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了59.97% 的Java用户
     * 	内存消耗:43.9 MB,击败了20.18% 的Java用户
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
