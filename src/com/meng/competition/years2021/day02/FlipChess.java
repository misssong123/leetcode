package com.meng.competition.years2021.day02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCP 41. 黑白翻转棋
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。
 * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（
 * 中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 *
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，
 * 其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 *
 * 注意：
 *
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 * 示例 1：
 *
 * 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]
 *
 * 输出：3
 *
 * 解释：
 * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
 *
 * 示例 2：
 *
 * 输入：chessboard = [".X.",".O.","XO."]
 *
 * 输出：2
 *
 * 解释：
 * 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。
 *
 * 示例 3：
 *
 * 输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]
 *
 * 输出：4
 *
 * 解释：
 * 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。
 *
 * 提示：
 *
 * 1 <= chessboard.length, chessboard[i].length <= 8
 * chessboard[i] 仅包含 "."、"O" 和 "X"
 */
public class FlipChess {
    public int flipChess(String[] chessboard) {
        return -1;
    }

    /**
     * 黑白棋盘，当下了一颗黑子后，如果能沿着 [上，下，左，右，左上，左下，右上，右下] 连续找到白棋，
     * 且最终能遇到黑子拦截，则将这条路径上所有的白子翻转为黑子，翻转后得到的新黑子能触发连锁反应。
     *
     * 现在给你一次下黑子的机会，问最多能翻转多少颗白子。
     *
     * 思路
     * 1. 对棋盘每个空格进行讨论
     *
     * 这道题很明显找不出什么实质性的最优解规律，
     * 只能枚举每个空格下黑子的情况，
     * 看哪个格子能得到最优解。
     *
     * 2. 每一次模拟前复制棋盘
     *
     * 由于需要模拟每个空格下黑棋后的棋盘变化，不复制一份棋盘的话，每次模拟结束时需要还原现场，比较麻烦。
     *
     *
     * for (int i = 0; i < n; i++) {
     *     for (int j = 0; j < m; j++) {
     *         if (board[i][j] == '.') {
     *             // copy the board
     *             // To Do..
     *         }
     *     }
     * }
     * 3. 下黑子后进行八个方向的模拟
     *
     * 将八个方向的横纵坐标的步长用数组保存，方便遍历的同时，可以简化代码：
     *
     *
     * final int[] dir_x = { 1, -1, 0, 0, 1, 1, -1, -1 };
     * final int[] dir_y = { 0, 0, -1, 1, -1, 1, -1, 1 };
     * 接着对每一个方向进行模拟搜索，如果 「 连续遇到白子，且最后可以遇到黑子 」 ，
     * 则表示被搜索到的 白子 都可以被翻转成 黑子。反之，我们需要还原现场，当作这个方向上什么都没有发生过。
     *
     *
     * private LinkedList<Integer> search(char[][] arr, int x, int y, int step_x, int step_y) {
     *     // 保存白子下标
     *     LinkedList<Integer> temp = new LinkedList<>();
     *     boolean flag = false;
     *
     *     while (check(x, y)) {
     *         if (arr[x][y] != 'O') {
     *             // 遇到不是白子时，可能是黑子或者空格
     *             // 如果是黑子，则代表这个方向搜索到的白子可以被翻转
     *             flag = arr[x][y] == 'X';
     *             break;
     *         } else {
     *             // 保存白子（新黑子）下标并翻转
     *             temp.add(x * 10 + y);
     *             arr[x][y] = 'X';
     *         }
     *         x += step_x;
     *         y += step_y;
     *     }
     *     if (!flag) {
     *         // 需要还原现场，将保存的新黑子下标还原成白子
     *         while (!temp.isEmpty()) {
     *             var pos = temp.poll();
     *             arr[pos / 10][pos % 10] = 'O';
     *         }
     *     }
     *
     *     // 如果该方向有效，返回所有新黑子的下标位置，否则是空数组
     *     return temp;
     * }
     *
     * private boolean check(int x, int y) {
     *     // 检查下标是否有效
     *     return x >= 0 && x < n && y >= 0 && y < m;
     * }
     * 4. 对每一轮的新黑子进行连锁反应讨论
     *
     * 既然是连锁反应，操作会很套娃，因此可以使用递归来帮助我们简化代码。
     *
     *
     * private int process(char[][] arr, int x, int y) {
     *     // 存储每一颗 黑子 在八个方向上得到的 新黑子位置
     *     LinkedList<Integer> q = new LinkedList<>();
     *
     *     for (int i = 0; i < 8; i++) {
     *         int new_x = x + dir_x[i];
     *         int new_y = y + dir_y[i];
     *         // addAll：存下第 i 个方向搜索到的所有新黑子下标位置
     *         q.addAll(search(arr, new_x, new_y, dir_x[i], dir_y[i]));
     *     }
     *
     *
     *     // q.size() 正是 当前黑子 能翻转的白子数量
     *     int res = q.size();
     *     while (!q.isEmpty()) {
     *         var pos = q.poll();
     *         // 递归调用，每一颗 新黑子 都可以当作 你所下的第一颗黑子
     *         res += process(arr, pos / 10, pos % 10);
     *     }
     *
     *     return res;
     * }
     *
     * 作者：xy_fs
     * 链接：https://leetcode.cn/problems/fHi6rV/solution/xyfs-mo-ni-ti-si-lu-zheng-que-zui-zhong-9ddsw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 8.40%
     * 的用户
     * 内存消耗：
     * 41.3 MB
     * , 在所有 Java 提交中击败了
     * 32.06%
     * 的用户
     * 通过测试用例：
     * 154 / 154
     */
    final int[] dir_x = { 1, -1, 0, 0, 1, 1, -1, -1 };
    final int[] dir_y = { 0, 0, -1, 1, -1, 1, -1, 1 };
    int n, m;

