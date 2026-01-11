


class Solution {
    /**
     * 思路有误
     * @param matrix
     * @return
     */
    public int maximalRectangleError(char[][] matrix) {
        int m = matrix.length , n = matrix[0].length;
        int[][][] dp = new int[m][n][2];
        int res = 0;
        //初始化
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int val = matrix[i][j] - '0';
                if (val == 1){
                    if ( i > 0){
                        dp[i][j][0] = dp[i-1][j][0];
                    }
                    if (j > 0){
                        dp[i][j][1] = dp[i][j-1][1];
                    }
                    dp[i][j][0] += 1;
                    dp[i][j][1] += 1;
                    res = Math.max(res,Math.max(dp[i][j][1], dp[i][j][0]));
                }
            }
        }
        for(int i = 1 ; i < m ; i++){
            for(int j = 1 ; j < n ; j++){
                int val = matrix[i][j] - '0';
                if (val == 1&&dp[i][j][0] > 1 && dp[i][j][1] > 1){
                    int len = 2,tempI = i , tempJ = j;
                    while(dp[tempI-1][j][0] > 1&& dp[tempI-1][j][1] > 1 ){

                    }
                }
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:49.5 MB,击败了9.85% 的Java用户
     * @param matrix
     * @return
     */
    int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n + 1]; // 末尾多一个 0，理由见我 84 题题解
        int ans = 0;
        for (char[] row : matrix) {
            // 计算底边为 row 的柱子高度
            for (int j = 0; j < n; j++) {
                if (row[j] == '0') {
                    heights[j] = 0; // 柱子高度为 0
                } else {
                    heights[j]++; // 柱子高度加一
                }
            }
            ans = Math.max(ans, largestRectangleArea(heights)); // 调用 84 题代码
        }
        return ans;
    }

    // 84. 柱状图中最大的矩形
    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] st = new int[n]; // 用数组模拟栈
        int top = -1; // 栈顶下标
        st[++top] = -1; // 在栈中只有一个数的时候，栈顶的「下面那个数」是 -1，对应 left[i] = -1 的情况
        int ans = 0;
        for (int right = 0; right < n; right++) {
            int h = heights[right];
            while (top > 0 && heights[st[top]] >= h) {
                int i = st[top--]; // 矩形的高（的下标）
                int left = st[top]; // 栈顶下面那个数就是 left
                ans = Math.max(ans, heights[i] * (right - left - 1));
            }
            st[++top] = right;
        }
        return ans;
    }


}

