package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestEqualSubarray2831 {
    /**
     * 解答成功:
     * 	执行耗时:92 ms,击败了26.03% 的Java用户
     * 	内存消耗:73.8 MB,击败了5.03% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int longestEqualSubarrayMy(List<Integer> nums, int k) {
        Map<Integer,List<Integer>> cache = new HashMap<>();
        for(int i = 0 ;i < nums.size() ; i++){
            int num =  nums.get(i);
            cache.computeIfAbsent(num,x->new ArrayList<>()).add(i);
        }
        int ans = 1;
        for(List<Integer> list : cache.values()){
            //当前数组的长度小于已有最大长度不处理
            if (list.size()<=ans){
                continue;
            }
            int first = 0;
            int last = 1;
            while (last <list.size()){
                int count = last -first+1;
                int diff = list.get(last) - list.get(first) + 1;
                if (diff <= count + k){
                    ans = Math.max(ans,count);
                    last++;
                }else {
                    first++;
                }
            }
        }
        return ans;
    }
    /**
     * 执行用时分布
     * 101
     * ms
     * 击败
     * 16.04%
     * 使用 Java 的用户
     * 消耗内存分布
     * 63.15
     * MB
     * 击败
     * 66.58%
     * 使用 Java 的用户
     */
    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            cnt.put(nums.get(j), cnt.getOrDefault(nums.get(j), 0) + 1);
            /*当前区间中，无法以 nums[i] 为等值元素构成合法等值数组*/
            while (j - i + 1 - cnt.get(nums.get(i)) > k) {
                cnt.put(nums.get(i), cnt.get(nums.get(i)) - 1);
                i++;
            }
            ans = Math.max(ans, cnt.get(nums.get(j)));
        }
        return ans;
    }

}
