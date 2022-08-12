package com.meng.graphtheory.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 733. 图像渲染(简单)
 * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
 *
 * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
 *
 * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为 newColor 。
 *
 * 最后返回 经过上色渲染后的图像 。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析: 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
 * 示例 2:
 *
 * 输入: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * 输出: [[2,2,2],[2,2,2]]
 *
 *
 * 提示:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], newColor < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
public class FloodFill {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 38.30%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 77.41%
     * 的用户
     * 通过测试用例：
     * 277 / 277
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[] xIndex = {0,0,1,-1};
        int[] yIndex = {1,-1,0,0};
        int lines = image.length;
        int rows = image[0].length;
        int originColor = image[sr][sc];
        boolean[][] flags = new boolean[lines][rows];
        List<int[]> cache = new ArrayList<>();
        cache.add(new int[]{sr,sc});
        while (cache.size() > 0 ){
            int size = cache.size();
            for(int i = 0 ; i < size ; i++){
                int[] temp = cache.remove(0);
                int x = temp[0];
                int y = temp[1];
                flags[x][y] = true;
                image[x][y] = color;
                for(int j = 0 ; j < 4 ; j++){
                    int newX = x + xIndex[j];
                    int newY = y + yIndex[j];
                    if (newX >=0 && newX < lines && newY >= 0 && newY < rows && !flags[newX][newY] && image[newX][newY] == originColor){
                        cache.add(new int[]{newX,newY});
                    }
                }
            }
        }
        return image;
    }

    private static void printPoint(int[][] image){
        for(int[] point : image){
            System.out.println(Arrays.toString(point));
        }
    }
    public static void main(String[] args) {
        FloodFill demo = new FloodFill();
        int sr = 1, sc = 1, newColor = 2;
        int[][] image = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        printPoint(image);
        demo.floodFill(image,sr,sc,newColor);
        System.out.println("------------------------------");
        printPoint(image);

    }

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 38.70%
     * 的用户
     * 通过测试用例：
     * 277 / 277
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor != newColor) {
            dfs(image, sr, sc, currColor, newColor);
        }
        return image;
    }

    public void dfs(int[][] image, int x, int y, int color, int newColor) {
        if (image[x][y] == color) {
            image[x][y] = newColor;
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < image.length && my >= 0 && my < image[0].length) {
                    dfs(image, mx, my, color, newColor);
                }
            }
        }
    }

}
