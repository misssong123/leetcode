package com.meng.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 869. 重新排序得到 2 的幂
 * 难度
 * 中等
 *
 * 97
 *
 *
 *
 *
 *
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：true
 * 示例 2：
 *
 * 输入：10
 * 输出：false
 * 示例 3：
 *
 * 输入：16
 * 输出：true
 * 示例 4：
 *
 * 输入：24
 * 输出：false
 * 示例 5：
 *
 * 输入：46
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOf2 {
    boolean[] vis;

    /**
     * 方法一：搜索回溯 + 位运算
     *
     * 将
     * n
     * n 的十进制表示视作一个字符数组，我们可以枚举该数组的所有排列，对每个不含前导零的排列判断其对应的整数是否为
     * 2
     * 2 的幂。
     *
     * 这可以拆分成两个子问题：
     *
     * 枚举可能包含重复字符的数组的全排列，读者可参考「47. 全排列 II」的官方题解；
     * 判断一个整数是否为
     * 2
     * 2 的幂，读者可参考「231. 2 的幂」的官方题解。
     * 对于本题的具体实现，我们可以在递归搜索全排列的同时，计算出当前排列的已枚举的部分所对应的整数
     * num
     * num。在我们枚举当前排列的下一个字符
     * ch
     * ch 时，将
     * ch
     * ch 加到
     * num
     * num 的末尾，即 num = num * 10 + ch - '0'，然后递归进入下一层。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reordered-power-of-2/solution/zhong-xin-pai-xu-de-dao-2-de-mi-by-leetc-4fvs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     * 执行用时：
     * 35 ms
     * , 在所有 Java 提交中击败了
     * 21.99%
     * 的用户
     * 内存消耗：
     * 35.2 MB
     * , 在所有 Java 提交中击败了
     * 78.42%
     * 的用户
     * 通过测试用例：
     * 136 / 136
     */
    public boolean reorderedPowerOf21(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        Arrays.sort(nums);
        vis = new boolean[nums.length];
        return backtrack(nums, 0, 0);
    }

    public boolean backtrack(char[] nums, int idx, int num) {
        if (idx == nums.length) {
            return isPowerOfTwo(num);
        }
        for (int i = 0; i < nums.length; ++i) {
            // 不能有前导零
            if ((num == 0 && nums[i] == '0') || vis[i] || (i > 0 && !vis[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            vis[i] = true;
            if (backtrack(nums, idx + 1, num * 10 + nums[i] - '0')) {
                return true;
            }
            vis[i] = false;
        }
        return false;
    }

    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }
    Set<String> powerOf2Digits = new HashSet<String>();

    /**
     *方法二：预处理 + 哈希表
     *
     * 由于我们可以按任何顺序将数字重新排序，因此对于两个不同的整数
     * a
     * a 和
     * b
     * b，如果其十进制表示的字符数组，从小到大排序后的结果是相同的，那么若
     * a
     * a 能够重排得到
     * 2
     * 2 的幂，
     * b
     * b 也可以；若
     * a
     * a 不能重排得到
     * 2
     * 2 的幂，那么
     * b
     * b 也不能。
     *
     * 进一步地，只要
     * a
     * a 和
     * b
     * b 的十进制表示的字符数组中，从
     * 0
     * 0 到
     * 9
     * 9 每个字符的出现次数，在
     * a
     * a 和
     * b
     * b 中都是一样的，那么
     * a
     * a 和
     * b
     * b 能否重排得到
     * 2
     * 2 的幂的结果是一样的。
     *
     * 由于
     * 2
     * 29
     * <
     * 1
     * 0
     * 9
     * <
     * 2
     * 30
     * 2
     * 29
     *  <10
     * 9
     *  <2
     * 30
     *  ，因此在
     * [
     * 1
     * ,
     * 1
     * 0
     * 9
     * ]
     * [1,10
     * 9
     *  ] 范围内有
     * 2
     * 0
     * ,
     * 2
     * 1
     * ,
     * ⋯
     *
     * ,
     * 2
     * 29
     * 2
     * 0
     *  ,2
     * 1
     *  ,⋯,2
     * 29
     *   一共
     * 30
     * 30 个
     * 2
     * 2 的幂。对这
     * 30
     * 30 个数的每个数，我们可以预处理其十进制表示的字符数组中从
     * 0
     * 0 到
     * 9
     * 9 每个字符的出现次数，记在一个长度为
     * 10
     * 10 的数组中，并用一哈希表记录这些数组。对于数字
     * n
     * n，我们同样统计其十进制表示的字符数组中从
     * 0
     * 0 到
     * 9
     * 9 每个字符的出现次数，然后去哈希表中查找，若存在则说明
     * n
     * n 可以通过重排得到
     * 2
     * 2 的幂，否则不能。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reordered-power-of-2/solution/zhong-xin-pai-xu-de-dao-2-de-mi-by-leetc-4fvs/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 97.93%
     * 的用户
     * 内存消耗：
     * 35.8 MB
     * , 在所有 Java 提交中击败了
     * 21.58%
     * 的用户
     * 通过测试用例：
     * 136 / 136
     */
    public boolean reorderedPowerOf22(int n) {
        init();
        return powerOf2Digits.contains(countDigits(n));
    }

    public void init() {
        for (int n = 1; n <= 1e9; n <<= 1) {
            powerOf2Digits.add(countDigits(n));
        }
    }

    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }

}
