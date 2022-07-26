package com.meng.level2.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 210. 课程表 II(中等)
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 *
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 *
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 */
public class FindOrder {
    int num = 0;
    boolean flag = false;

    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 93.61%
     * 的用户
     * 内存消耗：
     * 42.9 MB
     * , 在所有 Java 提交中击败了
     * 15.01%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] step = new int[numCourses];
        num = numCourses-1;
        List<List<Integer>> cache = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i++){
            cache.add(new ArrayList<>());
        }
        for(int [] nums : prerequisites){
            cache.get(nums[1]).add(nums[0]);
        }
        for(int i = 0 ; i < numCourses ; i++){
            if (step[i] == 0 && !flag){
                dfs(i,step,res,cache);
            }
        }
        if (flag){
            return new int[0];
        }
        return res;
    }

    private void dfs(int index,int[] step, int[] res, List<List<Integer>> cache) {
        if (flag){
            return;
        }
        step[index] = 1;
        for(int num : cache.get(index)){
            if (step[num] == 0){
                dfs(num,step,res,cache);
                if (flag){
                    return;
                }
            }else if (step[num] == 1){
                flag = true;
                return;
            }
        }
        step[index] = 2;
        res[num--] = index;
    }

    public static void main(String[] args) {
        FindOrder demo = new FindOrder();
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,1},{3,1},{3,2}};
        System.out.println(Arrays.toString(demo.findOrder(numCourses,prerequisites)));
    }



    /**
     *
     * @param numCourses
     * @param prerequisites
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 99.86%
     * 的用户
     * 内存消耗：
     * 42.9 MB
     * , 在所有 Java 提交中击败了
     * 8.65%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    // 存储有向图
    List<List<Integer>> edges;
    // 标记每个节点的状态：0=未搜索，1=搜索中，2=已完成
    int[] visited;
    // 用数组来模拟栈，下标 n-1 为栈底，0 为栈顶
    int[] result;
    // 判断有向图中是否有环
    boolean valid = true;
    // 栈下标
    int index;
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 每次挑选一个「未搜索」的节点，开始进行深度优先搜索
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        // 如果没有环，那么就有拓扑排序
        return result;
    }

    public void dfs(int u) {
        // 将节点标记为「搜索中」
        visited[u] = 1;
        // 搜索其相邻节点
        // 只要发现有环，立刻停止搜索
        for (int v: edges.get(u)) {
            // 如果「未搜索」那么搜索相邻节点
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            // 如果「搜索中」说明找到了环
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        // 将节点标记为「已完成」
        visited[u] = 2;
        // 将节点入栈
        result[index--] = u;
    }

}
