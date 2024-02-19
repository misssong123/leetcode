package com.meng.thinking.leetcode.editor.cn;

import java.util.PriorityQueue;

class MagicTowerLCP30 {
    /**
     * 解答成功:
     * 	执行耗时:10 ms,击败了93.58% 的Java用户
     * 	内存消耗:54.5 MB,击败了98.41% 的Java用户
     * @param nums
     * @return
     */
    public int magicTowerMy(int[] nums) {
        long sum = 0L;
        for (int num : nums){
            sum += num;
        }
        if (sum < 0){
            return -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        long temp = 0L;
        int ans = 0;
        for(int num : nums){
            if (num < 0){
                queue.add(num);
            }
            temp += num;
            if (temp < 0){
                Integer poll = queue.poll();
                temp -= poll;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:14 ms,击败了64.18% 的Java用户
     * 	内存消耗:57.7 MB,击败了8.94% 的Java用户
     * @param nums
     * @return
     */
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int ans = 0;
        long hp = 1, delay = 0;
        for (int num : nums) {
            if (num < 0) {
                pq.offer(num);
            }
            hp += num;
            if (hp <= 0) {
                ++ans;
                int curr = pq.poll();
                delay += curr;
                hp -= curr;
            }
        }
        hp += delay;
        return hp <= 0 ? -1 : ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
