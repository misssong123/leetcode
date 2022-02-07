package com.meng.origin;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * 888. 公平的糖果棒交换
 *
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 *
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 *
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 *
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 *
 *
 *
 * 示例 1：
 *
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 *
 * 示例 2：
 *
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 *
 * 示例 3：
 *
 * 输入：A = [2], B = [1,3]
 * 输出：[2,3]
 *
 * 示例 4：
 *
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 10000
 *     1 <= B.length <= 10000
 *     1 <= A[i] <= 100000
 *     1 <= B[i] <= 100000
 *     保证爱丽丝与鲍勃的糖果总量不同。
 *     答案肯定存在。
 */
public class FairCandySwap {
    /**
     * 执行用时：18 ms, 在所有 Java 提交中击败了34.14% 的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了43.70% 的用户
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumX = Arrays.stream(A).sum();
        int sumY = Arrays.stream(B).sum();
        int diff = (sumX - sumY)/2;
        int [] result = new int[2];
        Set<Integer> set =  new HashSet<>();
        Arrays.stream(B).forEach(e -> set.add(e));
        for(int a : A){
            if (set.contains(a - diff)){
                result[0] = a;
                result[1] = a - diff;
                break;
            }
        }
        return result;
    }
    /**
     * 方法一：哈希表
     *
     * 思路及算法
     *
     * 记爱丽丝的糖果棒的总大小为 sumA\textit{sumA}sumA，鲍勃的糖果棒的总大小为 sumB\textit{sumB}sumB。设答案为 {x,y}\{x,y\}{x,y}，即爱丽丝的大小为 xxx 的糖果棒与鲍勃的大小为 yyy 的糖果棒交换，则有如下等式：
     *
     * sumA−x+y=sumB+x−y\textit{sumA} - x + y = \textit{sumB} + x - y sumA−x+y=sumB+x−y
     *
     * 化简，得：
     *
     * x=y+sumA−sumB2x = y + \frac{\textit{sumA} - \textit{sumB}}{2} x=y+2sumA−sumB​
     *
     * 即对于 BBB 中的任意一个数 y′y'y′，只要 AAA 中存在一个数 x′x'x′，满足 x′=y′+sumA−sumB2x' = y' + \dfrac{\textit{sumA} - \textit{sumB}}{2}x′=y′+2sumA−sumB​，那么 {x′,y′}\{x',y'\}{x′,y′} 即为一组可行解。
     *
     * 为了快速查询 AAA 中是否存在某个数，我们可以先将 AAA 中的数字存入哈希表中。然后遍历 BBB 序列中的数 y′y'y′，在哈希表中查询是否有对应的 x′x'x′。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/fair-candy-swap/solution/gong-ping-de-tang-guo-jiao-huan-by-leetc-tlam/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：17 ms, 在所有 Java 提交中击败了35.00% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了92.92% 的用户
     */
    public int[] fairCandySwap1(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<Integer>();
        for (int num : A) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
    /**
     * 本题只需要判断一个Alice或Bob中是否存在一个数是否存在，如果用Map或者用Set实现效率并不高。
     * 我们知道在判断一个数是否存在时使用BitMap更为高效！
     *
     * 作者：ggeorge500
     * 链接：https://leetcode-cn.com/problems/fair-candy-swap/solution/yi-chong-geng-gao-xiao-de-zuo-fa-bitmapj-xwtv/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 另一种算法
     * 执行用时：5 ms, 在所有 Java 提交中击败了94.66% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了95.16% 的用户
     */
    public int[] fairCandySwap2(int[] A, int[] B) {
        int maxLen = 100001, len1 = A.length, len2 = B.length;
        BitSet set = new BitSet(maxLen);
        int sumA = 0, sumB = 0;
        for (int i = 0; i < len1; i++) {
            sumA += A[i];
            set.set(A[i]);
        }
        for (int i = 0; i < len2; i++) {
            sumB += B[i];
        }
        int left = (sumA - sumB) / 2;
        for (int i = 0; i < len2; i++) {
            if (B[i] + left > 0 && set.get(B[i] + left)) return new int[]{B[i] + left, B[i]};
        }
        return null;
    }
}
