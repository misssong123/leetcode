package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class ProductExceptSelf238 {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 22.53%
     * 的用户
     * 内存消耗：
     * 50.4 MB
     * , 在所有 Java 提交中击败了
     * 46.37%
     * 的用户
     * 通过测试用例：
     * 22 / 22
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int [] res = new int[nums.length];
        long total = 1L;
        int zeroNum = 0;
        for(int num : nums){
            if (num == 0&&zeroNum==0){
                zeroNum ++;
                continue;
            }
            total *= num;
        }
        if (zeroNum > 1){
            return res;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if (nums[i] == 0){
                res[i] = (int)total;
            }else {
                res[i] = zeroNum >0 ?0 :(int)total / nums[i];
            }
        }
        return res;
    }

    /**
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 50.3 MB
     * , 在所有 Java 提交中击败了
     * 49.75%
     * 的用户
     * 通过测试用例：
     * 22 / 22
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

}

