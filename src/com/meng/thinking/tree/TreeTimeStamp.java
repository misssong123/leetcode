package com.meng.thinking.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉树的时间戳
 * 二叉树进入的时间点和出去的时间点,便于统计一节点是否为另一个节点的子树夫节点
 */
public class TreeTimeStamp {
    static int[]xor, in, out;
    int clock;
    static List<Integer>[] g;
    public static void main(String[] args) {
    }

    /**
     * 树的出入度问题
     */
    private static void treeTime() {
        Tree node1 = new Tree(0);
        Tree node2 = new Tree(0);
        Tree node3 = new Tree(0);
        Tree node4 = new Tree(0);
        Tree node5 = new Tree(0);
        Tree node6 = new Tree(0);
        Tree node7 = new Tree(0);
        Tree node8 = new Tree(0);
        Tree node9 = new Tree(0);
        Tree node10 = new Tree(0);

    }
    /**
     * 数组构成的树的出入度问题
     */
    private static void arrayTime() {
        int[] nums = {0,1,2,3,4,5,6,7,8,9,10};
        int[][]edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{3,8},{6,9},{6,10}};
        int n = nums.length;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        TreeTimeStamp demo = new TreeTimeStamp();
        demo.dfs(0, -1);
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(out));
    }

    void dfs(int x, int fa) {
        in[x] = ++clock;
        for (int y : g[x])
            if (y != fa) {
                dfs(y, x);
            }
        out[x] = clock;
    }
}
