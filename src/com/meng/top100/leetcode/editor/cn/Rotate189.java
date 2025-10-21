package com.meng.top100.leetcode.editor.cn;

class Rotate189 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了49.94% 的Java用户
     * 	内存消耗:56.2 MB,击败了66.41% 的Java用户
     * @param nums
     * @param k
     */
    public void rotate189(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        if (k != 0){
            int[] temp = new int[k];
            //保存最后K个元素
            for(int i = 0 ; i < k ; i++){
                temp[i] = nums[len - k + i];
            }
            //移动元素
            for(int i = len - k - 1 ; i >=0; i--){
                nums[i + k] = nums[i];
            }
            //将保存的元素放入数组
            for(int i = 0 ; i < k ; i++){
                nums[i] = temp[i];
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.5 MB,击败了18.61% 的Java用户
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n; // 轮转 k 次等于轮转 k % n 次
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}
