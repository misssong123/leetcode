package com.meng.algorithm;

import java.util.Arrays;

/**
 * 1154. 一年中的第几天
 * 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 *
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 *
 *
 *
 * 示例 1：
 *
 * 输入：date = "2019-01-09"
 * 输出：9
 * 示例 2：
 *
 * 输入：date = "2019-02-10"
 * 输出：41
 * 示例 3：
 *
 * 输入：date = "2003-03-01"
 * 输出：60
 * 示例 4：
 *
 * 输入：date = "2004-03-01"
 * 输出：61
 *
 *
 * 提示：
 *
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 */
public class DayOfYear {
    /**
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 85.04%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 60.77%
     * 的用户
     * 通过测试用例：
     * 10957 / 10957
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        String[] split = date.split("-");
        Integer month = Integer.parseInt(split[1]);
        Integer day = Integer.parseInt(split[2]);
        if (month == 1){
            return day;
        }
        int[] nums = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        int sum = 0;
        for(int i = 0 ; i < month - 1 ; i++){
            sum+=nums[i];
        }
        sum+=day;
        if (month > 2){
            Integer year = Integer.parseInt(split[0]);
            if ((year % 100 != 0 && year % 4 == 0) || (year % 100 == 0 && year % 400 == 0)){
                sum+=1;
            }
        }
        return sum;
    }

    /**
     * 方法一：直接计算
     * 思路与算法
     *
     * 我们首先从给定的字符串 \textit{date}date 中提取出年 \textit{year}year，月 \textit{month}month 以及日 \textit{day}day。
     *
     * 这样一来，我们就可以首先统计到 \textit{month}month 的前一个月为止的天数。这一部分只需要使用一个长度为 1212 的数组，预先记录每一个月的天数，再进行累加即可。随后我们将答案再加上 \textit{day}day，就可以得到 \textit{date}date 是一年中的第几天。
     *
     * 需要注意的是，如果 \textit{year}year 是闰年，那么二月份会多出一天。闰年的判定方法为：\textit{year}year 是 400400 的倍数，或者 \textit{year}year 是 44 的倍数且不是 100100 的倍数。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/day-of-the-year/solution/yi-nian-zhong-de-di-ji-tian-by-leetcode-2i0gr/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param date
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 99.09%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 14.78%
     * 的用户
     * 通过测试用例：
     * 10957 / 10957
     */
    public int dayOfYear1(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        int ans = 0;
        for (int i = 0; i < month - 1; ++i) {
            ans += amount[i];
        }
        return ans + day;
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("09"));
    }
}
