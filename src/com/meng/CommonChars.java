package com.meng;

import java.util.*;

/**
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CommonChars {
    public static void main(String[] args) {
        String[] A = {"bella","label","roller"};
        CommonChars demo = new CommonChars();
        System.out.println(demo.commonChars(A));
    }

    /**
     * 思路
     * 1.存放第一个字符每个字母存放的结果
     * 2.遍历每一个字符，与第一个字符求交集，后面的字符再与交集求交集
     * 3.直到交集为空或者数组遍历完成
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        //存放结果
        Map<Character,Integer> result = new LinkedHashMap<>();
        //存放临时结果
        Map<Character,Integer> temp = new HashMap<>();
        //结果
        List<String> list = new ArrayList<>();
        for (String s : A){
            for (char c : s.toCharArray()){
                temp.put(c,temp.getOrDefault(c,0)+1);
            }
            if (result.size() == 0){
                result.putAll(temp);
                temp.clear();
                continue;
            }
            for (Character c : result.keySet()){
                if (temp.get(c)== null){
                    result.put(c,0);
                    continue;
                }
                result.put(c,Math.min(result.get(c),temp.get(c)));
            }
            if (result.size() == 0)
                return list;
            temp.clear();
        }
        for (Character c : result.keySet())
            for (int i = 0 ; i< result.get(c);i++)
                list.add(c+"");
        return list;
    }
    /**
     * 官方解法
     *如果字符 ccc 在所有字符串中均出现了 kkk 次及以上，那么最终答案中需要包含 kkk 个 ccc。因此，我们可以使用 minfreq[c]\textit{minfreq}[c]minfreq[c] 存储字符 ccc 在所有字符串中出现次数的最小值。
     * 我们可以依次遍历每一个字符串。当我们遍历到字符串 sss 时，我们使用 freq[c]\textit{freq}[c]freq[c] 统计 sss 中每一个字符 ccc 出现的次数。在统计完成之后，我们再将每一个 minfreq[c]\textit{minfreq}[c]minfreq[c] 更新为其本身与 freq[c]\textit{freq}[c]freq[c] 的较小值。这样一来，当我们遍历完所有字符串后，minfreq[c]\textit{minfreq}[c]minfreq[c] 就存储了字符 ccc 在所有字符串中出现次数的最小值。
     * 由于题目保证了所有的字符均为小写字母，因此我们可以用长度为 262626 的数组分别表示 minfreq\textit{minfreq}minfreq 以及 freq\textit{freq}freq。
     * 在构造最终的答案时，我们遍历所有的小写字母 ccc，并将 minfreq[c]\textit{minfreq}[c]minfreq[c] 个 ccc 添加进答案数组即可。
     */
    public List<String> commonChars1(String[] A) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String word: A) {
            int[] freq = new int[26];
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }
}
