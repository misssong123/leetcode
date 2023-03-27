package com.meng.oneQuestionPerDay.leetcode.editor.cn;
class Convert6 {
    /**
     * 执行用时：
     * 58 ms
     * , 在所有 Java 提交中击败了
     * 6.66%
     * 的用户
     * 内存消耗：
     * 43.3 MB
     * , 在所有 Java 提交中击败了
     * 16.13%
     * 的用户
     * 通过测试用例：
     * 1157 / 1157
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        int len = s.length();
        char [][] chars = new char[numRows][len];
        for(int i = 0 ; i < numRows ; i++){
            for(int j = 0 ; j < len ; j++){
                chars[i][j] = ' ';
            }
        }
        int x = 0 , y = 0 ;
        boolean flag = false;
        for(char c : s.toCharArray()) {
            chars[x][y] = c;
            if (flag) {//左-->右
                if (x == 0) {
                    flag = false;
                    x++;
                    continue;
                }
                x--;
                y++;
            } else {//上-->下
                if (x >= numRows - 1) {
                    flag = true;
                    x--;
                    y++;
                    continue;
                }
                x++;
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < numRows ; i++){
            for(int j = 0 ; j < len ; j++){
                if (chars[i][j]!=' '){
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "A";
        int numRows = 2;
        Convert6 demo = new Convert6();
        //PAHNAPLSIIGYIR
        //PAHNAPLSIIGYIR
        System.out.println(demo.convert(s,numRows));
    }
}
