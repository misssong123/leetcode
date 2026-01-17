package com.meng.oneday.leetcode.editor.cn;

class LargestSquareArea2975 {
    /**
     * 解答成功:
     * 	执行耗时:110 ms,击败了18.52% 的Java用户
     * 	内存消耗:46.8 MB,击败了59.26% 的Java用户
     * @param bottomLeft
     * @param topRight
     * @return
     */
    public long largestSquareArea2975(int[][] bottomLeft, int[][] topRight) {
        int res = 0;
        int n = bottomLeft.length;
        for (int i = 0 ; i < n ; i++){
            for (int j = i + 1 ; j < n ; j++){
                res = Math.max(res, getArea(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j]));
            }
        }
        return (long)res * res;
    }

    private int getArea(int[] point1Start, int[] point1End, int[] point2Start, int[] point2End) {
        int x1 = point1Start[0] , y1 = point1Start[1],x2 = point1End[0], y2 = point1End[1];
        int x3 = point2Start[0] , y3 = point2Start[1],x4 = point2End[0], y4 = point2End[1];
        if(x1 >= x4 || x2 <= x3 || y1 >= y4 || y2 <= y3){
            return 0;
        }
        int x5 = Math.max(x1, x3),y5 = Math.max(y1, y3),x6 = Math.min(x2, x4),y6 = Math.min(y2, y4);
        return Math.min(x6 - x5, y6 - y5);
    }

    /**
     * 解答成功:
     * 	执行耗时:42 ms,击败了66.67% 的Java用户
     * 	内存消耗:46.8 MB,击败了59.26% 的Java用户
     * @param bottomLeft
     * @param topRight
     * @return
     */
    public long largestSquareAreaOther1(int[][] bottomLeft, int[][] topRight) {
        int maxSide = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            int[] b1 = bottomLeft[i];
            int[] t1 = topRight[i];
            for (int j = 0; j < i; j++) {
                int[] b2 = bottomLeft[j];
                int[] t2 = topRight[j];
                int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]);
                int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]);
                int side = Math.min(width, height);
                maxSide = Math.max(maxSide, side);
            }
        }
        return (long) maxSide * maxSide;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了92.59% 的Java用户
     * 	内存消耗:47.1 MB,击败了11.11% 的Java用户
     * @param bottomLeft
     * @param topRight
     * @return
     */
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int maxSide = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            int[] b1 = bottomLeft[i];
            int[] t1 = topRight[i];
            if (t1[0] - b1[0] <= maxSide || t1[1] - b1[1] <= maxSide) {
                continue; // 最优性剪枝：maxSide 不可能变大
            }
            for (int j = 0; j < i; j++) {
                int[] b2 = bottomLeft[j];
                int[] t2 = topRight[j];
                int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]); // 右上横坐标 - 左下横坐标
                int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]); // 右上纵坐标 - 左下纵坐标
                int side = Math.min(width, height);
                maxSide = Math.max(maxSide, side);
            }
        }
        return (long) maxSide * maxSide;
    }

}
