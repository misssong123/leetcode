package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

class LastRemaining62II {
    List<Integer> list = new ArrayList<>();

    /**
     * 执行用时：
     * 1072 ms
     * , 在所有 Java 提交中击败了
     * 5.76%
     * 的用户
     * 内存消耗：
     * 43.4 MB
     * , 在所有 Java 提交中击败了
     * 21.06%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        for(int i = 0 ; i < n ; i++){
            list.add(i);
        }
        int i = 0;
        while (list.size()>1){
            i = (i + m-1)%list.size();
            list.remove(i);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
       LastRemaining62II demo = new LastRemaining62II();
       System.out.println(demo.lastRemaining1(5,3));
    }

    /**
     *
     * @param n
     * @param m
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 31.91%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 36.95%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     */
    public int lastRemaining1(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 98.92%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 45.22%
     * 的用户
     * 通过测试用例：
     * 36 / 36
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; ++i) {
            f = (m + f) % i;
        }
        return f;
    }

}

