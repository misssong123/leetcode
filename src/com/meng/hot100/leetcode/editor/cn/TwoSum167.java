package com.meng.hot100.leetcode.editor.cn;

class TwoSum167 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.65% 的Java用户
     * 	内存消耗:46.5 MB,击败了28.34% 的Java用户
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum167(int[] numbers, int target) {
        int l = 0 ,r= numbers.length - 1;
        while (true){
            int sum = numbers[l] + numbers[r];
            if (sum == target){
                return new int[]{l+1,r+1};
            }else if (sum < target){
                l++;
            }else{
                r--;
            }
        }
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.65% 的Java用户
     * 	内存消耗:46.1 MB,击败了88.21% 的Java用户
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (true) {
            int s = numbers[left] + numbers[right];
            if (s == target) {
                return new int[]{left + 1, right + 1}; // 题目要求下标从 1 开始
            }
            if (s > target) {
                right--;
            } else {
                left++;
            }
        }
    }
}
