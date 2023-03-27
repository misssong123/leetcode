package com.meng.dynamicprogramming.practise;

import java.util.Arrays;

/**
 * # [USACO1.5][IOI1994]数字三角形 Number Triangles
 *
 * ## 题目描述
 *
 * 观察下面的数字金字塔。
 *
 *
 * 写一个程序来查找从最高点到底部任意处结束的路径，使路径经过数字的和最大。每一步可以走到左下方的点也可以到达右下方的点。
 *
 * ```cpp
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 * ```
 * 在上面的样例中,从 $7 \to 3 \to 8 \to 7 \to 5$ 的路径产生了最大
 *
 * ## 输入格式
 *
 * 第一个行一个正整数 $r$ ,表示行的数目。
 *
 * 后面每行为这个数字金字塔特定行包含的整数。
 *
 * ## 输出格式
 *
 * 单独的一行,包含那个可能得到的最大的和。
 *
 * ## 样例 #1
 *
 * ### 样例输入 #1
 *
 * ```
 * 5
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 * ```
 *
 * ### 样例输出 #1
 *
 * ```
 * 30
 * ```
 *
 * ## 提示
 *
 * 【数据范围】
 * 对于 $100\%$ 的数据，$1\le r \le 1000$，所有输入在 $[0,100]$ 范围内。
 *
 * 题目翻译来自NOCOW。
 *
 * USACO Training Section 1.5
 *
 * IOI1994 Day1T1
 */
public class T0001NumberTriangles {
    public int numberTriangles(int[][] nums){
        int len  = nums.length;
        for(int i = len - 2 ; i >= 0 ; i--){
            for(int j = 0 ; j <= i ; j++){
                nums[i][j] = Math.max(nums[i][j]+nums[i+1][j],nums[i][j]+nums[i+1][j+1]);
            }
        }
        return nums[0][0];
    }
    public static void main(String[] args) {
        int[][] nums = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
        T0001NumberTriangles demo = new T0001NumberTriangles();
        demo.print(nums);
        System.out.println(demo.numberTriangles(nums));
        demo.print(nums);
    }
    public void print(int[][] nums){
        for(int[] num : nums){
            System.out.println(Arrays.toString(num));
        }
    }
}
