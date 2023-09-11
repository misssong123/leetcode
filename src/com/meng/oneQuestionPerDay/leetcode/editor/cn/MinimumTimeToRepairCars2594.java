package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class SolutionRepairCars2594 {
    //无思路
    public long repairCars1(int[] ranks, int cars) {
        return -1L;
    }

    /**
     * 时间
     * 详情
     * 77ms
     * 击败 30.04%使用 Java 的用户
     * 内存
     * 详情
     * 51.67MB
     * 击败 32.75%使用 Java 的用户
     * @param ranks
     * @param cars
     * @return
     */
    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = 1l * ranks[0] * cars * cars;
        while (l < r) {
            long m = l + r >> 1;
            if (check(ranks, cars, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public boolean check(int[] ranks, int cars, long m) {
        long cnt = 0;
        for (int x : ranks) {
            cnt += (long) Math.sqrt(m / x);
        }
        return cnt >= cars;
    }

}

