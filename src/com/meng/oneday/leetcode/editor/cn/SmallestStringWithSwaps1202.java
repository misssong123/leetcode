package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class SmallestStringWithSwaps1202 {
    /**
     * 解答成功:
     * 	执行耗时:47 ms,击败了56.82% 的Java用户
     * 	内存消耗:86.1 MB,击败了38.64% 的Java用户
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps1202(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        //分组
        for (List<Integer> pair : pairs) {
            int x = pair.get(0), y = pair.get(1);
            union(parent, x, y);
        }
        //将同一组的字符放到一个list中
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            if (!map.containsKey(root)) {
                map.put(root, new ArrayList<>());
            }
            map.get(root).add(i);
        }
        //将同一组的字符排序，然后替换
        char[] chars = s.toCharArray();
        for (List<Integer> list : map.values()) {
            sort(chars, list);
        }
        return new String(chars);
    }

    private void sort(char[] chars, List<Integer> list) {
        int[] cnts = new int[26];
        for (int i : list) {
            cnts[chars[i] - 'a']++;
        }
        list.sort(Integer::compareTo);
        int index = 0;
        for (int i = 0 ; i < 26 ; i++){
            while (cnts[i] > 0){
                chars[list.get(index)] = (char) ('a' + i);
                index++;
                cnts[i]--;
            }
        }
    }

    private void union(int[] parent, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        if (xParent != yParent) {
            parent[yParent] = xParent;
        }
    }

    private int find(int[] parent, int y) {
        int p = parent[y];
        while (p != parent[p]) {
            p = parent[p];
        }
        parent[y] = p;
        return p;
    }

    /**
     * 解答成功:
     * 	执行耗时:47 ms,击败了56.82% 的Java用户
     * 	内存消耗:84 MB,击败了77.27% 的Java用户
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        DisjointSetUnion1202 dsu = new DisjointSetUnion1202(s.length());
        for (List<Integer> pair : pairs) {
            dsu.unionSet(pair.get(0), pair.get(1));
        }
        Map<Integer, List<Character>> map = new HashMap<Integer, List<Character>>();
        for (int i = 0; i < s.length(); i++) {
            int parent = dsu.find(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Character>());
            }
            map.get(parent).add(s.charAt(i));
        }
        for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
            Collections.sort(entry.getValue(), new Comparator<Character>() {
                public int compare(Character c1, Character c2) {
                    return c2 - c1;
                }
            });
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int x = dsu.find(i);
            List<Character> list = map.get(x);
            sb.append(list.remove(list.size() - 1));
        }
        return sb.toString();
    }

}
class DisjointSetUnion1202 {
    int[] f;
    int[] rank;
    int n;

    public DisjointSetUnion1202(int n) {
        this.n = n;
        rank = new int[n];
        Arrays.fill(rank, 1);
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    public int find(int x) {
        return f[x] == x ? x : (f[x] = find(f[x]));
    }

    public void unionSet(int x, int y) {
        int fx = find(x), fy = find(y);
        if (fx == fy) {
            return;
        }
        if (rank[fx] < rank[fy]) {
            int temp = fx;
            fx = fy;
            fy = temp;
        }
        rank[fx] += rank[fy];
        f[fy] = fx;
    }
}

