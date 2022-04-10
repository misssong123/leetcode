package com.meng.DataStructureFundamentals.nineteen19;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *841. 钥匙和房间
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 *
 * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 *
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：rooms = [[1],[2],[3],[]]
 * 输出：true
 * 解释：
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 *
 * 输入：rooms = [[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 *
 *
 * 提示：
 *
 * n == rooms.length
 * 2 <= n <= 1000
 * 0 <= rooms[i].length <= 1000
 * 1 <= sum(rooms[i].length) <= 3000
 * 0 <= rooms[i][j] < n
 * 所有 rooms[i] 的值 互不相同
 */
public class CanVisitAllRooms {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 61.35%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 10.98%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] flags = new boolean[rooms.size()];
        Queue<List<Integer>> cache = new LinkedList<>();
        cache.add(rooms.get(0));
        flags[0] =true;
        while (!cache.isEmpty()){
            List<Integer> poll = cache.poll();
            for(int num : poll){
                if (!flags[num]){
                    flags[num] = true;
                    cache.add(rooms.get(num));
                }
            }
        }
        for(boolean flag : flags){
            if (!flag){
                return false;
            }
        }
        return true;
    }



    /**
     * 方法一：深度优先搜索
     * 思路及解法
     *
     * 我们可以使用深度优先搜索的方式遍历整张图，统计可以到达的节点个数，并利用数组 \textit{vis}vis 标记当前节点是否访问过，以防止重复访问。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/keys-and-rooms/solution/yao-chi-he-fang-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param rooms
     * @return
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 72.74%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     */
    boolean[] vis;
    int num;
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }

}
