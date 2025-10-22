package com.meng.top100.leetcode.editor.cn;

class ProductExceptSelf238 {
    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了86.08% 的Java用户
     * 内存消耗:54.1 MB,击败了85.06% 的Java用户
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf238(int[] nums) {
        int zeroIndex = -1, zeroCount = 0;
        int[] res = new int[nums.length];
        long sum = 1L;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                zeroCount++;
                zeroIndex = i;
                if (zeroCount > 1) {
                    break;
                }
            } else {
                sum *= num;
            }
        }
        //存在多个零
        if (zeroCount > 1) {
            return res;
        }
        //存在一个零
        if (zeroCount == 1) {
            res[zeroIndex] = (int) sum;
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = (int) (sum / nums[i]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了86.08% 的Java用户
     * 内存消耗:55.9 MB,击败了5.07% 的Java用户
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf238Pre(int[] nums) {
        int[] res = new int[nums.length];
        int[] pre = new int[nums.length];
        pre[0] = 1;
        //前缀和
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        int suf = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = pre[i] * suf;
            suf *= nums[i];
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了86.08% 的Java用户
     * 	内存消耗:54.1 MB,击败了85.06% 的Java用户
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }

        int pre = 1;
        for (int i = 0; i < n; i++) {
            // 此时 pre 为 nums[0] 到 nums[i-1] 的乘积，直接乘到 suf[i] 中
            suf[i] *= pre;
            pre *= nums[i];
        }

        return suf;
    }

}
