package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class PivotInteger2485 {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 66.36%
     * 的用户
     * 通过测试用例：
     * 428 / 428
     * @param n
     * @return
     */
    public int pivotInteger(int n) {
        //计算总数
        int sum = (1 + n )*n / 2;
        //计算中枢数
        int curr = 0;
        for (int i = 1 ; i <= n ; i++){
            curr += i;
            if (curr == sum - curr+i){
                return i;
            }
            if (curr > sum - curr){
                return -1;
            }
        }
        return -1;
    }

    /**
     *执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 60.25%
     * 的用户
     * 通过测试用例：
     * 428 / 428
     * @param n
     * @return
     */
    public int pivotInteger1(int n) {
        int t = (n * n + n) / 2;
        int x = (int) Math.sqrt(t);
        if (x * x == t) {
            return x;
        }
        return -1;
    }

}

