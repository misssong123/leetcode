package com.meng.algorithm.solution519;

import java.util.*;

/**
 * 519. 随机翻转矩阵
 * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
 *
 * 尽量最少调用内置的随机函数，并且优化时间和空间复杂度。
 *
 * 实现 Solution 类：
 *
 * Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
 * int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
 * void reset() 将矩阵中所有的值重置为 0
 *
 *
 * 示例：
 *
 * 输入
 * ["Solution", "flip", "flip", "flip", "reset", "flip"]
 * [[3, 1], [], [], [], [], []]
 * 输出
 * [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
 *
 * 解释
 * Solution solution = new Solution(3, 1);
 * solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 * solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
 * solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
 * solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
 * solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 104
 * 每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
 * 最多调用 1000 次 flip 和 reset 方法。
 */

/**
 * 内存限制
 */
class Solution {
    List<Integer> list ;
    int sum ;
    int n ;
    Random random;
    public Solution(int m, int n) {
        sum = m * n;
        list = new ArrayList<>(sum);
        for(int i = 0 ; i < m * n ; i++){
            list.add(i);
        }
        this.n = n ;
        random =  new Random();
    }

    public int[] flip() {
        int index = random.nextInt(list.size());
        index = list.remove(index);
        int x = index / n;
        int y = index % n;
        return new int[]{x,y};
    }

    public void reset() {
        list.clear();
        for(int i = 0 ; i < sum ; i++){
            list.add(i);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        random.nextInt(10);
    }
}

/**
 * 方法一：数组映射
 * 解题思路
 *
 * 我们可以考虑将矩阵转换为一个长度为 m \times nm×n 的一维数组 \textit{map}map，对于矩阵中的位置 (i, j)(i,j)，它对应了 \textit{map}map 中的元素 \textit{map}[i * n + j]map[i∗n+j]，这样就保证了矩阵和 mapmap 的元素映射。在经过 m \times n-km×n−k 次翻转 \texttt{flip}flip 后，我们会修改 \textit{map}map 与矩阵的映射，使得当前矩阵中有m \times n-km×n−k 个 11 和 kk 个 00。
 * 此时我们可以利用数组中元素的交换，使得 \textit{map}[0 \cdots k - 1]map[0⋯k−1] 映射到矩阵中的 00，而 \textit{map}[k \cdots m \times n - 1]map[k⋯m×n−1] 映射到矩阵中的 11。这样的好处是，当我们进行下一次翻转操作时，我们只需要在 [0, k-1)[0,k−1) 这个区间生成随机数 xx，并将 \textit{map}[x]map[x] 映射到的矩阵的位置进行翻转即可。
 *
 * 在将 \textit{map}[x]map[x] 进行翻转后，此时矩阵中有 k - 1k−1 个 00，所以我们需要保证 \textit{map}[0 .. k - 2]map[0..k−2] 都映射到矩阵中的 00。由于此时 \textit{map}[x]map[x] 映射到了矩阵中的 11，因此我们可以将 \textit{map}[x]map[x] 与 \textit{map}[k - 1]map[k−1] 的值进行交换，即将这个新翻转的 11 作为 \textit{map}[k - 1]map[k−1] 的映射，而把原本 \textit{map}[k - 1]map[k−1] 映射到的 00 交给 xx。这样我们就保证了在每一次翻转操作后，\textit{map}map 中的前 kk 个元素恰好映射到矩阵中的所有 kk 个 00。
 *
 * 那么我们如何维护这个一维数组 \textit{map}map 呢？我们可以发现，\textit{map}map 中的大部分映射关系是不会改变的，即矩阵中的 (i, j)(i,j) 映射到 A[i * n + j]A[i∗n+j]，因此我们可以使用一个 \texttt{HashMap}HashMap 存储那些 \textit{map}map 中那些被修改了的映射。对于一个数 xx，如果 xx 不是 \texttt{HashMap}HashMap 中的一个键，那么它直接映射到最开始的 (x/n, x \%n)(x/n,x%n)；如果 xx 是 \texttt{HashMap}HashMap 中的一个键，那么它映射到其在 \texttt{HashMap}HashMap 中对应的值。实际运行中 \texttt{HashMap}HashMap 的大小仅和翻转次数成正比，因为每一次翻转操作我们会交换 \textit{map}map 中两个元素的映射，即最多有两个元素的映射关系被修改。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/random-flip-matrix/solution/sui-ji-fan-zhuan-ju-zhen-by-leetcode-sol-pfmr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 30 ms
 * , 在所有 Java 提交中击败了
 * 65.57%
 * 的用户
 * 内存消耗：
 * 39 MB
 * , 在所有 Java 提交中击败了
 * 82.79%
 * 的用户
 * 通过测试用例：
 * 20 / 20
 */
class Solution1 {
    Map<Integer, Integer> map = new HashMap<>();
    int m, n, total;
    Random rand = new Random();

