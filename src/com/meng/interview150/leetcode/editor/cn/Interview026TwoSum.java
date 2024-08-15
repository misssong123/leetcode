package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview026TwoSum {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.05% 的Java用户
     * 	内存消耗:46.4 MB,击败了17.72% 的Java用户
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0 ,right = numbers.length-1;
        while (left<right){
            int mid = numbers[left]+numbers[right];
            if(mid == target){
                return new int[]{left+1,right+1};
            }else if (mid < target){
                left++;
            }else {
                right--;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
