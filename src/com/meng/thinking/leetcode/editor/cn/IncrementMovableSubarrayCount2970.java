package com.meng.thinking.leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class IncrementMovableSubarrayCount2970 {
    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了18.07% 的Java用户
     * 	内存消耗:42 MB,击败了79.52% 的Java用户
     * 遍历
     * @param nums
     * @return
     */
    public int incremovableSubarrayCountMy(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for(int i = 0 ; i <n ; i++){
            for (int j = i ; j  < n ; j++){
                //剩余元素小于等于1时，直接+1
                if (n-j+i-1 <=1){
                    ans++;
                    continue;
                }
                if (isIncrement(nums,i,j)){
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean isIncrement(int[] nums, int i, int j) {
        int pre = -1;
        for(int k = 0 ; k < nums.length ; k++){
            if (k>=i && k <= j){
                continue;
            }
            if (pre == -1){
                pre = k;
            }else if (nums[k] <= nums[pre]){
                return false;
            }else {
                pre = k;
            }
        }
        return true;
    }

    /**
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42 MB,击败了90.36% 的Java用户
     * @param a
     * @return
     */
    public int incremovableSubarrayCount(int[] a) {
        int n = a.length;
        int i = 0;
        while (i < n - 1 && a[i] < a[i + 1]) {
            i++;
        }
        if (i == n - 1) { // 每个非空子数组都可以移除
            return n * (n + 1) / 2;
        }

        int ans = i + 2; // 不保留后缀的情况，一共 i+2 个
        // 枚举保留的后缀为 a[j:]
        for (int j = n - 1; j == n - 1 || a[j] < a[j + 1]; j--) {
            while (i >= 0 && a[i] >= a[j]) {
                i--;
            }
            // 可以保留前缀 a[:i+1], a[:i], ..., a[:0] 一共 i+2 个
            ans += i + 2;
        }
        return ans;
    }



}
//leetcode submit region end(Prohibit modification and deletion)
