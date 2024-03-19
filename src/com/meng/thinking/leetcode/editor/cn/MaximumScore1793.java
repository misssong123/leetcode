package com.meng.thinking.leetcode.editor.cn;

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumScore1793 {
    /**
     * 思路错误
     * 长短木板取得是两边的最小值，
     * 这个问题需要取区域内的最小值，会导致长短木板无法适用于本题的场景
     * @param nums
     * @param k
     * @return
     */
    public int maximumScoreMy(int[] nums, int k) {
        int left = 0 , right = nums.length -1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0 ; i < nums.length ; i++){
            queue.add(nums[i]);
        }
        int res = 0;
        while (left <= right&&!queue.isEmpty()){
            res = Math.max(res,(right-left+1)*queue.peek());
            int index = 0;
            if (left== k){
                index =  right;
                right--;
            }else if (right == k){
                index = left;
                left++;
            }else {
                if (nums[left] < nums[right]){
                    index = left;
                    left++;
                }else {
                    index =  right;
                    right--;
                }
            }
            queue.remove(nums[index]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了68.92% 的Java用户
     * 	内存消耗:56.4 MB,击败了37.84% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumScore1(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int ans = 0;
        for (int i = nums[k];; --i) {
            while (left >= 0 && nums[left] >= i) {
                --left;
            }
            while (right < n && nums[right] >= i) {
                ++right;
            }
            ans = Math.max(ans, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.1 MB,击败了51.35% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int left = k - 1, right = k + 1;
        int ans = 0;
        for (int i = nums[k];;) {
            while (left >= 0 && nums[left] >= i) {
                --left;
            }
            while (right < n && nums[right] >= i) {
                ++right;
            }
            ans = Math.max(ans, (right - left - 1) * i);
            if (left == -1 && right == n) {
                break;
            }
            i = Math.max((left == -1 ? -1 : nums[left]), (right == n ? -1 : nums[right]));
            if (i == -1) {
                break;
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
