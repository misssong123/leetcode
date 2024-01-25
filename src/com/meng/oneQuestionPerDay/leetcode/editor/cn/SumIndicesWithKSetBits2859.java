package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class SumIndicesWithKSetBits2859 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了19.11% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int res = 0 ;
        for(int i = 0 ; i < nums.size() ; i++){
            if (Integer.bitCount(i)==k){
                res+=nums.get(i);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
