package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class ConvertTemperature2469 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 36.14%
     * 的用户
     * 通过测试用例：
     * 74 / 74
     * @param celsius
     * @return
     */
    public double[] convertTemperature(double celsius) {
        double[] res = new double[2];
        res[0] = celsius + 273.15;
        res[1] = celsius * 1.80 + 32.00;
        return res;
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 82.58%
     * 的用户
     * 通过测试用例：
     * 74 / 74
     * @param celsius
     * @return
     */
    public double[] convertTemperature1(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }


}

