package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumOperations2844 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了78.33% 的Java用户
     * 	内存消耗:41.3 MB,击败了35.00% 的Java用户
     * @param num
     * @return
     */
    public int minimumOperationsMy(String num) {
        int zeroFirst = -1,zeroEnd = -1,fiveFirst= -1,fiveEnd = -1;
        for(int i = num.length()-1; i >=0 ; i--){
            if(num.charAt(i) == '0'){
                if (zeroFirst == -1){
                    zeroFirst = i;
                }else if (zeroEnd ==-1){
                    zeroEnd = i;
                }
            }else if (num.charAt(i) == '5'){
                if (fiveFirst == -1) {
                    fiveFirst = i;
                }
                if (zeroFirst!=-1&&zeroEnd==-1){
                    zeroEnd = i;
                }
            }else if (num.charAt(i) == '7'){
                if (fiveFirst!=-1&&fiveEnd==-1){
                    fiveEnd = i;
                }
            }else if (num.charAt(i) == '2'){
                if (fiveFirst!=-1&&fiveEnd==-1){
                    fiveEnd = i;
                }
            }
        }
        int res = num.length();
        //存在满足的情况
        if (zeroFirst != -1&&zeroEnd != -1){
            int temp = (zeroFirst-zeroEnd-1) + (num.length()-1 - zeroFirst);
            res = Math.min(res,temp);
        }
        if (fiveFirst != -1&&fiveEnd != -1){
            int temp = (fiveFirst-fiveEnd-1) + (num.length()-1 - fiveFirst);
            res = Math.min(res,temp);
        }
        //存在zeroFirst的情况
        if (zeroFirst != -1){
            res = Math.min(res,num.length()-1);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.1 MB,击败了81.67% 的Java用户
     * @param num
     * @return
     */
    public int minimumOperations(String num) {
        int n = num.length();
        boolean found0 = false;
        boolean found5 = false;
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if (found0 && (c == '0' || c == '5') ||
                    found5 && (c == '2' || c == '7')) {
                return n - i - 2;
            }
            if (c == '0') {
                found0 = true;
            } else if (c == '5') {
                found5 = true;
            }
        }
        return found0 ? n - 1 : n;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
