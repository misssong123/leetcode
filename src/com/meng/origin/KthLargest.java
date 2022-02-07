package com.meng.origin;

import java.util.*;

/**
 * 703. 数据流中的第 K 大元素
 *
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest 类：
 *
 *     KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 *     int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 *
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 *
 * 提示：
 *
 *     1 <= k <= 104
 *     0 <= nums.length <= 104
 *     -104 <= nums[i] <= 104
 *     -104 <= val <= 104
 *     最多调用 add 方法 104 次
 *     题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class KthLargest {
    /**
     * 执行用时：21 ms, 在所有 Java 提交中击败了42.10% 的用户
     * 内存消耗：43.7 MB, 在所有 Java 提交中击败了35.80% 的用户
     */
    private PriorityQueue<Integer> queue ;
    private int count ;
    public KthLargest(int k, int[] nums) {
        count = k;
        queue = new PriorityQueue<>(k);
        for(int i = 0 ; i < nums.length ; i++){
            queue.offer(nums[i]);
        }
        while (queue.size()>k){
            queue.poll();
        }
    }
    public int add(int val) {
       queue.offer(val);
        while (queue.size()>count){
            queue.poll();
        }
        return queue.peek();
    }
    public static void main(String[] args) {
        int k = 2;
        int [] nums = {0};
        KthLargest1 demo = new KthLargest1(k,nums);
        System.out.println(demo.add(-1));
        System.out.println(demo.add(1));
        System.out.println(demo.add(-2));
        System.out.println(demo.add(-4));
        System.out.println(demo.add(3));
    }
}

/**
 * 方法一：优先队列
 *
 * 我们可以使用一个大小为 kkk 的优先队列来存储前 kkk 大的元素，其中优先队列的队头为队列中最小的元素，也就是第 kkk 大的元素。
 *
 * 在单次插入的操作中，我们首先将元素 val\textit{val}val 加入到优先队列中。如果此时优先队列的大小大于 kkk，我们需要将优先队列的队头元素弹出，以保证优先队列的大小为 kkk。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/solution/shu-ju-liu-zhong-de-di-k-da-yuan-su-by-l-woz8/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 官方解法
 * 执行用时：21 ms, 在所有 Java 提交中击败了42.10% 的用户
 * 内存消耗：44.1 MB, 在所有 Java 提交中击败了9.71% 的用户
 */
class KthLargest1 {
    PriorityQueue<Integer> pq;
    int k;

    public KthLargest1(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
