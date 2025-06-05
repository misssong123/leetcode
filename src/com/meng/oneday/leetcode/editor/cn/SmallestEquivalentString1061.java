package com.meng.oneday.leetcode.editor.cn;

class SmallestEquivalentString1061 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了68.93% 的Java用户
     * 	内存消耗:41.1 MB,击败了72.82% 的Java用户
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public String smallestEquivalentString1061(String s1, String s2, String baseStr) {
        int[] index = new int[26];
        for (int i = 0; i < 26; i++) {
            index[i] = i;
        }
        int len = s1.length();
        for(int i = 0 ; i < len ; i ++){
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            int aParent = findParent(index,a);
            int bParent = findParent(index,b);
            if(aParent > bParent){
                index[aParent] = bParent;
                index[a] = bParent;
                index[b] = bParent;
            }else if(aParent < bParent){
                index[bParent] = aParent;
                index[a] = aParent;
                index[b] = aParent;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            sb.append((char) (findParent(index, c - 'a') + 'a'));
        }
        return sb.toString();
    }
    private int findParent(int[] index , int i){
        return index[i] == i ? i : findParent(index,index[i]);
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了85.44% 的Java用户
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] fa = new int[26];
        for (int i = 0; i < 26; i++) {
            fa[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            merge(fa, s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        char[] s = baseStr.toCharArray();
        for (int i = 0; i < s.length; i++) {
            s[i] = (char) (find(fa, s[i] - 'a') + 'a');
        }
        return new String(s);
    }

    private int find(int[] fa, int x) {
        if (fa[x] != x) {
            fa[x] = find(fa, fa[x]);
        }
        return fa[x];
    }

    private void merge(int[] fa, int x, int y) {
        int fx = find(fa, x);
        int fy = find(fa, y);
        // 把大的代表元指向小的代表元
        if (fx < fy) {
            fa[fy] = fx;
        } else {
            fa[fx] = fy;
        }
    }

}
