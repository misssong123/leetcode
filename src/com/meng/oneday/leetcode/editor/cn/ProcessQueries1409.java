package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class ProcessQueries1409 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了39.29% 的Java用户
     * 	内存消耗:42 MB,击败了26.79% 的Java用户
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries1409(int[] queries, int m) {
        List<Integer> list = new ArrayList<>(m);
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = list.indexOf(queries[i]);
            list.remove(res[i]);
            list.add(0,queries[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.7 MB,击败了62.50% 的Java用户
     * @param queries
     * @param m
     * @return
     */
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] pos = new int[m];
        ArrayTree tree = new ArrayTree(m+n);
        for(int i = 0 ; i < m ; i++){
            pos[i] = i+n;
            tree.update(i+n,1);
        }
        int[] ans = new int[n];
        for(int i = 0 ; i < n ; i++){
            int val = queries[i];
            //计算当前元素下标
            int index = pos[val-1];
            //计算当前值前面的数字个数
            ans[i] = tree.query(index);
            //更新树状数组
            tree.update(index,-1);
            //更新当前元素的下标
            pos[val-1] = n - i - 1;
            //更新树状数组
            tree.update(pos[val-1],1);
        }
        return ans;
    }
    class ArrayTree{
        int[] trees;
        int n;
        public ArrayTree(int n){
            this.n = n + 1;
            trees = new int[n + 1];
        }
        public void update(int i,int val){
            int index = i +1;
            while (index < n){
                trees[index] += val;
                index += (index&-index);
            }
        }
        public int query(int i){
            int res = 0;
            while (i > 0){
                res += trees[i];
                i -= (i&-i);
            }
            return res;
        }
    }
}
