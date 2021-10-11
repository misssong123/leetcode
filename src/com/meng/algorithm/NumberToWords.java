package com.meng.algorithm;

/**
 * 273. 整数转换英文表示
 * 难度
 * 困难
 *
 * 217
 *
 *
 *
 *
 *
 * 将非负整数 num 转换为其对应的英文表示。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 *
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 *
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4：
 *
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 */
public class NumberToWords {
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};
    /**
     *方法一：递归
     *
     * 由于非负整数
     * num
     * num 的最大值为
     * 2
     * 31
     * −
     * 1
     * 2
     * 31
     *  −1，因此最多有
     * 10
     * 10 位数。将整数转换成英文表示中，将数字按照
     * 3
     * 3 位一组划分，将每一组的英文表示拼接之后即可得到整数
     * num
     * num 的英文表示。
     *
     * 每一组最多有
     * 3
     * 3 位数，可以使用递归的方式得到每一组的英文表示。根据数字所在的范围，具体做法如下：
     *
     * 小于
     * 20
     * 20 的数可以直接得到其英文表示；
     * 大于等于
     * 20
     * 20 且小于
     * 100
     * 100 的数首先将十位转换成英文表示，然后对个位递归地转换成英文表示；
     * 大于等于
     * 100
     * 100 的数首先将百位转换成英文表示，然后对其余部分（十位和个位）递归地转换成英文表示。
     * 从高到低的每一组的单位依次是
     * 1
     * 0
     * 9
     * 10
     * 9
     *  、
     * 1
     * 0
     * 6
     * 10
     * 6
     *  、
     * 1
     * 0
     * 3
     * 10
     * 3
     *  、
     * 1
     * 1，除了最低组以外，每一组都有对应的表示单位的词，分别是
     * “Billion"
     * “Billion"、
     * “Million"
     * “Million"、
     * “Thousand"
     * “Thousand"。
     *
     * 得到每一组的英文表示后，需要对每一组加上对应的表示单位的词，然后拼接得到整数
     * num
     * num 的英文表示。
     *
     * 具体实现中需要注意以下两点：
     *
     * 只有非零的组的英文表示才会拼接到整数
     * num
     * num 的英文表示中；
     * 如果
     * num
     * =
     * 0
     * num=0，则不适用上述做法，而是直接返回
     * “Zero"
     * “Zero"。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-to-english-words/solution/zheng-shu-zhuan-huan-ying-wen-biao-shi-b-ivik/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 92.33%
     * 的用户
     * 内存消耗：
     * 37.8 MB
     * , 在所有 Java 提交中击败了
     * 63.25%
     * 的用户
     */
    public String numberToWords1(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer curr = new StringBuffer();
                recursion(curr, curNum);
                curr.append(thousands[i]).append(" ");
                sb.append(curr);
            }
        }
        return sb.toString().trim();
    }

    public void recursion(StringBuffer curr, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num < 20) {
            curr.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            curr.append(tens[num / 10]).append(" ");
            recursion(curr, num % 10);
        } else {
            curr.append(singles[num / 100]).append(" Hundred ");
            recursion(curr, num % 100);
        }
    }

    /**
     * 方法二：迭代
     *
     * 也可以使用迭代的方式得到每一组的英文表示。由于每一组最多有
     * 3
     * 3 位数，因此依次得到百位、十位、个位上的数字，生成该组的英文表示，注意只有非零位才会被添加到英文表示中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-to-english-words/solution/zheng-shu-zhuan-huan-ying-wen-biao-shi-b-ivik/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 92.33%
     * 的用户
     * 内存消耗：
     * 37.7 MB
     * , 在所有 Java 提交中击败了
     * 63.25%
     * 的用户
     * 通过测试用例：
     * 601 / 601
     */
    public String numberToWords2(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                sb.append(toEnglish(curNum)).append(thousands[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public String toEnglish(int num) {
        StringBuffer curr = new StringBuffer();
        int hundred = num / 100;
        num %= 100;
        if (hundred != 0) {
            curr.append(singles[hundred]).append(" Hundred ");
        }
        int ten = num / 10;
        if (ten >= 2) {
            curr.append(tens[ten]).append(" ");
            num %= 10;
        }
        if (num > 0 && num < 10) {
            curr.append(singles[num]).append(" ");
        } else if (num >= 10) {
            curr.append(teens[num - 10]).append(" ");
        }
        return curr.toString();
    }

}

