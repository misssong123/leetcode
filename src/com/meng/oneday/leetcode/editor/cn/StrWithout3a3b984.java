package com.meng.oneday.leetcode.editor.cn;

class StrWithout3a3b984 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.3 MB,击败了13.33% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public String strWithout3a3b984(int a, int b) {
        StringBuilder sb = new StringBuilder();
        while (a != b && a > 0 && b > 0){
            if (a > b){
                sb.append("aab");
                a -= 2;
                b -= 1;
            }else {
                sb.append("bba");
                a -= 1;
                b -= 2;
            }
        }
        if (a == b){
            while (a > 0){
                sb.append("ab");
                a -= 1;
                b -= 1;
            }
        }
        if (a > 0){
            sb.repeat("a",a);
        }
        if (b > 0){
            sb.repeat("b",b);
        }
        return sb.toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了88.00% 的Java用户
     * 	内存消耗:42.1 MB,击败了46.67% 的Java用户
     * @param A
     * @param B
     * @return
     */
    public String strWithout3a3b(int A, int B) {
        StringBuilder ans = new StringBuilder();

        while (A > 0 || B > 0) {
            boolean writeA = false;
            int L = ans.length();
            if (L >= 2 && ans.charAt(L-1) == ans.charAt(L-2)) {
                if (ans.charAt(L-1) == 'b')
                    writeA = true;
            } else {
                if (A >= B)
                    writeA = true;
            }

            if (writeA) {
                A--;
                ans.append('a');
            } else {
                B--;
                ans.append('b');
            }
        }

        return ans.toString();
    }

}
