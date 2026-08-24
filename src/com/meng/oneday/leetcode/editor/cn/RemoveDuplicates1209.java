package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class RemoveDuplicates1209 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了63.57% 的Java用户
     * 	内存消耗:48.9 MB,击败了10.71% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates1209(String s, int k) {
        Deque<int[]> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            if(stack.isEmpty() || stack.peekLast()[0] != c){
                stack.add(new int[]{c,1});
            }else{
                if((stack.peekLast()[1] + 1) == k){
                    int num = k - 1;
                    while (num > 0){
                        stack.pollLast();
                        num--;
                    }
                }else{
                    stack.add(new int[]{c,stack.peekLast()[1]+1});
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append((char)stack.pop()[0]);
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了63.57% 的Java用户
     * 	内存消耗:46.4 MB,击败了55.00% 的Java用户
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {
        // 更快的写法见【Java 数组】
        ArrayList<int[]> st = new ArrayList<>();
        st.add(new int[]{0, 0}); // 加个哨兵，无需判断栈是否为空

        for (char ch : s.toCharArray()) {
            if (st.get(st.size()-1)[0] != ch) { // ch 与栈顶字母不同
                st.add(new int[]{ch, 1}); // 创建一个新的 pair，计数器从 1 开始
            } else if (st.get(st.size()-1)[1] == k - 1) { // 连续 k 个相同字母
                st.remove(st.size()-1); // 消除
            } else { // 相同但无法消除
                st.get(st.size()-1)[1]++; // 只需把计数器增加 1
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int[] p : st) {
            for (int i = 0; i < p[1]; i++){
                ans.append((char) p[0]);
            }
        }
        return ans.toString();
    }

}
