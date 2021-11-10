package com.meng.algorithm;

import com.meng.Hanota;
import com.meng.HasCycle;

import java.util.*;

/**
 * 496. 下一个更大元素 I
 * 难度
 * 简单
 *
 * 547
 *
 *
 *
 *
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 *
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 */
public class NextGreaterElement {
    /**
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 98.82%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 96.69%
     * 的用户
     * 通过测试用例：
     * 15 / 15
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length,n = nums2.length;
        Map<Integer,Integer> map = new HashMap<>();
        int [] res = new int[m];
        for(int i = 0 ; i < n ; i++){
            map.put(nums2[i],i);
        }
        for(int i = 0 ; i < m ; i++){
            int index = map.get(nums1[i])+1;
            while (index < n){
                if (nums2[index] > nums1[i]){
                    break;
                }
                index++;
            }
            res[i] = index == n ? -1 : nums2[index];
        }
        return res;
    }

    /**
     * 方法一：暴力
     *
     * 思路和算法
     *
     * 根据题意，我们发现
     * nums
     * 1
     * nums
     * 1
     * ​
     *   是一个查询数组，逐个查询
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中元素右边的第一个更大的值。因此，我们可以暴力地逐个计算
     * nums
     * 1
     * nums
     * 1
     * ​
     *   中的每个元素值
     * nums
     * 1
     * [
     * i
     * ]
     * nums
     * 1
     * ​
     *  [i] 在
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中对应位置的右边的第一个比
     * nums
     * 1
     * [
     * i
     * ]
     * nums
     * 1
     * ​
     *  [i] 大的元素值。具体地，我们使用如下方法：
     *
     * 初始化与
     * nums
     * 1
     * nums
     * 1
     * ​
     *   等长的查询数组
     * res
     * res。
     * 遍历
     * nums
     * 1
     * nums
     * 1
     * ​
     *   中的所有元素，不妨设当前遍历到元素为
     * nums
     * 1
     * [
     * i
     * ]
     * nums
     * 1
     * ​
     *  [i]：
     * 从前向后遍历
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中的元素，直至找到
     * nums
     * 2
     * [
     * j
     * ]
     * =
     * nums
     * 1
     * [
     * i
     * ]
     * nums
     * 2
     * ​
     *  [j]=nums
     * 1
     * ​
     *  [i]；
     * 从
     * j
     * +
     * 1
     * j+1 开始继续向后遍历，直至找到
     * nums
     * 2
     * [
     * k
     * ]
     * >
     * nums
     * 2
     * [
     * j
     * ]
     * nums
     * 2
     * ​
     *  [k]>nums
     * 2
     * ​
     *  [j]，其中
     * k
     * ≥
     * j
     * +
     * 1
     * k≥j+1；
     * 如果找到了
     * nums
     * 2
     * [
     * k
     * ]
     * nums
     * 2
     * ​
     *  [k]，则将
     * res
     * [
     * i
     * ]
     * res[i] 置为
     * nums
     * 2
     * [
     * k
     * ]
     * nums
     * 2
     * ​
     *  [k]，否则将
     * res
     * [
     * i
     * ]
     * res[i] 置为
     * −
     * 1
     * −1。
     * 查询数组
     * res
     * res 即为最终结果。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 15.07%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 33.49%
     * 的用户
     * 通过测试用例：
     * 15 / 15
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = 0;
            while (j < n && nums2[j] != nums1[i]) {
                ++j;
            }
            int k = j + 1;
            while (k < n && nums2[k] < nums2[j]) {
                ++k;
            }
            res[i] = k < n ? nums2[k] : -1;
        }
        return res;
    }

    /**
     * 方法二：单调栈 + 哈希表
     *
     * 思路
     *
     * 我们可以先预处理
     * nums
     * 2
     * nums
     * 2
     * ​
     *  ，使查询
     * nums
     * 1
     * nums
     * 1
     * ​
     *   中的每个元素在
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中对应位置的右边的第一个更大的元素值时不需要再遍历
     * nums
     * 2
     * nums
     * 2
     * ​
     *  。于是，我们将题目分解为两个子问题：
     *
     * 第
     * 1
     * 1 个子问题：如何更高效地计算
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中每个元素右边的第一个更大的值；
     * 第
     * 2
     * 2 个子问题：如何存储第
     * 1
     * 1 个子问题的结果。
     * 算法
     *
     * 我们可以使用单调栈来解决第
     * 1
     * 1 个子问题。倒序遍历
     * nums
     * 2
     * nums
     * 2
     * ​
     *  ，并用单调栈中维护当前位置右边的更大的元素列表，从栈底到栈顶的元素是单调递减的。
     *
     * 具体地，每次我们移动到数组中一个新的位置
     * i
     * i，就将当前单调栈中所有小于
     * nums
     * 2
     * [
     * i
     * ]
     * nums
     * 2
     * ​
     *  [i] 的元素弹出单调栈，当前位置右边的第一个更大的元素即为栈顶元素，如果栈为空则说明当前位置右边没有更大的元素。随后我们将位置
     * i
     * i 的元素入栈。
     *
     * 可以结合以下例子来理解。
     *
     *
     * 1 / 13
     *
     * 因为题目规定了
     * nums
     * 2
     * nums
     * 2
     * ​
     *   是没有重复元素的，所以我们可以使用哈希表来解决第
     * 2
     * 2 个子问题，将元素值与其右边第一个更大的元素值的对应关系存入哈希表。
     *
     * 细节
     *
     * 因为在这道题中我们只需要用到
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中元素的顺序而不需要用到下标，所以栈中直接存储
     * nums
     * 2
     * nums
     * 2
     * ​
     *   中元素的值即可。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 46.99%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 96.73%
     * 的用户
     * 通过测试用例：
     * 15 / 15
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num1 = {4,1,2};
        int[] num2= {1,3,4,2};
        NextGreaterElement demo = new NextGreaterElement();
        System.out.println(Arrays.toString(demo.nextGreaterElement(num1,num2)));
    }
}
