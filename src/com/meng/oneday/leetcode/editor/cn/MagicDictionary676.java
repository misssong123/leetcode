package com.meng.oneday.leetcode.editor.cn;

/**
 * 解答成功:
 * 	执行耗时:35 ms,击败了35.90% 的Java用户
 * 	内存消耗:47.6 MB,击败了41.03% 的Java用户
 */
class MagicDictionary676 {
    Dictionary676[] parent;
    public MagicDictionary676() {
        parent = new Dictionary676[26];
    }
    
    public void buildDict(String[] dictionary) {
        for (String str : dictionary) {
            buildDict(str,parent);
        }
    }

    private void buildDict(String str, Dictionary676[] parent) {
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            if (parent[index] == null) {
                parent[index] = new Dictionary676();
                parent[index].children = new Dictionary676[26];
            }
            //是否最后一位
            if (i == str.length() - 1) {
                parent[index].end = true;
            }
            parent = parent[index].children;
        }
    }

    public boolean search(String searchWord) {
        return search(searchWord,parent,0,0,false);
    }

    private boolean search(String searchWord,
                           Dictionary676[] parent, int index, int cnt,boolean isEnd) {
        if (index == searchWord.length()) {
            return cnt == 1 && isEnd;
        }
        int n = searchWord.charAt(index) - 'a';
        for (int i = 0; i < 26; i++) {
            if (parent[i] != null) {
                if (n == i){
                    if (search(searchWord,parent[i].children,index + 1,cnt,parent[i].end)) {
                        return true;
                    }
                }else if (cnt == 0 && search(searchWord,parent[i].children,
                        index + 1,cnt + 1,parent[i].end)){
                    return true;
                }
            }
        }
        return false;
    }
}
class Dictionary676{
    public boolean end;
    public Dictionary676[] children;
}

/**
 * 解答成功:
 * 	执行耗时:56 ms,击败了7.69% 的Java用户
 * 	内存消耗:58.1 MB,击败了5.13% 的Java用户
 */
class MagicDictionaryOther {
    int N = 100 * 100, M = 26, idx = 0;
    int[][] tr = new int[N][M];
    boolean[] isEnd = new boolean[N * M];
    void add(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (tr[p][u] == 0) tr[p][u] = ++idx;
            p = tr[p][u];
        }
        isEnd[p] = true;
    }
    boolean query(String s, int idx, int p, int limit) {
        if (limit < 0) return false;
        if (idx == s.length()) return isEnd[p] && limit == 0;
        int u = s.charAt(idx) - 'a';
        for (int i = 0; i < 26; i++) {
            if (tr[p][i] == 0) continue;
            if (query(s, idx + 1, tr[p][i], i == u ? limit : limit - 1)) return true;
        }
        return false;
    }
    public void buildDict(String[] ss) {
        for (String s : ss) add(s);
    }
    public boolean search(String s) {
        return query(s, 0, 0, 1);
    }
}

/**
 * 解答成功:
 * 	执行耗时:20 ms,击败了84.62% 的Java用户
 * 	内存消耗:46.1 MB,击败了94.87% 的Java用户
 */
class MagicDictionary {
    String[] ss;
    public void buildDict(String[] _ss) {
        ss = _ss;
    }
    public boolean search(String str) {
        for (String s : ss) {
            int cnt = 0;
            for (int i = 0; s.length() == str.length() && i < s.length() && cnt <= 1; i++) {
                if (s.charAt(i) != str.charAt(i)) cnt++;
            }
            if (cnt == 1) return true;
        }
        return false;
    }
}

