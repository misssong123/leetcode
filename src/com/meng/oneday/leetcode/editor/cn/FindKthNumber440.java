package com.meng.oneday.leetcode.editor.cn;

class FindKthNumber440 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.8 MB,击败了13.39% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber440(int n, int k) {
       int node = 1;
       k--;
       while(k > 0){
           //计算当前节点及其子树的节点数
           int size = getNum(n,node);
           if (size <= k){
               node++;
               k -= size;
           }else {
               node *= 10;
               k--;
           }
       }
       return node;
    }

    private int getNum(int n, int node) {
        int size = 0;
        long left = node, right = node + 1;
        while (left <= n){
            size += Math.min(right,n+1) - left;
            left *= 10;
            right *= 10;
        }
        return size;
    }


    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了98.91% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int findKthNumberOther1(int n, int k) {
        int node = 1;
        k--; // 访问节点 node
        while (k > 0) {
            int size = countSubtreeSize(n, node);
            if (size <= k) { // 向右，跳过 node 子树
                node++; // 访问 node 右侧兄弟节点
                k -= size; // 访问子树中的每个节点，以及新的 node 节点
            } else { // 向下，深入 node 子树
                node *= 10; // 访问 node 的第一个儿子
                k--; // 访问新的 node 节点
            }
        }
        return node;
    }

    // 逐层统计 node 子树大小
    private int countSubtreeSize(int n, int node) {
        // 子树大小不会超过 n，所以 size 用 int 类型
        // 但计算过程中的 left 和 right 会超过 int，所以用 long 类型
        int size = 0;
        long left = node;
        long right = node + 1;
        while (left <= n) {
            // 这一层的最小值是 left，最大值是 min(right, n + 1) - 1
            size += Math.min(right, n + 1) - left;
            left *= 10; // 继续，计算下一层
            right *= 10;
        }
        return size;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.5 MB,击败了63.93% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public int findKthNumberOther2(int n, int k) {
        int pow10 = 1; // 直接跳到子树 1 最后一层，left 和 right 需要乘以 pow10
        for (int x = n / 10; x > 0; x /= 10) {
            pow10 *= 10;
        }

        int node = 1;
        k--; // 访问节点 node
        while (k > 0) {
            int size = countSubtreeSize(node, n, pow10);
            if (size <= k) { // 向右，跳过 node 子树
                node++; // 访问 node 右侧兄弟节点
                k -= size; // 访问子树中的每个节点，以及新的 node 节点
            } else { // 向下，深入 node 子树
                pow10 /= 10; // 子树层数减一
                node *= 10; // 访问 node 的第一个儿子
                k--; // 访问新的 node 节点
            }
        }
        return node;
    }

    // 统计 node 子树大小，O(1)
    private int countSubtreeSize(int node, int n, int pow10) {
        int size = (pow10 - 1) / 9; // 1+10+100+...
        long left = (long) node * pow10;
        long right = (long) (node + 1) * pow10;
        if (left <= n) {
            size += Math.min(right, n + 1) - left; // 余项
        }
        return size;
    }

}
