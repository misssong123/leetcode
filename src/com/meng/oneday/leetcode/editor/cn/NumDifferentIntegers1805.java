package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class NumDifferentIntegers1805 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.88% 的Java用户
     * 	内存消耗:42.4 MB,击败了37.50% 的Java用户
     * @param word
     * @return
     */
    public int numDifferentIntegers1805(String word) {
        Set<String> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for(char c : word.toCharArray()){
            if(c >= '0' && c <= '9'){
                sb.append(c);
            }else{
                if (sb.length() > 0){
                    set.add(removeZero(sb));
                    sb = new StringBuffer();
                }
            }
        }
        if (sb.length() > 0){
            set.add(removeZero(sb));
        }
        return set.size();
    }
    private String removeZero(StringBuffer sb){
        //移除前导零
        while (sb.length() > 0 && sb.charAt(0) == '0'){
            sb.deleteCharAt(0);
        }
        if (sb.length() > 0){
            return sb.toString();
        }else{
            return "0";
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.88% 的Java用户
     * 	内存消耗:42.1 MB,击败了84.38% 的Java用户
     * @param word
     * @return
     */
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<String>();
        int n = word.length(), p1 = 0, p2;
        while (true) {
            while (p1 < n && !Character.isDigit(word.charAt(p1))) {
                p1++;
            }
            if (p1 == n) {
                break;
            }
            p2 = p1;
            while (p2 < n && Character.isDigit(word.charAt(p2))) {
                p2++;
            }
            while (p2 - p1 > 1 && word.charAt(p1) == '0') { // 去除前导 0
                p1++;
            }
            set.add(word.substring(p1, p2));
            p1 = p2;
        }
        return set.size();
    }

}
