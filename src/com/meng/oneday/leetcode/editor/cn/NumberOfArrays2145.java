package com.meng.oneday.leetcode.editor.cn;

class NumberOfArrays2145 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了28.57% 的Java用户
     * 	内存消耗:60.3 MB,击败了51.43% 的Java用户
     */
    public int numberOfArrays2145(int[] differences, int lower, int upper) {
        long min = 10000,max  = -10000;
        long sum = 0;

        for(int num :differences){
            sum += num;
            min = Math.min(min,sum);
            max = Math.max(max,sum);
        }
        if (min < 0){
            lower -= min;
        }
        if(max > 0){
            upper -= max;
        }
        return Math.max(upper - lower +1,0);
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:60.6 MB,击败了8.57% 的Java用户
     * @param differences
     * @param lower
     * @param upper
     * @return
     */
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long s = 0, minS = 0, maxS = 0; // s[0] = 0
        for (int d : differences) {
            s += d;
            minS = Math.min(minS, s);
            maxS = Math.max(maxS, s);
        }
        return (int) Math.max(upper - lower - maxS + minS + 1, 0);
    }


}
