package com.meng.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1705. 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 *
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 *
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 * 示例 2：
 *
 * 输入：apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * 输出：5
 * 解释：你可以吃掉 5 个苹果：
 * - 第一天到第三天，你吃的都是第一天长出来的苹果。
 * - 第四天和第五天不吃苹果。
 * - 第六天和第七天，你吃的都是第六天长出来的苹果。
 *
 *
 * 提示：
 *
 * apples.length == n
 * days.length == n
 * 1 <= n <= 2 * 104
 * 0 <= apples[i], days[i] <= 2 * 104
 * 只有在 apples[i] = 0 时，days[i] = 0 才成立
 */
public class EatenApples {
    /**
     * 执行用时：
     * 88 ms
     * , 在所有 Java 提交中击败了
     * 33.13%
     * 的用户
     * 内存消耗：
     * 40 MB
     * , 在所有 Java 提交中击败了
     * 46.39%
     * 的用户
     * 通过测试用例：
     * 70 / 70
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int res = 0;
        PriorityQueue<int[]> cache = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = apples.length;
        for(int i = 0 ; i < n || !cache.isEmpty() ;i++){
            if (i < n && apples[i] > 0){
                cache.add(new int[]{i+days[i]-1,apples[i]});
            }
            while (!cache.isEmpty() && cache.peek()[0]<i){
                cache.poll();
            }
            if (!cache.isEmpty()){
                int[] poll = cache.poll();
                poll[1]--;
                if (poll[1]>0){
                    cache.add(poll);
                }
                res++;
            }
        }
        return res;
    }

    /**
     * 方法一：贪心 + 优先队列
     * 为了将吃苹果的数目最大化，应该使用贪心策略，在尚未腐烂的苹果中优先选择腐烂日期最早的苹果。
     *
     * 为了得到腐烂日期最早的苹果，可以使用优先队列存储每个苹果的腐烂日期，优先队列中最小的元素（即最早的腐烂日期）会最先被取出。由于数组 \textit{apples}apples 和 \textit{days}days 的长度 nn 最大为 2 \times 10^42×10
     * 4
     *  ，两个数组中的每个元素最大为 2 \times 10^42×10
     * 4
     *  ，因此苹果的总数最大可达 (2 \times 10^4) \times (2 \times 10^4) = 4 \times 10^8(2×10
     * 4
     *  )×(2×10
     * 4
     *  )=4×10
     * 8
     *  。如果直接使用优先队列存储每个苹果的腐烂日期，则会导致优先队列中的元素个数过多，时间复杂度和空间复杂度过高，因此需要使用优化的表示法。可以在优先队列中存储二元组，每个二元组表示苹果的腐烂日期和在该日期腐烂的苹果个数，则优先队列中的元素个数最多为 nn 个（即数组长度），可以显著降低时间复杂度和空间复杂度。
     *
     * 计算吃苹果的最大数目分成两个阶段，第一阶段是第 00 天到第 n - 1n−1 天，即天数在数组下标范围内，第二阶段是第 nn 天及以后，即天数在数组下标范围外。
     *
     * 在第一阶段，由于每天树上都可能长出苹果，因此需要对每一天分别计算是否能吃到苹果，并更新优先队列。具体做法如下：
     *
     * 将优先队列中的所有腐烂日期小于等于当前日期的元素取出，这些元素表示已经腐烂的苹果，无法食用；
     *
     * 根据 \textit{days}days 和 \textit{apples}apples 的当前元素计算当天长出的苹果的腐烂日期和数量，如果数量大于 00，则将腐烂日期和数量加入优先队列；
     *
     * 如果优先队列不为空，则当天可以吃 11 个苹果，将优先队列的队首元素的数量减 11，如果队首元素的数量变成 00 则将队首元素取出。
     *
     * 在第二阶段，由于树上不会再长出苹果，因此只需要考虑能吃到的苹果数量。由于优先队列中的每个元素的数量可能很大，因此需要根据当前日期和优先队列的队首元素的腐烂日期和数量计算能吃到的苹果数量。
     *
     * 假设当前日期是第 ii 天，首先将优先队列中的所有腐烂日期小于等于 ii 的元素取出，如果优先队列不为空，则根据优先队列的队首元素计算能吃到的苹果数量。假设优先队列的队首元素的腐烂日期是 xx，数量是 yy，其中 x > ix>i，则有 yy 个苹果，距离腐烂还有 x - ix−i 天，因此能吃到的苹果数量是 \textit{curr} = \min(x - i, y)curr=min(x−i,y)。将优先队列的队首元素 (x, y)(x,y) 取出并将日期增加 \textit{curr}curr，重复上述操作直到优先队列为空，即可得到吃苹果的最大数目。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/solution/chi-ping-guo-de-zui-da-shu-mu-by-leetcod-93ka/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param apples
     * @param days
     * @return
     * 执行用时：
     * 35 ms
     * , 在所有 Java 提交中击败了
     * 88.55%
     * 的用户
     * 内存消耗：
     * 40.1 MB
     * , 在所有 Java 提交中击败了
     * 43.37%
     * 的用户
     * 通过测试用例：
     * 70 / 70
     */
    public int eatenApples1(int[] apples, int[] days) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int i = 0;
        while (i < n) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            int rottenDay = i + days[i];
            int count = apples[i];
            if (count > 0) {
                pq.offer(new int[]{rottenDay, count});
            }
            if (!pq.isEmpty()) {
                int[] arr = pq.peek();
                arr[1]--;
                if (arr[1] == 0) {
                    pq.poll();
                }
                ans++;
            }
            i++;
        }
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] arr = pq.poll();
            int curr = Math.min(arr[0] - i, arr[1]);
            ans += curr;
            i += curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        EatenApples demo = new EatenApples();
        int[] apples = {3,0,0,0,0,4};
        int[] days = {3,0,0,0,0,4};
        System.out.println(demo.eatenApples(apples,days));
    }
}
