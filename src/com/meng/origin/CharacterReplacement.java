package com.meng.origin;

/**
 * 424. 替换后的最长重复字符
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 104。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 *
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class CharacterReplacement {
    /**暴力遍历【想到应该使用滑动窗口进行求职，但没有想出窗口滑动变化的条件如何保持】
     * 执行用时：1591 ms, 在所有 Java 提交中击败了5.02% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了65.68% 的用户
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int len = s.length() , max = 0 ,result =0,temp = 0;
        char c = ' ';
        for(int i = 0 ; i < len  ; i++){
            max = Math.min(max+temp,len);
            result = Math.max(max,result);
            if (i+result-k>=len)
                break;
            max = 1 ;
            temp = k ;
            c = s.charAt(i);
            for(int j = i +1 ; j < len ; j++){
                if (s.charAt(j) == c){
                    max++;
                }else {
                    if (temp > 0){
                        temp--;
                        max++;
                    }else {
                        result = Math.max(result,max);
                        break;
                    }
                }
            }
        }
        result = Math.max(max,result);
        return result;
    }

    /**
     * 官方解法
     * 方法一：双指针
     *
     * 我们可以枚举字符串中的每一个位置作为右端点，然后找到其最远的左端点的位置，满足该区间内除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过 kkk 个。
     *
     * 这样我们可以想到使用双指针维护这些区间，每次右指针右移，如果区间仍然满足条件，那么左指针不移动，否则左指针至多右移一格，保证区间长度不减小。
     *
     * 虽然这样的操作会导致部分区间不符合条件，即该区间内非最长重复字符超过了 kkk 个。但是这样的区间也同样不可能对答案产生贡献。当我们右指针移动到尽头，左右指针对应的区间的长度必然对应一个长度最大的符合条件的区间。
     *
     * 实际代码中，由于字符串中仅包含大写字母，我们可以使用一个长度为 262626 的数组维护每一个字符的出现次数。每次区间右移，我们更新右移位置的字符出现的次数，然后尝试用它更新重复字符出现次数的历史最大值，最后我们使用该最大值计算出区间内非最长重复字符的数量，以此判断左指针是否需要右移即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-n6aza/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：8 ms, 在所有 Java 提交中击败了38.63% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了75.46% 的用户
     */
    public int characterReplacement1(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxN = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxN = Math.max(maxN, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxN > k) {//使用前后窗口差距是否能满足最大值和可替换值，来实现左侧窗口的滑动
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
    public static void main(String[] args) {
        CharacterReplacement demo = new CharacterReplacement();
        String s = "ABBB";
        System.out.println(demo.characterReplacement(s,2));
    }
}
