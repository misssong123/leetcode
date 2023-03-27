package com.meng.graphtheory.day08;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 *
 *
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class FindCircleNum {
    public int findCircleNum(int[][] isConnected) {
        return -1;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 86.07%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 43.45%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param isConnected
     * @return
     */
    public int findCircleNum1(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                provinces++;
            }
        }
        return provinces;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    /**
     *执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 10.86%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 37.54%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < cities; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 86.07%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 40.08%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param isConnected
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int cities = isConnected.length;
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

}
