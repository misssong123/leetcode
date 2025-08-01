package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Generate118 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.78% 的Java用户
     * 	内存消耗:41.4 MB,击败了14.79% 的Java用户
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate118(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Collections.singletonList(1));
        if (numRows >=2){
            for(int i = 2 ; i <= numRows ; i++){
                int pre = i - 2;
                List<Integer> list = new ArrayList<>();
                for(int j = 0 ; j < i ; j++){
                    if (j == 0 || j == i -1){
                        list.add(1);
                    }else{
                        list.add(res.get(pre).get(j-1) + res.get(pre).get(j));
                    }
                }
                res.add(list);
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了97.78% 的Java用户
     * 	内存消耗:41.5 MB,击败了6.32% 的Java用户
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> c = new ArrayList<>(numRows); // 预分配空间
        c.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1); // 预分配空间
            row.add(1);
            for (int j = 1; j < i; j++) {
                // 左上方的数 + 正上方的数
                row.add(c.get(i - 1).get(j - 1) + c.get(i - 1).get(j));
            }
            row.add(1);
            c.add(row);
        }
        return c;
    }

}
