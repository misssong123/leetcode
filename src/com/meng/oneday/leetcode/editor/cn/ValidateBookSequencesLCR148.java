package com.meng.oneday.leetcode.editor.cn;

import java.util.*;

class ValidateBookSequencesLCR148 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了58.09% 的Java用户
     * 	内存消耗:43.5 MB,击败了44.85% 的Java用户
     * @param putIn
     * @param takeOut
     * @return
     */
    public boolean validateBookSequencesLCR148(int[] putIn, int[] takeOut) {
        Set<Integer> set = new HashSet<>();
        Deque<Integer> deque = new LinkedList<>();
        int m = putIn.length,n= takeOut.length;
        int i = 0 , j = 0;
        while(j < n){
            int target = takeOut[j];
            while (i<m &&!set.contains(target)){
                set.add(putIn[i]);
                deque.offerLast(putIn[i]);
                i++;
            }
            if (!set.contains(target)){
                return false;
            }
            while (!deque.isEmpty() && deque.peekLast() != target){
                set.remove(deque.pollLast());
            }
            if (deque.isEmpty()){
                return false;
            }
            deque.pollLast();
            j++;
        }
        return true;
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了20.59% 的Java用户
     * 	内存消耗:43.4 MB,击败了66.54% 的Java用户
     * @param putIn
     * @param takeOut
     * @return
     */
    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : putIn) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == takeOut[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

}
