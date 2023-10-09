package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionSplitNum2578 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:38.2 MB,击败了68.00% 的Java用户
     * @param num
     * @return
     */
    public int splitNum1(int num) {
        int[] nums = new int[10];
        int n = 0;
        while (num != 0){
            int t = num % 10;
            nums[t]++;
            if (t != 0){
                n++;
            }
            num = num /10;
        }
        if (n==0){
            return 0;
        }
        int first = 0 ,second = 0;
        boolean flag = true;
        for(int i = 1 ; i <= 9 ; i++){
            if (nums[i] >0){
                for(int j = 0 ; j < nums[i] ; j++){
                    if (flag){
                        first = first*10 + i;
                    }else {
                        second = second*10 + i;
                    }
                    flag = !flag;
                }
            }
        }
        return first + second;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了91.00% 的Java用户
     * 	内存消耗:38.3 MB,击败了52.00% 的Java用户
     * @param num
     * @return
     */
    public int splitNum(int num) {
        char[] stnum = Integer.toString(num).toCharArray();
        Arrays.sort(stnum);
        int num1 = 0, num2 = 0;
        for (int i = 0; i < stnum.length; ++i) {
            if (i % 2 == 0) {
                num1 = num1 * 10 + (stnum[i] - '0');
            } else {
                num2 = num2 * 10 + (stnum[i] - '0');
            }
        }
        return num1 + num2;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
