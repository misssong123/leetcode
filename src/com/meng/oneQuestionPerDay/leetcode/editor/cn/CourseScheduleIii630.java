package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionScheduleCourse630 {
    int[][] temp;
    int len;
    boolean[] flags;
    int max = 0;

    /**
     * 解答成功:
     * 	执行耗时:30 ms,击败了91.01% 的Java用户
     * 	内存消耗:52.5 MB,击败了85.39% 的Java用户
     * @param courses
     * @return
     */
    public int scheduleCourse1(int[][] courses) {
        temp = courses;
        len = courses.length;
        flags = new boolean[len];
        for (int i = 0 ; i < len ; i++){
            if (temp[i][0] > temp[i][1]){
                continue;
            }
            int temp = dfs(i,1,courses[i][0]);
            max = Math.max(temp,max);
        }
        return max;
    }
    /**
     *
     * @param index 当前坐标
     * @param num 当前数据
     * @param start 当前开始时间
     */
    private int dfs(int index, int num, int start) {
        int t = 0;
        flags[index] =true;
        for(int i = 0 ; i < len ; i++){
            if (index == i || flags[i] || temp[i][1] < start + temp[i][0]){
                continue;
            }
            t = Math.max(dfs(i,num + 1,start +temp[i][0])+num,t);
        }
        flags[index] = false;
        return t;
    }

    public static void main(String[] args) {
        SolutionScheduleCourse630 demo = new SolutionScheduleCourse630();
        int[][] courses = {{1,2}};
        System.out.println(demo.scheduleCourse(courses));
    }
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        // 优先队列中所有课程的总时间
        int total = 0;

        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                total -= q.poll() - ti;
                q.offer(ti);
            }
        }

        return q.size();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
