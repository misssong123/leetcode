package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class AddStrings415 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.5 MB
     * , 在所有 Java 提交中击败了
     * 74.78%
     * 的用户
     * 通过测试用例：
     * 317 / 317
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int n = num1.length()-1,m = num2.length()-1;
        StringBuffer sb = new StringBuffer();
        int temp = 0;
        while (n >= 0 || m >= 0){
            int n1 = n>= 0 ? (num1.charAt(n) - '0') : 0;
            int n2 = m>= 0 ? (num2.charAt(m) - '0') : 0;
            sb.append((n1 + n2 + temp) % 10);
            temp = (n1 + n2 + temp) / 10;
            --n;
            --m;
        }
        if (temp > 0){
            sb.append(temp);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
       AddStrings415 demo = new AddStrings415();
        System.out.println(demo.addStrings("11","123"));
    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.3 MB
     * , 在所有 Java 提交中击败了
     * 96.86%
     * 的用户
     * 通过测试用例：
     * 317 / 317
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings2(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

}

