package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MinLength2696 {
    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了77.13% 的Java用户
     * 	内存消耗:43.3 MB,击败了7.63% 的Java用户
     * @param s
     * @return
     */
    public int minLengthMy(String s) {
        LinkedList<Character> chars = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if(chars.isEmpty()){
                chars.push(c);
            }else if ((c == 'D' && chars.peek() == 'C')||(c == 'B' && chars.peek() == 'A')){
                chars.poll();
            }else {
                chars.push(c);
            }
        }
        return chars.size();
    }

    /**
     * 解答成功:
     * 	执行耗时:3 ms,击败了77.13% 的Java用户
     * 	内存消耗:42 MB,击败了62.33% 的Java用户
     * @param s
     * @return
     */
    public int minLength(String s) {
        List<Character> stack = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.add(c);
            int m = stack.size();
            if (m >= 2 &&
                    (stack.get(m - 2) == 'A' && stack.get(m - 1) == 'B' ||
                            stack.get(m - 2) == 'C' && stack.get(m - 1) == 'D')) {
                stack.remove(m - 1);
                stack.remove(m - 2);
            }
        }
        return stack.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
