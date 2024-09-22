package com.meng.interview150.leetcode.editor.cn;

class Interview112MaxSubArray {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:55.9 MB,击败了68.11% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubArrayMy(int[] nums) {
        int ans = nums[0];
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++) {
            pre = nums[i] + Math.max(pre,0);
            ans = Math.max(ans,pre);
        }
        return ans;
    }


    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了3.89% 的Java用户
     * 	内存消耗:55.6 MB,击败了92.62% 的Java用户
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
}
