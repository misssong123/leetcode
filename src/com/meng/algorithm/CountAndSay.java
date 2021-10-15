package com.meng.algorithm;

/**
 * 38. 外观数列
 * 难度
 * 中等
 *
 * 804
 *
 *
 *
 *
 *
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 *
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * 前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 * 要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
 *
 * 例如，数字字符串 "3322251" 的描述如下图：
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出："1"
 * 解释：这是一个基本样例。
 * 示例 2：
 *
 * 输入：n = 4
 * 输出："1211"
 * 解释：
 * countAndSay(1) = "1"
 * countAndSay(2) = 读 "1" = 一 个 1 = "11"
 * countAndSay(3) = 读 "11" = 二 个 1 = "21"
 * countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
 *
 *
 * 提示：
 *
 * 1 <= n <= 30
 * 通过次数236,383提交次数398,629
 * 请问您在哪类招聘中遇到此题？
 */
public class CountAndSay {
    /**
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 49.41%
     * 的用户
     * 内存消耗：
     * 35.9 MB
     * , 在所有 Java 提交中击败了
     * 84.00%
     * 的用户
     * 通过测试用例：
     * 30 / 30
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1){
            return "1";
        }
        String ans = "1";
        StringBuffer cache = new StringBuffer();
        for(int i = 2 ; i <= n  ; i++){
            char[] chars = ans.toCharArray();
            char cur = chars[0];
            int num = 1;
            for(int j = 1 ; j < chars.length; j++){
                if (chars[j] != cur){
                    cache.append(num);
                    cache.append(cur);
                    cur = chars[j];
                    num = 0;
                }
                num++;
            }
            if (num != 0){
                cache.append(num);
                cache.append(cur);
            }
            ans = cache.toString();
            cache.delete(0,ans.length()+1);
        }
        return ans;
    }

    /**
     * 方法一：遍历生成
     *
     * 思路
     *
     * 通过阅读题目，我们可以了解到所谓的「外观数列」，其实本质上只是依次统计字符串中连续相同字符的个数。
     *
     * 例如字符串
     * 1112234445666
     * 1112234445666 我们依次统计连续相同字符的个数为:
     * 3
     * 3 个连续的字符
     * 1
     * 1,
     * 2
     * 2 个连续的
     * 1
     * 1，
     * 1
     * 1 个连续的字符
     * 3
     * 3，
     * 3
     * 3 个连续的字符
     * 4
     * 4，
     * 1
     * 1 个连续的字符
     * 5
     * 5，
     * 3
     * 3 个连续的字符
     * 6
     * 6。我们对其进行整理为
     * (3)1(2)2(1)3(3)4(1)5(3)6
     * (3)1(2)2(1)3(3)4(1)5(3)6，我们将括号内的数字也转换为字符串为
     * 312213341536
     * 312213341536。
     * 算法
     *
     * 题目中给定的递归公式定义的数字字符串序列如下：
     *
     * countAndSay(1)
     *  
     * =
     *  
     * "1"
     * countAndSay(1) = "1"；
     * countAndSay(n)
     * countAndSay(n) 是对
     * countAndSay(n-1)
     * countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 我们定义字符串
     * S
     * i
     * S
     * i
     * ​
     *   表示
     * countAndSay(i)
     * countAndSay(i)，我们如果要求得
     * S
     * n
     * S
     * n
     * ​
     *  ，则我们需先求出
     * S
     * n
     * −
     * 1
     * S
     * n−1
     * ​
     *  ，然后按照上述描述的方法生成，即从左到右依次扫描字符串
     * S
     * n
     * −
     * 1
     * S
     * n−1
     * ​
     *   中连续相同的字符的最大数目，然后将字符的统计数目转化为数字字符串再连接上对应的字符。我们已知
     * S
     * 1
     * S
     * 1
     * ​
     *   为
     * "1"
     * "1"，我们按照上述方法依次生成
     * S
     * 2
     * ,
     * S
     * 3
     * ,
     * …
     * ,
     * S
     * n
     * −
     * 1
     * ,
     * S
     * n
     * S
     * 2
     * ​
     *  ,S
     * 3
     * ​
     *  ,…,S
     * n−1
     * ​
     *  ,S
     * n
     * ​
     *   即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-and-say/solution/wai-guan-shu-lie-by-leetcode-solution-9rt8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 32.01%
     * 的用户
     * 内存消耗：
     * 37.7 MB
     * , 在所有 Java 提交中击败了
     * 30.12%
     * 的用户
     */
    public String countAndSay1(int n) {
        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                sb.append(Integer.toString(pos - start)).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }

        return str;
    }

}
