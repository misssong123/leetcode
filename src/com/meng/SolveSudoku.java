package com.meng;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 *     数字 1-9 在每一行只能出现一次。
 *     数字 1-9 在每一列只能出现一次。
 *     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 空白格用 '.' 表示。
 *
 *     给定的数独序列只包含数字 1-9 和字符 '.' 。
 *     你可以假设给定的数独只有唯一解。
 *     给定数独永远是 9x9 形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 主要思路,
 * 1.降维
 * 2.按照八皇后进行分析
 */
public class SolveSudoku {
    //分别存放行，列，区域块中的存放情况
    private boolean[][] rows = new boolean[9][9];
    private boolean[][] columns = new boolean[9][9];
    private boolean[][] ranges = new boolean[9][9];
    //存放值为'.'的下标
    private List<int[]> list = new ArrayList<>();
    //记录是否找到
    boolean flag = false;
    public void solveSudoku(char[][] board) {
        for(int i = 0 ;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                char temp = board[i][j];
                if (temp == '.'){
                    list.add(new int[]{i,j});
                }else {
                    int index = i/3*3 + j/3;
                    int n = temp-'0'-1;
                    rows[i][n]=true;
                    columns[j][n]=true;
                    ranges[index][n]=true;
                }
            }
        }
        if (list.size()>0)
            solve(board,0,list.size());
    }

    private void solve(char[][] board,int index, int size) {
        //表示已经找到
        if (index == size){
            flag =true;
            return;
        }
        int [] temp = list.get(index);
        int i=temp[0],j=temp[1];
        for(int n=0;n<9;n++){
            if (!flag && check(i,j,n)){
                //将下标设置为true,并在原数组中填入该值
                rows[i][n] = true;
                columns[j][n] =true;
                ranges[i/3*3+j/3][n]=true;
                board[i][j]=(char)('0'+n+1);
                //继续进行下一个
                solve(board,index+1,size);
                //表示当前元素不符合
                rows[i][n] = false;
                columns[j][n] = false;
                ranges[i/3*3+j/3][n] = false;
            }
        }
    }

    /**
     *
     * @param i 当前行下标
     * @param j 当前列下标
     * @param n 当前待填的数字
     * @return
     */
    private boolean check(int i, int j, int n) {
        int index = i/3*3 +j/3;
        if (rows[i][n] || columns[j][n] || ranges[index][n])
            return false;
        return true;
    }
}

