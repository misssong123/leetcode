package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CheckArithmeticSubarrays1630 {
    /**
     * 执行用时：
     * 19 ms
     * , 在所有 Java 提交中击败了
     * 46.83%
     * 的用户
     * 内存消耗：
     * 41.7 MB
     * , 在所有 Java 提交中击败了
     * 96.83%
     * 的用户
     * 通过测试用例：
     * 102 / 102
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length ;
        List<Boolean> res = new ArrayList<>();
        for(int i = 0 ; i < n ; i++){
            int len = r[i]-l[i]+1;
            if (len<=2){
                res.add(true);
                continue;
            }
            int[] temp = new int[len];
            System.arraycopy(nums,l[i],temp,0,len);
            Arrays.sort(temp);
            int diff = temp[1] - temp[0];
            boolean flag = true;
            for(int index = 2 ; index < len && flag ; index++ ){
                if (temp[index] - temp[index-1] != diff){
                    res.add(false);
                    flag = false;
                }
            }
            if (flag){
                res.add(true);
            }
        }
        return res;
    }

    /**
     * 方法一：多次遍历 + 枚举
     *
     * 思路与算法
     *
     * 要想判断某个子数组是否可以重新排列成等差数列，最简单的方法是建立一个等长度的临时数组，将子数组进行拷贝并排序。对于排完序后的子数组，我们只需要进行一次遍历，判断相邻两个元素的差值是否保持不变，就能判断出它是否为等差数列。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/arithmetic-subarrays/solution/deng-chai-zi-shu-zu-by-leetcode-solution-pmrp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param l
     * @param r
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 97.22%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 91.67%
     * 的用户
     * 通过测试用例：
     * 102 / 102
     */
    public List<Boolean> checkArithmeticSubarrays1(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<Boolean>();
        for (int i = 0; i < n; ++i) {
            int left = l[i], right = r[i];
            int minv = nums[left], maxv = nums[left];
            for (int j = left + 1; j <= right; ++j) {
                minv = Math.min(minv, nums[j]);
                maxv = Math.max(maxv, nums[j]);
            }

            if (minv == maxv) {
                ans.add(true);
                continue;
            }
            if ((maxv - minv) % (right - left) != 0) {
                ans.add(false);
                continue;
            }

            int d = (maxv - minv) / (right - left);
            boolean flag = true;
            boolean[] seen = new boolean[right - left + 1];
            for (int j = left; j <= right; ++j) {
                if ((nums[j] - minv) % d != 0) {
                    flag = false;
                    break;
                }
                int t = (nums[j] - minv) / d;
                if (seen[t]) {
                    flag = false;
                    break;
                }
                seen[t] = true;
            }
            ans.add(flag);
        }
        return ans;
    }

}

