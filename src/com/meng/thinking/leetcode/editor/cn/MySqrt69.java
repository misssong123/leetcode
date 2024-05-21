package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MySqrt69 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了85.08% 的Java用户
     * 	内存消耗:39.8 MB,击败了61.34% 的Java用户
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 1){
            return x;
        }
        int left = 1, right = x ;
        int ans = 0;
        while (left <= right){
            int mid = left + (right - left + 1) / 2;
            long temp = (long)mid * mid;
            if (temp <= x){
                ans = mid;
                left = mid+1;
            }else{
                right = mid -1;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
