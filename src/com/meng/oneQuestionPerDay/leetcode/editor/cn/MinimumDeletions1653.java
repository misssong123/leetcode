package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

class MinimumDeletions1653 {
    /**
     * 1.计算出某个位置左侧b的数量和右侧a的数据
     * 2.计算某个点左侧值和右侧值相加的最小值
     * 3.将最小值，移除所有a，移除所有b三者在去最小值
     *
     * @param s
     * @return
     * 执行用时：
     * 67 ms
     * , 在所有 Java 提交中击败了
     * 9.89%
     * 的用户
     * 内存消耗：
     * 42.7 MB
     * , 在所有 Java 提交中击败了
     * 16.48%
     * 的用户
     * 通过测试用例：
     * 157 / 157
     */
    public int minimumDeletions(String s) {
        int len = s.length(),countB = 0 ,countA = 0;
        int[] leftB = new int[len];
        int[] rightA = new int[len];
        for(int i = 0 ; i < len ; i++){
            boolean flag = s.charAt(i)=='b';
            if (i > 0){
                leftB[i] = leftB[i-1] + (s.charAt(i-1)=='b'?1:0);
            }
            if (flag){
                countB++;
            }else {
                countA++;
            }
        }
        if (countA == 0 || countB==0){
            return 0;
        }
        int res = Math.min(countA,countB);
        for(int i = len -2 ; i >=0; i--){
            rightA[i] = rightA[i+1] + (s.charAt(i+1)=='a'?1:0);
            res = Math.min(res,rightA[i] + leftB[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aababbab";
        MinimumDeletions1653 demo = new MinimumDeletions1653();
        System.out.println(demo.minimumDeletions(s));
    }
    /**
     *
     * @param s
     * @return
     * 执行用时：
     * 54 ms
     * , 在所有 Java 提交中击败了
     * 13.19%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 78.02%
     * 的用户
     * 通过测试用例：
     * 157 / 157
     */
    public int minimumDeletions1(String s) {
        int leftb = 0, righta = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                righta++;
            }
        }
        int res = righta;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                righta--;
            } else {
                leftb++;
            }
            res = Math.min(res, leftb + righta);
        }
        return res;
    }

}
