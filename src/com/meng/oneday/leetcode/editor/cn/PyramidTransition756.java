package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class PyramidTransition756 {
    /**
     * 解答成功:
     * 	执行耗时:403 ms,击败了27.88% 的Java用户
     * 	内存消耗:42.5 MB,击败了98.57% 的Java用户
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransition756(String bottom, List<String> allowed) {
        int n = bottom.length();
        int[][][] cnts = new int[6][6][6];
        int[][] caches = new int[n][n];
        for(String s : allowed) {
            int x = s.charAt(0) - 'A';
            int y = s.charAt(1) - 'A';
            int index = s.charAt(2) - 'A';
            cnts[x][y][index]++;
        }
        for (int i = 0; i < n; i++) {
            caches[n-1][i] = bottom.charAt(i) - 'A';
        }
        return dfs(cnts,caches,0,n-2);
    }

    private boolean dfs(int[][][] cnts, int[][] caches,int index, int level) {
        if(level < 0 ){
            return true;
        }
        if (index  > level){
            return dfs(cnts,caches,0,level-1);
        }
        for(int i = 0 ; i < 6 ; i++){
            if(cnts[caches[level+1][index]][caches[level+1][index+1]][i] > 0){
                caches[level][index] = i;
                if(dfs(cnts,caches,index+1,level)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:305 ms,击败了29.05% 的Java用户
     * 	内存消耗:46.1 MB,击败了15.08% 的Java用户
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransitionOther1(String bottom, List<String> allowed) {
        // 三角形底部两个字母 -> [三角形顶部字母]
        List<Character>[][] groups = new ArrayList[6][6];
        for (List<Character>[] row : groups) {
            Arrays.setAll(row, item -> new ArrayList<>());
        }
        for (String S : allowed) {
            char[] s = S.toCharArray();
            groups[s[0] - 'A'][s[1] - 'A'].add(s[2]);
        }

        int n = bottom.length();
        char[][] pyramid = new char[n][];
        for (int i = 0; i < n - 1; i++) {
            pyramid[i] = new char[i + 1];
        }
        pyramid[n - 1] = bottom.toCharArray();

        // 从倒数第二行开始填
        return dfsOther1(n - 2, 0, pyramid, groups);
    }

    // 现在准备填 (i, j) 这个格子
    // 返回继续填能否填完所有格子（从下往上填，每行从左到右填）
    private boolean dfsOther1(int i, int j, char[][] pyramid, List<Character>[][] groups) {
        if (i < 0) { // 所有格子都已填完
            return true;
        }

        if (j == i + 1) { // i 行已填完
            return dfsOther1(i - 1, 0, pyramid, groups); // 开始填 i-1 行
        }

        // 枚举 (i, j) 填什么字母
        // 这取决于 (i+1, j) 和 (i+1, j+1) 填的字母
        for (char top : groups[pyramid[i + 1][j] - 'A'][pyramid[i + 1][j + 1] - 'A']) {
            pyramid[i][j] = top;
            if (dfsOther1(i, j + 1, pyramid, groups)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:46 ms,击败了41.90% 的Java用户
     * 	内存消耗:47.1 MB,击败了6.14% 的Java用户
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransitionOther2(String bottom, List<String> allowed) {
        List<Character>[][] groups = new ArrayList[6][6];
        for (List<Character>[] row : groups) {
            Arrays.setAll(row, item -> new ArrayList<>());
        }
        for (String S : allowed) {
            char[] s = S.toCharArray();
            groups[s[0] - 'A'][s[1] - 'A'].add(s[2]);
        }

        int n = bottom.length();
        char[][] pyramid = new char[n][];
        for (int i = 0; i < n - 1; i++) {
            pyramid[i] = new char[i + 1];
        }
        pyramid[n - 1] = bottom.toCharArray();

        Set<String> vis = new HashSet<>(); // 访问标记

        return dfsOther2(n - 2, 0, pyramid, vis, groups);
    }

    private boolean dfsOther2(int i, int j, char[][] pyramid, Set<String> vis, List<Character>[][] groups) {
        if (i < 0) {
            return true;
        }

        if (j == i + 1) {
            String row = new String(pyramid[i]);
            if (!vis.add(row)) { // 这一行之前填过一模一样的，继续填，没能填到塔顶
                return false; // 直接返回
            }
            return dfsOther2(i - 1, 0, pyramid, vis, groups);
        }

        for (char top : groups[pyramid[i + 1][j] - 'A'][pyramid[i + 1][j + 1] - 'A']) {
            pyramid[i][j] = top;
            if (dfsOther2(i, j + 1, pyramid, vis, groups)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了82.12% 的Java用户
     * 	内存消耗:46.4 MB,击败了9.49% 的Java用户
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransitionOther3(String bottom, List<String> allowed) {
        List<Character>[][] groups = new ArrayList[6][6];
        for (List<Character>[] row : groups) {
            Arrays.setAll(row, item -> new ArrayList<>());
        }
        for (String S : allowed) {
            char[] s = S.toCharArray();
            groups[s[0] - 'A'][s[1] - 'A'].add(s[2]);
        }

        int n = bottom.length();
        char[][] pyramid = new char[n][];
        for (int i = 0; i < n - 1; i++) {
            pyramid[i] = new char[i + 1];
        }
        pyramid[n - 1] = bottom.toCharArray();

        Set<String> vis = new HashSet<>();

        return dfsOther3(n - 2, 0, pyramid, vis, groups);
    }

    private boolean dfsOther3(int i, int j, char[][] pyramid, Set<String> vis, List<Character>[][] groups) {
        if (i < 0) {
            return true;
        }

        String row = new String(pyramid[i], 0, j);
        if (vis.contains(row)) { // 之前填过一模一样的，这个局部的金字塔无法填完
            return false; // 继续递归也无法填完，直接返回
        }

        if (j == i + 1) {
            vis.add(row);
            return dfsOther3(i - 1, 0, pyramid, vis, groups);
        }

        for (char top : groups[pyramid[i + 1][j] - 'A'][pyramid[i + 1][j + 1] - 'A']) {
            pyramid[i][j] = top;
            if (dfsOther3(i, j + 1, pyramid, vis, groups)) {
                return true;
            }
        }
        return false;
    }
}
