package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionFindOrder210 {
    /**
     * 1.计算每个点的出度
     * 2.依次移除出度为0的节点
     * 3.移除出度为0的节点时，移除依赖当前节点的点
     * @param numCourses
     * @param prerequisites
     * @return
     * 解答成功:
     * 	执行耗时:7 ms,击败了25.85% 的Java用户
     * 	内存消耗:43.9 MB,击败了41.04% 的Java用户
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] points = new int[numCourses];
        Map<Integer,List<Integer>> cache = new HashMap<>();
        for(int [] pre : prerequisites){
            points[pre[0]]++;
            List<Integer> list = cache.getOrDefault(pre[1], new ArrayList<>());
            list.add(pre[0]);
            cache.put(pre[1],list);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0 ; i < numCourses ; i++){
            if (points[i] == 0){
                queue.add(i);
            }
        }
        int num = 0;
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            res[num] = poll;
            if (cache.get(poll)!=null){
                List<Integer> list = cache.get(poll);
                for(int index : list){
                    points[index]--;
                    if (points[index] == 0){
                        queue.add(index);
                    }
                }
            }
            num++;
        }
        return num == numCourses ? res : new int[0];
    }


    // 存储有向图
    List<List<Integer>> edges;
    // 存储每个节点的入度
    int[] indeg;
    // 存储答案
    int[] result;
    // 答案下标
    int index;

    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了34.65% 的Java用户
     * 	内存消耗:44 MB,击败了28.45% 的Java用户
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        result = new int[numCourses];
        index = 0;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        // 将所有入度为 0 的节点放入队列中
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 从队首取出一个节点
            int u = queue.poll();
            // 放入答案中
            result[index++] = u;
            for (int v: edges.get(u)) {
                --indeg[v];
                // 如果相邻节点 v 的入度为 0，就可以选 v 对应的课程了
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (index != numCourses) {
            return new int[0];
        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
