package com.meng.top100.leetcode.editor.cn;

class MinNumberOperations1526 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了95.95% 的Java用户
     * 	内存消耗:55.8 MB,击败了79.73% 的Java用户
     * @param target
     * @return
     */
    public int minNumberOperations1526(int[] target) {
        int res = target[0];
        for(int i = 1 ; i < target.length ; i++){
            res += Math.max(target[i] - target[i-1],0);
        }
        return res;
    }
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int ans = target[0];
        for (int i = 1; i < n; ++i) {
            ans += Math.max(target[i] - target[i - 1], 0);
        }
        return ans;
    }
}
