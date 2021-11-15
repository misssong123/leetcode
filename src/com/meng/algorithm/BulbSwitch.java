package com.meng.algorithm;

/**
 * 319. 灯泡开关
 * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。
 *
 * 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
 *
 * 找出并返回 n 轮后有多少个亮着的灯泡。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 3
 * 输出：1
 * 解释：
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= n <= 109
 */
public class BulbSwitch {
    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35 MB
     * , 在所有 Java 提交中击败了
     * 83.10%
     * 的用户
     * 通过测试用例：
     * 35 / 35
     * @param n
     * @return
     * 方法一：数学
     * 思路与算法
     *
     * 如果我们将所有的灯泡从左到右依次编号为 1, 2, \cdots, n1,2,⋯,n，那么可以发现：
     *
     * 在第 ii 轮时，我们会将所有编号为 ii 的倍数的灯泡进行切换。
     *
     * 因此，对于第 kk 个灯泡，它被切换的次数恰好就是 kk 的约数个数。如果 kk 有偶数个约数，那么最终第 kk 个灯泡的状态为暗；如果 kk 有奇数个约数，那么最终第 kk 个灯泡的状态为亮。
     *
     * 对于 kk 而言，如果它有约数 xx，那么一定有约数 \dfrac{k}{x}
     * x
     * k
     * ​
     *  。因此只要当 x^2 \neq kx
     * 2
     *
     * 
     * ​
     *  =k 时，约数都是「成对」出现的。这就说明，只有当 kk 是「完全平方数」时，它才会有奇数个约数，否则一定有偶数个约数。
     *
     * 因此我们只需要找出 1, 2, \cdots, n1,2,⋯,n 中的完全平方数的个数即可，答案即为 \lfloor \sqrt{n} \rfloor⌊
     * n
     * ​
     *  ⌋，其中 \lfloor \cdot \rfloor⌊⋅⌋ 表示向下取整。
     *
     * 细节
     *
     * 由于 \sqrt{n}
     * n
     * ​
     *   涉及到浮点数运算，为了保证不出现精度问题，我们可以计算 \sqrt{n + \dfrac{1}{2}}
     * n+
     * 2
     * 1
     * ​
     *
     * ​
     *  ，这样可以保证计算出来的结果向下取整在 3232 位整数范围内一定正确。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/bulb-switcher/solution/deng-pao-kai-guan-by-leetcode-solution-rrgp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
