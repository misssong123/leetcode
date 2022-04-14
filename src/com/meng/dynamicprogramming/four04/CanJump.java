package com.meng.dynamicprogramming.four04;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 */
public class CanJump {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 94.53%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 81.55%
     * 的用户
     * 通过测试用例：
     * 170 / 170
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int max = 0 ,len = nums.length;
        for(int i = 0 ; i< len - 1 ; i++){
            max = Math.max(max,i + nums[i]);
            if (max >= len-1){
                return true;
            }
            if (max == i){
                return false;
            }
        }
        return true;
    }

    /**
     * 方法一：贪心
     * 我们可以用贪心的方法解决这个问题。
     *
     * 设想一下，对于数组中的任意一个位置 yy，我们如何判断它是否可以到达？根据题目的描述，只要存在一个位置 xx，它本身可以到达，并且它跳跃的最大长度为 x + \textit{nums}[x]x+nums[x]，这个值大于等于 yy，即 x + \textit{nums}[x] \geq yx+nums[x]≥y，那么位置 yy 也可以到达。
     *
     * 换句话说，对于每一个可以到达的位置 xx，它使得 x+1, x+2, \cdots, x+\textit{nums}[x]x+1,x+2,⋯,x+nums[x] 这些连续的位置都可以到达。
     *
     * 这样以来，我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 xx，如果它在 最远可以到达的位置 的范围内，那么我们就可以从起点通过若干次跳跃到达该位置，因此我们可以用 x + \textit{nums}[x]x+nums[x] 更新 最远可以到达的位置。
     *
     * 在遍历的过程中，如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。反之，如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 作为答案。
     *
     * 以题目中的示例一
     *
     *
     * [2, 3, 1, 1, 4]
     * 为例：
     *
     * 我们一开始在位置 00，可以跳跃的最大长度为 22，因此最远可以到达的位置被更新为 22；
     *
     * 我们遍历到位置 11，由于 1 \leq 21≤2，因此位置 11 可达。我们用 11 加上它可以跳跃的最大长度 33，将最远可以到达的位置更新为 44。由于 44 大于等于最后一个位置 44，因此我们直接返回 True。
     *
     * 我们再来看看题目中的示例二
     *
     *
     * [3, 2, 1, 0, 4]
     * 我们一开始在位置 00，可以跳跃的最大长度为 33，因此最远可以到达的位置被更新为 33；
     *
     * 我们遍历到位置 11，由于 1 \leq 31≤3，因此位置 11 可达，加上它可以跳跃的最大长度 22 得到 33，没有超过最远可以到达的位置；
     *
     * 位置 22、位置 33 同理，最远可以到达的位置不会被更新；
     *
     * 我们遍历到位置 44，由于 4 > 34>3，因此位置 44 不可达，我们也就不考虑它可以跳跃的最大长度了。
     *
     * 在遍历完成之后，位置 44 仍然不可达，因此我们返回 False。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 94.53%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 30.05%
     * 的用户
     * 通过测试用例：
     * 170 / 170
     */
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
