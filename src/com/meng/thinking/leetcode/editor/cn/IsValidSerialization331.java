package com.meng.thinking.leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class IsValidSerialization331 {
    /**
     * 解答成功:
     * 	执行耗时:4 ms,击败了74.17% 的Java用户
     * 	内存消耗:42.4 MB,击败了58.75% 的Java用户
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder.charAt(0)=='#'&&preorder.length()!=1){
            return false;
        }
        String[] strArr = preorder.split(",");
        int stack =1;
        for (String str : strArr){
            if (stack <= 0){
                return false;
            }
            if (str.equals("#")){
                stack--;
            }else {
                stack++;
            }
        }
        return stack==0;
    }

    /**
     *解答成功:
     * 	执行耗时:4 ms,击败了74.17% 的Java用户
     * 	内存消耗:41.4 MB,击败了67.09% 的Java用户
     * @param preorder
     * @return
     */
    public boolean isValidSerialization1(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了90.83% 的Java用户
     * 	内存消耗:40.8 MB,击败了89.38% 的Java用户
     * @param preorder
     * @return
     */
    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                slots--;
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
