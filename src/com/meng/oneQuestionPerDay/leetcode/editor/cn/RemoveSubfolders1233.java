package com.meng.oneQuestionPerDay.leetcode.editor.cn;


import java.util.*;


class RemoveSubfolders1233 {
    /**
     * 执行用时：
     * 65 ms
     * , 在所有 Java 提交中击败了
     * 35.50%
     * 的用户
     * 内存消耗：
     * 49.9 MB
     * , 在所有 Java 提交中击败了
     * 97.04%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     * @param folder
     * @return
     */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        res.add(folder[0]);
        for(int i = 1 ; i < folder.length ; i++){
            String temp = res.get(res.size()-1);
            if (temp.equals("/")||temp.length()>folder[i].length() || !(folder[i].startsWith(temp+"/"))){
                res.add(folder[i]);
            }
        }
        return res;
    }



    public static void main(String[] args) {
        RemoveSubfolders1233 demo = new RemoveSubfolders1233();
        String[] folder = {"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(demo.removeSubfolders(folder));
        System.out.println(demo.removeSubfolders1(folder));
    }
    /**
     * 排序
     * @param folder
     * @return
     * 的用户
     * 内存消耗：
     * 50.2 MB
     * , 在所有 Java 提交中击败了
     * 89.94%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public List<String> removeSubfolders1(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<String>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; ++i) {
            int pre = ans.get(ans.size() - 1).length();
            if (!(pre < folder[i].length() && ans.get(ans.size() - 1).equals(folder[i].substring(0, pre)) && folder[i].charAt(pre) == '/')) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }

    /**
     * 字典树
     * @param folder
     * @return
     * 执行用时：
     * 111 ms
     * , 在所有 Java 提交中击败了
     * 12.43%
     * 的用户
     * 内存消耗：
     * 66.7 MB
     * , 在所有 Java 提交中击败了
     * 13.02%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public List<String> removeSubfolders2(String[] folder) {
        Trie1233 root = new Trie1233();
        for (int i = 0; i < folder.length; ++i) {
            List<String> path = split(folder[i]);
            Trie1233 cur = root;
            for (String name : path) {
                cur.children.putIfAbsent(name, new Trie1233());
                cur = cur.children.get(name);
            }
            cur.ref = i;
        }

        List<String> ans = new ArrayList<String>();
        dfs(folder, ans, root);
        return ans;
    }

    public List<String> split(String s) {
        List<String> ret = new ArrayList<String>();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '/') {
                ret.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(ch);
            }
        }
        ret.add(cur.toString());
        return ret;
    }

    public void dfs(String[] folder, List<String> ans, Trie1233 cur) {
        if (cur.ref != -1) {
            ans.add(folder[cur.ref]);
            return;
        }
        for (Trie1233 child : cur.children.values()) {
            dfs(folder, ans, child);
        }
    }
}

class Trie1233 {
    int ref;
    Map<String, Trie1233> children;

    public Trie1233() {
        ref = -1;
        children = new HashMap<String, Trie1233>();
    }
}

