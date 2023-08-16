package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class LargestAltitude1732 {
    /**
     * 详情
     * -ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 38.29mb
     * 击败 22.92%使用 Java 的用户
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain) {
        int max = 0,temp = 0;
        for(int num : gain){
            temp += num;
            max = Math.max(max,temp);
        }
        return max;
    }
}

