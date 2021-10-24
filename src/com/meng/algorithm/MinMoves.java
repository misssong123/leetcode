package com.meng.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
/**
 * 453. 最小操作次数使数组元素相等
 * 难度
 * 简单
 *
 * 317
 *
 *
 *
 *
 *
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 示例 2：
 *
 * 输入：nums = [1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 */
public class MinMoves {
    /**
     * 模拟，操作时间限制
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return 0;
        }
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        if (min == max) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compare);
        PriorityQueue<Integer> last = new PriorityQueue<>(Integer::compare);
        for(int num : nums){
            queue.add(num);
        }
        int res = 1;
        while (queue.size()>1){
            last.add(queue.poll()+1);
        }
        boolean flag = true;
        while (last.peek() != queue.peek() || !flag){
            flag = true;
            while (last.size()>0){
                queue.add(last.poll());
            }
            int pre = queue.peek();
            while (queue.size()>1){
                if (pre != queue.peek()){
                    flag = false;
                }
                last.add(queue.poll()+1);
            }
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        MinMoves demo = new MinMoves();
        int[] nums = {1,1000000};
        System.out.println(demo.minMoves(nums));
    }
    /**
     * 方法一：数学
     *
     * 思路和算法
     *
     * 因为只需要找出让数组所有元素相等的最小操作次数，所以我们不需要考虑数组中各个元素的绝对大小，即不需要真正算出数组中所有元素相等时的元素值，只需要考虑数组中元素相对大小的变化即可。
     *
     * 因此，每次操作既可以理解为使
     * n
     * −
     * 1
     * n−1 个元素增加
     * 1
     * 1，也可以理解使
     * 1
     * 1 个元素减少
     * 1
     * 1。显然，后者更利于我们的计算。
     *
     * 于是，要计算让数组中所有元素相等的操作数，我们只需要计算将数组中所有元素都减少到数组中元素最小值所需的操作数，即计算
     *
     * ∑
     * i
     * =
     * 0
     * n
     * −
     * 1
     * nums
     * [
     * i
     * ]
     * −
     * m
     * i
     * n
     * (
     * nums
     * )
     * ∗
     * n
     * i=0
     * ∑
     * n−1
     * ​
     *  nums[i]−min(nums)∗n
     *
     * 其中
     * n
     * n 为数组
     * nums
     * nums 的长度，
     * min
     * (
     * nums
     * )
     * min(nums)为数组
     * nums
     * nums 中元素的最小值。
     *
     * 在实现中，为避免溢出，我们可以逐个累加每个元素与数组中元素最小值的差，即计算
     *
     * ∑
     * i
     * =
     * 0
     * n
     * −
     * 1
     * (
     * nums
     * [
     * i
     * ]
     * −
     * min
     * (
     * nums
     * )
     * )
     * i=0
     * ∑
     * n−1
     * ​
     *  (nums[i]−min(nums))
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/solution/zui-xiao-cao-zuo-ci-shu-shi-shu-zu-yuan-3meg3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 55.88%
     * 的用户
     * 内存消耗：
     * 38.9 MB
     * , 在所有 Java 提交中击败了
     * 43.50%
     * 的用户
     */
    public int minMoves2(int[] nums) {
        int minNum = Arrays.stream(nums).min().getAsInt();
        int res = 0;
        for (int num : nums) {
            res += num - minNum;
        }
        return res;
    }


}
