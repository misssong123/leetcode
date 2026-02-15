package com.meng.oneday.leetcode.editor.cn;

class AddBinary67 {
    /**
     * 解答成功:
     * 	执行耗时:2 ms,击败了96.18% 的Java用户
     * 	内存消耗:42.9 MB,击败了75.83% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public String addBinary67(String a, String b) {
        int index1 = a.length() - 1 , index2 = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0 ;
        while (index1 >= 0 || index2 >= 0){
            int num1 = index1 >= 0 ? a.charAt(index1) - '0' : 0;
            int num2 = index2 >= 0 ? b.charAt(index2) - '0' : 0;
            int sum = num1 + num2 + carry;
            sb.append(sum % 2);
            carry = sum / 2;
            index1 --;
            index2 --;
        }
        if (carry > 0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了100.00% 的Java用户
     * 	内存消耗:42.9 MB,击败了69.22% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        // 保证 a.length() >= b.length()，简化后续代码逻辑
        if (a.length() < b.length()) {
            return addBinary(b, a);
        }

        int n = a.length();
        int m = b.length();
        char[] ans = new char[n + 1];
        int carry = 0; // 保存进位

        for (int i = n - 1, j = m - 1; i >= 0; i--, j--) {
            int x = a.charAt(i) - '0';
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            ans[i + 1] = (char) (sum % 2 + '0');
            carry = sum / 2;
        }

        ans[0] = (char) (carry + '0');
        // 如果 carry == 0 则去掉 ans[0]
        return new String(ans, carry ^ 1, n + carry);
    }

}
