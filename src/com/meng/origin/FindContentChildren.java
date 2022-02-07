package com.meng.origin;

import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 *
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 *
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *
 *
 *
 * 提示：
 *
 *     1 <= g.length <= 3 * 104
 *     0 <= s.length <= 3 * 104
 *     1 <= g[i], s[j] <= 231 - 1
 */
public class FindContentChildren {
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.57% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了46.50% 的用户
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int sIndex = 0 , gIndex = 0 , sLen = s.length ,gLen = g.length,count=0;
        while (sIndex < s.length && gIndex < gLen){
            while (sIndex < sLen && s[sIndex] < g[gIndex]) {
                sIndex++;
            }
            if (sIndex<sLen)
                count++;
            sIndex++;
            gIndex++;
        }
        return count;
    }
    /**
     * 方法一：排序 + 贪心算法
     *
     * 为了尽可能满足最多数量的孩子，从贪心的角度考虑，应该按照孩子的胃口从小到大的顺序依次满足每个孩子，且对于每个孩子，应该选择可以满足这个孩子的胃口且尺寸最小的饼干。证明如下。
     *
     * 假设有 mmm 个孩子，胃口值分别是 g1g_1g1​ 到 gmg_mgm​，有 nnn 块饼干，尺寸分别是 s1s_1s1​ 到 sns_nsn​，满足 gi≤gi+1g_i \le g_{i+1}gi​≤gi+1​ 和 sj≤sj+1s_j \le s_{j+1}sj​≤sj+1​，其中 1≤i<m1 \le i < m1≤i<m，1≤j<n1 \le j < n1≤j<n。
     *
     * 假设在对前 i−1i-1i−1 个孩子分配饼干之后，可以满足第 iii 个孩子的胃口的最小的饼干是第 jjj 块饼干，即 sjs_jsj​ 是剩下的饼干中满足 gi≤sjg_i \le s_jgi​≤sj​ 的最小值，最优解是将第 jjj 块饼干分配给第 iii 个孩子。如果不这样分配，考虑如下两种情形：
     *
     *     如果 i<mi<mi<m 且 gi+1≤sjg_{i+1} \le s_jgi+1​≤sj​ 也成立，则如果将第 jjj 块饼干分配给第 i+1i+1i+1 个孩子，且还有剩余的饼干，则可以将第 j+1j+1j+1 块饼干分配给第 iii 个孩子，分配的结果不会让更多的孩子被满足；
     *
     *     如果 j<nj<nj<n，则如果将第 j+1j+1j+1 块饼干分配给第 iii 个孩子，当 gi+1≤sjg_{i+1} \le s_jgi+1​≤sj​ 时，可以将第 jjj 块饼干分配给第 i+1i+1i+1 个孩子，分配的结果不会让更多的孩子被满足；当 gi+1>sjg_{i+1}>s_jgi+1​>sj​ 时，第 jjj 块饼干无法分配给任何孩子，因此剩下的可用的饼干少了一块，因此分配的结果不会让更多的孩子被满足，甚至可能因为少了一块可用的饼干而导致更少的孩子被满足。
     *
     * 基于上述分析，可以使用贪心算法尽可能满足最多数量的孩子。
     *
     * 首先对数组 ggg 和 sss 排序，然后从小到大遍历 ggg 中的每个元素，对于每个元素找到能满足该元素的 sss 中的最小的元素。具体而言，令 iii 是 ggg 的下标，jjj 是 sss 的下标，初始时 iii 和 jjj 都为 000，进行如下操作。
     *
     * 对于每个元素 g[i]g[i]g[i]，找到未被使用的最小的 jjj 使得 g[i]≤s[j]g[i] \le s[j]g[i]≤s[j]，则 s[j]s[j]s[j] 可以满足 g[i]g[i]g[i]。由于 ggg 和 sss 已经排好序，因此整个过程只需要对数组 ggg 和 sss 各遍历一次。当两个数组之一遍历结束时，说明所有的孩子都被分配到了饼干，或者所有的饼干都已经被分配或被尝试分配（可能有些饼干无法分配给任何孩子），此时被分配到饼干的孩子数量即为可以满足的最多数量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/assign-cookies/solution/fen-fa-bing-gan-by-leetcode-solution-50se/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.57% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了66.37% 的用户
     */
    public int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int numOfChildren = g.length, numOfCookies = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
            while (j < numOfCookies && g[i] > s[j]) {
                j++;
            }
            if (j < numOfCookies) {
                count++;
            }
        }
        return count;
    }
}
