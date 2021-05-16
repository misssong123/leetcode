package com.meng;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. 罗马数字转整数
 *
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 *
 *
 * 示例 1:
 *
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 3:
 *
 * 输入: "IX"
 * 输出: 9
 *
 * 示例 4:
 *
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 *
 * 示例 5:
 *
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length <= 15
 *     s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 *     题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 *     题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 *     IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 *     关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 */
public class RomanToInt {
    /**
     * 执行用时：15 ms, 在所有 Java 提交中击败了6.30% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了32.49% 的用户
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int res = 0 ;
        Map<String,Integer> cache = new HashMap<>();
        cache.put("I",1);
        cache.put("IV",4);
        cache.put("V",5);
        cache.put("IX",9);
        cache.put("X",10);
        cache.put("XL",40);
        cache.put("L",50);
        cache.put("XC",90);
        cache.put("C",100);
        cache.put("CD",400);
        cache.put("D",500);
        cache.put("CM",900);
        cache.put("M",1000);
        int len = s.length();
        for(int i = 0 ; i < len ; i++){
            if (i + 1 < len && cache.get(s.charAt(i+1)+"") > cache .get(s.charAt(i) + "")){
                res += cache.get(s.substring(i,i+2));
                i++;
                continue;
            }
            res += cache.get(s.substring(i,i+1));
        }
        return res ;
    }

    /**
     *方法一：模拟
     *
     * 思路
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     *
     * 例如 XXVII\texttt{XXVII}XXVII 可视作 X+X+V+I+I=10+10+5+1+1=27\texttt{X}+\texttt{X}+\texttt{V}+\texttt{I}+\texttt{I}=10+10+5+1+1=27X+X+V+I+I=10+10+5+1+1=27。
     *
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     *
     * 例如 XIV\texttt{XIV}XIV 可视作 X−I+V=10−1+5=14\texttt{X}-\texttt{I}+\texttt{V}=10-1+5=14X−I+V=10−1+5=14。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/roman-to-integer/solution/luo-ma-shu-zi-zhuan-zheng-shu-by-leetcod-w55p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法1
     * @param args
     * 执行用时：7 ms, 在所有 Java 提交中击败了39.91% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了53.78% 的用户
     */
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt1(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = symbolValues.get(s.charAt(i));
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        RomanToInt demo = new RomanToInt();
        System.out.println(demo.romanToInt("III"));
    }
}
