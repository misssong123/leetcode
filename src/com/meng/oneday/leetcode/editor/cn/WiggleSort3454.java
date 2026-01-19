package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;

class WiggleSort3454 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了65.13% 的Java用户
     * 	内存消耗:48 MB,击败了54.36% 的Java用户
     * @param nums
     */
    public void wiggleSort3454(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] res = new int[nums.length];
        int r = (len + 1) /2;
        int l = r - 1;
        int end = len - 1;
        for(int i = 0 ; i < len ; i+=2){
            res[i] = nums[l--];
            if(end >= r){
                res[i+1] = nums[end--];
            }
        }
        System.arraycopy(res,0,nums,0,len);
    }
    int[] nums;
    int n;
    int qselect(int l, int r, int k) {
        if (l == r) return nums[l];
        int x = nums[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(i, j);
        }
        int cnt = j - l + 1;
        if (k <= cnt) return qselect(l, j, k);
        else return qselect(j + 1, r, k - cnt);
    }
    void swap(int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }
    int getIdx(int x) {
        return (2 * x + 1) % (n | 1);
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了65.13% 的Java用户
     * 	内存消耗:48.6 MB,击败了8.72% 的Java用户
     * @param _nums
     */
    public void wiggleSort(int[] _nums) {
        nums = _nums;
        n = nums.length;
        int x = qselect(0, n - 1, n + 1 >> 1);
        int l = 0, r = n - 1, loc = 0;
        while (loc <= r) {
            if (nums[getIdx(loc)] > x) swap(getIdx(loc++), getIdx(l++));
            else if (nums[getIdx(loc)] < x) swap(getIdx(loc), getIdx(r--));
            else loc++;
        }
    }

}
