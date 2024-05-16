package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class NumberOfWeeks1953 {
    /**
     * 解答成功:
     * 	执行耗时:6 ms,击败了27.91% 的Java用户
     * 	内存消耗:54.9 MB,击败了68.60% 的Java用户
     * @param milestones
     * @return
     */
    public long numberOfWeeks(int[] milestones) {
        int max = Arrays.stream(milestones).max().getAsInt();
        long sum = 0;
        for(int i = 0 ;i <milestones.length ; i++){
            sum +=  milestones[i];
        }
        if (sum >= max *  2L){
            return sum;
        }else {
            return (sum-max)*2+1;
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了95.35% 的Java用户
     * 	内存消耗:55.1 MB,击败了18.60% 的Java用户
     * @param milestones
     * @return
     */
    public long numberOfWeeksOfficial(int[] milestones) {
        int longest = milestones[0]; // 耗时最长工作所需周数
        long rest = 0;
        for (int count : milestones) {
            longest = Math.max(longest, count);
            rest += count;
        }
        // 其余工作共计所需周数
        rest -= longest;
        if (longest > rest + 1) {
            // 此时无法完成所耗时最长的工作
            return rest * 2 + 1;
        } else {
            // 此时可以完成所有工作
            return longest + rest;
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
