package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

class SumSubarrayMins907 {
    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了61.72% 的Java用户
     * 	内存消耗:49 MB,击败了100.00% 的Java用户
     * @param arr
     * @return
     */
    public int sumSubarrayMins907(int[] arr) {
        LinkedList<int[]> minStack = new LinkedList<>();
        long sum = 0L;
        int len = arr.length;
        for (int i = len -1 ; i >= 0 ; i--){
            int temp = arr[i];
            while (!minStack.isEmpty() && arr[i] < arr[minStack.peek()[0]]){
                minStack.pollFirst();
            }
            if(minStack.isEmpty()){
                temp += arr[i] * (len - 1 - i);
            }else{
                int index = minStack.peek()[0];
                int sufSum = minStack.peek()[1];
                temp += sufSum + (index - 1 - i) * arr[i];
            }
            sum += temp;
            minStack.addFirst(new int[]{i , temp});
        }
        return (int)(sum % 1000000007);
    }
    private static final long MOD = (long) 1e9 + 7;

    /**
     * 解答成功:
     * 	执行耗时:17 ms,击败了61.72% 的Java用户
     * 	内存消耗:51 MB,击败了89.95% 的Java用户
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 哨兵
        for (int r = 0; r <= arr.length; ++r) {
            int x = r < arr.length ? arr[r] : -1; // 假设 arr 末尾有个 -1
            while (st.size() > 1 && arr[st.peek()] >= x) {
                int i = st.pop();
                ans += (long) arr[i] * (i - st.peek()) * (r - i); // 累加贡献
            }
            st.push(r);
        }
        return (int) (ans % MOD);
    }

}
