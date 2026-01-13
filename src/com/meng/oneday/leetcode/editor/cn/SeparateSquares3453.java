package com.meng.oneday.leetcode.editor.cn;

import java.util.Map;
import java.util.TreeMap;

class SeparateSquares3453 {
    /**
     * 解答成功:
     * 	执行耗时:134 ms,击败了66.67% 的Java用户
     * 	内存消耗:145.8 MB,击败了16.66% 的Java用户
     * @param squares
     * @return
     */
    public double separateSquares3453(int[][] squares) {
        //计算总面积
        long sumArea = 0L;
        double l = 0 , r = 0;
        for (int[] square : squares) {
            sumArea += (long) square[2] * square[2];
            r = Math.max(r,square[1] + square[2]);
        }
        while (r - l > 0.00001){
            double mid = (l + r) / 2;
            if (check3453(squares,mid,sumArea)){
                r = mid;
            }else{
                l = mid;
            }
        }
        return r;
    }

    private boolean check3453(int[][] squares, double mid, long sumArea) {
        double area = 0 ;
        for (int[] square : squares) {
            if (square[1] < mid){
                area += Math.min(mid - square[1],square[2]) * square[2];
            }
        }
        return area * 2 >= sumArea;
    }

    /**
     * 解答成功:
     * 	执行耗时:163 ms,击败了56.06% 的Java用户
     * 	内存消耗:145.5 MB,击败了34.85% 的Java用户
     * @param squares
     * @return
     */
    public double separateSquaresOther1(int[][] squares) {
        long totArea = 0;
        int maxY = 0;
        for (int[] sq : squares) {
            int l = sq[2];
            totArea += (long) l * l;
            maxY = Math.max(maxY, sq[1] + l);
        }

        double left = 0;
        double right = maxY;
        for (int i = 0; i < 47; i++) {
            double mid = (left + right) / 2;
            if (checkOther1(squares, mid, totArea)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (left + right) / 2; // 区间中点误差小
    }

    private boolean checkOther1(int[][] squares, double y, long totArea) {
        double area = 0;
        for (int[] sq : squares) {
            double yi = sq[1];
            if (yi < y) {
                double l = sq[2];
                area += l * Math.min(y - yi, l);
            }
        }
        return area >= totArea / 2.0;
    }

    private static final int M = 100_000;

    /**
     * 解答成功:
     * 	执行耗时:84 ms,击败了96.21% 的Java用户
     * 	内存消耗:141.8 MB,击败了50.00% 的Java用户
     * @param squares
     * @return
     */
    public double separateSquaresOther2(int[][] squares) {
        long totArea = 0;
        int maxY = 0;
        for (int[] sq : squares) {
            int l = sq[2];
            totArea += (long) l * l;
            maxY = Math.max(maxY, sq[1] + l);
        }

        long left = 0;
        long right = (long) maxY * M;
        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (checkOther2(squares, mid, totArea)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (double) right / M;
    }

    private boolean checkOther2(int[][] squares, long multiY, double totArea) {
        long area = 0;
        for (int[] sq : squares) {
            long y = sq[1];
            if (y * M < multiY) {
                long l = sq[2];
                area += l * Math.min(multiY - y * M, l * M);
            }
        }
        return area * 2 >= totArea * M;
    }

    /**
     * 解答成功:
     * 	执行耗时:172 ms,击败了32.57% 的Java用户
     * 	内存消耗:139.5 MB,击败了61.36% 的Java用户
     * @param squares
     * @return
     */
    public double separateSquares(int[][] squares) {
        long totArea = 0;
        TreeMap<Integer, Long> diff = new TreeMap<>();
        for (int[] sq : squares) {
            int y = sq[1];
            long l = sq[2];
            totArea += l * l;
            diff.merge(y, l, Long::sum);
            diff.merge(y + (int) l, -l, Long::sum);
        }

        long area = 0;
        long sumL = 0;
        int preY = 0; // 不好计算下一个 y，改成维护上一个 y
        for (Map.Entry<Integer,Long> e : diff.entrySet()) {
            int y = e.getKey();
            area += sumL * (y - preY); // 底边长 * 高 = 新增面积
            if (area * 2 >= totArea) {
                return y - (area * 2 - totArea) / (sumL * 2.0);
            }
            preY = y;
            sumL += e.getValue(); // 矩形底边长度之和
        }
        return -1;
    }
}
