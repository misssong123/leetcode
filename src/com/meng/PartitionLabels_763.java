package com.meng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 *
 *
 * 提示：
 *
 *     S的长度在[1, 500]之间。
 *     S只包含小写字母 'a' 到 'z' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionLabels_763 {
    /**
     * 思路
     * 1.遍历字符串，找到每个字符最后出现的位置
     * 2.遍历字符串
     *      a.记录当前位置first，记录当前字符最后一次出现的位置end
     *      b.记录向下遍历字符串
     *      c.比较当前字符串最后出现位置与end的大小，将较大值赋给end
     *      d.直到当前坐标与end相同时，将end-first+1的结果存放到结果中
     * 3.继续遍历字符串，过程重复2，直到遍历结束
     * 执行用时：8 ms, 在所有 Java 提交中击败了34.30% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了25.60% 的用户
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        //存放字符及最后出现位置的结果
        Map<Character,Integer> map = new HashMap<>();
        int len = S.length();
        for(int i = 0 ; i < len ; i++){
            map.put(S.charAt(i),i);
        }
        List<Integer> ans = new ArrayList<>();
        int index = 0 ,first,end;
        while(index<len){
            first = index;
            end = map.get(S.charAt(index));
            index ++;
            while (index <= end){
                end = Math.max(end,map.get(S.charAt(index)));
                index++;
            }
            ans.add(end-first +1 );
        }
        return ans;
    }
    /**
     * 官方解法
     * 方法一：贪心算法 + 双指针
     *
     * 思路与算法
     *
     * 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。因此需要遍历字符串，得到每个字母最后一次出现的下标位置。
     *
     * 在得到每个字母最后一次出现的下标位置之后，可以使用贪心算法和双指针的方法将字符串划分为尽可能多的片段，具体做法如下。
     *
     *     从左到右遍历字符串，遍历的同时维护当前片段的开始下标 start\textit{start}start 和结束下标 end\textit{end}end，初始时 start=end=0\textit{start}=\textit{end}=0start=end=0。
     *
     *     对于每个访问到的字母 ccc，得到当前字母的最后一次出现的下标位置 endc\textit{end}_cendc​，则当前片段的结束下标一定不会小于 endc\textit{end}_cendc​，因此令 end=max⁡(end,endc)\textit{end}=\max(\textit{end},\textit{end}_c)end=max(end,endc​)。
     *
     *     当访问到下标 end\textit{end}end 时，当前片段访问结束，当前片段的下标范围是 [start,end][\textit{start},\textit{end}][start,end]，长度为 end−start+1\textit{end}-\textit{start}+1end−start+1，将当前片段的长度添加到返回值，然后令 start=end+1\textit{start}=\textit{end}+1start=end+1，继续寻找下一个片段。
     *
     *     重复上述过程，直到遍历完字符串。
     *
     * 上述做法使用贪心的思想寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，如果取更短的片段，则一定会出现同一个字母出现在多个片段中的情况。由于每次取的片段都是符合要求的最短的片段，因此得到的片段数也是最多的。
     *
     * 由于每个片段访问结束的标志是访问到下标 end\textit{end}end，因此对于每个片段，可以保证当前片段中的每个字母都一定在当前片段中，不可能出现在其他片段，可以保证同一个字母只会出现在同一个片段。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/partition-labels/solution/hua-fen-zi-mu-qu-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：3 ms, 在所有 Java 提交中击败了96.93% 的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了91.65% 的用户
     */
    public List<Integer> partitionLabels1(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}
