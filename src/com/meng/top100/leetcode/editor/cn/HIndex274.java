package com.meng.top100.leetcode.editor.cn;

class HIndex274 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了28.46% 的Java用户
     * @param citations
     * @return
     */
    public int hIndex274(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        //记录引用次数
        for (int citation : citations) {
            cnt[Math.min(n,citation)]++;
        }
        //前缀和
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + cnt[i];
        }
        for(int i = n ; i > 0 ; i--){
            if (pre[n] - pre[i-1] >=i){
                return i;
            }
        }
        return 0;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.6 MB,击败了76.84% 的Java用户
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int c : citations) {
            cnt[Math.min(c, n)]++; // 引用次数 > n，等价于引用次数为 n
        }
        int s = 0;
        for (int i = n; ; i--) { // i=0 的时候，s>=i 一定成立
            s += cnt[i];
            if (s >= i) { // 说明有至少 i 篇论文的引用次数至少为 i
                return i;
            }
        }
    }

}
