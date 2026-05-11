package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SeparateDigits2553 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了7.14% 的Java用户
     * 	内存消耗:46 MB,击败了30.95% 的Java用户
     * @param nums
     * @return
     */
    public int[] separateDigits2553(int[] nums) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>(len);
        List<Integer> temp = new ArrayList<>();
        for(int num : nums){
            if (num < 10){
                list.add(num);
            }else{
                while (num > 0){
                    temp.add(num % 10);
                    num /= 10;
                }
                Collections.reverse(temp);
                list.addAll(temp);
                temp.clear();
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了45.24% 的Java用户
     * 	内存消耗:45.9 MB,击败了40.48% 的Java用户
     * @param nums
     * @return
     */
    public int[] separateDigitsOther(int[] nums) {
        List<Integer> digits = new ArrayList<>();
        for (int x : nums) {
            for (char ch : String.valueOf(x).toCharArray()) {
                digits.add(ch - '0');
            }
        }

        int m = digits.size();
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = digits.get(i);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了59.52% 的Java用户
     * 	内存消耗:46 MB,击败了33.33% 的Java用户
     * @param nums
     * @return
     */
    public int[] separateDigits(int[] nums) {
        List<Integer> digits = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int x = nums[i]; x > 0; x /= 10) {
                digits.add(x % 10);
            }
        }

        int m = digits.size();
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = digits.get(m - 1 - i);
        }
        return ans;
    }

}
