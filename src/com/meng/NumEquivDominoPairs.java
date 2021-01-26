package com.meng;

import java.util.HashMap;
import java.util.Map;

/**
 * 1128. 等价多米诺骨牌对的数量
 *
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 *
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 *
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 *
 *
 * 示例：
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 *
 *
 *
 * 提示：
 *
 *     1 <= dominoes.length <= 40000
 *     1 <= dominoes[i][j] <= 9
 */
public class NumEquivDominoPairs {
    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了21.43% 的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了87.24% 的用户
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0 ;
        for(int[] demo : dominoes){
            int cur = demo[0]>demo[1]?demo[1]*10+demo[0] : demo[0]*10+demo[1];
            if (map.get(cur)==null){
                map.put(cur,0);
            }
            int num = map.get(cur);
            count+=num;
            map.put(cur,num+1);
        }
        return count;
    }
    /**
     * 方法一：二元组表示 + 计数
     *
     * 思路及解法
     *
     * 本题中我们需要统计所有等价的多米诺骨牌，其中多米诺骨牌使用二元对代表，「等价」的定义是，在允许翻转两个二元对的的情况下，使它们的元素一一对应相等。
     *
     * 于是我们不妨直接让每一个二元对都变为指定的格式，即第一维必须不大于第二维。这样两个二元对「等价」当且仅当两个二元对完全相同。
     *
     * 注意到二元对中的元素均不大于 999，因此我们可以将每一个二元对拼接成一个两位的正整数，即 (x,y)→10x+y(x, y) \to 10x + y(x,y)→10x+y。这样就无需使用哈希表统计元素数量，而直接使用长度为 100100100 的数组即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-yjlz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法
     * 执行用时：3 ms, 在所有 Java 提交中击败了85.51% 的用户
     * 内存消耗：47.3 MB, 在所有 Java 提交中击败了89.09% 的用户
     */
    public int numEquivDominoPairs1(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }
    /**
     * 哈希表
     *
     *     把有序数对封装成类，每一个多米诺骨牌就对应了类的一个对象；
     *     在遍历的过程中使用哈希表记录出现的数对的数量。注意：有序数对中只要出现的字符对应相等或者交叉对应相等，在哈希表中就认为它们相等，因此须要 重写 hashCode() 方法、 equlas() 方法。例如 [1, 2] 和 [2, 1] 就须要认为是等价的对象；
     *     假设某一类「等价」的对象的总数为 NNN，这一类中任意取出 222 个的组合数 CN2=N(N−1)2C_N^2 = \frac{N(N - 1)}{2}CN2​=2N(N−1)​ 就是这一类对总的「满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量」。对每一类的频数依次求频数对 222 的组合数，再求和即可（其实也可以一遍遍历，一遍用加法计算，我们放在方法二里介绍）。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-dui-de-shu-li-08z8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 官方解法2
     * 执行用时：13 ms, 在所有 Java 提交中击败了26.33% 的用户
     * 内存消耗：47.2 MB, 在所有 Java 提交中击败了95.68% 的用户
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        // 为了避免哈希表自动扩容，根据题目的数据范围，设置哈希表初始化的大小为 100
        // Pair 类重写了 hashCode() 和 equals() 方法
        Map<Pair, Integer> freq = new HashMap<>(100);
        for (int[] dominoe : dominoes) {
            Pair key = new Pair(dominoe[0], dominoe[1]);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        // 根据组合数公式 C_n^2 = (n * (n - 1)) / 2 计算等价骨牌能够组成的组合数，再求和
        int count = 0;
        for (int f : freq.values()) {
            count += (f * (f - 1)) / 2;
        }
        return count;
    }

    private class Pair {

        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 让有序数对 [a, b] 和 [b, a] 认为是相等的对象
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return key == pair.key && value == pair.value || key == pair.value && value == pair.key;
        }

        /**
         * 让相同的数对映射到同一个位置
         *
         * @return
         */
        @Override
        public int hashCode() {
            if (key > value) {
                return value * 10 + key;
            }
            return key * 10 + value;
        }
    }
}