    public int flipChess1(String[] chessboard) {
        n = chessboard.length;
        m = chessboard[0].length();
        char[][] board = copyBoard(chessboard);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.') {
                    char[][] copy = copyBoard(chessboard);
                    copy[i][j] = 'X';
                    int cnt = process(copy, i, j);
                    res = Math.max(res, cnt);
                }
            }
        }

        return res;
    }

    private char[][] copyBoard(String[] chessboard) {
        char[][] board = new char[n][m];
        int idx = 0;

        for (String line : chessboard) {
            board[idx++] = line.toCharArray();
        }

        return board;
    }

    private int process(char[][] arr, int x, int y) {
        LinkedList<Integer> q = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            int new_x = x + dir_x[i];
            int new_y = y + dir_y[i];
            q.addAll(search(arr, new_x, new_y, dir_x[i], dir_y[i]));
        }

        int res = q.size();
        while (!q.isEmpty()) {
            int pos = q.poll();
            res += process(arr, pos / 10, pos % 10);
        }

        return res;
    }

    private LinkedList<Integer> search(char[][] arr, int x, int y, int step_x, int step_y) {
        LinkedList<Integer> temp = new LinkedList<>();
        boolean flag = false;

        while (check(x, y)) {
            if (arr[x][y] != 'O') {
                flag = arr[x][y] == 'X';
                break;
            } else {
                temp.add(x * 10 + y);
                arr[x][y] = 'X';
            }
            x += step_x;
            y += step_y;
        }
        if (!flag) {
            while (!temp.isEmpty()) {
                int pos = temp.poll();
                arr[pos / 10][pos % 10] = 'O';
            }
        }

        return temp;
    }

    private boolean check(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
    /**
     *方法：BFS
     * 思路及算法
     *
     * 考虑利用 BFS 模拟，对每个棋盘先拷贝一份，尝试修改每个空位，并更新变黑棋子的最大值。
     *
     * 具体 BFS 逻辑为：
     * 1.在空格附近的8个格子里找是否有非空格的
     * 2.如果有则顺着这个方向继续找黑色棋子,并更新黑色路径的最大长度
     * 3.找到黑色棋子则把该路径上的白色棋子置为黑色，并加入队列，继续BFS
     * 4.将最多变黑的棋子数量返回
     *
     * 作者：hu-li-hu-wai
     * 链接：https://leetcode.cn/problems/fHi6rV/solution/bfs-by-hu-li-hu-wai-g277/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 80.15%
     * 的用户
     * 内存消耗：
     * 41.2 MB
     * , 在所有 Java 提交中击败了
     * 42.75%
     * 的用户
     * 通过测试用例：
     * 154 / 154
     */
    int m2, n2;
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public int flipChess2(String[] chessboard) {
        this.m2 = chessboard.length;
        this.n2 = chessboard[0].length();
        int res = 0;
        for (int i = 0; i < m2; i++) {
            for (int j = 0; j < n2; j++) {
                if (chessboard[i].charAt(j) == '.') {
                    char[][] clone = copy(chessboard);
                    res = Math.max(res, flipOnlyOneChess(clone, i, j));
                }
            }
        }
        return res;
    }

    private char[][] copy(String[] chessboard) {
        char[][] cs = new char[m2][n2];
        for (int i = 0; i < m2; i++) {
            cs[i] = chessboard[i].toCharArray();
        }
        return cs;
    }

    public int flipOnlyOneChess(char[][] clone, int x0, int y0) {
        int max = Math.max(m2, n2), ans = 0;
        //原节点置黑
        clone[x0][y0] = 'X';
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x0, y0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            //遍历8个方向
            for (int[] d : dir) {
                int x = p[0] + d[0], y = p[1] + d[1];
                if (!isValid(x, y, m2, n2) || clone[x][y] == '.') continue;
                int len = 0;
                for (int i = 0; i < max; i++) {
                    //顺着这个方向继续找黑色棋子
                    int nx = x + d[0] * i, ny = y + d[1] * i;
                    if (!isValid(nx, ny, m2, n2)) continue;
                    char c = clone[nx][ny];
                    //若该方向上有空白棋子，则直接中断退出
                    if (c == '.') {
                        break;
                        //找到则将黑色路径的长度更新
                    } else if (c == 'X') len = i + 1;
                }
                for (int i = 0; i < len; i++) {
                    int nx = x + d[0] * i, ny = y + d[1] * i;
                    if (!isValid(nx, ny, m2, n2)) continue;
                    char c = clone[nx][ny];
                    //如果路径上有白色棋子，则计数并加入队列
                    if (c == 'O') {
                        ans++;
                        queue.add(new int[]{nx, ny});
                        clone[nx][ny] = 'X';
                    }
                }
            }
        }
        return ans;
    }

    private boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
