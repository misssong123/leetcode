package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class ReconstructMatrix1253 {
    /**
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 30.43%
     * 的用户
     * 内存消耗：
     * 59.6 MB
     * , 在所有 Java 提交中击败了
     * 46.74%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     * @param upper
     * @param lower
     * @param colsum
     * @return
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int size = colsum.length;
        int sum = Arrays.stream(colsum).sum();
        List<List<Integer>> res = new ArrayList<>();
        if (sum == upper + lower){
            List<Integer> p0 = new ArrayList<>();
            List<Integer> p1 = new ArrayList<>();
            for (int i = 0 ; i < size ; i++){
                //等于2的时候，上下都要填充1
                if (colsum[i] == 2){
                    p0.add(1);
                    p1.add(1);
                    upper--;
                    lower--;
                }else if (colsum[i] ==0){//等于0的时候，上下都填充0
                    p0.add(0);
                    p1.add(0);
                }else {//等于1的时候，优先将upper和lower中大的数据填充1
                    if (upper > lower){
                        p0.add(1);
                        p1.add(0);
                        upper--;
                    }else {
                        p0.add(0);
                        p1.add(1);
                        lower--;
                    }
                }
                if (upper < 0 || lower < 0){
                    return Collections.emptyList();
                }
            }
            res.add(p0);
            res.add(p1);
        }
        return res;
    }

    /**
     * 方法一：贪心
     * @param upper
     * @param lower
     * @param colsum
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 66.30%
     * 的用户
     * 内存消耗：
     * 59.4 MB
     * , 在所有 Java 提交中击败了
     * 52.17%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     */
    public List<List<Integer>> reconstructMatrix2(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        int sum = 0, two = 0;
        for (int i = 0; i < n; ++i) {
            if (colsum[i] == 2) {
                ++two;
            }
            sum += colsum[i];
        }
        if (sum != upper + lower || Math.min(upper, lower) < two) {
            return new ArrayList<List<Integer>>();
        }
        upper -= two;
        lower -= two;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < 2; ++i) {
            res.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; ++i) {
            if (colsum[i] == 2) {
                res.get(0).add(1);
                res.get(1).add(1);
            } else if (colsum[i] == 1) {
                if (upper > 0) {
                    res.get(0).add(1);
                    res.get(1).add(0);
                    --upper;
                } else {
                    res.get(0).add(0);
                    res.get(1).add(1);
                }
            } else {
                res.get(0).add(0);
                res.get(1).add(0);
            }
        }
        return res;
    }

}
