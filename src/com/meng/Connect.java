package com.meng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Connect {
    /**
     * 其他类中已经定义过该类，故将该工具类定义为内部类
     */
    class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 层次遍历
     * 思路
     * 1.创建form和to集合来保存获取到的值
     * 2.将form的元素依次取出，并将该元素的左节点和右节点放入到to集合中
     * 3.form取出的next即为form现存的第一个元素
     * 4.若form为空时,交换form和to的地址
     * 5.直到form中不存在元素为止
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root != null){
            //存放值的集合
            List<Node> form = new ArrayList<>();
            //交换集合
            List<Node> to = new ArrayList<>();
            //临时中间变量
            List<Node> temp = new ArrayList<>();
            form.add(root);
            while (!form.isEmpty()){
                Node node = form.remove(0);
                if (node.left != null)
                    to.add(node.left);
                if (node.right != null)
                    to.add(node.right);
                if (form.size()==0){
                    temp = form;
                    form = to;
                    to = temp;
                    continue;
                }
                node.next = form.get(0);
            }
        }

        return root;
    }
    /**
     * 递归
     * 该节点的左节点指向该节点的右节点
     * 该节点的右节点指向该节点next的左节点
     * 依次递归循环即可
     */
    public Node connectRecursion(Node root) {
        if (root != null)
            connect(root,null);
        return root;
    }

    private void connect(Node node, Node next) {
        if (node == null)
            return;
        node.next = next;
        connect(node.left,node.right);
        Node t= node.next == null ? null : node.next.left;
        connect(node.right,t);
    }

    /**
     * 官方解法1
     *方法一：层次遍历
     *
     * 思路与算法
     *
     * 题目本身希望我们将二叉树的每一层节点都连接起来形成一个链表。因此直观的做法我们可以对二叉树进行层次遍历，在层次遍历的过程中将我们将二叉树每一层的节点拿出来遍历并连接。
     *
     * 层次遍历基于广度优先搜索，它与广度优先搜索的不同之处在于，广度优先搜索每次只会取出一个节点来拓展，而层次遍历会每次将队列中的所有元素都拿出来拓展，这样能保证每次从队列中拿出来遍历的元素都是属于同一层的，因此我们可以在遍历的过程中修改每个节点的 next 指针，同时拓展下一层的新队列。
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            // 记录当前队列大小
            int size = queue.size();

            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                Node node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // 返回根节点
        return root;
    }
    /**
     * 官方解法2
     *方法二：使用已建立的 next\text{next}next 指针
     * @param root
     * @return
     */
        public Node connect2(Node root) {
            if (root == null) {
                return root;
            }

            // 从根节点开始
            Node leftmost = root;

            while (leftmost.left != null) {

                // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
                Node head = leftmost;

                while (head != null) {

                    // CONNECTION 1
                    head.left.next = head.right;

                    // CONNECTION 2
                    if (head.next != null) {
                        head.right.next = head.next.left;
                    }

                    // 指针向后移动
                    head = head.next;
                }

                // 去下一层的最左的节点
                leftmost = leftmost.left;
            }

            return root;
        }
}
