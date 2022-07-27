package com.meng.thinking.tree;

import java.util.Arrays;

/**
 * 二叉树的常见方法
 */
public class TreeTest {
    int[] in = new int[11];
    int[] out = new int[11];
    int index = 0;
    int[] xor = new int[11];
    public static void main(String[] args) {
        Tree root = buildTree();
        TreeTest demo = new TreeTest();
        demo.preOrder(root);
        System.out.println();
       /* demo.midOrder(root);
        System.out.println();
        demo.sufOrder(root);*/
        /**
         * [1, 2, 7, 3, 6, 8, 9, 4, 5, 10, 11]
         * [11, 6, 11, 5, 6, 8, 11, 4, 5, 10, 11]
         */
        System.out.println(Arrays.toString(demo.in));
        System.out.println(Arrays.toString(demo.out));
        System.out.println(Arrays.toString(demo.xor));
    }

    /**
     *
     * @return
     */
    public static Tree buildTree(){
        Tree root = new Tree(0);
        Tree node2 = new Tree(1);
        Tree node3 = new Tree(2);
        Tree node4 = new Tree(3);
        Tree node5 = new Tree(4);
        Tree node6 = new Tree(5);
        Tree node7 = new Tree(6);
        Tree node8 = new Tree(7);
        Tree node9 = new Tree(8);
        Tree node10 = new Tree(9);
        Tree node11 = new Tree(10);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node7.left = node10;
        node7.right = node11;
        return root;
    }

    /**
     * 前序遍历
     * @param root
     */
    public void preOrder(Tree root){
        if (root == null){
            return;
        }
        xor[root.val] = root.val;
        index++;
        in[root.val] = index;
        preOrder(root.left);
        if (root.left != null){
            xor[root.val] ^= xor[root.left.val];
        }
        preOrder(root.right);
        if (root.right != null){
            xor[root.val] ^= xor[root.right.val];
        }
        out[root.val] = index;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void midOrder(Tree root){
        if (root == null){
            return;
        }
        midOrder(root.left);
        System.out.print(root.val+"-->");
        midOrder(root.right);
    }

    /**
     * 后续遍历
     * @param root
     */
    public void sufOrder(Tree root){
        if (root == null){
            return;
        }
        sufOrder(root.left);
        sufOrder(root.right);
        System.out.print(root.val+"-->");
    }
}
