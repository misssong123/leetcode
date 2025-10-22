package com.meng.top100.leetcode.editor.cn;

class Jump45 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.45% 的Java用户
     * 	内存消耗:44.3 MB,击败了29.50% 的Java用户
     * @param nums
     * @return
     */
    public int jump45(int[] nums) {
        if (nums.length == 1){
            return 0;
        }
        int pre = 0,max = 0 ,res = 0;
        for(int i = 0 ; i < nums.length -1 ; i++){
            max = Math.max(max,i+nums[i]);
            if(pre == i){
                pre = max;
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.45% 的Java用户
     * 	内存消耗:44.1 MB,击败了86.27% 的Java用户
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int ans = 0;
        int curRight = 0; // 已建造的桥的右端点
        int nextRight = 0; // 下一座桥的右端点的最大值
        for (int i = 0; i < nums.length - 1; i++) {
            // 遍历的过程中，记录下一座桥的最远点
            nextRight = Math.max(nextRight, i + nums[i]);
            if (i == curRight) { // 无路可走，必须建桥
                curRight = nextRight; // 建桥后，最远可以到达 next_right
                ans++;
            }
        }
        return ans;
    }

}
