package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class CamelMatch1023 {
    /**
     * 思路错误
     * @param queries
     * @param pattern
     * @return
     */
    public List<Boolean> camelMatchError(String[] queries, String pattern) {
        List<String> patternList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        //调整pattern
        for (char c : pattern.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                if (!sb.isEmpty()){
                    patternList.add(sb.toString());
                    sb.delete(0,sb.length());
                }
            }
            sb.append(c);
        }
        if (!sb.isEmpty()){
            patternList.add(sb.toString());
            sb.delete(0,sb.length());
        }
        //匹配
        List<Boolean> res = new ArrayList<>(queries.length);
        for (String query : queries) {
            int index = 0;
            boolean flag = true;
            sb.delete(0,sb.length());
            for (char c : query.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    if (!sb.isEmpty()){
                        String str = sb.toString();
                        if (index >= patternList.size() || !contains(str,patternList.get(index))){
                            flag = false;
                            break;
                        }
                        index++;
                        sb.delete(0,sb.length());
                    }
                }
                sb.append(c);
            }
            if (!sb.isEmpty()){
                String str = sb.toString();
                if (index >= patternList.size() || !contains(str,patternList.get(index))){
                    flag = false;
                }
                index++;
            }
            res.add(flag && index == patternList.size());
        }
        return res;
    }

    private boolean contains(String str, String s) {
        char c = str.charAt(0);
        char c1 = s.charAt(0);
        boolean cFlag =  c >= 'A' && c <= 'Z';
        boolean c1Flag =  c1 >= 'A' && c1 <= 'Z';
        if (cFlag != c1Flag || (cFlag && str.charAt(0) != s.charAt(0))){
            return false;
        }
        int index = cFlag ? 1 : 0;
        for (int i = index; i < str.length() && index < s.length(); i++) {
            if (str.charAt(i)== s.charAt(index)){
                index++;
            }
        }
        return index == s.length();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了42.98% 的Java用户
     * 	内存消耗:42.2 MB,击败了79.34% 的Java用户
     * @param queries
     * @param pattern
     * @return
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;
        List<Boolean> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(true);
        }
        for (int i = 0; i < n; i++) {
            String query = queries[i];
            int j = 0;
            for (int k = 0; k < query.length(); k++) {
                char q = query.charAt(k);
                // 两个置为 false 的条件：
                // 1、已经匹配到全部字符了但仍然有大写字母
                // 2、还没有匹配完全部字符但是有不属于 pattern 的大写字母
                if ((j == pattern.length() && Character.isUpperCase(q))
                        || (j < pattern.length() && Character.isUpperCase(q)
                        && q != pattern.charAt(j))) {
                    ans.set(i, false);
                    break;
                }
                // 如果匹配到了则移动指针
                if (j < pattern.length() && q == pattern.charAt(j)) {
                    j++;
                }
            }
            // 循环完没有匹配完的也需要置为 false
            if (j < pattern.length()) {
                ans.set(i, false);
            }
        }
        return ans;
    }
}
