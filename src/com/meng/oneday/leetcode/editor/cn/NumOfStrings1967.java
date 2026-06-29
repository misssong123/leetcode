package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

class NumOfStrings1967 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.7 MB,击败了60.00% 的Java用户
     * @param patterns
     * @param word
     * @return
     */
    public int numOfStrings1967(String[] patterns, String word) {
        int res = 0;
        for (String str : patterns) {
            if(word.contains(str)){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:13 ms,击败了16.67% 的Java用户
     * 	内存消耗:46.7 MB,击败了6.67% 的Java用户
     * @param patterns
     * @param word
     * @return
     */
    public int numOfStrings(String[] patterns, String word) {
        AhoCorasick ac = new AhoCorasick();
        for (String pattern : patterns) {
            ac.put(pattern);
        }
        ac.buildFail();

        Node1967 cur = ac.root;
        int ans = 0;
        for (char ch : word.toCharArray()) {
            cur = cur.son[ch - 'a']; // 如果没有匹配，相当于移动到 fail 的 son[ch-'a']
            // 可能匹配更短的模式串，要继续在 last 链上找
            for (Node1967 match = cur; match.cnt >= 0; match = match.last) {
                ans += match.cnt;
                match.cnt = -1; // 避免重复统计
            }
        }
        return ans;
    }

}
class Node1967 {
    Node1967[] son = new Node1967[26];
    Node1967 fail; // 当 node.son[i] 失配时，node.fail.son[i] 即为下一个待匹配节点（等于 root 则表示没有匹配）
    Node1967 last; // 后缀链接（suffix link），用来快速跳到一定是某个模式串末尾的节点（等于 root 则表示匹配结束）
    int cnt; // node 是 cnt 个模式串的末尾
}
class AhoCorasick {
    Node1967 root = new Node1967();

    // 把模式串 pattern 插入 AC 自动机（代码和字典树一样）
    void put(String pattern) {
        Node1967 cur = root;
        for (char ch : pattern.toCharArray()) {
            ch -= 'a';
            if (cur.son[ch] == null) {
                cur.son[ch] = new Node1967();
            }
            cur = cur.son[ch];
        }
        cur.cnt++;
    }

    // BFS，构建 AC 自动机的 fail 和 last，方便快速查询
    void buildFail() {
        root.fail = root.last = root;

        Queue<Node1967> q = new ArrayDeque<>();
        for (int i = 0; i < root.son.length; i++) {
            Node1967 son = root.son[i];
            if (son == null) {
                root.son[i] = root;
                continue;
            }
            son.fail = son.last = root; // 第一层的 fail 都指向根节点
            q.add(son);
        }

        // BFS
        while (!q.isEmpty()) {
            Node1967 cur = q.poll();
            for (int i = 0; i < 26; i++) {
                Node1967 son = cur.son[i];
                if (son == null) {
                    // 把虚拟子节点 cur.son[i] 设置为 cur.fail.son[i]
                    // 方便失配时直接跳到下一个可能匹配的位置（但不一定是某个模式串的末尾）
                    cur.son[i] = cur.fail.son[i];
                    continue;
                }
                son.fail = cur.fail.son[i]; // 计算失配位置
                // 沿着 last 往上走，可以直接跳到一定是某个模式串末尾的节点（如果跳到 root 表示匹配结束）
                son.last = son.fail.cnt > 0 ? son.fail : son.fail.last;
                q.add(son);
            }
        }
    }
}

