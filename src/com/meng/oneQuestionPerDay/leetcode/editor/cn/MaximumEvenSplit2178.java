package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class MaximumEvenSplit2178 {
    /**
     * 执行用时：
     * 13 ms
     * , 在所有 Java 提交中击败了
     * 18.37%
     * 的用户
     * 内存消耗：
     * 53.9 MB
     * , 在所有 Java 提交中击败了
     * 71.43%
     * 的用户
     * 通过测试用例：
     * 56 / 56
     * @param finalSum
     * @return
     */
    public List<Long> maximumEvenSplit(long finalSum) {
        // 1. 如果是奇数，直接返回空集合
        if (finalSum % 2 == 1) {
            return new ArrayList<>();
        }
        //尽可能选择较小的偶数，若存在后一个值不满足的请求，则和前一个数据进行结合
        List<Long> result = new ArrayList<>();
        long sum = 2L;
        while (finalSum >= sum) {
            result.add(sum);
            finalSum -= sum;
            sum += 2;
        }
        if (finalSum != 0){
            int size = result.size()-1;
            result.set(size,result.get(size)+finalSum);
        }
        return result;
    }

    /**
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 38.78%
     * 的用户
     * 内存消耗：
     * 54 MB
     * , 在所有 Java 提交中击败了
     * 67.35%
     * 的用户
     * 通过测试用例：
     * 56 / 56
     * @param finalSum
     * @return
     */
    public List<Long> maximumEvenSplit1(long finalSum) {
        List<Long> res = new ArrayList<>();
        if (finalSum % 2 > 0) {
            return res;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            res.add(i);
            finalSum -= i;
        }
        res.set(res.size() - 1, res.get(res.size() - 1) + finalSum);
        return res;
    }

}

