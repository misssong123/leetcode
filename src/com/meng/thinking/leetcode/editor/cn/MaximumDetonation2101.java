package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumDetonation2101 {
    /**
     * 解答成功:
     * 	执行耗时:114 ms,击败了12.02% 的Java用户
     * 	内存消耗:43.7 MB,击败了60.08% 的Java用户
     * @param bombs
     * @return
     */
    public static int maximumDetonationMy(int[][] bombs) {
        int len = bombs.length;
        boolean[][] cache = new boolean[len][len];
        for(int i = 0 ; i < len ; i++){
            for (int j = 0 ; j < len ; j++){
                if (containsPoint(bombs[i][0], bombs[i][1], bombs[i][2],
                        bombs[j][0], bombs[j][1])){
                    cache[i][j] = true;
                }
            }
        }
        int max = 0;
        for(int i = 0 ; i < len ; i++){
            boolean[] flag = new boolean[len];
            int temp = 1;
            flag[i] = true;
            List<Integer> list = new ArrayList<>();
            for (int j = 0 ; j < len ; j++){
                if (!flag[j]&&cache[i][j]){
                    list.add(j);
                    flag[j] =true;
                    temp++;
                }
            }
            while (!list.isEmpty()){
                List<Integer> tempList = new ArrayList<>();
                for (int index : list){
                    for (int j = 0 ; j < len ; j++){
                        if (!flag[j]&&cache[index][j]){
                            tempList.add(j);
                            flag[j] =true;
                            temp++;
                        }
                    }
                }
                list.clear();
                list.addAll(tempList);
            }
            max = Math.max(max,temp);
        }
        return max;
    }
    // 判断圆是否包含点
    public static boolean containsPoint(int circleX, int circleY, int radius, int pointX, int pointY) {
        double distanceSquared = Math.pow(circleX - pointX, 2) + Math.pow(circleY - pointY, 2);
        double radiusSquared = Math.pow(radius, 2);
        return distanceSquared <= radiusSquared;
    }

    /**
     * 解答成功:
     * 	执行耗时:83 ms,击败了57.94% 的Java用户
     * 	内存消耗:43.9 MB,击败了25.32% 的Java用户
     * @param bombs
     * @return
     */
    public int maximumDetonationOther(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            long x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];
            for (int j = 0; j < n; j++) {
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                if (j != i && dx * dx + dy * dy <= r * r) {
                    g[i].add(j); // i 可以引爆 j
                }
            }
        }

        int ans = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(vis, false);
            ans = Math.max(ans, dfs(g, vis, i));
        }
        return ans;
    }

    private int dfs(List<Integer>[] g, boolean[] vis, int x) {
        vis[x] = true;
        int cnt = 1;
        for (int y : g[x]) {
            if (!vis[y]) {
                cnt += dfs(g, vis, y);
            }
        }
        return cnt;
    }

    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了93.99% 的Java用户
     * 	内存消耗:43.4 MB,击败了69.10% 的Java用户
     * @param bombs
     * @return
     */
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        BitSet[] f = new BitSet[n];
        for (int i = 0; i < n; i++) {
            long x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];
            f[i] = new BitSet(n);
            for (int j = 0; j < n; j++) {
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                if (dx * dx + dy * dy <= r * r) {
                    f[i].set(j); // i 可以到达 j
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (BitSet fi : f) {
                if (fi.get(k)) { // i 可以到达 k
                    fi.or(f[k]); // i 也可以到 k 可以到达的点
                }
            }
        }

        int ans = 0;
        for (BitSet s : f) {
            ans = Math.max(ans, s.cardinality()); // 集合大小的最大值
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
