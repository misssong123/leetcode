package com.meng.thinking.leetcode.editor.cn;

import java.util.*;

class FindLUSLength522 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了28.82% 的Java用户
     * 	内存消耗:40.3 MB,击败了17.06% 的Java用户
     * @param strs
     * @return
     */
    public int findLUSlengthMy(String[] strs) {
        Map<String,Integer> cache = new HashMap<>();
        Map<Integer, List<String>> num = new HashMap<>();
        int[] index = new int[51];
        Set<String> ignore = new HashSet<>();
        for(String str :strs){
            cache.put(str,cache.getOrDefault(str,0)+1);
            if (!num.containsKey(str.length())){
                num.put(str.length(),new ArrayList<>());
            }
            num.get(str.length()).add(str);
            index[str.length()]++;
        }
        for(int i = 50 ; i >= 0 ; i--){
            if (index[i]>=1){
                for (String str : num.get(i)){
                    if(cache.get(str)==1){
                        if (ignore.size()<=0){
                            return str.length();
                        }
                        boolean flag = true;
                        for (String s : ignore){
                            if (contains(s,str)){
                                flag = false;
                                break;
                            }
                        }
                        if (flag){
                            return str.length();
                        }
                    }else {
                        ignore.add(str);
                    }
                }
            }
        }
        return -1;
    }
    private boolean contains(String str,String s){
        int index1 = 0,index2 = 0;
        while (index1<str.length() && index2<s.length()){
            if (str.charAt(index1)==s.charAt(index2)){
                index2++;
            }
            index1++;
        }
        return index2 == s.length();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了95.88% 的Java用户
     * 	内存消耗:40.1 MB,击败了67.06% 的Java用户
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            boolean check = true;
            for (int j = 0; j < n; ++j) {
                if (i != j && strs[i].length()<=strs[j].length() && isSubseq(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }


}
//leetcode submit region end(Prohibit modification and deletion)
