package com.meng;

/**
 * 1052. 爱生气的书店老板
 *
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *
 *
 * 示例：
 *
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 *
 *
 * 提示：
 *
 *     1 <= X <= customers.length == grumpy.length <= 20000
 *     0 <= customers[i] <= 1000
 *     0 <= grumpy[i] <= 1
 */
public class MaxSatisfied {
    /**
     执行用时：3 ms, 在所有 Java 提交中击败了79.44% 的用户
     内存消耗：40.7 MB, 在所有 Java 提交中击败了85.67% 的用户
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = customers.length;
        int max = 0;
        int sum = 0;
        for(int i = 0 ; i < len ; i++){
            if (grumpy[i]==0 || i < X){
                sum += customers[i];
            }
        }
        //移动位置，计算增加顾客最大的区间
        max = sum;
        for(int i = X ; i < len ; i++){
            sum += customers[i]*grumpy[i] - customers[i-X]*grumpy[i-X];
            max = Math.max(sum,max);
        }
        return max;
    }
    /**
     * 方法一：滑动窗口
     *
     * 假设数组 customers\textit{customers}customers 和 grumpy\textit{grumpy}grumpy 的长度是 nnn，不使用秘密技巧时，满意的顾客数量是 total\textit{total}total，则 total\textit{total}total 的值为：
     *
     * total=∑i=0n−1customers[i]×I(grumpy[i]=0)\textit{total}=\sum\limits_{i=0}^{n-1} \textit{customers}[i] \times \mathbb{I}(\textit{grumpy}[i]=0) total=i=0∑n−1​customers[i]×I(grumpy[i]=0)
     *
     * 其中 I(grumpy[i]=0)\mathbb{I}(\textit{grumpy}[i]=0)I(grumpy[i]=0) 为示性函数，当 grumpy[i]=0\textit{grumpy}[i]=0grumpy[i]=0 时 I(grumpy[i]=0)=1\mathbb{I}(\textit{grumpy}[i]=0)=1I(grumpy[i]=0)=1，当 grumpy[i]=1\textit{grumpy}[i]=1grumpy[i]=1 时 I(grumpy[i]=0)=0\mathbb{I}(\textit{grumpy}[i]=0)=0I(grumpy[i]=0)=0。
     *
     * 秘密技巧的效果是，找到数组 grumpy\textit{grumpy}grumpy 的一个长度为 XXX 的子数组，使得该子数组中的元素都变成 000，数组 customers\textit{customers}customers 的对应下标范围的子数组中的所有顾客都变成满意的。如果对下标范围 [i−X+1,i][i-X+1,i][i−X+1,i] 的子数组使用秘密技巧时（其中 i≥X−1i \ge X-1i≥X−1），增加的满意顾客的数量是 increasei\textit{increase}_iincreasei​，则 increasei\textit{increase}_iincreasei​ 的值为：
     *
     * increasei=∑j=i−X+1icustomers[j]×I(grumpy[j]=1)\textit{increase}_i=\sum\limits_{j=i-X+1}^i \textit{customers}[j] \times \mathbb{I}(\textit{grumpy}[j]=1) increasei​=j=i−X+1∑i​customers[j]×I(grumpy[j]=1)
     *
     * 其中 I(grumpy[j]=1)\mathbb{I}(\textit{grumpy}[j]=1)I(grumpy[j]=1) 为示性函数，当 grumpy[j]=1\textit{grumpy}[j]=1grumpy[j]=1 时 I(grumpy[j]=1)=1\mathbb{I}(\textit{grumpy}[j]=1)=1I(grumpy[j]=1)=1，当 grumpy[j]=0\textit{grumpy}[j]=0grumpy[j]=0 时 I(grumpy[j]=1)=0\mathbb{I}(\textit{grumpy}[j]=1)=0I(grumpy[j]=1)=0。由于 grumpy[j]\textit{grumpy}[j]grumpy[j] 的值只能是 111 或 000，因此 I(grumpy[j]=1)=grumpy[j]\mathbb{I}(\textit{grumpy}[j]=1)=\textit{grumpy}[j]I(grumpy[j]=1)=grumpy[j]，increasei\textit{increase}_iincreasei​ 的值可以表示成如下形式：
     *
     * increasei=∑j=i−X+1icustomers[j]×grumpy[j]\textit{increase}_i=\sum\limits_{j=i-X+1}^i \textit{customers}[j] \times \textit{grumpy}[j] increasei​=j=i−X+1∑i​customers[j]×grumpy[j]
     *
     * 为了让满意的顾客数量最大化，应该找到满足 X−1≤i<nX-1 \le i<nX−1≤i<n 的下标 iii，使得 increasei\textit{increase}_iincreasei​ 的值最大。
     *
     * 注意到当 i>X−1i>X-1i>X−1 时，将 iii 替换成 i−1i-1i−1，可以得到 increasei−1\textit{increase}_{i-1}increasei−1​ 的值为：
     *
     * increasei−1=∑j=i−Xi−1customers[j]×grumpy[j]\textit{increase}_{i-1}=\sum\limits_{j=i-X}^{i-1} \textit{customers}[j] \times \textit{grumpy}[j] increasei−1​=j=i−X∑i−1​customers[j]×grumpy[j]
     *
     * 将 increasei\textit{increase}_iincreasei​ 和 increasei−1\textit{increase}_{i-1}increasei−1​ 相减，可以得到如下关系：
     *
     *  increasei−increasei−1=customers[i]×grumpy[i]−customers[i−X]×grumpy[i−X]\begin{aligned} &\quad \ \textit{increase}_i-\textit{increase}_{i-1} \\ &= \textit{customers}[i] \times \textit{grumpy}[i]-\textit{customers}[i-X] \times \textit{grumpy}[i-X] \end{aligned} ​ increasei​−increasei−1​=customers[i]×grumpy[i]−customers[i−X]×grumpy[i−X]​
     *
     * 整理得到：
     *
     * increasei=increasei−1−customers[i−X]×grumpy[i−X]+customers[i]×grumpy[i]\textit{increase}_i=\textit{increase}_{i-1}-\textit{customers}[i-X] \times \textit{grumpy}[i-X]+\textit{customers}[i] \times \textit{grumpy}[i] increasei​=increasei−1​−customers[i−X]×grumpy[i−X]+customers[i]×grumpy[i]
     *
     * 上述过程可以看成维护一个长度为 XXX 的滑动窗口。当滑动窗口从下标范围 [i−X,i−1][i-X,i-1][i−X,i−1] 移动到下标范围 [i−X+1,i][i-X+1,i][i−X+1,i] 时，下标 i−Xi-Xi−X 从窗口中移出，下标 iii 进入到窗口内。
     *
     * 利用上述关系，可以在 O(1)O(1)O(1) 的时间内通过 increasei−1\textit{increase}_{i-1}increasei−1​ 得到 increasei\textit{increase}_iincreasei​。
     *
     * 由于长度为 XXX 的子数组的最小结束下标是 X−1X-1X−1，因此第一个长度为 XXX 的子数组对应的 increase\textit{increase}increase 的值为 increaseX−1\textit{increase}_{X-1}increaseX−1​，需要通过遍历数组 customers\textit{customers}customers 和 grumpy\textit{grumpy}grumpy 的前 XXX 个元素计算得到。从 increaseX\textit{increase}_XincreaseX​ 到 increasen−1\textit{increase}_{n-1}increasen−1​ 的值则可利用上述关系快速计算得到。只需要遍历数组 customers\textit{customers}customers 和 grumpy\textit{grumpy}grumpy 各一次即可得到 X≤i<nX \le i<nX≤i<n 的每个 increasei\textit{increase}_iincreasei​ 的值，时间复杂度是 O(n)O(n)O(n)。
     *
     * 又由于计算初始的 total\textit{total}total 的值需要遍历数组 customers\textit{customers}customers 和 grumpy\textit{grumpy}grumpy 各一次，因此整个过程只需要遍历数组 customers\textit{customers}customers 和 grumpy\textit{grumpy}grumpy 各两次，时间复杂度是 O(n)O(n)O(n)。
     *
     * 在上述过程中维护增加的满意顾客的最大数量，记为 maxIncrease\textit{maxIncrease}maxIncrease，则满意顾客的最大总数是 total+maxIncrease\textit{total}+\textit{maxIncrease}total+maxIncrease。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner/solution/ai-sheng-qi-de-shu-dian-lao-ban-by-leetc-dloq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     *执行用时：2 ms, 在所有 Java 提交中击败了99.01% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了22.90% 的用户
     */
    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int total = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }


}
