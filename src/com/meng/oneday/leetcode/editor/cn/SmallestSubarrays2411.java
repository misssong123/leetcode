package com.meng.oneday.leetcode.editor.cn;

class SmallestSubarrays2411 {
    /**
     * 超时
     * @param nums
     * @return
     */
    public int[] smallestSubarraysError(int[] nums) {
        int len = nums.length;
        int[] target = new int[len];
        for(int i = len - 1 ; i >=0 ; i--){
            target[i] = nums[i];
            if (i != len - 1){
                target[i] |= target[i+1];
            }
        }
        int[] res = new int[len];
        for(int i = 0 ; i < len ;i++){
            //剪枝
            if(i > 0&&nums[i-1]==0&&target[i] != 0){
                res[i] = res[i-1] -1;
                continue;
            }
            int temp = 0;
            for(int j = i ; j < len ; j++){
                temp |= nums[j];
                if (temp == target[i]){
                    res[i] = j - i + 1;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:77 ms,击败了8.45% 的Java用户
     * 	内存消耗:60.2 MB,击败了63.38% 的Java用户
     * @param nums
     * @return
     */
    public int[] smallestSubarrays2411(int[] nums) {
        int[] target = new int[32];
        for(int num : nums){
            String binaryString = Integer.toBinaryString(num);
            int len = binaryString.length();
            for(int i = len -1 ; i>=0;i--){
                if (binaryString.charAt(i) == '1'){
                    target[len - 1 - i]++;
                }
            }
        }
        int[] temp = new int[32];
        int len = nums.length;
        int[] res =  new int[len];
        int right = 0;
        for(int i = 0 ; i < len ; i++){
            while (right < len && !equalVal(target,temp)){
                String binaryString = Integer.toBinaryString(nums[right]);
                int l = binaryString.length();
                for(int j = l -1 ; j>=0;j--){
                    if (binaryString.charAt(j) == '1'){
                        temp[l - 1 - j]++;
                    }
                }
                right++;
            }
            res[i] = Math.max(right - i,1);
            //移除
            String binaryString = Integer.toBinaryString(nums[i]);
            int l = binaryString.length();
            for(int j = l -1 ; j>=0;j--){
                if (binaryString.charAt(j) == '1'){
                    temp[l - 1 - j]--;
                    target[l - 1 - j]--;
                }
            }
        }
        return res;
    }
    private boolean equalVal(int[] a ,int[] b){
        for(int i = 0 ; i < 32 ; i++){
            if (a[i] >0 &&b[i] <=0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:60 MB,击败了85.91% 的Java用户
     * @param nums
     * @return
     */
    public int[] smallestSubarraysOther(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) { // 计算右端点为 i 的子数组的或值
            int x = nums[i];
            ans[i] = 1; // 子数组的长度至少是 1
            // 循环直到 nums[j] 无法增大，其左侧元素也无法增大
            for (int j = i - 1; j >= 0 && (nums[j] | x) != nums[j]; j--) {
                nums[j] |= x; // nums[j] 增大，现在 nums[j] = 原数组 nums[j] 到 nums[i] 的或值
                ans[j] = i - j + 1; // nums[j] 最后一次增大时的子数组长度就是答案
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了100.00% 的Java用户
     * 	内存消耗:60.2 MB,击败了67.60% 的Java用户
     * @param nums
     * @return
     */
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[n - 1] = 1;
        if (n == 1) {
            return ans;
        }

        // 保证栈中至少有两个数，方便判断窗口右端点是否要缩小
        nums[n - 1] |= nums[n - 2];
        int leftOr = 0, right = n - 1, bottom = n - 2;
        for (int left = n - 2; left >= 0; left--) {
            leftOr |= nums[left];
            // 子数组 [left,right] 的或值 = 子数组 [left,right-1] 的或值，说明窗口右端点可以缩小
            while (right > left && (leftOr | nums[right]) == (leftOr | nums[right - 1])) {
                right--;
                // 栈中只剩一个数
                if (bottom >= right) {
                    // 重新构建一个栈，栈底为 left，栈顶为 right
                    for (int i = left + 1; i <= right; i++) {
                        nums[i] |= nums[i - 1];
                    }
                    bottom = left;
                    leftOr = 0;
                }
            }
            ans[left] = right - left + 1;
        }
        return ans;
    }


}
