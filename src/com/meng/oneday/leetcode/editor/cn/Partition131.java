package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;


class Partition131 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了95.04% 的Java用户
     * 	内存消耗:55.9 MB,击败了38.31% 的Java用户
     */
    List<List<String>> result ;
    List<String> temp ;

    public List<List<String>> partition131(String s) {
        result = new ArrayList<>();
        temp = new ArrayList<>();
        dfs(s,0);
        return result;
    }

    private void dfs(String s, int startIndex) {
        //终止条件
        if(startIndex == s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex ; i < s.length() ; i++){
            String sub = s.substring(startIndex,i+1);
            if (isPalindrome(sub)){
                temp.add(sub);
                dfs(s,i+1);
                temp.remove(temp.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int start = 0,end = s.length()-1;
        while(start < end){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    private final List<List<String>> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;

    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了42.81% 的Java用户
     * 	内存消耗:56 MB,击败了34.02% 的Java用户
     * @param s
     * @return
     */
    public List<List<String>> partitionOther(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        for (int j = i; j < s.length(); j++) { // 枚举子串的结束位置
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了95.04% 的Java用户
     * 	内存消耗:56 MB,击败了28.96% 的Java用户
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    // start 表示当前这段回文子串的开始位置
    private void dfs(int i, int start) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }

        // 不选 i 和 i+1 之间的逗号（i=n-1 时一定要选）
        if (i < s.length() - 1) {
            dfs(i + 1, start);
        }

        // 选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1); // 下一个子串从 i+1 开始
            path.remove(path.size() - 1); // 恢复现场
        }
    }


}
