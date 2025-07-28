package com.meng.oneday.leetcode.editor.cn;

class CountHillValley2210 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了27.14% 的Java用户
     * @param nums
     * @return
     */
    public int countHillValley2210(int[] nums) {
        int res = 0;
        for(int i = 1 ; i < nums.length -1 ; i++){
            if(nums[i] == nums[i-1]){
                continue;
            }
            //寻找左侧的值
            int l = i - 1;
            while (l >=0 && nums[l] == nums[i]){
                l--;
            }
            //寻找右侧的值
            int r = i + 1;
            while (r < nums.length && nums[r] == nums[i]){
                r++;
            }
            if (l < 0 || r >= nums.length){
                continue;
            }
            if ((nums[l] < nums[i] && nums[r] < nums[i]) || (nums[l] > nums[i] && nums[r] > nums[i])){
                res++;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.9 MB,击败了30.65% 的Java用户
     * @param nums
     * @return
     */
    public int countHillValleyOther1(int[] nums) {
        int ans = 0;
        int pre = nums[0]; // 上个连续相同段的元素
        for (int i = 1; i < nums.length - 1; i++) {
            int cur = nums[i]; // 当前连续相同段的元素
            int nxt = nums[i + 1]; // 下个连续相同段的元素
            if (cur == nxt) { // 同一段
                continue;
            }
            // 注意 pre 可能等于 cur，比如 [1,1,2] 中 pre = cur = 1，nxt = 2
            if (pre != cur && (pre < cur) == (cur > nxt)) {
                ans++;
            }
            pre = cur;
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:40.8 MB,击败了52.76% 的Java用户
     * @param nums
     * @return
     */
    public int countHillValleyOther2(int[] nums) {
        int ans = 0;
        int preState = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int curState = Integer.compare(nums[i], nums[i + 1]);
            if (curState == 0) {
                continue;
            }
            if (preState == -curState) {
                ans++;
            }
            preState = curState;
        }
        return ans;
    }

}
