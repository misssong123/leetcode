package com.meng.interview150.leetcode.editor.cn;


import java.util.*;

class Interview093CloneGraph {
    /**
     * 解答成功:
     * 	执行耗时:27 ms,击败了21.25% 的Java用户
     * 	内存消耗:42 MB,击败了5.08% 的Java用户
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node,new Node(node.val));
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            for(Node neighbor : cur.neighbors){
                //未创建
                if (!map.containsKey(neighbor)){
                    queue.offer(neighbor);
                    map.put(neighbor,new Node(neighbor.val));
                }
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
    private HashMap <Node, Node> visited = new HashMap <> ();

    /**
     * 解答成功:
     * 	执行耗时:25 ms,击败了91.27% 的Java用户
     * 	内存消耗:42 MB,击败了7.46% 的Java用户
     * @param node
     * @return
     */
    public Node cloneGraphOfficial1(Node node) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraphOfficial1(neighbor));
        }
        return cloneNode;
    }

    /**
     * 解答成功:
     * 	执行耗时:26 ms,击败了46.19% 的Java用户
     * 	内存消耗:41.8 MB,击败了52.39% 的Java用户
     * @param node
     * @return
     */
    public Node cloneGraphOfficial2(Node node) {
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
