package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStoneSum1962 {
    /**
     * 解答成功:
     * 	执行耗时:462 ms,击败了31.68% 的Java用户
     * 	内存消耗:58.8 MB,击败了5.94% 的Java用户
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSumMy(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : piles){
            queue.add(num);
        }
        for(int i = 0 ; i < k ; i++){
            queue.add((queue.poll() + 1) / 2);
        }
        int sum = 0;
        while (!queue.isEmpty()){
            sum += queue.poll();
        }
        return sum;
    }

    /**
     * 解答成功:
     * 	执行耗时:468 ms,击败了26.73% 的Java用户
     * 	内存消耗:58.8 MB,击败了5.94% 的Java用户
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSum1(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));
        for (int pile : piles) {
            pq.offer(pile);
        }
        for (int i = 0; i < k; i++) {
            int pile = pq.poll();
            pile -= pile / 2;
            pq.offer(pile);
        }
        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    /**
     * 解答成功:
     * 	执行耗时:120 ms,击败了84.16% 的Java用户
     * 	内存消耗:57.6 MB,击败了17.82% 的Java用户
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSum(int[] piles, int k) {
        heapify(piles); // 原地堆化（最大堆）
        while (k-- > 0 && piles[0] > 0) {
            piles[0] -= piles[0] / 2; // 直接修改堆顶
            sink(piles, 0); // 堆化（只需要把 piles[0] 下沉）
        }

        int ans = 0;
        for (int x : piles) {
            ans += x;
        }
        return ans;
    }

    // 原地堆化（最大堆）
    // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2*i+1], h[2*i+2])
    private void heapify(int[] h) {
        // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
        // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
        for (int i = h.length / 2 - 1; i >= 0; i--) {
            sink(h, i);
        }
    }

    // 把 h[i] 不断下沉，每次找左右儿子中最大的交换，直到 i 的左右儿子都 <= h[i] 时停止
    private void sink(int[] h, int i) {
        int n = h.length;
        while (2 * i + 1 < n) {
            int j = 2 * i + 1; // i 的左儿子
            if (j + 1 < n && h[j + 1] > h[j]) { // i 的右儿子比 i 的左儿子大
                j++;
            }
            if (h[j] <= h[i]) { // 说明 i 的左右儿子都 <= h[i]，停止下沉
                break;
            }
            swap(h, i, j); // 下沉
            i = j;
        }
    }

    // 交换 h[i] 和 h[j]
    private void swap(int[] h, int i, int j) {
        int tmp = h[i];
        h[i] = h[j];
        h[j] = tmp;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
