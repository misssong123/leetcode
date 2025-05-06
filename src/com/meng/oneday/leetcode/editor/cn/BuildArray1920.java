package com.meng.oneday.leetcode.editor.cn;

class BuildArray1920 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了85.80% 的Java用户
     * 	内存消耗:44.4 MB,击败了85.80% 的Java用户
     * @param nums
     * @return
     */
    public int[] buildArray1920(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0 ; i < nums.length; i++){
            res[i] = nums[nums[i]];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了85.80% 的Java用户
     * 	内存消耗:44.6 MB,击败了31.36% 的Java用户
     * @param nums
     * @return
     */
    public int[] buildArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) { // 已搬家
                continue;
            }
            int cur = i;
            while (nums[cur] != i) {
                int nxt = nums[cur];
                nums[cur] = ~nums[nxt]; // 把下一个数搬过来，同时做标记（取反）
                cur = nxt;
            }
            nums[cur] = ~x; // 对于这一组的最后一个数，把起点 x=nums[i] 搬过来
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = ~nums[i]; // 复原
        }
        return nums;
    }

}
