package com.meng.hot100.leetcode.editor.cn;

import com.meng.top100.leetcode.editor.cn.dto.Node;

import java.util.*;

class CloneGraph_133 {
    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了91.83% 的Java用户
     * 	内存消耗:43.6 MB,击败了5.03% 的Java用户
     * @param node
     * @return
     */
    public Node cloneGraph133(Node node) {
        if (Objects.isNull(node)){
            return null;
        }
        Map<Integer, Node> map = new HashMap<>();
        Map<Integer, Node> oldMap = new HashMap<>();
        List<Node> list = new ArrayList<>();
        list.add(node);
        while (!list.isEmpty()){
            List<Node> temp = new ArrayList<>();
            for (Node n : list){
                if(!oldMap.containsKey(n.val)){
                    oldMap.put(n.val,n);
                    map.put(n.val,new Node(n.val));
                    for (Node ne : n.neighbors){
                        if (!oldMap.containsKey(ne.val)){
                            temp.add(ne);
                        }
                    }
                }
            }
            list = temp;
        }
        for (Node n : oldMap.values()){
            List<Node> nei = new ArrayList<>();
            for (Node ne : n.neighbors){
                nei.add(map.get(ne.val));
            }
            map.get(n.val).neighbors = nei;
        }
        return map.get(node.val);
    }

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了91.83% 的Java用户
     * 	内存消耗:43.5 MB,击败了5.67% 的Java用户
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        HashMap<Node, Node> visited = new HashMap();

        // 将题目给定的节点添加到队列
        LinkedList<Node> queue = new LinkedList<Node> ();
        queue.add(node);
        // 克隆第一个节点并存储到哈希表中
        visited.put(node, new Node(node.val, new ArrayList()));

        // 广度优先搜索
        while (!queue.isEmpty()) {
            // 取出队列的头节点
            Node n = queue.remove();
            // 遍历该节点的邻居
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // 如果没有被访问过，就克隆并存储在哈希表中
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
                    // 将邻居节点加入队列中
                    queue.add(neighbor);
                }
                // 更新当前节点的邻居列表
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}
