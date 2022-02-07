package com.meng.origin;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1046. 最后一块石头的重量
 *
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 *     如果 x == y，那么两块石头都会被完全粉碎；
 *     如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 *
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *
 *
 *
 * 提示：
 *
 *     1 <= stones.length <= 30
 *     1 <= stones[i] <= 1000
 */
public class LastStoneWeight {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了41.83% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了56.47% 的用户
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        if(stones.length==1)
            return stones[0];
        PriorityQueue<Integer> queue = new PriorityQueue<>((x,y)->y-x);
        for(int i : stones)
            queue.add(i);
        while (queue.size()>1){
            int x = queue.poll();
            int y = queue.poll();
            if (x>y)
                queue.add(x-y);
        }
        return queue.size()>0?queue.peek():0;
    }
    /**
     * 方法一：最大堆
     *
     * 将所有石头的重量放入最大堆中。每次依次从队列中取出最重的两块石头 aaa 和 bbb，必有 a≥ba \ge ba≥b。如果 a>ba>ba>b，则将新石头 a−ba-ba−b 放回到最大堆中；如果 a=ba=ba=b，两块石头完全被粉碎，因此不会产生新的石头。重复上述操作，直到剩下的石头少于 222 块。
     *
     * 最终可能剩下 111 块石头，该石头的重量即为最大堆中剩下的元素，返回该元素；也可能没有石头剩下，此时最大堆为空，返回 000。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/last-stone-weight/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-b-xgsx/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：2 ms, 在所有 Java 提交中击败了41.83% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了55.65% 的用户
     */
    public int lastStoneWeight1(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    /**
     *如果数组长度等于1 直接返回。
     * 然后首先排序
     * 然后取出倒数第一个数的索引，倒数第二个数的索引。
     * 然后设置一个stoneCount记录当前数组中剩余的石头数量。
     * 如果数量大于1的话就进行while循环。
     * 在循环里面如果倒数第一个数和倒数第二个数相同，则把这两个数设置为0，代表石头被消灭,石头的数量 stoneCount -= 2；
     * 在循环里面如果倒数第一个数和倒数第二个数不相同，则先把倒数第一个数减去倒数第二个数再赋值回去，然后把倒数第二个数设置为0，代表石头被消灭,石头的数量 stoneCount -= 1；
     *
     * 然后最后判断如果是stoneCount = 0的话 就返回0，
     * 如果stoneCount = 1的话 就返回倒数第一个数的值。
     *
     * 作者：bobby996
     * 链接：https://leetcode-cn.com/problems/last-stone-weight/solution/1046ti-10000-3662-by-bobby996-03tg/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param stones
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了51.51% 的用户
     */
    public int lastStoneWeight2(int[] stones) {
        if(stones.length == 1) {
            return stones[0];
        }
        Arrays.sort(stones);
        int stoneCount = stones.length;
        int lastOneIndex = stones.length - 1 ;
        int lastTwoIndex = lastOneIndex - 1;
        while(stoneCount > 1) {
            if(stones[lastOneIndex] == stones[lastTwoIndex]) {
                stones[lastOneIndex] = 0;
                stones[lastTwoIndex] = 0;
                stoneCount -= 2;
            }else {
                stones[lastOneIndex] = stones[lastOneIndex] - stones[lastTwoIndex];
                stones[lastTwoIndex] = 0;
                stoneCount -= 1;
            }
            Arrays.sort(stones);
        }
        if(stoneCount == 0) {
            return 0;
        }else {
            return stones[lastOneIndex];
        }
    }
}
