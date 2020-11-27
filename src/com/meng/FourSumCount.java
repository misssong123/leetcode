package com.meng;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FourSumCount {
    /**
     * 超时，不建议使用这种做法
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int ans = 0;
        for(int a : A){
            for(int b : B){
                for(int c : C){
                    for (int d :D){
                        if (a+b+c+d==0)
                            ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     *方法一：分组 + 哈希表
     *
     * 思路与算法
     *
     * 我们可以将四个数组分成两部分，AAA 和 BBB 为一组，CCC 和 DDD 为另外一组。
     *
     * 对于 AAA 和 BBB，我们使用二重循环对它们进行遍历，得到所有 A[i]+B[j]A[i]+B[j]A[i]+B[j] 的值并存入哈希映射中。对于哈希映射中的每个键值对，每个键表示一种 A[i]+B[j]A[i]+B[j]A[i]+B[j]，对应的值为 A[i]+B[j]A[i]+B[j]A[i]+B[j] 出现的次数。
     *
     * 对于 CCC 和 DDD，我们同样使用二重循环对它们进行遍历。当遍历到 C[k]+D[l]C[k]+D[l]C[k]+D[l] 时，如果 −(C[k]+D[l])-(C[k]+D[l])−(C[k]+D[l]) 出现在哈希映射中，那么将 −(C[k]+D[l])-(C[k]+D[l])−(C[k]+D[l]) 对应的值累加进答案中。
     *
     * 最终即可得到满足 A[i]+B[j]+C[k]+D[l]=0A[i]+B[j]+C[k]+D[l]=0A[i]+B[j]+C[k]+D[l]=0 的四元组数目
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     * 执行用时：68 ms, 在所有 Java 提交中击败了91.67% 的用户
     * 内存消耗：56.8 MB, 在所有 Java 提交中击败了85.67% 的用户
     */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }

}
