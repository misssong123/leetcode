package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class AddBinary67 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 95.59%
     * 的用户
     * 通过测试用例：
     * 296 / 296
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        int aLen = a.length() - 1,bLen = b.length()-1,pre = 0;
        while (aLen >=0 || bLen >= 0){
           int n =  aLen >=0 ? (a.charAt(aLen) - '0'):0;
           int m =  bLen >=0 ? (b.charAt(bLen) - '0'):0;
           int sum = pre + n + m;
           sb.append(sum % 2);
           pre = sum / 2;
           aLen--;
           bLen--;
        }
        if (pre != 0){
            sb.append(pre);
        }
        return sb.reverse().toString();
    }
}

