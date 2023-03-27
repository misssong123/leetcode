package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class CountAsterisks235 {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.3 MB
     * , 在所有 Java 提交中击败了
     * 88.33%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     * @param s
     * @return
     */
    public int countAsterisks(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int num = 0;
        for(char c : chars){
            if (c == '|'){
                num++;
            }
            if (num % 2 == 1){
                continue;
            }
            if (c == '*'){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountAsterisks235 demo = new CountAsterisks235();
        String s = "l|*e*et|c**o|*de|";
        System.out.println(demo.countAsterisks(s));
    }

    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 59.15%
     * 的用户
     * 内存消耗：
     * 39.8 MB
     * , 在所有 Java 提交中击败了
     * 34.21%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     * @param s
     * @return
     */
    public int countAsterisks1(String s) {
        boolean valid = true;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '|') {
                valid = !valid;
            } else if (c == '*' && valid) {
                res++;
            }
        }
        return res;
    }

}
