package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumSumOfHeights2865 {
    /**
     * 解答成功:
     * 	执行耗时:5 ms,击败了77.75% 的Java用户
     * 	内存消耗:43.5 MB,击败了31.20% 的Java用户
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeightsMy(List<Integer> maxHeights) {
        int len = maxHeights.size();
        long[] pre = new long[len];
        long[] next = new long[len];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        //前置处理
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty()&&maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.poll();
            }
            pre[i] = (!stack.isEmpty() ?(pre[stack.peek()]+maxHeights.get(stack.peek()) + (long) (i - stack.peek() - 1) *maxHeights.get(i))
                    : (long) i *maxHeights.get(i));
            stack.push(i);
        }
        stack.clear();
        stack.push(len-1);
        //后置处理
        for (int i = len-2; i >= 0; i--) {
            while (!stack.isEmpty()&&maxHeights.get(stack.peek()) > maxHeights.get(i)){
                stack.poll();
            }
            next[i] = (!stack.isEmpty() ?(next[stack.peek()]+maxHeights.get(stack.peek()) + (long) (stack.peek() - i - 1) *maxHeights.get(i))
                    : (long) (len - i - 1) *maxHeights.get(i));
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0 ; i < len; i++){
            ans = Math.max(ans,pre[i]+next[i]+maxHeights.get(i));
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了96.99% 的Java用户
     * 	内存消耗:43.4 MB,击败了65.01% 的Java用户
     * @param maxHeights
     * @return
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                stack1.pop();
            }
            if (stack1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[stack1.peek()] + (long) (i - stack1.peek()) * maxHeights.get(i);
            }
            stack1.push(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                stack2.pop();
            }
            if (stack2.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
            }
            stack2.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
