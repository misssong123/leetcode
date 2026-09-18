package com.meng.oneday.leetcode.editor.cn;

class FindMagicIndex0803 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.9 MB,击败了6.12% 的Java用户
     * @param nums
     * @return
     */
    public int findMagicIndex0803(int[] nums) {
        int index =0;
        for(int num : nums){
            if (num == index){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:46.4 MB,击败了87.76% 的Java用户
     * @param nums
     * @return
     */
    public int findMagicIndexOther(int[] nums) {
        return getAnswer(nums, 0, nums.length - 1);
    }

    public int getAnswer(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, right);
    }
}