/**
 * 官方解法一
 * 方法一：递归
 *
 * 思路
 *
 * 最容易想到的方法是用一个数组记录每个数字是否出现。由于我们可以填写的数字范围为 [1,9][1, 9][1,9]，而数组的下标从 000 开始，因此在存储时，我们使用一个长度为 999 的布尔类型的数组，其中 iii 个元素的值为 True\text{True}True，当且仅当数字 i+1i+1i+1 出现过。例如我们用 line[2][3]=True\textit{line}[2][3] = \text{True}line[2][3]=True 表示数字 444 在第 222 行已经出现过，那么当我们在遍历到第 222 行的空白格时，就不能填入数字 444。
 *
 * 算法
 *
 * 我们首先对整个数独数组进行遍历，当我们遍历到第 iii 行第 jjj 列的位置：
 *
 *     如果该位置是一个空白格，那么我们将其加入一个用来存储空白格位置的列表中，方便后续的递归操作；
 *
 *     如果该位置是一个数字 xxx，那么我们需要将 line[i][x−1]\textit{line}[i][x-1]line[i][x−1]，column[j][x−1]\textit{column}[j][x-1]column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1]\textit{block}[\lfloor i/3 \rfloor][\lfloor j/3 \rfloor][x-1]block[⌊i/3⌋][⌊j/3⌋][x−1] 均置为 True\text{True}True。
 *
 * 当我们结束了遍历过程之后，就可以开始递归枚举。当递归到第 iii 行第 jjj 列的位置时，我们枚举填入的数字 xxx。根据题目的要求，数字 xxx 不能和当前行、列、九宫格中已经填入的数字相同，因此 line[i][x−1]\textit{line}[i][x-1]line[i][x−1]，column[j][x−1]\textit{column}[j][x-1]column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1]\textit{block}[\lfloor i/3 \rfloor][\lfloor j/3 \rfloor][x-1]block[⌊i/3⌋][⌊j/3⌋][x−1] 必须均为 False\text{False}False。
 *
 * 当我们填入了数字 xxx 之后，我们要将上述的三个值都置为 True\text{True}True，并且继续对下一个空白格位置进行递归。在回溯到当前递归层时，我们还要将上述的三个值重新置为 False\text{False}False。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class SolveSudokuSolution1 {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}

/**
 * 官方解法二
 * 方法二：位运算优化
 *
 * 思路与算法
 *
 * 在方法一中，我们使用了长度为 999 的数组表示每个数字是否出现过。我们同样也可以借助位运算，仅使用一个整数表示每个数字是否出现过。
 *
 * 具体地，数 bbb 的二进制表示的第 iii 位（从低到高，最低位为第 000 位）为 111，当且仅当数字 i+1i+1i+1 已经出现过。例如当 bbb 的二进制表示为 (011000100)2(011000100)_2(011000100)2​ 时，就表示数字 333，777，888 已经出现过。
 *
 * 位运算有一些基础的使用技巧。下面列举了所有在代码中使用到的技巧：
 *
 *     对于第 iii 行第 jjj 列的位置，line[i] ∣ column[j] ∣ block[⌊i/3⌋][⌊j/3⌋]\textit{line}[i] ~|~ \textit{column}[j] ~|~ \textit{block}[\lfloor i/3 \rfloor][\lfloor j/3 \rfloor]line[i] ∣ column[j] ∣ block[⌊i/3⌋][⌊j/3⌋] 中第 kkk 位为 111，表示该位置不能填入数字 k+1k+1k+1（因为已经出现过），其中 ∣|∣ 表示按位或运算。如果我们对这个值进行 ∼\sim∼ 按位取反运算，那么第 kkk 位为 111 就表示该位置可以填入数字 k+1k+1k+1，我们就可以通过寻找 111 来进行枚举。由于在进行按位取反运算后，这个数的高位也全部变成了 111，而这是我们不应当枚举到的，因此我们需要将这个数和 (111111111)2=(1FF)16(111111111)_2 = (\text{1FF})_{16}(111111111)2​=(1FF)16​ 进行按位与运算 &\&&，将所有无关的位置为 000；
 *
 *     我们可以使用按位异或运算 ∧\wedge∧，将第 iii 位从 000 变为 111，或从 111 变为 000。具体地，与数 1<<i1 << i1<<i 进行按位异或运算即可，其中 <<<<<< 表示左移运算；
 *
 *     我们可以用 b & (−b)b ~\&~ (-b)b & (−b) 得到 bbb 二进制表示中最低位的 111，这是因为 (−b)(-b)(−b) 在计算机中以补码的形式存储，它等于 ∼b+1\sim b + 1∼b+1。bbb 如果和 ∼b\sim b∼b 进行按位与运算，那么会得到 000，但是当 ∼b\sim b∼b 增加 111 之后，最低位的连续的 111 都变为 000，而最低位的 000 变为 111，对应到 bbb 中即为最低位的 111，因此当 bbb 和 ∼b+1\sim b + 1∼b+1 进行按位与运算时，只有最低位的 111 会被保留；
 *
 *     当我们得到这个最低位的 111 时，我们可以通过一些语言自带的函数得到这个最低位的 111 究竟是第几位（即 iii 值），具体可以参考下面的代码；
 *
 *     我们可以用 bbb 和最低位的 111 进行按位异或运算，就可以将其从 bbb 中去除，这样就可以枚举下一个 111。同样地，我们也可以用 bbb 和 b−1b-1b−1 进行按位与运算达到相同的效果，读者可以自行尝试推导。
 *
 * 实际上，方法二中整体的递归 + 回溯的框架与方法一是一致的。不同的仅仅是我们将一个数组「压缩」成了一个数而已。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class SolveSudokuSolution2 {
    private int[] line = new int[9];
    private int[] column = new int[9];
    private int[][] block = new int[3][3];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        for (; mask != 0 && !valid; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs(board, pos + 1);
            flip(i, j, digit);
        }
    }

    public void flip(int i, int j, int digit) {
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }
}
/**
 * 官方解法三
 * 方法三：枚举优化
 *
 * 思路与算法
 *
 * 我们可以顺着方法二的思路继续优化下去：
 *
 *     如果一个空白格只有唯一的数可以填入，也就是其对应的 bbb 值和 b−1b-1b−1 进行按位与运算后得到 000（即 bbb 中只有一个二进制位为 111）。此时，我们就可以确定这个空白格填入的数，而不用等到递归时再去处理它。
 *
 * 这样一来，我们可以不断地对整个数独进行遍历，将可以唯一确定的空白格全部填入对应的数。随后我们再使用与方法二相同的方法对剩余无法唯一确定的空白格进行递归 + 回溯。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class SolveSudokuSolution3{
    class Solution {
        private int[] line = new int[9];
        private int[] column = new int[9];
        private int[][] block = new int[3][3];
        private boolean valid = false;
        private List<int[]> spaces = new ArrayList<int[]>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] != '.') {
                        int digit = board[i][j] - '0' - 1;
                        flip(i, j, digit);
                    }
                }
            }

            while (true) {
                boolean modified = false;
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if (board[i][j] == '.') {
                            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                            if ((mask & (mask - 1)) == 0) {
                                int digit = Integer.bitCount(mask - 1);
                                flip(i, j, digit);
                                board[i][j] = (char) (digit + '0' + 1);
                                modified = true;
                            }
                        }
                    }
                }
                if (!modified) {
                    break;
                }
            }

            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        spaces.add(new int[]{i, j});
                    }
                }
            }

            dfs(board, 0);
        }

        public void dfs(char[][] board, int pos) {
            if (pos == spaces.size()) {
                valid = true;
                return;
            }

            int[] space = spaces.get(pos);
            int i = space[0], j = space[1];
            int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
            for (; mask != 0 && !valid; mask &= (mask - 1)) {
                int digitMask = mask & (-mask);
                int digit = Integer.bitCount(digitMask - 1);
                flip(i, j, digit);
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                flip(i, j, digit);
            }
        }

        public void flip(int i, int j, int digit) {
            line[i] ^= (1 << digit);
            column[j] ^= (1 << digit);
            block[i / 3][j / 3] ^= (1 << digit);
        }
    }
}