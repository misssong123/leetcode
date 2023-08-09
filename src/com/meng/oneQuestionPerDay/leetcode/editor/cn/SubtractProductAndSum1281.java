package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SubtractProductAndSum1281 {
    /**
     * 时间
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 37.42mb
     * 击败 46.05%使用 Java 的用户
     * @param n
     * @return
     */
    public int subtractProductAndSum(int n) {
        List<Integer> cache = new ArrayList<>();
        while (n > 0){
            cache.add(n%10);
            n = n / 10;
        }
        int s = 0 , m = 1;
        for(int num : cache){
            s += num;
            m *= num;
        }
        return m - s;
    }

    /**
     * 时间
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 37.19mb
     * 击败 85.94%使用 Java 的用户
     * @param n
     * @return
     */
    public int subtractProductAndSum1(int n) {
        int m = 1, s = 0;
        while (n != 0) {
            int x = n % 10;
            n /= 10;
            m *= x;
            s += x;
        }
        return m - s;
    }
}

