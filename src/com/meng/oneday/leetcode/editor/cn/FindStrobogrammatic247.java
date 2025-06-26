package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindStrobogrammatic247 {
    public static char[][] paris = {{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
    public static char[] middle = {'0','1','8'};
    List<String> res = new ArrayList<>();
    char[] chars;

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:54.1 MB,击败了96.55% 的Java用户
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic247(int n) {
        if (n == 1){
            return Arrays.asList("0","1","8");
        }
        chars = new char[n];
        if (n % 2 == 0){
            dfs(n/2 - 1,n-1);
        }else {
            for(char i : middle){
                chars[n/2] = i ;
                dfs(n/2 - 1,n-1);
            }
        }
        return res;
    }

    private void dfs(int index, int len) {
        if (index < 0){
            res.add(new String(chars));
            return;
        }
        int start = index == 0 ? 1 : 0;
        for(int i = start ; i < 5 ; i++){
            chars[index] = paris[i][0];
            chars[len - index] = paris[i][1];
            dfs(index - 1,len);
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:93 ms,击败了12.64% 的Java用户
     * 	内存消耗:54.5 MB,击败了50.58% 的Java用户
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n) {
        List<String> strobogrammatics = findStrobogrammaticWithZero(n);
        int size = strobogrammatics.size();
        for (int i = size - 1; i >= 0; i--) {
            String strobogrammatic = strobogrammatics.get(i);
            if (strobogrammatic.length() > 1 && strobogrammatic.charAt(0) == '0') {
                strobogrammatics.remove(i);
            }
        }
        return strobogrammatics;
    }

    public List<String> findStrobogrammaticWithZero(int n) {
        List<String> strobogrammatics = new ArrayList<String>();
        if (n == 0) {
            strobogrammatics.add("");
        } else if (n == 1) {
            strobogrammatics.add("0");
            strobogrammatics.add("1");
            strobogrammatics.add("8");
        } else {
            List<String> prevStrobogrammatics = findStrobogrammaticWithZero(n - 2);
            for (String prevStrobogrammatic : prevStrobogrammatics) {
                strobogrammatics.add("0" + prevStrobogrammatic + "0");
                strobogrammatics.add("1" + prevStrobogrammatic + "1");
                strobogrammatics.add("6" + prevStrobogrammatic + "9");
                strobogrammatics.add("8" + prevStrobogrammatic + "8");
                strobogrammatics.add("9" + prevStrobogrammatic + "6");
            }
        }
        return strobogrammatics;
    }
}
