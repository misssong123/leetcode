package com.meng.hot100;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 *
 * 提示：
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class T035CanFinish {
    public static void main(String[] args) {
        //20
        //[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]
       int numCourses = 3 ;
       int[][] prerequisites =  {{1,1}};
       T035CanFinish demo = new T035CanFinish();
        System.out.println(demo.canFinish(numCourses,prerequisites));
        System.out.println(demo.canFinish1(numCourses,prerequisites));
        System.out.println(demo.canFinish2(numCourses,prerequisites));
    }

    /**
     * 思路错误
     * 错误思路：将需要的提前课程存放在一起比对，未考虑连锁依赖的情况
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 ){
            return true;
        }
        Map<Integer, Set<Integer>> nums = new HashMap<>();
        for(int[] num : prerequisites){
            int start = num[0];
            int end = num[1];
            if (start == end){
                return false;
            }
            nums.computeIfAbsent(start, k -> new HashSet<>());
            if (nums.get(end) != null ){
                if (nums.get(end).contains(start)){
                    return false;
                }
                nums.get(start).addAll(nums.get(end));
            }
            nums.get(start).add(end);
        }
        return true;
    }
    /**
     * 方法一：深度优先搜索
     * 思路
     *
     * 我们可以将深度优先搜索的流程与拓扑排序的求解联系起来，用一个栈来存储所有已经搜索完成的节点。
     *
     * 对于一个节点 uu，如果它的所有相邻节点都已经搜索完成，那么在搜索回溯到 uu 的时候，uu 本身也会变成一个已经搜索完成的节点。这里的「相邻节点」指的是从 uu 出发通过一条有向边可以到达的所有节点。
     *
     * 假设我们当前搜索到了节点 uu，如果它的所有相邻节点都已经搜索完成，那么这些节点都已经在栈中了，此时我们就可以把 uu 入栈。可以发现，如果我们从栈顶往栈底的顺序看，由于 uu 处于栈顶的位置，那么 uu 出现在所有 uu 的相邻节点的前面。因此对于 uu 这个节点而言，它是满足拓扑排序的要求的。
     *
     * 这样以来，我们对图进行一遍深度优先搜索。当每个节点进行回溯的时候，我们把该节点放入栈中。最终从栈顶到栈底的序列就是一种拓扑排序。
     *
     * 算法
     *
     * 对于图中的任意一个节点，它在搜索的过程中有三种状态，即：
     *
     * 「未搜索」：我们还没有搜索到这个节点；
     *
     * 「搜索中」：我们搜索过这个节点，但还没有回溯到该节点，即该节点还没有入栈，还有相邻的节点没有搜索完成）；
     *
     * 「已完成」：我们搜索过并且回溯过这个节点，即该节点已经入栈，并且所有该节点的相邻节点都出现在栈的更底部的位置，满足拓扑排序的要求。
     *
     * 通过上述的三种状态，我们就可以给出使用深度优先搜索得到拓扑排序的算法流程，在每一轮的搜索搜索开始时，我们任取一个「未搜索」的节点开始进行深度优先搜索。
     *
     * 我们将当前搜索的节点 uu 标记为「搜索中」，遍历该节点的每一个相邻节点 vv：
     *
     * 如果 vv 为「未搜索」，那么我们开始搜索 vv，待搜索完成回溯到 uu；
     *
     * 如果 vv 为「搜索中」，那么我们就找到了图中的一个环，因此是不存在拓扑排序的；
     *
     * 如果 vv 为「已完成」，那么说明 vv 已经在栈中了，而 uu 还不在栈中，因此 uu 无论何时入栈都不会影响到 (u, v)(u,v) 之前的拓扑关系，以及不用进行任何操作。
     *
     * 当 uu 的所有相邻节点都为「已完成」时，我们将 uu 放入栈中，并将其标记为「已完成」。
     *
     * 在整个深度优先搜索的过程结束后，如果我们没有找到图中的环，那么栈中存储这所有的 nn 个节点，从栈顶到栈底的顺序即为一种拓扑排序。
     *
     * 下面的幻灯片给出了深度优先搜索的可视化流程。图中的「白色」「黄色」「绿色」节点分别表示「未搜索」「搜索中」「已完成」的状态。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param numCourses
     * @param prerequisites
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 92.71%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 87.77%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    /**
     * 方法二: 广度优先搜索
     * 思路
     *
     * 方法一的深度优先搜索是一种「逆向思维」：最先被放入栈中的节点是在拓扑排序中最后面的节点。我们也可以使用正向思维，顺序地生成拓扑排序，这种方法也更加直观。
     *
     * 我们考虑拓扑排序中最前面的节点，该节点一定不会有任何入边，也就是它没有任何的先修课程要求。当我们将一个节点加入答案中后，我们就可以移除它的所有出边，代表着它的相邻节点少了一门先修课程的要求。如果某个相邻节点变成了「没有任何入边的节点」，那么就代表着这门课可以开始学习了。按照这样的流程，我们不断地将没有入边的节点加入答案，直到答案中包含所有的节点（得到了一种拓扑排序）或者不存在没有入边的节点（图中包含环）。
     *
     * 上面的想法类似于广度优先搜索，因此我们可以将广度优先搜索的流程与拓扑排序的求解联系起来。
     *
     * 算法
     *
     * 我们使用一个队列来进行广度优先搜索。初始时，所有入度为 00 的节点都被放入队列中，它们就是可以作为拓扑排序最前面的节点，并且它们之间的相对顺序是无关紧要的。
     *
     * 在广度优先搜索的每一步中，我们取出队首的节点 uu：
     *
     * 我们将 uu 放入答案中；
     *
     * 我们移除 uu 的所有出边，也就是将 uu 的所有相邻节点的入度减少 11。如果某个相邻节点 vv 的入度变为 00，那么我们就将 vv 放入队列中。
     *
     * 在广度优先搜索的过程结束后。如果答案中包含了这 nn 个节点，那么我们就找到了一种拓扑排序，否则说明图中存在环，也就不存在拓扑排序了。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/course-schedule/solution/ke-cheng-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param numCourses
     * @param prerequisites
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 63.11%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 14.23%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     */
    int[] indeg;
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return visited == numCourses;
    }

}
