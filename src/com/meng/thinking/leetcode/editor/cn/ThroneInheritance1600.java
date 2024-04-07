package com.meng.thinking.leetcode.editor.cn;
import java.util.*;
/**
 * 解答成功:
 * 	执行耗时:267 ms,击败了90.32% 的Java用户
 * 	内存消耗:104.8 MB,击败了29.03% 的Java用户
 */
class ThroneInheritance1600 {
    Map<String,List<String>> nodes;
    Set<String> dead;
    String king;
    public ThroneInheritance1600(String kingName) {
        nodes = new HashMap<>();
        dead = new HashSet<>();
        king = kingName;
    }
    
    public void birth(String parentName, String childName) {
        nodes.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }
    
    public void death(String name) {
        dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        dfs(ans, king);
        return ans;
    }

    private void dfs(List<String> ans, String king) {
        if (!dead.contains(king)){
            ans.add(king);
        }
        for (String child : nodes.getOrDefault(king, new ArrayList<>())){
            dfs(ans, child);
        }
    }
}

/**
 * 解答成功:
 * 	执行耗时:324 ms,击败了9.68% 的Java用户
 * 	内存消耗:102.4 MB,击败了48.39% 的Java用户
 */
class ThroneInheritanceOfficial {
    Map<String, List<String>> edges;
    Set<String> dead;
    String king;

    public ThroneInheritanceOfficial(String kingName) {
        edges = new HashMap<String, List<String>>();
        dead = new HashSet<String>();
        king = kingName;
    }

    public void birth(String parentName, String childName) {
        List<String> children = edges.getOrDefault(parentName, new ArrayList<String>());
        children.add(childName);
        edges.put(parentName, children);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<String>();
        preorder(ans, king);
        return ans;
    }

    private void preorder(List<String> ans, String name) {
        if (!dead.contains(name)) {
            ans.add(name);
        }
        List<String> children = edges.getOrDefault(name, new ArrayList<String>());
        for (String childName : children) {
            preorder(ans, childName);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
