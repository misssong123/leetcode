package com.meng.oneday.leetcode.editor.cn;

class MaxJumps1340 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了89.74% 的Java用户
     * 	内存消耗:45.7 MB,击败了71.79% 的Java用户
     * @param arr
     * @param d
     * @return
     */
    public int maxJumps1340(int[] arr, int d) {
        int n = arr.length;
        int[] memory = new int[n];
        int max = 0;
        //遍历起点可能性
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dfs(arr, i, d, memory));
        }
        return max;
    }

    private int dfs(int[] arr, int i, int d, int[] memory) {
        if (memory[i] != 0) {
            return memory[i];
        }
        int max = 1;
        //向左跳
        for (int j = i - 1 ; j >= Math.max(0,i-d) && arr[j] < arr[i]; j--) {
            max = Math.max(max, dfs(arr, j, d, memory) + 1);
        }
        //向右跳
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d) && arr[j] < arr[i]; j++) {
            max = Math.max(max, dfs(arr, j, d, memory) + 1);
        }
        return memory[i] = max;
    }
}
