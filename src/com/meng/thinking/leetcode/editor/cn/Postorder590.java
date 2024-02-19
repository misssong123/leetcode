package com.meng.thinking.leetcode.editor.cn;
import java.util.ArrayList;
import java.util.List;
class Postorder590 {
    List<Integer> res = new ArrayList<>();

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.7 MB,击败了25.47% 的Java用户
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        dfs(root);
        return res;
    }

    private void dfs(Node root) {
        if(root == null){
            return;
        }
        if (!(root.children == null || root.children.size() == 0)){
            for (Node child : root.children){
                dfs(child);
            }
        }
        res.add(root.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
