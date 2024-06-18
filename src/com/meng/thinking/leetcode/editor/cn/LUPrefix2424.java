package com.meng.thinking.leetcode.editor.cn;//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 解答成功:
 * 	执行耗时:31 ms,击败了52.85% 的Java用户
 * 	内存消耗:107.6 MB,击败了39.84% 的Java用户
 */
class LUPrefix2424 {
    int[] cache ;
    int index = 0;
    public LUPrefix2424(int n) {
        cache = new int[n];
    }
    
    public void upload(int video) {
        cache[video-1] = 1;
        while (index < cache.length && cache[index] != 0){
            index++;
        }
    }
    
    public int longest() {
        return index;
    }
}

/**
 * 解答成功:
 * 	执行耗时:34 ms,击败了34.96% 的Java用户
 * 	内存消耗:107.2 MB,击败了87.80% 的Java用户
 */
class LUPrefix {


    int[] u;
    boolean[] add;
    int n;

    public LUPrefix(int n) {
        this.n = n;
        u = new int[n + 1];
        for (int i = 0; i < u.length; i++) {
            u[i] = i;
        }
        add = new boolean[n + 1];
        add[0] = true;
    }

    public void upload(int video) {
        add[video] = true;
        if (add[video - 1]) {
            union(video - 1, video);
        }
        if (video < n && add[video + 1]) {
            union(video, video + 1);
        }
    }

    private void union(int l, int r) {
        u[l] = find(r);
    }

    public int longest() {
        return find(0);
    }

    private int find(int i) {
        if (u[i] != i) {
            u[i] = find(u[i]);
        }
        return u[i];
    }


}

