package com.meng.oneday.leetcode.editor.cn;

import java.util.List;

class MinimumRightShifts2855 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了43.75% 的Java用户
     * 	内存消耗:43.7 MB,击败了68.75% 的Java用户
     * @param nums
     * @return
     */
    public int minimumRightShifts2855(List<Integer> nums) {
        //校验是否最多存在两个爬坡点
        int size = nums.size();
        int first = -1;
        for (int i = 1 ; i < nums.size() ; i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                if(first == -1){
                    first = i;
                }else{
                    return -1;
                }
            }
        }
        return first == -1 ? 0 : nums.get(size-1) < nums.get(0) ? nums.size() - first : -1;
    }
}
