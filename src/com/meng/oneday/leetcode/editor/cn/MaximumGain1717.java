package com.meng.oneday.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class MaximumGain1717 {
    /**
     * 解答成功:
     * 	执行耗时:133 ms,击败了9.38% 的Java用户
     * 	内存消耗:54.3 MB,击败了6.25% 的Java用户
     * @param s
     * @param x
     * @param y
     * @return
     */
    public int maximumGain1717(String s, int x, int y) {
        //计算子串
        int res = 0;
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        String first = x >= y ? "ab" : "ba";
        String second = x >= y ? "ba" : "ab";
        Deque<Character> deque = new LinkedList<>();
        //计算最大得分的可能性
        for(char c : s.toCharArray()){
            if(!deque.isEmpty()&&isMatch(deque.peekLast(),c,first)){
                res += max;
                deque.pollLast();
            }else {
                deque.offerLast(c);
            }
        }
        //计算第二得分
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()){
            sb.append(deque.pollFirst());
        }
        //计算
        for(char c : sb.toString().toCharArray()){
            if(!deque.isEmpty()&&isMatch(deque.peekLast(),c,first)){
                res += max;
                deque.pollLast();
            }else if(!deque.isEmpty()&&isMatch(deque.peekLast(),c,second)){
                res += min;
                deque.pollLast();
            }else {
                deque.offerLast(c);
            }
        }
        return res;
    }
    private boolean isMatch(char c1,char c2,String target){
        return target.charAt(0) == c1 && target.charAt(1) == c2;
    }

    /**
     * 解答成功:
     * 	执行耗时:41 ms,击败了43.75% 的Java用户
     * 	内存消耗:44.7 MB,击败了46.88% 的Java用户
     * @param s
     * @param x
     * @param y
     * @return
     */
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
            s = s.replace('a', '\0').replace('b', 'a').replace('\0', 'b');
        }
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int cntA = 0, cntB = 0;
            while (i < s.length() && (s.charAt(i) == 'a' || s.charAt(i) == 'b')) {
                char c = s.charAt(i++);
                if (c == 'a'){
                    cntA++;
                }
                else {
                    if (cntA > 0) {
                        cntA--;
                        ans += x;
                    } else {
                        cntB++;
                    }
                }
            }
            ans += Math.min(cntA, cntB) * y;
        }
        return ans;
    }

}
