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
    public int longestEqualSubarray(List<Integer> nums, int k) {
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
}
//leetcode submit region end(Prohibit modification and deletion)
