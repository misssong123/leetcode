package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionSingleNumber260 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了6.36% 的Java用户
     * 	内存消耗:43.1 MB,击败了79.55% 的Java用户
     * @param nums
     * @return
     */
    public int[] singleNumber1(int[] nums) {
        int[] res = new int[2];
        Map<Integer,Integer> cache= new HashMap<>();
        for(int num : nums){
            cache.put(num,cache.getOrDefault(num,0)+1);
        }
        int index = 0;
        for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
            if (entry.getValue()==1){
                res[index] = entry.getKey();
                index++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.8 MB,击败了97.74% 的Java用户
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }

}
//leetcode submit region end(Prohibit modification and deletion)
