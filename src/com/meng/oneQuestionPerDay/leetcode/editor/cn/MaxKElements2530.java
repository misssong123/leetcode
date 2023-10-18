package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxKElements2530 {
    /**
     * 解答成功:
     * 	执行耗时:118 ms,击败了62.79% 的Java用户
     * 	内存消耗:59.3 MB,击败了37.21% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxKElements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->{return  b - a;});
        long res = 0;
        for(int num : nums){
            queue.add(num);
        }
        while (k > 0){
            Integer poll = queue.poll();
            if (poll == 0){
                break;
            }
            res += poll;
            queue.add((poll+2)/3);
            k--;
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:143 ms,击败了20.93% 的Java用户
     * 	内存消耗:58.3 MB,击败了83.72% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int num : nums) {
            q.offer(num);
        }
        long ans = 0;
        for (int i = 0; i < k; ++i) {
            int x = q.poll();
            ans += x;
            q.offer((x + 2) / 3);
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
