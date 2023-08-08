package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class IncreasingTriplet334 {
    /**
     * 执行用时：
     * 73 ms
     * , 在所有 Java 提交中击败了
     * 5.75%
     * 的用户
     * 内存消耗：
     * 128.6 MB
     * , 在所有 Java 提交中击败了
     * 23.73%
     * 的用户
     * 通过测试用例：
     * 80 / 80
     * @param nums
     * @return
     * 执行用时：
     * 9 ms
     * , 在所有 Java 提交中击败了
     * 5.75%
     * 的用户
     * 内存消耗：
     * 121.9 MB
     * , 在所有 Java 提交中击败了
     * 74.47%
     * 的用户
     * 通过测试用例：
     * 80 / 80
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length ;
        if (len >= 3){
            int min = Integer.MAX_VALUE;
            for(int i = 0 ; i < len  ; i ++){
                if (nums[i] >= min){
                    continue;
                }
                min = nums[i];
                int max = Integer.MAX_VALUE;
                for(int j = i+1 ; j < len  ; j ++){
                    if (nums[j] <= nums[i] || nums[j] >= max){
                        continue;
                    }
                    max = nums[j];
                    for(int k = j+1 ; k < len  ; k ++){
                        if (nums[k] > nums[j]){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 双向遍历
     * @param nums
     * @return
     */
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 贪心算法
     * @param nums
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 91.71%
     * 的用户
     * 内存消耗：
     * 126.2 MB
     * , 在所有 Java 提交中击败了
     * 53.44%
     * 的用户
     * 通过测试用例：
     * 80 / 80
     */
    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {2,5,1,10};
        IncreasingTriplet334 demo = new IncreasingTriplet334();
        System.out.println(demo.increasingTriplet2(nums));
    }

}

