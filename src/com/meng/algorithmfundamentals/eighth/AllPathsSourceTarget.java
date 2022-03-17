package com.meng.algorithmfundamentals.eighth;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 797. 所有可能的路径
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 *
 *  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：graph = [[1,2],[3],[3],[]]
 * 输出：[[0,1,3],[0,2,3]]
 * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
 * 示例 2：
 *
 *
 *
 * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 *
 * 提示：
 *
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i（即不存在自环）
 * graph[i] 中的所有元素 互不相同
 * 保证输入为 有向无环图（DAG）
 */
public class AllPathsSourceTarget {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 43.3 MB
     * , 在所有 Java 提交中击败了
     * 28.29%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int len = graph.length;
        temp.add(0);
        dfsPath(graph,0,len-1);
        return res;
    }

    private void dfsPath(int[][] graph, int index, int size) {
        if (index == size){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int num : graph[index]){
            temp.add(num);
            dfsPath(graph,num,size);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        AllPathsSourceTarget demo = new AllPathsSourceTarget();
        System.out.println(demo.allPathsSourceTarget(graph));
    }
    /**
     * 方法一：深度优先搜索
     * 思路和算法
     *
     * 我们可以使用深度优先搜索的方式求出所有可能的路径。具体地，我们从 00 号点出发，使用栈记录路径上的点。每次我们遍历到点 n-1n−1，就将栈中记录的路径加入到答案中。
     *
     * 特别地，因为本题中的图为有向无环图（\text{DAG}DAG），搜索过程中不会反复遍历同一个点，因此我们无需判断当前点是否遍历过。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target/solution/suo-you-ke-neng-de-lu-jing-by-leetcode-s-iyoh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param graph
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 43 MB
     * , 在所有 Java 提交中击败了
     * 36.70%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     */
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    Deque<Integer> stack = new ArrayDeque<Integer>();
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        stack.offerLast(0);
        dfs(graph, 0, graph.length - 1);
        return ans;
    }

    public void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            ans.add(new ArrayList<Integer>(stack));
            return;
        }
        for (int y : graph[x]) {
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast();
        }
    }


}
