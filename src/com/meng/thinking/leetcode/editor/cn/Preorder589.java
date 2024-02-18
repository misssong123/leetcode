package com.meng.thinking.leetcode.editor.cn;

import com.meng.thinking.leetcode.editor.cn.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Preorder589 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:44 MB,击败了5.05% 的Java用户
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        dfs(root);
        return res;
    }

    private void dfs(Node root) {
        if (root == null){
            return;
        }
        res.add(root.val);
        if (Objects.nonNull(root.children)){
            for (Node node : root.children){
                dfs(node);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
