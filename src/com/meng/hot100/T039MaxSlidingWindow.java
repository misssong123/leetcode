package com.meng.hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

class T039MaxSlidingWindow {
    /**
     * 解答成功:
     * 	执行耗时:111 ms,击败了5.05% 的Java用户
     * 	内存消耗:58.5 MB,击败了24.01% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowMy(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len -k+1];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->{
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        //补充原始数据
        for(int i = 0 ; i < k -1 ; i++){
            queue.add(new int[]{nums[i],i});
        }
        for(int i = 0 ; i <= len - k;  i++){
            int index = i + k -1;
            queue.add(new int[]{nums[index],index});
            res[i] = queue.peek()[0];
            while (!queue.isEmpty()&&queue.peek()[1] <= i){
                queue.poll();
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:43 ms,击败了25.10% 的Java用户
     * 	内存消耗:63 MB,击败了6.87% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        System.out.println(deque);
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
