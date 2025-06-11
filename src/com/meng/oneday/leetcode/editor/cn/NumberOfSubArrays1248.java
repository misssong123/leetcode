package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class NumberOfSubArrays1248 {
    /**
     * 1.计算所有奇数的下标
     * 2.依次挪移K个位置，计算左侧和右侧的偶数个数
     * 3.累加左侧和右侧的偶数个数乘积
     * 解答成功:
     * 	执行耗时:12 ms,击败了50.00% 的Java用户
     * 	内存消耗:54 MB,击败了43.76% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays1248(int[] nums, int k) {
        int size = nums.length;
        //奇数下标
        List<Integer> oddIndex = new ArrayList<>();
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] % 2 == 1){
                oddIndex.add(i);
            }
        }
        //不存在K个奇数，直接返回
        if (oddIndex.size() < k) {
            return 0;
        }
        int res = 0;
        //依次挪移符合条件的数据
        for(int i = 0 ; i < oddIndex.size() - k + 1 ; i++){
            int leftIndex = oddIndex.get(i);
            int rightIndex = oddIndex.get(i + k - 1);
            //左侧偶数个数
            int leftNum = i == 0 ? leftIndex + 1 : leftIndex - oddIndex.get(i - 1);
            //右侧偶数个数
            int rightNum = i + k == oddIndex.size()  ? size - rightIndex : oddIndex.get(i+k) - rightIndex;
            res += leftNum * rightNum;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了88.65% 的Java用户
     * 	内存消耗:51.7 MB,击败了90.40% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarraysOfficial1(int[] nums, int k) {
        int n = nums.length;
        int[] odd = new int[n + 2];
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; ++i) {
            if ((nums[i] & 1) != 0) {
                odd[++cnt] = i;
            }
        }
        odd[0] = -1;
        odd[++cnt] = n;
        for (int i = 1; i + k <= cnt; ++i) {
            ans += (odd[i] - odd[i - 1]) * (odd[i + k] - odd[i + k - 1]);
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了91.90% 的Java用户
     * 	内存消耗:51.6 MB,击败了92.77% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int odd = 0, ans = 0;
        cnt[0] = 1;
        for (int num : nums) {
            odd += num & 1;
            ans += odd >= k ? cnt[odd - k] : 0;
            cnt[odd] += 1;
        }
        return ans;
    }

}
