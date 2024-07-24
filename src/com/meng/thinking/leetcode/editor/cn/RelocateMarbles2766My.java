package com.meng.thinking.leetcode.editor.cn;

import java.util.*;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class RelocateMarbles2766My {
    /**
     *解答成功:
     * 	执行耗时:58 ms,击败了58.14% 的Java用户
     * 	内存消耗:61.2 MB,击败了73.26% 的Java用户
     * @param nums
     * @param moveFrom
     * @param moveTo
     * @return
     */
    public List<Integer> relocateMarblesMy(int[] nums, int[] moveFrom, int[] moveTo) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int num : nums){
            cache.put(num,1);
        }
        for (int i = 0 ; i < moveFrom.length ; i++){
            if (moveFrom[i] == moveTo[i]){
                continue;
            }
            cache.put(moveTo[i],1);
            cache.remove(moveFrom[i]);
        }
        return cache.keySet().stream().sorted().collect(Collectors.toList());
    }

    /**
     * 解答成功:
     * 	执行耗时:53 ms,击败了80.23% 的Java用户
     * 	内存消耗:62.2 MB,击败了36.05% 的Java用户
     * @param nums
     * @param moveFrom
     * @param moveTo
     * @return
     */
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> set = new HashSet<>(nums.length); // 预分配空间，效率更高
        for (int x : nums) {
            set.add(x);
        }

        for (int i = 0; i < moveFrom.length; i++) {
            set.remove(moveFrom[i]);
            set.add(moveTo[i]);
        }

        List<Integer> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
