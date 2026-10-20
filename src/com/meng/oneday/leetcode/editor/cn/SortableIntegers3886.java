package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class SortableIntegers3886 {
    /**
     * 解答成功:
     * 	执行耗时:70 ms,击败了64.29% 的Java用户
     * 	内存消耗:79.4 MB,击败了28.57% 的Java用户
     * @param nums
     * @return
     */
    public int sortableIntegers3886(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 1 ; i <= n ; i++){
            if (n % i == 0){
                if (check(nums,i)){
                    res += i;
                }
            }
        }
        return res;
    }

    private boolean check(int[] nums, int step) {
        int pre = -1;
        for (int i = 0 ; i < nums.length ; i+=step){
            int[] max = check(nums,i,i+step);
            if (max[0] == -1 || max[0] < pre){
                return false;
            }
            pre = max[1];
        }
        return true;
    }

    private int[] check(int[] nums, int start, int end) {
        if (end -start == 1){
            return new int[]{nums[start],nums[start]};
        }
        //最多存在两段非递减数据
        List<Integer> list = new ArrayList<>();
        for (int i = start ; i < end ; i++){
            if (i == start){
                list.add(nums[i]);
            }else {
                //新开始一段
                if (nums[i] < nums[i-1]){
                    //上一段的末尾
                    list.add(nums[i-1]);
                    //新一段的开头
                    list.add(nums[i]);
                }
                if (list.size() > 4){
                    return new int[]{-1,-1};
                }
            }
        }
        //最后一段的末尾
        list.add(nums[end-1]);
        if (list.size() > 4){
            return new int[]{-1,-1};
        }
        if (list.size() == 2){
            return new int[]{list.get(0),list.get(1)};
        }
        if (list.get(0) >= list.get(3) || list.get(2) >= list.get(1)){
            return new int[]{Math.min(list.get(0),list.get(2)),Math.max(list.get(1),list.get(3))};
        }
        return new int[]{-1,-1};
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了100.00% 的Java用户
     * 	内存消耗:69.1 MB,击败了78.57% 的Java用户
     * @param nums
     * @return
     */
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] nextDec = new int[n]; // nums[nextDec[i]] > nums[nextDec[i] + 1]
        nextDec[n - 1] = n;
        int p = n;
        // 对于每个 i，记录下一个递减的位置
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                p = i;
            }
            nextDec[i] = p;
        }

        int ans = 0;
        // 枚举 n 的因子 k
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                ans += solve(k, nums, nextDec);
                if (k * k < n) {
                    ans += solve(n / k, nums, nextDec);
                }
            }
        }
        return ans;
    }

    private int solve(int k, int[] nums, int[] nextDec) {
        int lastMax = 0; // 上一段的最大值
        for (int r = k - 1; r < nums.length; r += k) {
            int l = r - k + 1;
            int m = nextDec[l];
            if (m >= r) {
                // [l, r] 是递增的，最小值为 nums[l]，最大值为 nums[r]
                // 最小值必须 >= 上一段的最大值
                if (nums[l] < lastMax) {
                    return 0;
                }
                lastMax = nums[r];
            } else {
                // [l, m] 是第一段，[m+1, r] 是第二段
                // 第二段必须是递增的，且第二段的最小值必须 >= 上一段的最大值，且第二段的最大值必须 <= 第一段的最小值
                if (nextDec[m + 1] < r || nums[m + 1] < lastMax || nums[r] > nums[l]) {
                    return 0;
                }
                lastMax = nums[m];
            }
        }
        return k;
    }

}
