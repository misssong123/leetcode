package com.meng.algorithm;

/**
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 *
 *
 *
 * 示例 1:
 *
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 */
public class OneEditAway {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 47.68%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 74.62%
     * 的用户
     * 通过测试用例：
     * 1146 / 1146
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int len1 = first.length(),len2 = second.length();
        if (Math.abs(len1 - len2) >= 2){
            return false;
        }
        if (len1 == len2){
            return equalStr(first,second,0);
        }else{
            if (len1 > len2){
                return equalStr(first,second,1);
            }else{
                return  equalStr(second,first,1);
            }
        }
    }

    private boolean equalStr(String first, String second, int flag) {
        if (flag == 0){
            if (first.equals(second)){
                return true;
            }else{
                for(int i = 0 ;  i < first.length();i++){
                    if (first.charAt(i)!=second.charAt(i)){
                        first = first.substring(0,i)+first.substring(i+1);
                        second = second.substring(0,i)+second.substring(i+1);
                        return first.equals(second);
                    }
                }
                return true;
            }
        }else {
            for(int i = 0 ;  i < second.length();i++){
                if (first.charAt(i)!=second.charAt(i)){
                    first = first.substring(0,i)+first.substring(i+1);
                    return first.equals(second);
                }
            }
            return true;
        }
    }

    /**
     * 根据题意，两字符串 first , second 能够通过一次（或者零次）编辑互相转换的「充要条件」为：
     *
     * 两字符串 first , second 的长度之差 \leq 1≤1 ；
     * 当 first , second 长度相等时，两字符串各对应位置只有一个字符不同；
     * 当 first , second 长度之差为 11 时，「较短字符串」仅需在某位置添加一个字符，即可转化为「较长字符串」；
     * 因此，通过判断两字符串 first , second 是否满足以上全部条件，即可判定两字符串是否能够通过一次编辑完成互相转换。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/one-away-lcci/solution/mian-shi-ti-0105-yi-ci-bian-ji-qing-xi-t-xoy7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param first
     * @param second
     * @return
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 45.75%
     * 的用户
     * 通过测试用例：
     * 1146 / 1146
     */
    public boolean oneEditAway1(String first, String second) {
        int lf = first.length(), ls = second.length();
        if (lf > ls)
            return oneEditAway(second, first);
        if (ls - lf > 1)
            return false;
        if (lf == ls) {
            int count = 0;
            for (int i = 0; i < lf; i++) {
                if (first.charAt(i) != second.charAt(i))
                    count += 1;
            }
            return count <= 1;
        }
        int i = 0, ofs = 0;
        while (i < lf) {
            if (first.charAt(i) != second.charAt(i + ofs)) {
                if (++ofs > 1)
                    return false;
            } else {
                i += 1;
            }
        }
        return true;
    }
}
