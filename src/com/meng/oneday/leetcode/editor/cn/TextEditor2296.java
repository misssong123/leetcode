package com.meng.oneday.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:152 ms,击败了69.44% 的Java用户
 * 	内存消耗:95.2 MB,击败了13.89% 的Java用户
 */
class TextEditor2296 {
    MyNode head;
    MyNode tail;
    MyNode cursor;
    public TextEditor2296() {
        head = new MyNode('头');
        tail = new MyNode('尾');
        head.right = tail;
        tail.left = head;
        cursor = tail;
    }
    public void addText(String text) {
        for(char c : text.toCharArray()){
            MyNode node = new MyNode(c);
            node.left = cursor.left;
            node.right = cursor;
            cursor.left.right = node;
            cursor.left = node;
        }
    }
    public int deleteText(int k) {
        int res = 0;
        while (k > 0 && cursor.left != head){
            k--;
            res++;
            cursor.left = cursor.left.left;
            cursor.left.right = cursor;
        }
        return res;
    }

    public String cursorLeft(int k) {
        while (k > 0 && cursor.left != head){
            k--;
            cursor = cursor.left;
        }
        StringBuilder sb = new StringBuilder();
        MyNode node = cursor.left;
        while(node != head && sb.length() < 10){
            sb.append(node.c);
            node = node.left;
        }
        return sb.reverse().toString();
    }

    public String cursorRight(int k) {
        while (k > 0 && cursor != tail){
            k--;
            cursor = cursor.right;
        }
        StringBuilder sb = new StringBuilder();
        MyNode node = cursor.left;
        while(node != head && sb.length() < 10){
            sb.append(node.c);
            node = node.left;
        }
        return sb.reverse().toString();
    }
}

class MyNode{
    public MyNode left;
    public MyNode right;
    public char c;
    public MyNode(char c){
        this.c = c;
    }
}

/**
 * 解答成功:
 * 	执行耗时:131 ms,击败了98.15% 的Java用户
 * 	内存消耗:55.8 MB,击败了90.74% 的Java用户
 */
class TextEditorOther {
    private final StringBuilder left = new StringBuilder(); // 光标左侧字符
    private final StringBuilder right = new StringBuilder(); // 光标右侧字符

    public void addText(String text) {
        left.append(text); // 入栈
    }

    public int deleteText(int k) {
        k = Math.min(k, left.length());
        left.setLength(left.length() - k); // 出栈
        return k;
    }

    public String cursorLeft(int k) {
        while (k > 0 && left.length()>0) {
            right.append(left.charAt(left.length() - 1)); // 左手倒右手
            left.setLength(left.length() - 1);
            k--;
        }
        return text();
    }

    public String cursorRight(int k) {
        while (k > 0 && right.length()>0) {
            left.append(right.charAt(right.length() - 1)); // 右手倒左手
            right.setLength(right.length() - 1);
            k--;
        }
        return text();
    }

    private String text() {
        // 光标左边至多 10 个字符
        return left.substring(Math.max(left.length() - 10, 0));
    }
}
//待调整
class Node {
    Node[] ch = new Node[2];
    int sz;
    char key;

    Node(char key) {
        this.key = key;
        this.sz = 1;
    }

    int cmpKth(int k) {
        int leftSize = Node.size(ch[0]);
        int d = k - leftSize - 1;
        if (d < 0) {
            return 0;
        } else if (d > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    void maintain() {
        sz = 1 + Node.size(ch[0]) + Node.size(ch[1]);
    }

    Node rotate(int d) {
        Node x = ch[d ^ 1];
        ch[d ^ 1] = x.ch[d];
        x.ch[d] = this;
        maintain();
        x.maintain();
        return x;
    }

    Node splay(int k) {
        int d = cmpKth(k);
        if (d < 0) {
            return this;
        }
        k -= d * (Node.size(ch[0]) + 1);
        Node c = ch[d];
        if (c == null) {
            return this.rotate(d ^ 1);
        }
        int d2 = c.cmpKth(k);
        if (d2 >= 0) {
            int cK = k - d2 * (Node.size(c.ch[0]) + 1);
            c.ch[d2] = c.ch[d2] != null ? c.ch[d2].splay(cK) : null;
            if (d2 == d) {
                ch[d] = c;
                c = c.rotate(d ^ 1);
            } else {
                ch[d] = c.rotate(d);
            }
        }
        return rotate(d ^ 1);
    }

    static int size(Node node) {
        return node == null ? 0 : node.sz;
    }
}

class Pair {
    Node lo;
    Node ro;

    Pair(Node lo, Node ro) {
        this.lo = lo;
        this.ro = ro;
    }
}

class TextEditor {
    private Node root;
    private int cur;

    public TextEditor() {
        root = null;
        cur = 0;
    }

    public void addText(String text) {
        if (text.isEmpty()) return;
        Node newNode = buildSplay(text);
        if (cur == 0) {
            root = merge(newNode, root);
        } else {
            Pair split = split(root, cur);
            root = merge(merge(split.lo, newNode), split.ro);
        }
        cur += text.length();
    }

    public int deleteText(int k) {
        if (cur == 0 || k <= 0) return 0;
        int deleted = Math.min(k, cur);
        if (cur == deleted) {
            root = split(root, cur).ro;
            cur = 0;
            return deleted;
        } else {
            Pair split1 = split(root, cur);
            Pair split2 = split(split1.lo, cur - deleted);
            root = merge(split2.lo, split1.ro);
            cur -= deleted;
            return deleted;
        }
    }

    private Node buildSplay(String s) {
        if (s.isEmpty()) return null;
        int mid = s.length() / 2;
        Node node = new Node(s.charAt(mid));
        node.ch[0] = buildSplay(s.substring(0, mid));
        node.ch[1] = buildSplay(s.substring(mid + 1));
        node.maintain();
        return node;
    }

    private Pair split(Node node, int k) {
        if (node == null) return new Pair(null, null);
        node = node.splay(k);
        Node ro = node.ch[1];
        node.ch[1] = null;
        node.maintain();
        return new Pair(node, ro);
    }

    private Node merge(Node lo, Node ro) {
        if (lo == null) return ro;
        if (ro == null) return lo;
        lo = lo.splay(lo.sz);
        lo.ch[1] = ro;
        lo.maintain();
        return lo;
    }

    public String cursorLeft(int k) {
        cur = Math.max(cur - k, 0);
        return text();
    }

    public String cursorRight(int k) {
        cur = Math.min(cur + k, Node.size(root));
        return text();
    }

    private String text() {
        if (cur == 0 || root == null) return "";
        int start = Math.max(cur - 10, 0);
        if (start == 0) {
            root = root.splay(1);
        } else {
            root = root.splay(start + 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.key);
        int count = 1;
        Node node = root.ch[1];
        while (node != null && count < 10) {
            Node current = node;
            while (current.ch[0] != null && count < 10) {
                current = current.ch[0];
            }
            while (current != null && count < 10) {
                sb.append(current.key);
                count++;
                current = current.ch[1];
            }
        }
        return sb.toString();
    }
}