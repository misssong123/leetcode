package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class LargestLocal2373 {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 32.70%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 87.20%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     * @param grid
     * @return
     */
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n-2][n-2];
        int[] x = {0,0,1,-1,-1,1,-1,1};
        int[] y = {1,-1,0,0,-1,1,1,-1};
        for(int i = 1 ; i < n-1 ; i++){
            for(int j = 1 ; j < n -1 ; j++) {
                int max = grid[i][j];
                for(int num = 0 ; num < 8 ;num++){
                    max = Math.max(max,grid[i+x[num]][j+y[num]]);
                }
                res[i-1][j-1] = max;
            }
        }
        return res;
    }

    /**
     * 方法一：遍历
     *
     * 思路与算法
     *
     * 设
     * grid
     * grid 的大小为
     * n
     * ×
     * n
     * n×n，那么我们申请一个大小为
     * (
     * n
     * −
     * 2
     * )
     * ×
     * (
     * n
     * −
     * 2
     * )
     * (n−2)×(n−2) 的矩阵
     * res
     * res 用来存放答案。我们遍历
     * grid
     * grid 中每个
     * 3
     * ×
     * 3
     * 3×3 子矩阵的左上角，然后统计当前子矩阵的最大值放入
     * res
     * res 中。
     *
     * 具体做法是，我们顺序遍历
     * i
     *  
     * (
     * 0
     * ≤
     * i
     * <
     * n
     * −
     * 2
     * )
     * i (0≤i<n−2)，再顺序遍历
     * j
     *  
     * (
     * 0
     * ≤
     * j
     * <
     * n
     * −
     * 2
     * )
     * j (0≤j<n−2)，接着遍历求解
     * {
     * grid
     * (
     * x
     * ,
     * y
     * )
     *  
     * ∣
     *  
     * i
     * ≤
     * x
     * <
     * i
     * +
     * 3
     * ,
     * j
     * ≤
     * y
     * <
     * j
     * +
     * 3
     * }
     * {grid(x,y) ∣ i≤x<i+3,j≤y<j+3} 的最大值放入
     * res
     * [
     * i
     * ]
     * [
     * j
     * ]
     * res[i][j] 中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/largest-local-values-in-a-matrix/solution/ju-zhen-zhong-de-ju-bu-zui-da-zhi-by-lee-o703/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param grid
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 81.99%
     * 的用户
     * 通过测试用例：
     * 50 / 50
     */
    public int[][] largestLocal1(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        res[i][j] = Math.max(res[i][j], grid[x][y]);
                    }
                }
            }
        }
        return res;
    }

}
