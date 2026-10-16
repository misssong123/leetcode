package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class MaxWidthRamp962 {
    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了23.68% 的Java用户
     * 	内存消耗:59.2 MB,击败了73.68% 的Java用户
     * @param nums
     * @return
     */
    public int maxWidthRamp962(int[] nums) {
        List<int[]> list = new ArrayList<>();
        int res = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if (list.isEmpty()){
                list.add(new int[]{nums[i],i});
            }else{
                int index = maxWidthRamp(list,nums[i]);
                if (index != -1){
                    res = Math.max(res, i - index);
                }
                if (nums[i] < list.getLast()[0]){
                    list.add(new int[]{nums[i],i});
                }
            }
        }
        return res;
    }
    private int maxWidthRamp(List<int[]> list,int target) {
        int left = 0, right = list.size() - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (list.get(mid)[0] <= target) {
                res = list.get(mid)[1];
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 解答成功:
     * 	执行耗时:38 ms,击败了23.68% 的Java用户
     * 	内存消耗:59.1 MB,击败了73.68% 的Java用户
     * @param A
     * @return
     */
    public int maxWidthRampOfficial(int[] A) {
        int N = A.length;
        Integer[] B = new Integer[N];
        for (int i = 0; i < N; ++i)
            B[i] = i;

        Arrays.sort(B, Comparator.comparingInt(i -> A[i]));

        int ans = 0;
        int m = N;
        for (int i: B) {
            ans = Math.max(ans, i - m);
            m = Math.min(m, i);
        }

        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:54 ms,击败了18.42% 的Java用户
     * 	内存消耗:58.5 MB,击败了86.84% 的Java用户
     * @param A
     * @return
     */
    public int maxWidthRamp(int[] A) {
        int n = A.length;
        int maxWidth = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<n;i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }

        for (int i=n-1;i>=0;i--) {
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                int pos = stack.pop();
                maxWidth = Math.max(maxWidth, i - pos);
            }
        }
        return maxWidth;
    }


}
