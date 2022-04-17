package com.meng.dynamicprogramming.five05;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 918. 环形子数组的最大和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 *
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 *
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 *
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 *
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 3 * 104
 */
public class MaxSubarraySumCircular {
    /**
     *思路错误
     * [5,-3,5]
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len * 2];
        System.arraycopy(nums,0,temp,0,len);
        System.arraycopy(nums,0,temp,len,len);
        int pre = -1,sum = 0 ,max = 0;
        for(int i = 0 ; i < len * 2 ; i++){
            if (i==pre+len){
                pre = i;
                sum = temp[i];
                max = Math.max(max,sum);
            }else {
                sum = Math.max(temp[i],sum+temp[i]);
                max = Math.max(sum,max);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubarraySumCircular demo = new MaxSubarraySumCircular();
        System.out.println(demo.maxSubarraySumCircular1(new int[]{5,-3,5}));
    }

    /**
     * 方法 2：前缀和 + 单调队列
     * 想法
     *
     * 首先，我们可以在一个定长数组上完成这个问题。
     *
     * 我们可以认为循环数组 A 的任意子段，一定是定长数组 A+A 的任一个子段。
     *
     * 例如，对于循环数组 A = [0,1,2,3,4,5]，那么它的子段 [4,5,0,1] 一定也是定长数组 [0,1,2,3,4,5,0,1,2,3,4,5] 的子段。令 B = A + A 就是这个定长数组。
     *
     * 假定 N = A\text{.length}N=A.length，考虑前缀和
     *
     * P_k = B[0] + B[1] + \cdots + B[k-1]
     * P
     * k
     * ​
     *  =B[0]+B[1]+⋯+B[k−1]
     *
     * 然后，考虑最大的 P_j - P_iP
     * j
     * ​
     *  −P
     * i
     * ​
     *   其中 j - i \leq Nj−i≤N。
     *
     * 考虑第 jj 个候选答案：对于固定 jj 的最优结果 P_j - P_iP
     * j
     * ​
     *  −P
     * i
     * ​
     *  。我们希望一个 ii 使得 P_iP
     * i
     * ​
     *   尽量小 并且满足 j - N \leq i < jj−N≤i<j，称对于第 jj 个候选答案的的最优 ii。我们可以用优先队列实现它。
     *
     * 算法
     *
     * 迭代 jj，每次计算第 jj 个候选答案。我们维护一个 queue 保存可能的最优 ii。
     *
     * 核心想法是如果 i_1 < i_2i
     * 1
     * ​
     *  <i
     * 2
     * ​
     *   且 P_{i_1} \geq P_{i_2}P
     * i
     * 1
     * ​
     *
     * ​
     *  ≥P
     * i
     * 2
     * ​
     *
     * ​
     *  ，那么就不再需要考虑 i_1i
     * 1
     * ​
     *  。
     *
     * 查看代码了解更多实现细节。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray/solution/huan-xing-zi-shu-zu-de-zui-da-he-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @return
     * 执行用时：
     * 26 ms
     * , 在所有 Java 提交中击败了
     * 5.40%
     * 的用户
     * 内存消耗：
     * 48.7 MB
     * , 在所有 Java 提交中击败了
     * 11.25%
     * 的用户
     * 通过测试用例：
     * 111 / 111
     */
    public int maxSubarraySumCircular1(int[] A) {
        int N = A.length;
        int[] P = new int[2*N+1];
        for (int i = 0; i < 2*N; ++i)
            P[i+1] = P[i] + A[i % N];
        System.out.println(Arrays.toString(P));
        int ans = A[0];
        Deque<Integer> deque = new ArrayDeque();
        deque.offer(0);

        for (int j = 1; j <= 2*N; ++j) {
            if (deque.peekFirst() < j-N)
                deque.pollFirst();

            ans = Math.max(ans, P[j] - P[deque.peekFirst()]);

            while (!deque.isEmpty() && P[j] <= P[deque.peekLast()])
                deque.pollLast();

            deque.offerLast(j);
        }

        return ans;
    }

}
