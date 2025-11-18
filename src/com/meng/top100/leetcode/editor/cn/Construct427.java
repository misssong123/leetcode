package com.meng.top100.leetcode.editor.cn;

class Construct427 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了45.71% 的Java用户
     * 	内存消耗:46.3 MB,击败了5.03% 的Java用户
     * @param grid
     * @return
     */
    public Node427 construct427(int[][] grid) {
        int n = grid.length;
        return dfs(grid,0,0,n);
    }

    private Node427 dfs(int[][] grid, int x, int y, int n) {
        if (n == 1){
            return new Node427(grid[x][y] == 1 ,true);
        }
        int len = n / 2;
        //左
        Node427 tL = dfs(grid,x,y,len);
        //右
        Node427 tR = dfs(grid,x,y+len,len);
        //下
        Node427 bL= dfs(grid,x+len,y,len);
        //下右
        Node427 bR = dfs(grid,x+len,y+len,len);
        if (tL.isLeaf && tR.isLeaf && bL.isLeaf && bR.isLeaf && tL.val == tR.val && tR.val == bL.val && bL.val == bR.val){
            return new Node427(tL.val,true);
        }
        return new Node427(false,false,tL,tR,bL,bR);
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了17.94% 的Java用户
     * 	内存消耗:45.5 MB,击败了15.08% 的Java用户
     * @param grid
     * @return
     */
    public Node427 construct(int[][] grid) {
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
 class Node427 {
        public boolean val;
        public boolean isLeaf;
        public Node427 topLeft;
        public Node427 topRight;
        public Node427 bottomLeft;
        public Node427 bottomRight;


        public Node427() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node427(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node427(boolean val, boolean isLeaf, Node427 topLeft, Node427 topRight, Node427 bottomLeft, Node427 bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
