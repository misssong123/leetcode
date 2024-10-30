package com.meng.hot100.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class T40Partition {
    List<List<String>> res = null;
    List<String> temp = null;
    Map<String,Boolean> map = null;

    /**
     * 解答成功:
     * 	执行耗时:12 ms,击败了24.98% 的Java用户
     * 	内存消耗:55.7 MB,击败了50.45% 的Java用户
     * @param s
     * @return
     */
    public List<List<String>> partitionMy(String s) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        map = new HashMap<>();
        partition(s,0);
        return res;
    }
    private void partition(String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        //可能的长度
        for(int i = 1 ; i <= s.length() - start ; i++){
            String str = s.substring(start,start+i);
            if(isPalindrome(str)){
                temp.add(str);
                partition(s,start+i);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String subStr){
        if (subStr.length() == 1){
            return true;
        }
        if (map.containsKey(subStr)){
            return map.get(subStr);
        }
        int left = 0 ,right = subStr.length() - 1;
        while (left < right && subStr.charAt(left) == subStr.charAt(right)){
            left++;
            right--;
        }
        map.put(subStr,left >= right);
        return left >= right;
    }

    int[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了95.49% 的Java用户
     * 	内存消耗:55.8 MB,击败了42.11% 的Java用户
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        n = s.length();
        f = new int[n][n];
        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (isPalindrome(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    public int isPalindrome(String s, int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        if (i >= j) {
            f[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            f[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            f[i][j] = -1;
        }
        return f[i][j];
    }
}
