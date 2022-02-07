package com.meng.origin;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 *
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 *
 * 示例 2：
 *
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 *
 * 示例 3：
 *
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *
 *
 *
 * 提示：
 *
 *     1 <= nums.length <= 10^5
 *     1 <= nums[i] <= 10^9
 *     0 <= limit <= 10^9
 */
public class LongestSubarray {
    /**
     执行用时：194 ms, 在所有 Java 提交中击败了19.52% 的用户
     内存消耗：56.8 MB, 在所有 Java 提交中击败了47.30% 的用户
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length,left = 0 , right = 0,max=0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>((o1, o2) -> {
            return o2 - o1;
        });
        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>((o1, o2) -> {
            return o1 - o2;
        });
        while (right < len){
            maxQueue.add(nums[right]);
            minQueue.add(nums[right]);
            if (maxQueue.peek()-minQueue.peek()>limit){
                max = Math.max(maxQueue.size()-1,max);
                while (maxQueue.peek() - minQueue.peek() >limit){
                    maxQueue.remove(nums[left]);
                    minQueue.remove(nums[left]);
                    left++;
                }
            }
                right++;
        }
        max = Math.max(maxQueue.size(),max);
        return max;
    }

    /**
     * 方法一：滑动窗口 + 有序集合
     *
     * 思路和解法
     *
     * 我们可以枚举每一个位置作为右端点，找到其对应的最靠左的左端点，满足区间中最大值与最小值的差不超过 limit\textit{limit}limit。
     *
     * 注意到随着右端点向右移动，左端点也将向右移动，于是我们可以使用滑动窗口解决本题。
     *
     * 为了方便统计当前窗口内的最大值与最小值，我们可以使用平衡树：
     *
     *     语言自带的红黑树，例如 C++\texttt{C++}C++ 中的 std::multiset\texttt{std::multiset}std::multiset，Java\texttt{Java}Java 中的 TreeMap\texttt{TreeMap}TreeMap；
     *
     *     第三方的平衡树库，例如 Python\texttt{Python}Python 中的 sortedcontainers\texttt{sortedcontainers}sortedcontainers（事实上，这个库的底层实现并不是平衡树，但各种操作的时间复杂度仍然很优秀）；
     *
     *     手写 Treap\texttt{Treap}Treap 一类的平衡树，例如下面的 Golang\texttt{Golang}Golang 代码。
     *
     * 来维护窗口内元素构成的有序集合。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solution/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：87 ms, 在所有 Java 提交中击败了32.66% 的用户
     * 内存消耗：47.8 MB, 在所有 Java 提交中击败了80.08% 的用户
     */
    public int longestSubarray1(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * 方法二：滑动窗口 + 单调队列
     *
     * 思路和解法
     *
     * 在方法一中，我们仅需要统计当前窗口内的最大值与最小值，因此我们也可以分别使用两个单调队列解决本题。
     *
     * 在实际代码中，我们使用一个单调递增的队列 queMin\textit{queMin}queMin 维护最小值，一个单调递减的队列 queMax\textit{queMax}queMax 维护最大值。这样我们只需要计算两个队列的队首的差值，即可知道当前窗口是否满足条件。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solution/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：39 ms, 在所有 Java 提交中击败了73.91% 的用户
     * 内存消耗：55 MB, 在所有 Java 提交中击败了66.48% 的用户
     */
    public int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<Integer>();
        Deque<Integer> queMin = new LinkedList<Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }
    public static void main(String[] args) {
        /*LongestSubarray demo = new LongestSubarray();
        int[] nums = {1,2,3,4,5,6,7,8};
        int limit = 2;
        System.out.println(demo.longestSubarray(nums,limit));*/
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(1,3);
        map.put(4,13);
        map.put(2,33);
        map.put(31,23);
        System.out.println(map);
    }
}
