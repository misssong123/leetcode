package com.meng.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. 整数替换
 * 给定一个正整数 n ，你可以做如下操作：
 *
 * 如果 n 是偶数，则用 n / 2替换 n 。
 * 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。
 * n 变为 1 所需的最小替换次数是多少？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 8
 * 输出：3
 * 解释：8 -> 4 -> 2 -> 1
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：4
 * 解释：7 -> 8 -> 4 -> 2 -> 1
 * 或 7 -> 6 -> 3 -> 2 -> 1
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 */
public class IntegerReplacement {
    /**
     * 方法一：枚举所有的情况
     * 思路与算法
     *
     * 我们可以使用递归的方法枚举所有将 nn 变为 11 的替换序列：
     *
     * 当 nn 为偶数时，我们只有唯一的方法将 nn 替换为 \dfrac{n}{2}
     * 2
     * n
     * ​
     *  。
     *
     * 当 nn 为奇数时，我们可以选择将 nn 增加 11 或减少 11。由于这两种方法都会将 nn 变为偶数，那么下一步一定是除以 22，因此这里我们可以看成使用两次操作，将 nn 变为 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   或 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *  。
     *
     * 细节
     *
     * 当 n = 2^{31}-1n=2
     * 31
     *  −1 时，计算 n+1n+1 会导致溢出，因此我们可以使用整数除法 \lfloor \dfrac{n}{2} \rfloor + 1⌊
     * 2
     * n
     * ​
     *  ⌋+1 和 \lfloor \dfrac{n}{2} \rfloor⌊
     * 2
     * n
     * ​
     *  ⌋ 分别计算 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   或 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *  ，其中 \lfloor \cdot \rfloor⌊⋅⌋ 表示向下取整。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-replacement/solution/zheng-shu-ti-huan-by-leetcode-solution-swef/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
        public int integerReplacement (int n){
            if (n == 1) {
                return 0;
            }
            if (n % 2 == 0) {
                return 1 + integerReplacement(n / 2);
            }
            return 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
    }
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    /**
     * 方法二：记忆化搜索
     * 思路与算法
     *
     * 我们给方法一的递归加上记忆化，这样递归树的每一层最多只会计算两个 nn 值，时间复杂度降低为 O(\log n)O(logn)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-replacement/solution/zheng-shu-ti-huan-by-leetcode-solution-swef/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int integerReplacement1(int n) {
        if (n == 1) {
            return 0;
        }
        if (!memo.containsKey(n)) {
            if (n % 2 == 0) {
                memo.put(n, 1 + integerReplacement1(n / 2));
            } else {
                memo.put(n, 2 + Math.min(integerReplacement1(n / 2), integerReplacement1(n / 2 + 1)));
            }
        }
        return memo.get(n);
    }

    /**
     * 方法三：贪心
     * 思路与算法
     *
     * 实际上，方法一和方法二中的递归枚举中的「最优解」是固定的：
     *
     * 当 nn 为偶数时，我们只有唯一的方法将 nn 替换为 \dfrac{n}{2}
     * 2
     * n
     * ​
     *  ；
     *
     * 当 nn 为奇数时，nn 除以 44 的余数要么为 11，要么为 33。
     *
     * 如果为 11，我们可以断定，应该将 nn 变成 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *  。如果我们必须将 nn 变成 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   才能得到最优解，而 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   是奇数，那么：
     *
     * 如果下一步进行 -1−1 再除以 22，得到 \dfrac{n-1}{4}
     * 4
     * n−1
     * ​
     *  ，那么从 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   可以除以 22 得到同样的结果；
     *
     * 如果下一步进行 +1+1 再除以 22，得到 \dfrac{n+3}{4}
     * 4
     * n+3
     * ​
     *  ，那么从 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   可以除以 22 再 +1+1 得到同样的结果。
     *
     * 因此将 nn 变成 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   总是不会劣于 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *  。
     *
     * 如果为 33，我们可以断定，应该将 nn 变成 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *  。如果我们必须将 nn 变成 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   才能得到最优解，而 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   是奇数，那么：
     *
     * 如果下一步进行 -1−1 再除以 22，得到 \dfrac{n-3}{4}
     * 4
     * n−3
     * ​
     *  ，那么从 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   可以除以 22 再 -1−1 得到同样的结果。
     *
     * 如果下一步进行 +1+1 再除以 22，得到 \dfrac{n+1}{4}
     * 4
     * n+1
     * ​
     *  ，那么从 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   可以除以 22 得到同样的结果。
     *
     * 因此将 nn 变成 \dfrac{n+1}{2}
     * 2
     * n+1
     * ​
     *   总是不会劣于 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *  。但这里还需要考虑一种例外的情况，如果 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   已经为 11，即 n=3n=3 时，后续就无需再进行任何操作，此时将 nn 变成 \dfrac{n-1}{2}
     * 2
     * n−1
     * ​
     *   才是最优的。
     *
     * 因此，我们只需要根据上面的分类讨论，求出将 nn 变为 11 的操作次数即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/integer-replacement/solution/zheng-shu-ti-huan-by-leetcode-solution-swef/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int integerReplacement2(int n) {
        int ans = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                ++ans;
                n /= 2;
            } else if (n % 4 == 1) {
                ans += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    ans += 2;
                    n = 1;
                } else {
                    ans += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return ans;
    }

}
