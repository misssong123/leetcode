package com.meng.oneday.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class MaxRemoval3362 {
    /**
     * 1.存储包含当前下标的右区间的值
     * 2.选取右区间的最大值
     * 3. 记录影响的区间数据
     * 解答成功:
     * 	执行耗时:81 ms,击败了23.33% 的Java用户
     * 	内存消耗:89.1 MB,击败了60.00% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int maxRemoval3362(int[] nums, int[][] queries) {
        int len  = nums.length,n = queries.length;
        int[] diff = new int[len+1];
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->b-a);
        int sum = 0 ,l = 0;
        for(int i = 0 ; i < len ; i++){
            sum += diff[i];
            //1.存储包含当前下标的右区间的值
            while (l < n && queries[l][0] <= i){
                queue.offer(queries[l][1]);
                l++;
            }
            //2.选取右区间的最大值
            while (sum < nums[i] && !queue.isEmpty() && queue.peek() >= i){
                sum ++;
                diff[queue.poll()+1]--;
            }
            if (sum < nums[i]){
                return -1;
            }
        }
        return queue.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:74 ms,击败了47.78% 的Java用户
     * 	内存消耗:88.7 MB,击败了66.67% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int maxRemovalOther(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = nums.length;
        int[] diff = new int[n + 1];
        int sumD = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i];
            // 维护左端点 <= i 的区间
            while (j < queries.length && queries[j][0] <= i) {
                pq.add(queries[j][1]);
                j++;
            }
            // 选择右端点最大的区间
            while (sumD < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                sumD++;
                diff[pq.poll() + 1]--;
            }
            if (sumD < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:80 ms,击败了25.56% 的Java用户
     * 	内存消耗:90.6 MB,击败了54.44% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public int maxRemovalOfficial(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        int[] deltaArray = new int[nums.length + 1];
        int operations = 0;

        for (int i = 0, j = 0; i < nums.length; i++) {
            operations += deltaArray[i];
            while (j < queries.length && queries[j][0] == i) {
                heap.offer(queries[j][1]);
                j++;
            }
            while (operations < nums[i] && !heap.isEmpty() && heap.peek() >= i) {
                operations += 1;
                deltaArray[heap.poll() + 1] -= 1;
            }
            if (operations < nums[i]) {
                return -1;
            }
        }
        return heap.size();
    }

}
