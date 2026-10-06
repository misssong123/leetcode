package com.meng.oneday.leetcode.editor.cn;

class MinAddToMakeValid921 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了85.09% 的Java用户
     * @param s
     * @return
     */
    public int minAddToMakeValid921(String s) {
        int res = 0;
        int l = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                l++;
            }else {
                if(l > 0){
                    l--;
                }else {
                    res++;
                }
            }
        }
        return res + l;
    }

    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了86.96% 的Java用户
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int left = 0; // 未配对的左括号的个数
        int ans = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left++;
            } else if (left > 0) {
                left--; // 左右括号配对
            } else { // 右括号太多了
                ans++; // 在 ch 左边任意位置插入一个左括号，与 ch 配对
            }
        }

        // 循环结束后，如果 left > 0，说明有 left 个多余的左括号
        // 在 s 末尾插入 left 个右括号
        return ans + left;
    }

}
