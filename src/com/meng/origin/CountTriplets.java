package com.meng.origin;

import java.util.HashMap;
import java.util.Map;

/**
 * 1442. 形成两个异或相等数组的三元组数目
 *
 * 给你一个整数数组 arr 。
 *
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *
 * a 和 b 定义如下：
 *
 *     a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 *     b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 *
 * 注意：^ 表示 按位异或 操作。
 *
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 *
 * 示例 2：
 *
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 *
 * 示例 3：
 *
 * 输入：arr = [2,3]
 * 输出：0
 *
 * 示例 4：
 *
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 *
 * 示例 5：
 *
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 *
 *
 *
 * 提示：
 *
 *     1 <= arr.length <= 300
 *     1 <= arr[i] <= 10^8
 */
public class CountTriplets {
    /**
     * 循环
     * @param arr
     * @return
     * 执行用时：28 ms, 在所有 Java 提交中击败了22.64% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了100.00% 的用户
     */
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }
        int ans = 0;
        for (int x = 0; x < n; ++x) {
            for (int y = x + 1; y < n; ++y) {
                for (int z = y; z < n; ++z) {
                    if (s[x] == s[z + 1]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 方法二：二重循环
     *
     * 当等式 Si=Sk+1S_i=S_{k+1}Si​=Sk+1​ 成立时，[i+1,k][i+1, k][i+1,k] 的范围内的任意 jjj 都是符合要求的，对应的三元组个数为 k−ik-ik−i。因此我们只需枚举下标 iii 和 kkk。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/solution/xing-cheng-liang-ge-yi-huo-xiang-deng-sh-jud0/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了66.04% 的用户
     */
    public int countTriplets2(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int k = i + 1; k < n; ++k) {
                if (s[i] == s[k + 1]) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }
    /**
     * 方法三：哈希表（一重循环）
     *
     * 对于下标 kkk，若下标 i=i1,i2,⋯ ,imi=i_1,i_2,\cdots,i_mi=i1​,i2​,⋯,im​ 时均满足 Si=Sk+1S_i=S_{k+1}Si​=Sk+1​，根据方法二，这些二元组 (i1,k),(i2,k),⋯ ,(im,k)(i_1,k),(i_2,k),\cdots,(i_m,k)(i1​,k),(i2​,k),⋯,(im​,k) 对答案的贡献之和为
     *
     * (k−i1)+(k−i2)+⋯+(k−im)=m⋅k−(i1+i2+⋯+im)(k-i_1)+(k-i_2)+\cdots+(k-i_m)=m\cdot k-(i_1+i_2+\cdots+i_m) (k−i1​)+(k−i2​)+⋯+(k−im​)=m⋅k−(i1​+i2​+⋯+im​)
     *
     * 也就是说，当遍历下标 kkk 时，我们需要知道所有满足 Si=Sk+1S_i=S_{k+1}Si​=Sk+1​ 的
     *
     *     下标 iii 的出现次数 mmm
     *     下标 iii 之和
     *
     * 这可以借助两个哈希表来做到，在遍历下标 kkk 的同时，一个哈希表统计 SkS_kSk​ 的出现次数，另一个哈希表统计值为 SkS_kSk​ 的下标之和。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/solution/xing-cheng-liang-ge-yi-huo-xiang-deng-sh-jud0/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：3 ms, 在所有 Java 提交中击败了42.45% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了13.21% 的用户
     */
    public int countTriplets3(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        Map<Integer, Integer> total = new HashMap<Integer, Integer>();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            if (cnt.containsKey(s[k + 1])) {
                ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + k);
        }
        return ans;
    }
    /**
     * 我们可以在计算异或前缀和的同时计算答案，从而做到仅遍历 arr\textit{arr}arr 一次就计算出答案。
     * 官方解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了56.60% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了76.89% 的用户
     */
    public int countTriplets4(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        Map<Integer, Integer> total = new HashMap<Integer, Integer>();
        int ans = 0, s = 0;
        for (int k = 0; k < n; ++k) {
            int val = arr[k];
            if (cnt.containsKey(s ^ val)) {
                ans += cnt.get(s ^ val) * k - total.get(s ^ val);
            }
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
            total.put(s, total.getOrDefault(s, 0) + k);
            s ^= val;
        }
        return ans;
    }

}
