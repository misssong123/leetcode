package com.meng.oneday.leetcode.editor.cn;

class MaxSumTwoNoOverlap1031 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了21.52% 的Java用户
     * 	内存消耗:43.6 MB,击败了6.33% 的Java用户
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    public int maxSumTwoNoOverlap1031(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        //计算secondLen的前后最大值
        int[]maxLeft = new int[n];
        int[] maxRight = new int[n];
        int sum = 0;
        //左侧最大值计算
        for (int i = 0 ; i < n ; i++){
            sum += nums[i];
            if (i >= secondLen -1){
                maxLeft[i] = i -1 >= 0 ? Math.max(maxLeft[i-1],sum) : sum;
                sum -= nums[i-secondLen+1];
            }
        }
        //右侧最大值计算
        sum = 0;
        for (int i = n-1 ; i >= 0 ; i--){
            sum += nums[i];
            if (i <= n - secondLen){
                maxRight[i] = i + 1 < n ?Math.max(maxRight[i+1],sum):sum ;
                sum -= nums[i+secondLen-1];
            }
        }
        int res = 0;
        sum = 0;
        for (int i = 0 ; i < n ; i++){
            sum += nums[i];
            if (i >= firstLen -1){
                int max = 0;
                if (i - firstLen >= 0){
                    max = Math.max(max,maxLeft[i-firstLen]);
                }
                if (i + 1 < n){
                    max = Math.max(max,maxRight[i+1]);
                }
                res = Math.max(res,sum + max);
                sum -= nums[i-firstLen+1];
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.3 MB,击败了59.49% 的Java用户
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    public int maxSumTwoNoOverlapOther(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + nums[i]; // 计算 nums 的前缀和
        return Math.max(f(s, firstLen, secondLen), f(s, secondLen, firstLen));
    }

    private int f(int[] s, int firstLen, int secondLen) {
        int maxSumA = 0, res = 0;
        for (int i = firstLen + secondLen; i < s.length; ++i) {
            maxSumA = Math.max(maxSumA, s[i - secondLen] - s[i - secondLen - firstLen]);
            res = Math.max(res, maxSumA + s[i] - s[i - secondLen]);
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:43.4 MB,击败了35.44% 的Java用户
     * @param nums
     * @param firstLen
     * @param secondLen
     * @return
     */
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + nums[i]; // 计算 nums 的前缀和
        int ans = 0, maxSumA = 0, maxSumB = 0;
        for (int i = firstLen + secondLen; i <= n; ++i) {
            maxSumA = Math.max(maxSumA, s[i - secondLen] - s[i - secondLen - firstLen]);
            maxSumB = Math.max(maxSumB, s[i - firstLen] - s[i - firstLen - secondLen]);
            ans = Math.max(ans, Math.max(maxSumA + s[i] - s[i - secondLen],  // 左 a 右 b
                    maxSumB + s[i] - s[i - firstLen])); // 左 b 右 a
        }
        return ans;
    }

}
