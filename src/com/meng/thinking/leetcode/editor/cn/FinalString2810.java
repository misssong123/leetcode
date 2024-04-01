package com.meng.thinking.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//leetcode submit region begin(Prohibit modification and deletion)
class FinalString2810 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了83.92% 的Java用户
     * 	内存消耗:43.5 MB,击败了94.12% 的Java用户
     * @param s
     * @return
     */
    public String finalStringMy(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                sb.reverse();
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了33.33% 的Java用户
     * 	内存消耗:43.8 MB,击败了28.63% 的Java用户
     * @param s
     * @return
     */
    public String finalString(String s) {
        Deque<Character> q = new ArrayDeque<>();
        boolean tail = true;
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                tail = !tail; // 修改添加方向
            } else if (tail) {
                q.addLast(c); // 加尾部
            } else {
                q.addFirst(c); // 加头部
            }
        }
        StringBuilder ans = new StringBuilder();
        for (char c : q) {
            ans.append(c);
        }
        if (!tail) {
            ans.reverse();
        }
        return ans.toString();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
