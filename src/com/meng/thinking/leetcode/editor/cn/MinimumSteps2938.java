package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumSteps2938 {
    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了85.71% 的Java用户
     * 	内存消耗:44.5 MB,击败了20.63% 的Java用户
     * @param s
     * @return
     */
    public long minimumStepsMy(String s) {
        long res = 0;
        int index = s.length() -1;
        for(int i = index ; i>=0 ;i--){
            if (s.charAt(i) == '1'){
                res += (index - i);
                index--;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了85.71% 的Java用户
     * 	内存消耗:44.6 MB,击败了15.87% 的Java用户
     * @param s
     * @return
     */
    public long minimumSteps(String s) {
        long ans = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                sum++;
            } else {
                ans += sum;
            }
        }
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
