package com.meng.thinking.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class FindIntersectionValues2956 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了90.48% 的Java用户
     * 	内存消耗:44.5 MB,击败了44.97% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] findIntersectionValuesMy(int[] nums1, int[] nums2) {
        int [] res = new int[2];
        int[] cache = new int[101];
        for (int num : nums2){
            cache[num] = 1;
        }
        int temp = 0;
        for (int num : nums1){
            if(cache[num]== 1){
                temp++;
            }
        }
        res[0] = temp;
        Arrays.fill(cache,0);
        for (int num : nums1){
            cache[num] = 1;
        }
        temp = 0;
        for (int num : nums2){
            if(cache[num]== 1){
                temp++;
            }
        }
        res[1] = temp;
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:7 ms,击败了60.32% 的Java用户
     * 	内存消耗:44.5 MB,击败了51.85% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int x : nums1) {
            set1.add(x);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int x : nums2) {
            set2.add(x);
        }

        int[] ans = new int[2];
        for (int x : nums1) {
            if (set2.contains(x)) {
                ans[0]++;
            }
        }
        for (int x : nums2) {
            if (set1.contains(x)) {
                ans[1]++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
