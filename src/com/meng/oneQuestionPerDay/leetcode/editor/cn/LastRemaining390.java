package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.LinkedList;

class LastRemaining390 {
    /**
     * 模拟
     * 超时
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        for(int i = 1 ; i <= n ; i++){
            left.addLast(i);
        }
        while (left.size() > 1 || right.size() >1){
            int index = 0;
            if (left.size() > 1){
                while (!left.isEmpty()){
                    Integer num = left.pollFirst();
                    if (index % 2 == 1){
                        right.addLast(num);
                    }
                    index++;
                }
            }else{
                while (!right.isEmpty()){
                    Integer num = right.pollLast();
                    if (index % 2 == 1){
                        left.addFirst(num);
                    }
                    index++;
                }
            }
        }
        return left.size() > 0 ? left.pop() : right.pop();
    }

    public static void main(String[] args) {
        LastRemaining390 demo = new LastRemaining390();
        System.out.println(demo.lastRemaining(9));
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 63.74%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 6.87%
     * 的用户
     * 通过测试用例：
     * 3377 / 3377
     * @param n
     * @return
     */
    public int lastRemaining1(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }


}
