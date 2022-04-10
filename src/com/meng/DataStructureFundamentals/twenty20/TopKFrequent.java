package com.meng.DataStructureFundamentals.twenty20;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 */
public class TopKFrequent {
    /**
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 90.06%
     * 的用户
     * 内存消耗：
     * 44.2 MB
     * , 在所有 Java 提交中击败了
     * 8.22%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> cache = new HashMap<>();
        for(int num : nums){
            cache.put(num,cache.getOrDefault(num,0)+1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
            queue.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] res = new int[k];
        for(int i = 0 ; i < k ; i++){
            res[i] = queue.poll()[0];
        }
        return res;
    }

    /**
     * 方法一：堆
     * 思路与算法
     *
     * 首先遍历整个数组，并使用哈希表记录每个数字出现的次数，并形成一个「出现次数数组」。找出原数组的前 kk 个高频元素，就相当于找出「出现次数数组」的前 kk 大的值。
     *
     * 最简单的做法是给「出现次数数组」排序。但由于可能有 O(N)O(N) 个不同的出现次数（其中 NN 为原数组长度），故总的算法复杂度会达到 O(N\log N)O(NlogN)，不满足题目的要求。
     *
     * 在这里，我们可以利用堆的思想：建立一个小顶堆，然后遍历「出现次数数组」：
     *
     * 如果堆的元素个数小于 kk，就可以直接插入堆中。
     * 如果堆的元素个数等于 kk，则检查堆顶与当前出现次数的大小。如果堆顶更大，说明至少有 kk 个数字的出现次数比当前值大，故舍弃当前值；否则，就弹出堆顶，并将当前值插入堆中。
     * 遍历完成后，堆中的元素就代表了「出现次数数组」中前 kk 大的值。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/qian-k-ge-gao-pin-yuan-su-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 90.06%
     * 的用户
     * 内存消耗：
     * 44 MB
     * , 在所有 Java 提交中击败了
     * 28.65%
     * 的用户
     * 通过测试用例：
     * 21 / 21
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

}
