package com.meng.interview150.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Interview013ProductExceptSelf {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:56.6 MB,击败了5.07% 的Java用户
     * @param nums
     * @return
     */
    public int[] productExceptSelfMy(int[] nums) {
        int zero = 0;
        int sum = 1;
        for(int num : nums){
            if(num == 0){
                zero++;
                if (zero>1){
                    break;
                }
                continue;
            }
            sum *= num;
        }
        for (int i = 0; i < nums.length; i++) {
            if(zero>1){
                nums[i] =0;
            }else{
                nums[i] = nums[i]==0?sum:zero>0?0:sum/nums[i];
            }
        }
        return nums;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了81.00% 的Java用户
     * 	内存消耗:54.1 MB,击败了66.07% 的Java用户
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
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