    public Solution1(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
    }

    public int[] flip() {
        int x = rand.nextInt(total);
        total--;
        // 查找位置 x 对应的映射
        int idx = map.getOrDefault(x, x);
        // 将位置 x 对应的映射设置为位置 total 对应的映射
        map.put(x, map.getOrDefault(total, total));
        return new int[]{idx / n, idx % n};
    }

    public void reset() {
        total = m * n;
        map.clear();
    }
}

/**
 * 方法二：分块
 * 解题思路
 *
 * 我们可以考虑另一种方法来维护这个一维数组 \textit{map}map。假设我们把这 m \times nm×n 个位置放到 kk 个桶中，第一个桶对应 map[0 \cdots a_{1}]map[0⋯a
 * 1
 * ​
 *  ]，第二个桶对应 \textit{map}[a_{1} + 1 \cdots a_{2}]map[a
 * 1
 * ​
 *  +1⋯a
 * 2
 * ​
 *  ]，以此类推。我们用 \textit{cnt}[i]cnt[i] 表示第 ii 个桶中还剩余的 00 的个数，并给每个桶分配一个集合 \texttt{HashSet}HashSet 存放桶中哪些位置对应的是 11（即被翻转过的位置）。
 *
 * 假设当前矩阵中还有 \textit{total}total 个 00，我们从 [1, \textit{total}][1,total] 中随机出一个整数 xx，并遍历所有的桶，根据所有的 \textit{cnt}[i]cnt[i] 可以找出第 xx 个 00 属于哪个桶。假设其属于第 ii 个桶，那么 xx 应该满足 \textit{sum}[i - 1] < x <= \textit{sum}[i]sum[i−1]<x<=sum[i]，其中 \textit{sum}[i]sum[i] 表示前 ii 个桶的 \textit{cnt}[i]cnt[i] 之和，即前 ii 个桶中 00 的个数。随后我们令 y = x - \textit{sum}[i - 1]y=x−sum[i−1]，即我们需要找到第 ii 个桶中的第 yy 个 00。我们可以依次遍历 [d \times i + 1 \cdots d \times (i+1)][d×i+1⋯d×(i+1)] 中的数，根据第 ii 个桶对应的集合，找出第 yy 个 00 的位置。最后我们将这个 00 进行翻转。
 *
 * 由于 \textit{map}map 被分成了 kk 个桶，因此每个桶的平均长度为 \lfloor \frac{m \times n}{k} \rfloor⌊
 * k
 * m×n
 * ​
 *  ⌋。在上述的方法中，遍历所有的桶的时间复杂度为 O(k)O(k)，而遍历第 ii 个桶的时间复杂度为 O(\frac{m \times n}{k})O(
 * k
 * m×n
 * ​
 *  )，因此总时间复杂度为 O(k + \frac{m \times n}{k})O(k+
 * k
 * m×n
 * ​
 *  )。根据均值不等式，可以得知在 k = \sqrt{m \times n}k=
 * m×n
 * ​
 *  ，总的时间复杂度最小。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/random-flip-matrix/solution/sui-ji-fan-zhuan-ju-zhen-by-leetcode-sol-pfmr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 134 ms
 * , 在所有 Java 提交中击败了
 * 6.56%
 * 的用户
 * 内存消耗：
 * 40.4 MB
 * , 在所有 Java 提交中击败了
 * 5.74%
 * 的用户
 * 通过测试用例：
 * 20 / 20
 */
class Solution2 {
    int m, n;
    int total, bucketSize;
    List<Set<Integer>> buckets = new ArrayList<>();
    Random rand = new Random();

    public Solution2(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.bucketSize = (int) Math.sqrt(total);
        for (int i = 0; i < total; i += bucketSize) {
            buckets.add(new HashSet<Integer>());
        }
    }

    public int[] flip() {
        int x = rand.nextInt(total);
        int sumZero = 0;
        int curr = 0;
        total--;

        for (Set<Integer> bucket : buckets) {
            if (sumZero + bucketSize - bucket.size() > x) {
                for (int i = 0; i < bucketSize; ++i) {
                    if (!bucket.contains(curr + i)) {
                        if (sumZero == x) {
                            bucket.add(curr + i);
                            return new int[]{(curr + i) / n, (curr + i) % n};
                        }
                        sumZero++;
                    }
                }
            }
            curr += bucketSize;
            sumZero += bucketSize - bucket.size();
        }

        return null;
    }

    public void reset() {
        total = m * n;
        for (Set<Integer> bucket : buckets) {
            bucket.clear();
        }
    }
}