package com.meng;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 *
 *     如果题目有解，该答案即为唯一答案。
 *     输入数组均为非空数组，且长度相同。
 *     输入数组中的元素均为非负数。
 *
 * 示例 1:
 *
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 *
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanCompleteCircuit {
    /**
     * 执行用时：29 ms, 在所有 Java 提交中击败了31.39% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了81.92% 的用户
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int m = gas.length,index = 0;
        while (index<m){
            if (gas[index]>=cost[index]){
                int diff = gas[index] - cost[index],n=index;
                while (diff>=0){
                    if (n == m-1)
                        n=0;
                    else
                        n++;
                    if (n==index)
                        return index;
                    diff += (gas[n]-cost[n]);
                }
            }
            index++;
        }
        return -1;
    }

    /**
     * 改进思路,若在x坐标是不满足要求，意味着从x及之前任意下标开始都不会满足需求
     * @param gas
     * @param cost
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了72.64% 的用户
     */
    public int canCompleteCircuitProve(int[] gas, int[] cost) {
        int m = gas.length,index = 0;
        while (index<m){
            if (gas[index]>=cost[index]){
                int diff = gas[index] - cost[index],n=index;
                while (diff>=0){
                    if (n == m-1)
                        n=0;
                    else
                        n++;
                    if (n==index)
                        return index;
                    diff += (gas[n]-cost[n]);
                }
                if (n>index)
                    index = n;
                else
                    return -1;
            }
            index++;
        }
        return -1;
    }
    /**
     * 官方解法
     * 方法一：一次遍历
     *
     * 思路与算法
     *
     * 最容易想到的解法是：从头到尾遍历每个加油站，并检查以该加油站为起点，最终能否行驶一周。我们可以通过减小被检查的加油站数目，来降低总的时间复杂度。
     *
     * 假设我们此前发现，从加油站 xxx 出发，每经过一个加油站就加一次油，第一个无法到达的加油站是 yyy（不妨设 x<yx<yx<y）。这就说明：
     *
     * ∑i=xygas[i]<∑i=xycost[i]∑i=xjgas[i]≥∑i=xjcost[i] (For all j∈[x,y)) \sum_{i=x}^{y}\textit{gas}[i] < \sum_{i=x}^{y}\textit{cost}[i] \\ \sum_{i=x}^{j}gas[i] \ge \sum_{i=x}^{j}cost[i] ~ \text{(For all $j \in [x, y)$) } i=x∑y​gas[i]<i=x∑y​cost[i]i=x∑j​gas[i]≥i=x∑j​cost[i] (For all j∈[x,y))
     *
     * 第一个式子表明无法到达加油站 yyy，第二个式子表明可以到达 yyy 之前的所有加油站。
     *
     * 现在，考虑任意一个位于 x,yx,yx,y 之间的加油站 zzz，我们现在考察从该加油站出发，能否到达加油站 yyy，也就是要判断 ∑i=zygas[i]\sum_{i=z}^{y}\textit{gas}[i]∑i=zy​gas[i] 与 ∑i=zycost[i]\sum_{i=z}^{y}\textit{cost}[i]∑i=zy​cost[i] 之间的大小关系。
     *
     * 根据上面的式子，我们得到：
     *
     * ∑i=zygas[i]=∑i=xygas[i]−∑i=xz−1gas[i]<∑i=xycost[i]−∑i=xz−1gas[i]<∑i=xycost[i]−∑i=xz−1cost[i]=∑i=zycost[i]\begin{aligned} \sum_{i=z}^{y}\textit{gas}[i]&=\sum_{i=x}^{y}\textit{gas}[i]-\sum_{i=x}^{z-1}\textit{gas}[i] \\ &< \sum_{i=x}^{y}\textit{cost}[i]-\sum_{i=x}^{z-1}\textit{gas}[i] \\ &< \sum_{i=x}^{y}\textit{cost}[i]-\sum_{i=x}^{z-1}cost[i] \\ &= \sum_{i=z}^{y}\textit{cost}[i] \end{aligned} i=z∑y​gas[i]​=i=x∑y​gas[i]−i=x∑z−1​gas[i]<i=x∑y​cost[i]−i=x∑z−1​gas[i]<i=x∑y​cost[i]−i=x∑z−1​cost[i]=i=z∑y​cost[i]​
     *
     * 这就说明：从 x,yx,yx,y 之间的任何一个加油站出发，都无法到达加油站 yyy。
     *
     * 在发现了这一个性质后，算法就很清楚了：我们首先检查第 000 个加油站，并试图找到第一个无法到达的加油站 xxx；如果能找到，下一次就从加油站 x+1x+1x+1 开始检查。最终，我们只遍历了原数组一次。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了80.99% 的用户
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }
}
