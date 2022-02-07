package com.meng.origin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 *
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 *
 * 最后返回经过上色渲染后的图像。
 *
 * 示例 1:
 *
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 *
 * 注意:
 *
 *     image 和 image[0] 的长度在范围 [1, 50] 内。
 *     给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 *     image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 */
public class FloodFill {
    public static void main(String[] args) {
        int [][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1;
        int sc = 1;
        int newColor =1;
        FloodFill demo = new FloodFill();
        demo.floodFill(image,sr,sc,newColor);
        for(int i = 0 ; i < image.length ;i++){
            for (int j =0 ; j < image[0].length ; j++)
                System.out.print(image[i][j]);
            System.out.println();
        }

    }

    /**
     * 1.创建大小相同的boolean数据，记录元素是否访问过
     * 2.依次遍历上下左右元素，如符合条件，且该元素未被访问，则遍历该元素的上下左右元素
     * 3.以此类推，直至所有符合条件的元素替换新值
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int origin = image[sr][sc];
        int height = image.length;
        int length = image[0].length;
        boolean [][] flag = new boolean[height][length];
        floodFill(image,sr,sc,newColor,origin,height,length,flag);
        return image;
    }
    public void floodFill(int [][] image,int sr,int sc,int newColor,int origin,int height,int length,boolean [][]flag){
        if (sr<0 || sr>=height || sc <0 || sc>=length)
            return;
        if (image[sr][sc] == origin && !flag[sr][sc]){
            image[sr][sc] = newColor;
            flag[sr][sc] = true;
            floodFill(image,sr+1,sc,newColor,origin,height,length,flag);
            floodFill(image,sr-1,sc,newColor,origin,height,length,flag);
            floodFill(image,sr,sc+1,newColor,origin,height,length,flag);
            floodFill(image,sr,sc-1,newColor,origin,height,length,flag);
        }

    }
}

/**
 * 官方解法
 */
class Solution {
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }
        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }
}
