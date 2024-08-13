package com.meng.interview150.leetcode.editor.cn;

/**
 * 数组轮转K次相当于
 * 1.数组整体反转
 * 2.前K个元素反转
 * 3.后面N-K个元素反转
 */
class Interview006RotateMy {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了59.00% 的Java用户
     * 	内存消耗:56.1 MB,击败了43.44% 的Java用户
     * @param nums
     * @param k
     */
    public void rotateMy(int[] nums, int k) {
        k = k%nums.length;
        if (k==0){
            return;
        }
        int[] temp = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++){
            temp[(i+k)%nums.length] = nums[i];
        }
        System.arraycopy(temp,0,nums,0,temp.length);
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.2 MB,击败了30.86% 的Java用户
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n; // 轮转 k 次等于轮转 k%n 次
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
