package com.meng.DataStructureFundamentals.third03;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class Rotate {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 5.03%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] temp = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                temp[j][n-i-1] = matrix[i][j];
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                matrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * 方法一：使用辅助数组
     * 我们以题目中的示例二
     *
     * \begin{bmatrix} 5 & 1 & 9 & 11 \\ 2 & 4 & 8 & 10 \\ 13 & 3 & 6 & 7 \\ 15 & 14 & 12 & 16 \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 5
     * 2
     * 13
     * 15
     * ​
     *
     * 1
     * 4
     * 3
     * 14
     * ​
     *
     * 9
     * 8
     * 6
     * 12
     * ​
     *
     * 11
     * 10
     * 7
     * 16
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 作为例子，分析将图像旋转 90 度之后，这些数字出现在什么位置。
     *
     * 对于矩阵中的第一行而言，在旋转后，它出现在倒数第一列的位置：
     *
     * \begin{bmatrix} 5 & 1 & 9 & 11 \\ \circ & \circ & \circ & \circ \\ \circ & \circ & \circ & \circ \\ \circ & \circ & \circ & \circ \\ \end{bmatrix} \xRightarrow[]{旋转后} \begin{bmatrix} \circ & \circ & \circ & 5 \\ \circ & \circ & \circ & 1 \\ \circ & \circ & \circ & 9 \\ \circ & \circ & \circ & 11 \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 5
     * ∘
     * ∘
     * ∘
     * ​
     *
     * 1
     * ∘
     * ∘
     * ∘
     * ​
     *
     * 9
     * ∘
     * ∘
     * ∘
     * ​
     *
     * 11
     * ∘
     * ∘
     * ∘
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     * 旋转后
     * ​
     *
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * 5
     * 1
     * 9
     * 11
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 并且，第一行的第 xx 个元素在旋转后恰好是倒数第一列的第 xx 个元素。
     *
     * 对于矩阵中的第二行而言，在旋转后，它出现在倒数第二列的位置：
     *
     * \begin{bmatrix} \circ & \circ & \circ & \circ \\ 2 & 4 & 8 & 10 \\ \circ & \circ & \circ & \circ \\ \circ & \circ & \circ & \circ \end{bmatrix} \xRightarrow[]{旋转后} \begin{bmatrix} \circ & \circ & 2 & \circ \\ \circ & \circ & 4 & \circ \\ \circ & \circ & 8 & \circ \\ \circ & \circ & 10 & \circ \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * ∘
     * 2
     * ∘
     * ∘
     * ​
     *
     * ∘
     * 4
     * ∘
     * ∘
     * ​
     *
     * ∘
     * 8
     * ∘
     * ∘
     * ​
     *
     * ∘
     * 10
     * ∘
     * ∘
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     * 旋转后
     * ​
     *
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * 2
     * 4
     * 8
     * 10
     * ​
     *
     * ∘
     * ∘
     * ∘
     * ∘
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 对于矩阵中的第三行和第四行同理。这样我们可以得到规律：
     *
     * 对于矩阵中第 ii 行的第 jj 个元素，在旋转后，它出现在倒数第 ii 列的第 jj 个位置。
     *
     * 我们将其翻译成代码。由于矩阵中的行列从 00 开始计数，因此对于矩阵中的元素 \textit{matrix}[\textit{row}][\textit{col}]matrix[row][col]，在旋转后，它的新位置为 \textit{matrix}_\textit{new}[\textit{col}][n - \textit{row} - 1]matrix
     * new
     * ​
     *  [col][n−row−1]。
     *
     * 这样以来，我们使用一个与 \textit{matrix}matrix 大小相同的辅助数组 {matrix}_\textit{new}matrix
     * new
     * ​
     *  ，临时存储旋转后的结果。我们遍历 \textit{matrix}matrix 中的每一个元素，根据上述规则将该元素存放到 {matrix}_\textit{new}matrix
     * new
     * ​
     *   中对应的位置。在遍历完成之后，再将 {matrix}_\textit{new}matrix
     * new
     * ​
     *   中的结果复制到原数组中即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 5.03%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    /**
     * 方法二：原地旋转
     * 题目中要求我们尝试在不使用额外内存空间的情况下进行矩阵的旋转，也就是说，我们需要「原地旋转」这个矩阵。那么我们如何在方法一的基础上完成原地旋转呢？
     *
     * 我们观察方法一中的关键等式：
     *
     * \textit{matrix}_\textit{new}[\textit{col}][n - \textit{row} - 1] = \textit{matrix}[\textit{row}][\textit{col}]
     * matrix
     * new
     * ​
     *  [col][n−row−1]=matrix[row][col]
     *
     * 它阻止了我们进行原地旋转，这是因为如果我们直接将 \textit{matrix}[\textit{row}][\textit{col}]matrix[row][col] 放到原矩阵中的目标位置 \textit{matrix}[\textit{col}][n - \textit{row} - 1]matrix[col][n−row−1]：
     *
     * \textit{matrix}[\textit{col}][n - \textit{row} - 1] = \textit{matrix}[\textit{row}][\textit{col}]
     * matrix[col][n−row−1]=matrix[row][col]
     *
     * 原矩阵中的 \textit{matrix}[\textit{col}][n - \textit{row} - 1]matrix[col][n−row−1] 就被覆盖了！这并不是我们想要的结果。因此我们可以考虑用一个临时变量 \textit{temp}temp 暂存 \textit{matrix}[\textit{col}][n - \textit{row} - 1]matrix[col][n−row−1] 的值，这样虽然 \textit{matrix}[\textit{col}][n - \textit{row} - 1]matrix[col][n−row−1] 被覆盖了，我们还是可以通过 \textit{temp}temp 获取它原来的值：
     *
     * \left\{ \begin{alignedat}{2} &\textit{temp} &&= \textit{matrix}[\textit{col}][n - \textit{row} - 1]\\ &\textit{matrix}[\textit{col}][n - \textit{row} - 1] &&= \textit{matrix}[\textit{row}][\textit{col}] \end{alignedat} \right.
     * {
     * ​
     *
     * temp
     * matrix[col][n−row−1]
     * ​
     *
     * ​
     *
     * =matrix[col][n−row−1]
     * =matrix[row][col]
     * ​
     *
     *
     * 那么 \textit{matrix}[\textit{col}][n - \textit{row} - 1]matrix[col][n−row−1] 经过旋转操作之后会到哪个位置呢？我们还是使用方法一中的关键等式，不过这次，我们需要将
     *
     * \left\{ \begin{alignedat}{2} & \textit{row} &&= \textit{col} \\ & \textit{col} &&= n - \textit{row} - 1 \end{alignedat} \right.
     * {
     * ​
     *
     * row
     * col
     * ​
     *
     * ​
     *
     * =col
     * =n−row−1
     * ​
     *
     *
     * 带入关键等式，就可以得到：
     *
     * \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1] = \textit{matrix}[\textit{col}][n - \textit{row} - 1]
     * matrix[n−row−1][n−col−1]=matrix[col][n−row−1]
     *
     * 同样地，直接赋值会覆盖掉 \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]matrix[n−row−1][n−col−1] 原来的值，因此我们还是需要使用一个临时变量进行存储，不过这次，我们可以直接使用之前的临时变量 \textit{temp}temp：
     *
     * \left\{ \begin{alignedat}{2} &\textit{temp} &&= \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]\\ &\textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1] &&= \textit{matrix}[\textit{col}][n - \textit{row} - 1]\\ &\textit{matrix}[\textit{col}][n - \textit{row} - 1] &&= \textit{matrix}[\textit{row}][\textit{col}] \end{alignedat} \right.
     * ⎩
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * ​
     *
     * temp
     * matrix[n−row−1][n−col−1]
     * matrix[col][n−row−1]
     * ​
     *
     * ​
     *
     * =matrix[n−row−1][n−col−1]
     * =matrix[col][n−row−1]
     * =matrix[row][col]
     * ​
     *
     *
     * 我们再重复一次之前的操作，\textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]matrix[n−row−1][n−col−1] 经过旋转操作之后会到哪个位置呢？
     *
     * \left\{ \begin{alignedat}{2} & \textit{row} &&= n - \textit{row} - 1\\ & \textit{col} &&= n - \textit{col} - 1 \end{alignedat} \right.
     * {
     * ​
     *
     * row
     * col
     * ​
     *
     * ​
     *
     * =n−row−1
     * =n−col−1
     * ​
     *
     *
     * 带入关键等式，就可以得到：
     *
     * \textit{matrix}[n - \textit{col} - 1][\textit{row}] = \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]
     * matrix[n−col−1][row]=matrix[n−row−1][n−col−1]
     *
     * 写进去：
     *
     * \left\{ \begin{alignedat}{2} &\textit{temp} &&= \textit{matrix}[n - \textit{col} - 1][\textit{row}]\\ &\textit{matrix}[n - \textit{col} - 1][\textit{row}] &&= \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]\\ &\textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1] &&= \textit{matrix}[\textit{col}][n - \textit{row} - 1]\\ &\textit{matrix}[\textit{col}][n - \textit{row} - 1] &&= \textit{matrix}[\textit{row}][\textit{col}] \end{alignedat} \right.
     * ⎩
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * ​
     *
     * temp
     * matrix[n−col−1][row]
     * matrix[n−row−1][n−col−1]
     * matrix[col][n−row−1]
     * ​
     *
     * ​
     *
     * =matrix[n−col−1][row]
     * =matrix[n−row−1][n−col−1]
     * =matrix[col][n−row−1]
     * =matrix[row][col]
     * ​
     *
     *
     * 不要灰心，再来一次！\textit{matrix}[n - \textit{col} - 1][\textit{row}]matrix[n−col−1][row] 经过旋转操作之后回到哪个位置呢？
     *
     * \left\{ \begin{alignedat}{2} & \textit{row} &&= n - \textit{col} - 1\\ & \textit{col} &&= \textit{row} \end{alignedat} \right.
     * {
     * ​
     *
     * row
     * col
     * ​
     *
     * ​
     *
     * =n−col−1
     * =row
     * ​
     *
     *
     * 带入关键等式，就可以得到：
     *
     * \textit{matrix}[\textit{row}][\textit{col}] = \textit{matrix}[n - \textit{col} - 1][\textit{row}]
     * matrix[row][col]=matrix[n−col−1][row]
     *
     * 我们回到了最初的起点 \textit{matrix}[\textit{row}][\textit{col}]matrix[row][col]，也就是说：
     *
     * \begin{cases} \textit{matrix}[\textit{row}][\textit{col}]\\ \textit{matrix}[\textit{col}][n - \textit{row} - 1]\\ \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]\\ \textit{matrix}[n - \textit{col} - 1][\textit{row}] \end{cases}
     * ⎩
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * matrix[row][col]
     * matrix[col][n−row−1]
     * matrix[n−row−1][n−col−1]
     * matrix[n−col−1][row]
     * ​
     *
     *
     * 这四项处于一个循环中，并且每一项旋转后的位置就是下一项所在的位置！因此我们可以使用一个临时变量 \textit{temp}temp 完成这四项的原地交换：
     *
     * \left\{ \begin{alignedat}{2} &\textit{temp} &&= \textit{matrix}[\textit{row}][\textit{col}]\\ &\textit{matrix}[\textit{row}][\textit{col}] &&= \textit{matrix}[n - \textit{col} - 1][\textit{row}]\\ &\textit{matrix}[n - \textit{col} - 1][\textit{row}] &&= \textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1]\\ &\textit{matrix}[n - \textit{row} - 1][n - \textit{col} - 1] &&= \textit{matrix}[\textit{col}][n - \textit{row} - 1]\\ &\textit{matrix}[\textit{col}][n - \textit{row} - 1] &&= \textit{temp} \end{alignedat} \right.
     * ⎩
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * ​
     *
     * temp
     * matrix[row][col]
     * matrix[n−col−1][row]
     * matrix[n−row−1][n−col−1]
     * matrix[col][n−row−1]
     * ​
     *
     * ​
     *
     * =matrix[row][col]
     * =matrix[n−col−1][row]
     * =matrix[n−row−1][n−col−1]
     * =matrix[col][n−row−1]
     * =temp
     * ​
     *
     *
     * 当我们知道了如何原地旋转矩阵之后，还有一个重要的问题在于：我们应该枚举哪些位置 (\textit{row}, \textit{col})(row,col) 进行上述的原地交换操作呢？由于每一次原地交换四个位置，因此：
     *
     * 当 nn 为偶数时，我们需要枚举 n^2 / 4 = (n/2) \times (n/2)n
     * 2
     *  /4=(n/2)×(n/2) 个位置，可以将该图形分为四块，以 4 \times 44×4 的矩阵为例：
     *
     *
     * 保证了不重复、不遗漏；
     *
     * 当 nn 为奇数时，由于中心的位置经过旋转后位置不变，我们需要枚举 (n^2-1) / 4 = ((n-1)/2) \times ((n+1)/2)(n
     * 2
     *  −1)/4=((n−1)/2)×((n+1)/2) 个位置，需要换一种划分的方式，以 5 \times 55×5 的矩阵为例：
     *
     *
     * 同样保证了不重复、不遗漏，矩阵正中央的点无需旋转。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.3 MB
     * , 在所有 Java 提交中击败了
     * 18.77%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 方法三：用翻转代替旋转
     * 我们还可以另辟蹊径，用翻转操作代替旋转操作。我们还是以题目中的示例二
     *
     * \begin{bmatrix} 5 & 1 & 9 & 11 \\ 2 & 4 & 8 & 10 \\ 13 & 3 & 6 & 7 \\ 15 & 14 & 12 & 16 \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 5
     * 2
     * 13
     * 15
     * ​
     *
     * 1
     * 4
     * 3
     * 14
     * ​
     *
     * 9
     * 8
     * 6
     * 12
     * ​
     *
     * 11
     * 10
     * 7
     * 16
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 作为例子，先将其通过水平轴翻转得到：
     *
     * \begin{bmatrix} 5 & 1 & 9 & 11 \\ 2 & 4 & 8 & 10 \\ 13 & 3 & 6 & 7 \\ 15 & 14 & 12 & 16 \end{bmatrix} \xRightarrow[]{水平翻转} \begin{bmatrix} 15 & 14 & 12 & 16 \\ 13 & 3 & 6 & 7 \\ 2 & 4 & 8 & 10 \\ 5 & 1 & 9 & 11 \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 5
     * 2
     * 13
     * 15
     * ​
     *
     * 1
     * 4
     * 3
     * 14
     * ​
     *
     * 9
     * 8
     * 6
     * 12
     * ​
     *
     * 11
     * 10
     * 7
     * 16
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     * 水平翻转
     * ​
     *
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 15
     * 13
     * 2
     * 5
     * ​
     *
     * 14
     * 3
     * 4
     * 1
     * ​
     *
     * 12
     * 6
     * 8
     * 9
     * ​
     *
     * 16
     * 7
     * 10
     * 11
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 再根据主对角线翻转得到：
     *
     * \begin{bmatrix} 15 & 14 & 12 & 16 \\ 13 & 3 & 6 & 7 \\ 2 & 4 & 8 & 10 \\ 5 & 1 & 9 & 11 \end{bmatrix} \xRightarrow[]{主对角线翻转} \begin{bmatrix} 15 & 13 & 2 & 5 \\ 14 & 3 & 4 & 1 \\ 12 & 6 & 8 & 9 \\ 16 & 7 & 10 & 11 \end{bmatrix}
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 15
     * 13
     * 2
     * 5
     * ​
     *
     * 14
     * 3
     * 4
     * 1
     * ​
     *
     * 12
     * 6
     * 8
     * 9
     * ​
     *
     * 16
     * 7
     * 10
     * 11
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     * 主对角线翻转
     * ​
     *
     * ⎣
     * ⎢
     * ⎢
     * ⎢
     * ⎡
     * ​
     *
     * 15
     * 14
     * 12
     * 16
     * ​
     *
     * 13
     * 3
     * 6
     * 7
     * ​
     *
     * 2
     * 4
     * 8
     * 10
     * ​
     *
     * 5
     * 1
     * 9
     * 11
     * ​
     *
     * ⎦
     * ⎥
     * ⎥
     * ⎥
     * ⎤
     * ​
     *
     *
     * 就得到了答案。这是为什么呢？对于水平轴翻转而言，我们只需要枚举矩阵上半部分的元素，和下半部分的元素进行交换，即
     *
     * \textit{matrix}[\textit{row}][\textit{col}] \xRightarrow[]{水平轴翻转} \textit{matrix}[n - \textit{row} - 1][\textit{col}]
     * matrix[row][col]
     * 水平轴翻转
     * ​
     *  matrix[n−row−1][col]
     *
     * 对于主对角线翻转而言，我们只需要枚举对角线左侧的元素，和右侧的元素进行交换，即
     *
     * \textit{matrix}[\textit{row}][\textit{col}] \xRightarrow[]{主对角线翻转} \textit{matrix}[\textit{col}][\textit{row}]
     * matrix[row][col]
     * 主对角线翻转
     * ​
     *  matrix[col][row]
     *
     * 将它们联立即可得到：
     *
     * \begin{aligned} \textit{matrix}[\textit{row}][\textit{col}] & \xRightarrow[]{水平轴翻转} \textit{matrix}[n - \textit{row} - 1][\textit{col}] \\ &\xRightarrow[]{主对角线翻转} \textit{matrix}[\textit{col}][n - \textit{row} - 1] \end{aligned}
     * matrix[row][col]
     * ​
     *
     * 水平轴翻转
     * ​
     *  matrix[n−row−1][col]
     * 主对角线翻转
     * ​
     *  matrix[col][n−row−1]
     * ​
     *
     *
     * 和方法一、方法二中的关键等式：
     *
     * \textit{matrix}_\textit{new}[\textit{col}][n - \textit{row} - 1] = \textit{matrix}[\textit{row}][\textit{col}]
     * matrix
     * new
     * ​
     *  [col][n−row−1]=matrix[row][col]
     *
     * 是一致的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 44.67%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
