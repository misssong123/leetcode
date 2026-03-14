package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class GetHappyString1415 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了64.20% 的Java用户
     * 	内存消耗:43.3 MB,击败了43.18% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public String getHappyString1415(int n, int k) {
        char[] chars = {'a','b','c'};
        char[] res = new char[n];
        List<String> list = new ArrayList<>();
        dfs(chars,res,0,list,k);
        return list.size()>=k?list.get(k-1):"";
    }

    private void dfs(char[] chars, char[] res, int index, List<String> list, int k) {
        if (list.size() == k){
            return;
        }
        //终止条件
        if(index == res.length){
            list.add(new String(res));
            return;
        }
        for (char aChar : chars) {
            if (index == 0 || aChar != res[index - 1]) {
                res[index] = aChar;
                dfs(chars, res, index + 1, list, k);
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.3 MB,击败了80.11% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public String getHappyString(int n, int k) {
        if (k > 3 << (n - 1)) {
            return "";
        }
        k--; // 改成从 0 开始，方便计算
        char[] ans = new char[n];
        ans[0] = (char) ('a' + (k >> (n - 1)));
        for (int i = 1; i < n; i++) {
            ans[i] = (char) ('a' + (k >> (n - 1 - i) & 1));
            if (ans[i] >= ans[i - 1]) {
                ans[i]++;
            }
        }
        return new String(ans);
    }

}
