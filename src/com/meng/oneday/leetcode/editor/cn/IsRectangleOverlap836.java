package com.meng.oneday.leetcode.editor.cn;

class IsRectangleOverlap836 {
    /**
     * 思路错误
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlapError(int[] rec1, int[] rec2) {
       //四个顶点
        //左上
        if (rec1[0] > rec2[0] && rec1[0] < rec2[2]
            && rec1[3] > rec2[1] && rec1[3] < rec2[3]){
            return true;
        }
        //左下
        if (rec1[0] > rec2[0] && rec1[0] < rec2[2]
                && rec1[1] > rec2[1] && rec1[1] < rec2[3]){
            return true;
        }
        //右上
        if (rec1[2] > rec2[0] && rec1[2] < rec2[2]
                && rec1[3] > rec2[1] && rec1[3] < rec2[3]){
            return true;
        }
        //右下
        if (rec1[2] > rec2[0] && rec1[2] < rec2[2]
                && rec1[1] > rec2[1] && rec1[1] < rec2[3]){
            return true;
        }
        //判断是否在内部
        if (rec1[0] >= rec2[0] && rec1[2] <= rec2[2] && rec1[1] >= rec2[1] && rec1[3] <= rec2[3]){
            return true;
        }
        return rec1[0] <= rec2[0] && rec1[2] >= rec2[2] && rec1[1] <= rec2[1] && rec1[3] >= rec2[3];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.2 MB,击败了30.00% 的Java用户
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlapAi1(int[] rec1, int[] rec2) {
        // rec1 在 rec2 左侧 OR 右侧 OR 下方 OR 上方
        // rec1 在 rec2 上边
        return rec1[2] > rec2[0] && // rec1 在 rec2 左边
                rec1[0] < rec2[2] && // rec1 在 rec2 右边
                rec1[3] > rec2[1] && // rec1 在 rec2 下边
                rec1[1] < rec2[3];
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了51.43% 的Java用户
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlapAi2(int[] rec1, int[] rec2) {
        boolean xOverlap = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2], rec2[2]);
        boolean yOverlap = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3], rec2[3]);
        return xOverlap && yOverlap;
    }
}
