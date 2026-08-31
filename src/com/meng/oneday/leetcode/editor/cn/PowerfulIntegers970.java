package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PowerfulIntegers970 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了30.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了20.00% 的Java用户
     * @param x
     * @param y
     * @param bound
     * @return
     */
    public List<Integer> powerfulIntegers970(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        //x的倍数
        List<Integer> xList = new ArrayList<>();
        if (x == 1){
            xList.add(1);
        }else{
            int xNum = 1;
            while (xNum <= bound) {
                xList.add(xNum);
                xNum *= x;
            }
        }

        List<Integer> yList = new ArrayList<>();
        if (y == 1){
            yList.add(1);
        }else {
            int yNum = 1;
            while (yNum <= bound) {
                yList.add(yNum);
                yNum *= y;
            }
        }
        for (Integer integer : xList) {
            for (Integer value : yList) {
                int sum = integer + value;
                if (sum <= bound) {
                    set.add(sum);
                } else {
                    break;
                }
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了90.00% 的Java用户
     * @param x
     * @param y
     * @param bound
     * @return
     */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<Integer>();
        int value1 = 1;
        for (int i = 0; i < 21; i++) {
            int value2 = 1;
            for (int j = 0; j < 21; j++) {
                int value = value1 + value2;
                if (value <= bound) {
                    set.add(value);
                } else {
                    break;
                }
                value2 *= y;
            }
            if (value1 > bound) {
                break;
            }
            value1 *= x;
        }
        return new ArrayList<Integer>(set);
    }
}
