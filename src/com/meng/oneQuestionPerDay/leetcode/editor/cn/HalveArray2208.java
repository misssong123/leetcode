package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Comparator;
import java.util.PriorityQueue;

class HalveArray2208 {
    /**
     * 执行用时：
     * 218 ms
     * , 在所有 Java 提交中击败了
     * 12.12%
     * 的用户
     * 内存消耗：
     * 56.8 MB
     * , 在所有 Java 提交中击败了
     * 31.82%
     * 的用户
     * 通过测试用例：
     * 62 / 62
     * @param nums
     * @return
     */
    public int halveArray(int[] nums) {
        double sum = 0.0;
        for(int num : nums){
            sum += num;
        }
        double target = (sum * 1.0 / 2);
        int res = 0;
        PriorityQueue<Double> queue = new PriorityQueue<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2 - o1 >= 0 ? 1 : -1;
            }
        });
        for(int num : nums){
            queue.add((double)num);
        }
        double temp = 0;
        while (temp < target){
            double v = queue.poll() / 2;
            temp += v;
            res++;
            queue.add(v);
        }
        return res;
    }

    /**
     *
     * @param nums
     * @return
     */
    public int halveArray1(int[] nums) {
        /**
         * 执行用时：
         * 167 ms
         * , 在所有 Java 提交中击败了
         * 60.61%
         * 的用户
         * 内存消耗：
         * 56 MB
         * , 在所有 Java 提交中击败了
         * 51.51%
         * 的用户
         * 通过测试用例：
         * 62 / 62
         */
        PriorityQueue<Double> pq = new PriorityQueue<Double>((a, b) -> b.compareTo(a));
        for (int num : nums) {
            pq.offer((double) num);
        }
        int res = 0;
        double sum = 0;
        for (int num : nums) {
            sum += num;
        }
        double sum2 = 0.0;
        while (sum2 < sum / 2) {
            double x = pq.poll();
            sum2 += x / 2;
            pq.offer(x / 2);
            res++;
        }
        return res;
    }

}

