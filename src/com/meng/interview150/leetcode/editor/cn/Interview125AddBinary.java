package com.meng.interview150.leetcode.editor.cn;

class Interview125AddBinary {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.74% 的Java用户
     * 	内存消耗:41.3 MB,击败了50.58% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public String addBinaryMy(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n1 = a.length()-1,n2 = b.length()-1;
        int diff = 0;
        while (n1>=0||n2>=0){
            int num1 = n1>=0?a.charAt(n1--)-'0':0;
            int num2 = n2>=0?b.charAt(n2--)-'0':0;
            int sum = diff + num2 + num1;
            sb.append(sum%2);
            diff = sum/2;
        }
        if (diff>0){
            sb.append(diff);
        }
        return sb.reverse().toString();
    }

    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了99.74% 的Java用户
     * 	内存消耗:41.2 MB,击败了79.56% 的Java用户
     * @param a
     * @param b
     * @return
     */
    public String addBinary1(String a, String b) {
        StringBuffer ans = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

}
