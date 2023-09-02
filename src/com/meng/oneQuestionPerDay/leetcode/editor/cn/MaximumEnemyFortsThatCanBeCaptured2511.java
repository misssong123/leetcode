package com.meng.oneQuestionPerDay.leetcode.editor.cn;


class SolutionCaptureForts2511 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.2 MB,击败了53.38% 的Java用户
     * @param forts
     * @return
     */
    public int captureForts1(int[] forts) {
        int preK = (forts[0] == -1)?0:-1,preS = (forts[0] == 1)?0:-1;
        int ans = 0;
        for(int i = 1 ; i < forts.length ; i++){
            //当前位置为士兵
            if (forts[i] == 1){
                if (preS > preK){
                    preS = i;
                    continue;
                }
                preS= i;
                if (preK == -1){
                    continue;
                }
                ans = Math.max(ans,i - preK -1);
            }else if (forts[i] == -1){//当前位置为空闲
                if (preK > preS){
                    preK = i;
                    continue;
                }
                preK = i;
                if (preS == -1){
                    continue;
                }
                ans = Math.max(ans,i - preS -1);
            }

        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.8 MB,击败了96.62% 的Java用户
     * @param forts
     * @return
     */
    public int captureForts(int[] forts) {
        int n = forts.length;
        int ans = 0, pre = -1;
        for (int i = 0; i < n; i++) {
            if (forts[i] == 1 || forts[i] == -1) {
                if (pre >= 0 && forts[i] != forts[pre]) {
                    ans = Math.max(ans, i - pre - 1);
                }
                pre = i;
            }
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
