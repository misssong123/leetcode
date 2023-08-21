package com.meng.oneQuestionPerDay.leetcode.editor.cn;

class CanChange2337 {
    /**
     * 时间
     * 详情
     * 13ms
     * 击败 97.47%使用 Java 的用户
     * 内存
     * 详情
     * 42.22MB
     * 击败 96.20%使用 Java 的用户
     * @param start
     * @param target
     * @return
     */
    public boolean canChange(String start, String target) {
        int sIndex = 0,len = start.length(),pre = -1;
        char[] chars = start.toCharArray();
        for (int i = 0 ; i < len ; i++){
            if (pre > i){//上一个位置的字符不能大于当前位置的字符
                return false;
            }
            if (target.charAt(i) == 'L'){
                while (sIndex < len && chars[sIndex] == '_'){
                    sIndex++;
                }
                //L允许位置较大
                if (sIndex >= len || sIndex < i || chars[sIndex] == 'R'){
                    return false;
                }
                //尽可能靠前,当前位置设置为_避免影响数据查询
                pre = i;
                chars[sIndex] = '_';
            }else if (target.charAt(i) == 'R'){
                //计算中间遇到的R
                int firstR = -1;
                while (sIndex <= i && (chars[sIndex] == '_'||chars[sIndex] == 'R')){
                    if (chars[sIndex] == 'R'&& firstR == -1){
                        firstR = sIndex;
                    }
                    sIndex++;
                }
                if (sIndex > len || firstR == -1){
                    return false;
                }
                pre = firstR + 1;
                sIndex = firstR + 1;
            }
        }
        //计算是否存在其他L,R字符
        for(int i = sIndex ; i < len ; i++){
            if (chars[i] != '_'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanChange2337 canChange2337 = new CanChange2337();
        System.out.println(canChange2337.canChange("____","R_RR"));
    }

    /**
     * 详情
     * 15ms
     * 击败 88.61%使用 Java 的用户
     * 内存
     * 详情
     * 42.06MB
     * 击败 98.73%使用 Java 的用户
     * @param start
     * @param target
     * @return
     */
    public boolean canChange1(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != target.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != '_') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (target.charAt(j) != '_') {
                return false;
            }
            j++;
        }
        return true;
    }

}

