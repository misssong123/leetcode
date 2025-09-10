package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class IsAdditiveNumber306 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.5 MB,击败了90.30% 的Java用户
     * @param num
     * @return
     */
    public boolean isAdditiveNumber306(String num) {
        int len = num.length();
        if (len < 3) {
            return false;
        }
        char[] chars = num.toCharArray();
        for(int i = 0 ; i < len / 2 ; i++){
            for(int j = i + 1 ; j <= (len - i)/2 + i ; j++){
                long first = getNum(chars,0,i);
                long second = getNum(chars,i+1,j);
                if (first == -1 || second == -1) {
                    continue;
                }
                if(isAdditiveNumber(chars,j+1,first,second)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditiveNumber(char[] chars, int start, long first, long second) {
        if(start == chars.length){
            return true;
        }
        if (chars[start] == '0' && first + second != 0) {
            return false;
        }
        long sum = 0;
        while(start < chars.length){
            sum = sum * 10 + (chars[start] - '0');
            start++;
            if (sum >= first + second) {
                break;
            }
        }
        if(sum == first + second){
            return isAdditiveNumber(chars,start,second,sum);
        }
        return false;
    }

    private long getNum(char[] chars,int start,int end){
        long res = 0;
        if (chars[start] == '0' && start != end) {
            return -1;
        }
        for(int i = start ; i <= end ; i++){
            res = res * 10 + (chars[i] - '0');
        }
        return res;
    }

    String num;
    int n;
    List<List<Integer>> list = new ArrayList<>();

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了36.36% 的Java用户
     * 	内存消耗:41.3 MB,击败了26.06% 的Java用户
     * @param _num
     * @return
     */
    public boolean isAdditiveNumber(String _num) {
        num = _num;
        n = num.length();
        return dfs(0);
    }
    boolean dfs(int u) {
        int m = list.size();
        if (u == n) return m >= 3;
        int max = num.charAt(u) == '0' ? u + 1 : n;
        List<Integer> cur = new ArrayList<>();
        for (int i = u; i < max; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1)) return true;
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
    boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> ans = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) t += a.get(i);
            if (i < b.size()) t += b.get(i);
            ans.add(t % 10);
            t /= 10;
        }
        if (t > 0) ans.add(t);
        boolean ok = c.size() == ans.size();
        for (int i = 0; i < c.size() && ok; i++) {
            if (c.get(i) != ans.get(i)) ok = false;
        }
        return ok;
    }
}
