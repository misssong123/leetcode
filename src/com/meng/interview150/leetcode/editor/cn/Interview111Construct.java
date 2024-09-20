package com.meng.interview150.leetcode.editor.cn;

class Interview111Construct {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了44.37% 的Java用户
     * 	内存消耗:43.2 MB,击败了84.56% 的Java用户
     * @param grid
     * @return
     */
    public Node427 construct(int[][] grid) {
        if (isEqual(grid, 0, 0, grid.length - 1, grid.length - 1,grid[0][0])) {
            return new Node427(grid[0][0] == 1, true);
        }
        int n = grid.length;
        return dfs(grid,0,0,n-1,n-1,n);
    }

    private Node427 dfs(int[][] grid, int x1, int y1, int x2, int y2, int n) {
        if(n == 1){
            return new Node427(grid[x1][y1]>0,true);
        }
        Node427 node = new Node427(true,false);
        //中间节点
        int middle = n / 2;
        int num;
        //左上
        num = grid[x1][y1];
        if (isEqual(grid,x1,y1,x1+middle-1,y1+middle-1,num)){
            node.topLeft = new Node427(num > 0,true);
        }else {
            node.topLeft = dfs(grid,x1,y1,x1+middle-1,y1+middle-1,middle);
        }
        //右上
        num = grid[x1][y1+middle];
        if (isEqual(grid,x1,y1+middle,x1+middle-1,y2,num)){
            node.topRight = new Node427(num > 0,true);
        }else {
            node.topRight = dfs(grid,x1,y1+middle,x1+middle-1,y2,middle);
        }
        //左下
        num = grid[x1+middle][y1];
        if (isEqual(grid,x1+middle,y1,x2,y1+middle-1,num)){
            node.bottomLeft = new Node427(num > 0,true);
        }else {
            node.bottomLeft = dfs(grid,x1+middle,y1,x2,y1+middle-1,middle);
        }
        //右下
        num = grid[x1+middle][y1+middle];
        if (isEqual(grid,x1+middle,y1+middle,x2,y2,num)){
            node.bottomRight = new Node427(num > 0,true);
        }else {
            node.bottomRight = dfs(grid,x1+middle,y1+middle,x2,y2,middle);
        }
        return node;
    }
    private boolean isEqual(int[][] grid,int x1,int y1,int x2,int y2,int target){
        for(int x = x1 ; x <= x2; x++){
            for (int y = y1; y <= y2; y++) {
                if (grid[x][y] != target) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了52.82% 的Java用户
     * @param grid
     * @return
     */
    public Node427 construct1(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    public Node427 dfs(int[][] grid, int r0, int c0, int r1, int c1) {
        boolean same = true;
        for (int i = r0; i < r1; ++i) {
            for (int j = c0; j < c1; ++j) {
                if (grid[i][j] != grid[r0][c0]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }

        if (same) {
            return new Node427(grid[r0][c0] == 1, true);
        }

        Node427 ret = new Node427(
                true,
                false,
                dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了20.82% 的Java用户
     * 	内存消耗:43.3 MB,击败了71.67% 的Java用户
     * @param grid
     * @return
     */
    public Node427 construct2(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs(grid, pre, 0, 0, n, n);
    }

    public Node427 dfs(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        if (total == 0) {
            return new Node427(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new Node427(true, true);
        }

        Node427 ret = new Node427(
                true,
                false,
                dfs(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
                dfs(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
                dfs(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
                dfs(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }

    public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
    }
}
