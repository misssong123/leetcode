package com.meng;

import java.util.HashSet;
import java.util.Set;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 *
 *
 * 提示：
 *
 *     皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TotalNQueens {
    int ans = 0 ;
    public int totalNQueens(int n) {
        if (n==1)
            return 1;
        if (n>3){
            int [] temp = new int[n];
            queen(temp,n,0);
        }
        return ans;
    }
    /**
     *
     * @param temp 存在当前皇后的存在情况
     * @param n 皇后个数
     * @param index 当前下标
     */
    private void queen(int[] temp, int n, int index) {
        if (index == n){
            ans ++;
            return;
        }
        for(int i = 0 ; i < n ;i++){
            if (check(temp,index,i)){
                temp[index] = i;
                queen(temp,n,index+1);
            }
        }
    }
    /**
     *
     * @param temp 存在当前皇后的存在情况
     * @param index 正在存放的皇后下标
     * @param val 存放当前皇后的下标
     * @return
     */
    private boolean check(int[] temp, int index, int val) {
        /**
         * 因为采用一维数组进行存储，不需要判断同行的情况
         * 只需要保证当前皇后所存储的位置不在已经存放皇后的同列或同一斜线即可
         */
        for(int i = 0 ; i < index ; i++){
            /**
             * temp[i] == val 同一列
             *  (index -i) == (Math.abs(temp[i]-val)) 同一斜线
             */
            if (temp[i] == val || (index -i) == (Math.abs(temp[i]-val))){
                return false;
            }
        }
        return true;
    }

    /**
     * 官方解法1
     * 基于集合的回溯
     *
     * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 columns\textit{columns}columns、diagonals1\textit{diagonals}_1diagonals1​ 和 diagonals2\textit{diagonals}_2diagonals2​ 分别记录每一列以及两个方向的每条斜线上是否有皇后。
     *
     * 列的表示法很直观，一共有 NNN 列，每一列的下标范围从 000 到 N−1N-1N−1，使用列的下标即可明确表示每一列。
     *
     * 如何表示两个方向的斜线呢？对于每个方向的斜线，需要找到斜线上的每个位置的行下标与列下标之间的关系。
     *
     * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如 (0,0)(0,0)(0,0) 和 (3,3)(3,3)(3,3) 在同一条方向一的斜线上。因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
     * 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，例如 (3,0)(3,0)(3,0) 和 (1,2)(1,2)(1,2) 在同一条方向二的斜线上。因此使用行下标与列下标之和即可明确表示每一条方向二的斜线。
     * 每次放置皇后时，对于每个位置判断其是否在三个集合中，如果三个集合都不包含当前位置，则当前位置是可以放置皇后的位置。
     * @return
     */
    public int totalNQueens1(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack1(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack1(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack1(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    /**
     * 官方解法二
     * 基于位运算的回溯
     * 使用三个整数 columns\textit{columns}columns、diagonals1\textit{diagonals}_1diagonals1​ 和 diagonals2\textit{diagonals}_2diagonals2​ 分别记录每一列以及两个方向的每条斜线上是否有皇后，每个整数有 NNN 个二进制位。棋盘的每一列对应每个整数的二进制表示中的一个数位，其中棋盘的最左列对应每个整数的最低二进制位，最右列对应每个整数的最高二进制位。
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        return solve2(n, 0, 0, 0, 0);
    }

    public int solve2(int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                count += solve2(n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
            }
            return count;
        }
    }

}
