package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class LexicalOrder386 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了92.84% 的Java用户
     * 	内存消耗:47.2 MB,击败了80.45% 的Java用户
     */
    List<Integer> res ;
    public List<Integer> lexicalOrder386(int n) {
        res = new ArrayList<>();
        for(int i = 1 ; i < 10 && i <=n; i++){
            res.add(i);
            dfs(i*10,n);
        }
        return res;
    }
    private void dfs(int pre, int n) {
        for(int i = 0 ; i < 10 && pre+i <= n; i++){
            res.add(pre+i);
            dfs((pre+i)*10,n);
        }
    }

    /**
     *解答成功:
     * 	执行耗时:5 ms,击败了42.23% 的Java用户
     * 	内存消耗:47.2 MB,击败了95.46% 的Java用户
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }

}
