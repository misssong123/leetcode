package com.meng.oneday.leetcode.editor.cn;

import java.util.Objects;

class FinalValueAfterOperations2011 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了74.15% 的Java用户
     * 	内存消耗:41.8 MB,击败了83.90% 的Java用户
     * @param operations
     * @return
     */
    public int finalValueAfterOperations2011(String[] operations) {
        int x = 0;
        for(String operation : operations){
            if (Objects.equals(operation, "++X") || Objects.equals(operation, "X++")){
                x++;
            }else{
                x--;
            }
        }
        return x;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:41.9 MB,击败了68.29% 的Java用户
     * @param operations
     * @return
     */
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String s : operations) {
            ans += s.charAt(1) == '+' ? 1 : -1;
        }
        return ans;
    }

}
