package com.meng.thinking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    List<Integer>[] g;
    int[] nums, xor, in, out;
    int clock;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        this.nums = nums;
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        dfs(0, -1);
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(out));
        int ans = Integer.MAX_VALUE;
        for (int i = 2, x, y, z; i < n; ++i)
            for (int j = 1; j < i; ++j) {
                if (in[i] < in[j] && in[j] <= out[i]) { // i 是 j 的祖先节点
                    x = xor[j];
                    y = xor[i] ^ x;
                    z = xor[0] ^ xor[i];
                } else if (in[j] < in[i] && in[i] <= out[j]) { // j 是 i 的祖先节点
                    x = xor[i];
                    y = xor[j] ^ x;
                    z = xor[0] ^ xor[j];
                } else { // 删除的两条边分别属于两颗不相交的子树
                    x = xor[i];
                    y = xor[j];
                    z = xor[0] ^ x ^ y;
                }
                ans = Math.min(ans, Math.max(Math.max(x, y), z) - Math.min(Math.min(x, y), z));
                if (ans == 0) return 0; // 提前退出
            }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,8,9,10};
        int[][]edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{3,8},{6,9},{6,10}};
        Test demo = new Test();
        demo.minimumScore(nums,edges);
    }
    void dfs(int x, int fa) {
        in[x] = ++clock;
        xor[x] = nums[x];
        for (int y : g[x])
            if (y != fa) {
                dfs(y, x);
                xor[x] ^= xor[y];
            }
        out[x] = clock;
    }

}
