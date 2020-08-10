package com.meng;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 *     s.length 在1到50,000之间。
 *     s 只包含“0”或“1”字符。
 */
public class CountBinarySubstrings {
    public static void main(String[] args) {
        CountBinarySubstrings demo = new CountBinarySubstrings();
        System.out.println(demo.countBinarySubstrings("00110011"));
    }

    /**
     * 遍历字符串
     * 1.统计字符从当前当下一个不一样字符的长度
     * 2.比较是否与前一个统计值,取两者中小的那个数值，表示两者最多组成的可能性
     * 3.总数相加
     * 4.不要忘记最后一步的结果
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        //结果
        int result = 0;
        //上一个值
        int pre = 0;
        //当前值
        int cur = 0;
        //上一个截取的字符串
        char c =  ' ';
        //当前遍历的下标
        int i = 0;
        while(i<s.length()){
            if (s.charAt(i) == c){//"10101"
                cur ++;
            }else {
                result += Math.min(pre,cur);
                c = s.charAt(i);
                pre = cur;
                cur = 1;
            }
            i ++;
        }
        //最后一次的比较结果
        result += Math.min(pre,cur);
        return result;
    }
}
