package com.meng.oneday.leetcode.editor.cn;

class AngleClock1344 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.4 MB,击败了35.71% 的Java用户
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock1344(int hour, int minutes) {
        //计算时针数字
        double h = (hour % 12) * 5 + minutes * 5.0 / 60 ;
        //计算角度差值
        double diff = Math.abs(h - minutes);
        if (diff > 30){
            diff = 60 - diff;
        }
        return diff * 6;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44.3 MB,击败了53.57% 的Java用户
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock(int hour, int minutes) {
        double d = Math.abs(hour * 30 - minutes * 5.5);
        return Math.min(d, 360 - d);
    }


}
