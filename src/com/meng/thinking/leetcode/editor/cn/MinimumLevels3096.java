package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumLevels3096 {
    /**
     * 解答成功:
     * 	执行耗时:11 ms,击败了57.65% 的Java用户
     * 	内存消耗:58.3 MB,击败了39.41% 的Java用户
     * @param possible
     * @return
     */
    public int minimumLevelsMy(int[] possible) {
        int sum = 0 ;
        for(int n :possible){
            sum += n==0?-1:1;
        }
        int first = possible[0] == 0 ? -1 :1;
        sum -= first;
        if (first > sum){
            return 1;
        }
        for(int i = 1 ; i < possible.length -1 ; i++){
            int n = possible[i] == 0 ? -1 :1;
            first += n;
            sum -= n;
            if (first > sum){
                return i+1;
            }
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了95.88% 的Java用户
     * 	内存消耗:58.3 MB,击败了44.12% 的Java用户
     * @param possible
     * @return
     */
    public int minimumLevels(int[] possible) {
        int n = possible.length;
        // s = cnt1 - cnt0 = cnt1 - (n - cnt1) = cnt1 * 2 - n
        int s = 0;
        for (int x : possible) {
            s += x;
        }
        s = s * 2 - n;
        int pre = 0;
        for (int i = 0; i < n - 1; i++) {
            pre += possible[i] == 1 ? 2 : -2;
            if (pre > s) {
                return i + 1;
            }
        }
        return -1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
