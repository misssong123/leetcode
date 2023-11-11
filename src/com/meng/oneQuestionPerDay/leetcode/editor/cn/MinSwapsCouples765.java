package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class MinSwapsCouples765 {
    /**
     * 时间
     * 详情
     * 0ms
     * 击败 100.00%使用 Java 的用户
     * 内存
     * 详情
     * 37.91MB
     * 击败 56.51%使用 Java 的用户
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int[] index =  new int[row.length];
        for(int i = 0 ; i < row.length ; i++){
            index[row[i]] = i;
        }
        int res = 0;
        for(int i = 0 ; i < row.length ; i += 2){
            if ((row[i]^1) == row[i+1]){
                continue;
            }
            res++;
            row[index[row[i]^1]] = row[i+1];
            index[row[index[row[i]^1]]] =index[row[i]^1];
        }
        return res;
    }

    public static void main(String[] args) {
        MinSwapsCouples765 demo = new MinSwapsCouples765();
        System.out.println(demo.minSwapsCouples(new int[]{0,2,4,6,7,1,3,5}));
    }

    /**
     * 并查集
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.9 MB,击败了44.69% 的Java用户
     * @param row
     * @return
     */
    public int minSwapsCouples1(int[] row) {
        int len = row.length;
        int N = len / 2;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);
        }
        return N - unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }

    /**
     * 循环遍历
     * @param row
     * @return
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.8 MB,击败了58.12% 的Java用户
     */
    public int minSwapsCouples2(int[] row) {
        int N = row.length / 2;
        int[][] couples = new int[N][2];

        for (int i = 0; i < row.length; ++i)
            add(couples[row[i]/2], i/2 + 1);

        int[][] adj = new int[N+1][2];
        for (int[] couple: couples) {
            add(adj[couple[0]], couple[1]);
            add(adj[couple[1]], couple[0]);
        }

        int ans = N;
        for (int r = 1; r <= N; ++r) {
            if (adj[r][0] == 0 && adj[r][1] == 0)
                continue;
            ans--;
            int x = r, y = pop(adj[r]);
            while (y != r) {
                rem(adj[y], x);
                x = y;
                y = pop(adj[y]);
            }
        }
        return ans;
    }

    public void add(int[] pair, int x) {
        pair[pair[0] == 0 ? 0 : 1] = x;
    }

    public void rem(int[] pair, int x) {
        pair[pair[0] == x ? 0 : 1] = 0;
    }

    public int pop(int[] pair) {
        int x = pair[0];
        if (x != 0) {
            pair[0] = 0;
        } else {
            x = pair[1];
            pair[1] = 0;
        }
        return x;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.6 MB,击败了92.79% 的Java用户
     * @param row
     * @return
     */
    public int minSwapsCouples3(int[] row) {
        int ans = 0;
        for (int i = 0; i < row.length; i += 2) {
            int x = row[i];
            if (row[i+1] == (x ^ 1)) continue;
            ans++;
            for (int j = i+1; j < row.length; ++j) {
                if (row[j] == (x^1)) {
                    row[j] = row[i+1];
                    row[i+1] = x^1;
                    break;
                }
            }
        }
        return ans;
    }


}

