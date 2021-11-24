package com.meng.algorithm;

import java.util.*;

/**
 * 423. 从英文中重建数字
 * 给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "owoztneoer"
 * 输出："012"
 * 示例 2：
 *
 * 输入：s = "fviefuro"
 * 输出："45"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一
 * s 保证是一个符合题目要求的字符串
 */
public class OriginalDigits {
//    List<Integer> res = new ArrayList<>();
//    Map<Integer,Integer> temp = new HashMap<>();
//    int sum = 0;
//    public String originalDigits(String s) {
//        List<String> list = Arrays.asList("zero","one","two","three","four","five","six","seven","eight","nine");
//        int[] nums = new int[26];
//        for(char c : s.toCharArray()){
//            nums[c-'a']++;
//            sum++;
//        }
//        dfs(list,nums,sum);
//        Collections.sort(res);
//        StringBuilder sb = new StringBuilder();
//        for(int num : res){
//            sb.append(num);
//        }
//        return sb.toString();
//    }
//
//    private void dfs(List<String> list, int[] nums, int sum) {
//        if (sum == 0){
//            return;
//        }
//        for(String s : list){
//            temp.clear();
//            for(char c : s.toCharArray()){
//                int index = c - 'a';
//                temp.put(index,temp.getOrDefault(index,0)+1);
//                if (nums[index]<temp.get(index)){
//                    break;
//                }
//            }
//        }
//    }

    /**
     * 可以发现，\text{z, w, u, x, g}z, w, u, x, g 都只在一个数字中，即 0, 2, 4, 6, 80,2,4,6,8 中出现。因此我们可以使用一个哈希表统计每个字母出现的次数，那么 \text{z, w, u, x, g}z, w, u, x, g 出现的次数，即分别为 0, 2, 4, 6, 80,2,4,6,8 出现的次数。
     *
     * 随后我们可以注意那些只在两个数字中出现的字符：
     *
     * \text{h}h 只在 3, 83,8 中出现。由于我们已经知道了 88 出现的次数，因此可以计算出 33 出现的次数。
     *
     * \text{f}f 只在 4, 54,5 中出现。由于我们已经知道了 44 出现的次数，因此可以计算出 55 出现的次数。
     *
     * \text{s}s 只在 6, 76,7 中出现。由于我们已经知道了 66 出现的次数，因此可以计算出 77 出现的次数。
     *
     * 此时，我们还剩下 11 和 99 的出现次数没有求出：
     *
     * \text{o}o 只在 0, 1, 2, 4中出现，由于我们已经知道了 0, 2, 4出现的次数，因此可以计算出 1 出现的次数。
     * 最后的 99 就可以通过 \text{n, i, e}n, i, e 中的任一字符计算得到了。这里推荐使用 \text{i}i 进行计算，因为 \text{n}n 在 99 中出现了 22 次，\text{e}e 在 33 中出现了 22 次，容易在计算中遗漏。
     *
     * 当我们统计完每个数字出现的次数后，我们按照升序将它们进行拼接即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/solution/cong-ying-wen-zhong-zhong-jian-shu-zi-by-9g1r/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     * 执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 13.28%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 39.26%
     * 的用户
     * 通过测试用例：
     * 24 / 24
     */
    public String originalDigits(String s) {
        Map<Character, Integer> c = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            c.put(ch, c.getOrDefault(ch, 0) + 1);
        }

        int[] cnt = new int[10];
        cnt[0] = c.getOrDefault('z', 0);
        cnt[2] = c.getOrDefault('w', 0);
        cnt[4] = c.getOrDefault('u', 0);
        cnt[6] = c.getOrDefault('x', 0);
        cnt[8] = c.getOrDefault('g', 0);

        cnt[3] = c.getOrDefault('h', 0) - cnt[8];
        cnt[5] = c.getOrDefault('f', 0) - cnt[4];
        cnt[7] = c.getOrDefault('s', 0) - cnt[6];

        cnt[1] = c.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];

        cnt[9] = c.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.append((char) (i + '0'));
            }
        }
        return ans.toString();
    }
}
