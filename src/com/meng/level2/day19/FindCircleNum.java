package com.meng.level2.day19;

import java.util.ArrayList;
import java.util.List;

/**
 * 547. 省份数量(中等)
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
    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 8.28%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 98.08%
     * 的用户
     * 通过测试用例：
     * 113 / 113
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        boolean[] flag = new boolean[len];
        List<Integer> temp = new ArrayList<>();
        int num = 0;
        for(int i = 0 ; i < len ; i++){
            if (flag[i]){
                continue;
            }
            temp.add(i);
            while (temp.size() >0){
                int size = temp.size();
                for(int k =0 ; k < size ; k++){
                    int index = temp.remove(0);
                    for(int j = 0 ; j < len ; j++){
                        if (!flag[j] && isConnected[index][j] == 1){
                            temp.add(j);
                        }
                    }
                    flag[index] = true;
                }
            }
            num++;
        }
        return num;
    }
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 85.64%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 95.57%
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

}
