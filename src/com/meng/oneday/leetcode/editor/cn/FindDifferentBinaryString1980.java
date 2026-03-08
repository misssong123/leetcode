package com.meng.oneday.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class FindDifferentBinaryString1980 {
    /**
     * 解答成功:
     * 	执行耗时:9 ms,击败了2.17% 的Java用户
     * 	内存消耗:43.1 MB,击败了6.52% 的Java用户
     * @param nums
     * @return
     */
    public String findDifferentBinaryString1980(String[] nums) {
        //计算当前元素中的大小
        Set<Integer> set = new HashSet<>();
        for(String num : nums){
            set.add(Integer.parseInt(num,2));
        }
        //遍历可能性
        for(int i = 0 ; i < (1 << nums[0].length()) ; i++){
            if(!set.contains(i)){
                String binaryString = Integer.toBinaryString(i);
                return String.format("%0" + nums[0].length() + "d", Integer.parseInt(binaryString));
            }
        }
        return "";
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.4 MB,击败了91.30% 的Java用户
     * @param nums
     * @return
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) (nums[i].charAt(i) ^ 1);
        }
        return new String(ans);
    }

}
