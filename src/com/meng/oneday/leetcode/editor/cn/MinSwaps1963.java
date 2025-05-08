package com.meng.oneday.leetcode.editor.cn;

class MinSwaps1963 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了57.89% 的Java用户
     * 	内存消耗:54.9 MB,击败了34.74% 的Java用户
     * @param S
     * @return
     */
    public int minSwapsOther(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int c = 0;
        int j = s.length - 1;
        for (char b : s) {
            if (b == '[') {
                c++;
            } else if (c > 0) {
                c--;
            } else { // c == 0
                // 找最右边的左括号交换
                while (s[j] == ']') {
                    j--;
                }
                s[j] = ']'; // s[i] = '[' 可以省略
                ans++;
                c++; // s[i] 变成左括号，c 加一
            }
        }
        return ans;
    }

    /**
     * 解答成功:
     * 	执行耗时:18 ms,击败了35.75% 的Java用户
     * 	内存消耗:54.7 MB,击败了53.47% 的Java用户
     * @param S
     * @return
     */
    public int minSwaps1963(String S) {
        int ans = 0,num = 0,j = S.length() - 1;
        char[] chars = S.toCharArray();
        for(char c : chars){
            if (c == '['){
                num++;
            }else if(num > 0){
                num--;
            }else {
                //寻找右侧第一个左括号
                while(chars[j] == ']'){
                    j--;
                }
                chars[j] = ']';
                num++;
                ans++;
            }
        }
        return ans;
    }


}
