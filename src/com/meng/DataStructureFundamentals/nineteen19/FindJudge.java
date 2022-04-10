package com.meng.DataStructureFundamentals.nineteen19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 997. 找到小镇的法官
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 *
 * 如果小镇法官真的存在，那么：
 *
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 *
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2, trust = [[1,2]]
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * 示例 3：
 *
 * 输入：n = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 *
 * 提示：
 *
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * trust 中的所有trust[i] = [ai, bi] 互不相同
 * ai != bi
 * 1 <= ai, bi <= n
 */
public class FindJudge {
    /**
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 12.81%
     * 的用户
     * 内存消耗：
     * 48.9 MB
     * , 在所有 Java 提交中击败了
     * 26.09%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust) {
        if(n == 1 && trust.length == 0){
            return 1;
        }
        Map<Integer, List<Integer>> cache = new HashMap<>();
        boolean[] flags =  new boolean[n];
        for(int [] t : trust){
            flags[t[0]-1] = true;
            List<Integer> temp = cache.getOrDefault(t[1], new ArrayList<>());
            temp.add(t[0]);
            cache.put(t[1],temp);
        }
        for(Map.Entry<Integer,List<Integer>> entry : cache.entrySet()){
            if (entry.getValue().size() == n -1 && !flags[entry.getKey()]){
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 方法一：计算各节点的入度和出度
     * 思路及解法
     *
     * 题干描述了一个有向图。每个人是图的节点，\textit{trust}trust 的元素 \textit{trust}[i]trust[i] 是图的有向边，从 \textit{trust}[i][0]trust[i][0] 指向 \textit{trust}[i][1]trust[i][1]。我们可以遍历 \textit{trust}trust，统计每个节点的入度和出度，存储在 \textit{inDegrees}inDegrees 和 \textit{outDegrees}outDegrees 中。
     *
     * 根据题意，在法官存在的情况下，法官不相信任何人，每个人（除了法官外）都信任法官，且只有一名法官。因此法官这个节点的入度是 n-1n−1, 出度是 00。
     *
     * 我们可以遍历每个节点的入度和出度，如果找到一个符合条件的节点，由于题目保证只有一个法官，我们可以直接返回结果；如果不存在符合条件的点，则返回 -1−1。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-town-judge/solution/zhao-dao-xiao-zhen-de-fa-guan-by-leetcod-0dcg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param trust
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 98.07%
     * 的用户
     * 内存消耗：
     * 48.8 MB
     * , 在所有 Java 提交中击败了
     * 38.96%
     * 的用户
     * 通过测试用例：
     * 92 / 92
     */
    public int findJudge1(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
