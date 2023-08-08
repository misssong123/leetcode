package com.meng.oneQuestionPerDay.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
class RemoveComments722 {
    /**
     * 1.当前行为正常数据
     *  先出现//则截取当前行//前的数据
     *  先出现/*则查找对应的结束标识,截取/*前的数据以及对应结束标识后的数据
     * 2.当前行为寻找/*标识的行
     * 未发现则忽略当前行
     * 发现则截取当前结束标识后的数据
     * @param source
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 63.55%
     * 的用户
     * 内存消耗：
     * 39.7 MB
     * , 在所有 Java 提交中击败了
     * 99.07%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public List<String> removeComments(String[] source) {
        boolean flag = false;
        List<String> res = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for(String str : source){
            char[] chars = str.toCharArray();
            for(int i = 0 ; i < chars.length ; i++){
                if (flag){
                    if (chars[i] == '*' && i + 1 < chars.length && chars[i+1]=='/'){
                        flag = false;
                        i++;
                    }
                }else {
                    if (chars[i] == '/' && i + 1 < chars.length){
                        if (chars[i+1] == '/'){
                            break;
                        }else if (chars[i+1]=='*'){
                            i++;
                            flag = true;
                        }
                    }
                    if (!flag){
                        sb.append(chars[i]);
                    }
                }
            }
            if (!flag&&sb.length() > 0){
                res.add(sb.toString());
                sb.delete(0,sb.length());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] source = {"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        RemoveComments722 demo = new RemoveComments722();
        System.out.println(demo.removeComments(source));
    }

    /**
     * 模拟
     * @param source
     * @return
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 63.55%
     * 的用户
     * 内存消耗：
     * 39.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public List<String> removeComments1(String[] source) {
        List<String> res = new ArrayList<String>();
        StringBuilder newLine = new StringBuilder();
        boolean inBlock = false;
        for (String line : source) {
            for (int i = 0; i < line.length(); i++) {
                if (inBlock) {
                    if (i + 1 < line.length() && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i++;
                    }
                } else {
                    if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i++;
                    } else if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break;
                    } else {
                        newLine.append(line.charAt(i));
                    }
                }
            }
            if (!inBlock && newLine.length() > 0) {
                res.add(newLine.toString());
                newLine.setLength(0);
            }
        }
        return res;
    }

}
