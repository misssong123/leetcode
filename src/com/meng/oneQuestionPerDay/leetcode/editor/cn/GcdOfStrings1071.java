package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class GcdOfStrings1071 {
    /**
     * 暴力
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 75.29%
     * 的用户
     * 内存消耗：
     * 40.3 MB
     * , 在所有 Java 提交中击败了
     * 51.97%
     * 的用户
     * 通过测试用例：
     * 123 / 123
     */
    public String gcdOfStrings(String str1, String str2) {
        int minLen = Math.min(str1.length(),str2.length());
        String res = "";
        int len1 = str1.length();
        int len2 = str2.length();
        for(int i = minLen  ; i > 0 ; i--){
            if (len1 % i == 0 && len2 % i == 0){
                String sub = str1.substring(0,i);
                if (isCheck(str1,str2,sub)){
                    res = sub;
                    return res;
                }
            }
        }
        return res;
    }
    public boolean isCheck(String str1,String str2,String sub){
        StringBuffer sb = new StringBuffer();
        int num = str1.length() / sub.length();
        for(int i = 0 ; i < num ; i++){
            sb.append(sub);
        }
        if (str1.equals(sb.toString())){
            sb.delete(0,sb.length());
            num = str2.length() / sub.length();
            for(int i = 0 ; i < num ; i++){
                sb.append(sub);
            }
            return str2.equals(sb.toString());
        }
        return false;
    }

    /**
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings1(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        for (int i = Math.min(len1, len2); i >= 1; --i) { // 从长度大的开始枚举
            if (len1 % i == 0 && len2 % i == 0) {
                String X = str1.substring(0, i);
                if (check(X, str1) && check(X, str2)) {
                    return X;
                }
            }
        }
        return "";
    }

    public boolean check(String t, String s) {
        int lenx = s.length() / t.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 1; i <= lenx; ++i) {
            ans.append(t);
        }
        return ans.toString().equals(s);
    }

    /**
     * 枚举优化
     * @param str1
     * @param str2
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 75.29%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 88.98%
     * 的用户
     * 通过测试用例：
     * 123 / 123
     */
    public String gcdOfStrings2(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        String T = str1.substring(0, gcd(len1, len2));
        if (check2(T, str1) && check2(T, str2)) {
            return T;
        }
        return "";
    }

    public boolean check2(String t, String s) {
        int lenx = s.length() / t.length();
        StringBuffer ans = new StringBuffer();
        for (int i = 1; i <= lenx; ++i) {
            ans.append(t);
        }
        return ans.toString().equals(s);
    }

    public int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }
    /**
     * 数学
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 75.65%
     * 的用户
     * 通过测试用例：
     * 123 / 123
     */
    public String gcdOfStrings3(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, gcd3(str1.length(), str2.length()));
    }

    public int gcd3(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }

}

