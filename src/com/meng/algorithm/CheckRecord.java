package com.meng.algorithm;

/**
 * 551. 学生出勤记录 I
 *
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 *
 *     'A'：Absent，缺勤
 *     'L'：Late，迟到
 *     'P'：Present，到场
 *
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 *
 *     按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 *     学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 *
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "PPALLP"
 * 输出：true
 * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
 *
 * 示例 2：
 *
 * 输入：s = "PPALLL"
 * 输出：false
 * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length <= 1000
 *     s[i] 为 'A'、'L' 或 'P'
 */
public class CheckRecord {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了50.68% 的用户
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        int len = s.length();
        if(len < 2){
            return true;
        }
        int a = 0 , l = 0 ;
        char pre = s.charAt(0);
        for(char c : s.toCharArray()){
            if (c == 'A'){
                a++;
                l = 0 ;
            }else if(c == 'L'){
                if(pre == 'L'){
                    l++;
                }else{
                    l = 1;
                }
            }else {
                l = 0 ;
            }
            if (a == 2 || l == 3){
                return false;
            }
            pre = c;
        }
        return true;
    }

    /**
     * 方法一：一次遍历
     *
     * 可奖励的出勤记录要求缺勤次数少于 222 和连续迟到次数少于 333。判断出勤记录是否可奖励，只需要遍历出勤记录，判断这两个条件是否同时满足即可。
     *
     * 遍历过程中，记录缺勤次数和连续迟到次数，根据遍历到的字符更新缺勤次数和连续迟到次数：
     *
     *     如果遇到 ‘A’\text{`A'}‘A’，即缺勤，则将缺勤次数加 111，否则缺勤次数不变；
     *
     *     如果遇到 ‘L’\text{`L'}‘L’，即迟到，则将连续迟到次数加 111，否则将连续迟到次数清零。
     *
     * 如果在更新缺勤次数和连续迟到次数之后，出现缺勤次数大于或等于 222 或者连续迟到次数大于或等于 333，则该出勤记录不满足可奖励的要求，返回 false\text{false}false。如果遍历结束时未出现出勤记录不满足可奖励的要求的情况，则返回 true\text{true}true。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/student-attendance-record-i/solution/xue-sheng-chu-qin-ji-lu-i-by-leetcode-so-fcol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param s
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了96.35% 的用户
     */
    public boolean checkRecord1(String s) {
        int absents = 0, lates = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absents++;
                if (absents >= 2) {
                    return false;
                }
            }
            if (c == 'L') {
                lates++;
                if (lates >= 3) {
                    return false;
                }
            } else {
                lates = 0;
            }
        }
        return true;
    }
}
