package com.meng.oneday.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

class RobotWithString2434 {
    /**
     * 解答成功:
     * 	执行耗时:71 ms,击败了84.38% 的Java用户
     * 	内存消耗:53.6 MB,击败了77.08% 的Java用户
     * @param s
     * @return
     */
    public String robotWithString2434(String s) {
        int[] cnt = new int[26];
        int index = 25;
        for(char c : s.toCharArray()){
            int cur = c - 'a';
            cnt[cur]++;
            if (index > cur){
                index = cur;
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()){
            //优先选取累计字符
            while(!stack.isEmpty() && stack.peek() - 'a' <= index){
                sb.append(stack.pop());
            }
            int cur = c - 'a';
            //当前字符不是目标字符
            if (cur > index){
                stack.push(c);
                cnt[cur]--;
            }else{
                sb.append(c);
                cnt[cur]--;
                while(index < 26 && cnt[index] == 0){
                    index++;
                }
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:74 ms,击败了61.98% 的Java用户
     * 	内存消耗:54.2 MB,击败了58.86% 的Java用户
     * @param s
     * @return
     */
    public String robotWithString(String s) {
        int n = s.length();
        // 计算后缀最小值
        char[] sufMin = new char[n + 1];
        sufMin[n] = Character.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            sufMin[i] = (char) Math.min(sufMin[i + 1], s.charAt(i));
        }

        StringBuilder ans = new StringBuilder(n);
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st.push(s.charAt(i));
            while (!st.isEmpty() && st.peek() <= sufMin[i + 1]) {
                ans.append(st.pop());
            }
        }
        return ans.toString();
    }
}
