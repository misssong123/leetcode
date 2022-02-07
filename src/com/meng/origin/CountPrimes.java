package com.meng.origin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 *
 *
 *
 * 提示：
 *
 *     0 <= n <= 5 * 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountPrimes {
    /**
     * 执行用时：702 ms, 在所有 Java 提交中击败了5.88% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了92.80% 的用户
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int ans = 0;
        for(int i = 1 ; i < n ; i+=2){
            if (isPrimes(i))
                ans++;
        }
        return ans;
    }

    private boolean isPrimes(int k) {
        for(int i = 2 ; i * i <= k ; i++){
            if (k%i == 0)
                return false;
        }
        return true;
    }
    /**
     * 官方解法1
     * 方法二：埃氏筛
     *
     * 枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法，该算法由希腊数学家厄拉多塞（Eratosthenes\rm EratosthenesEratosthenes）提出，称为厄拉多塞筛法，简称埃氏筛。
     *
     * 我们考虑这样一个事实：如果 xxx 是质数，那么大于 xxx 的 xxx 的倍数 2x,3x,…2x,3x,\ldots2x,3x,… 一定不是质数，因此我们可以从这里入手。
     *
     * 我们设 isPrime[i]\textit{isPrime}[i]isPrime[i] 表示数 iii 是不是质数，如果是质数则为 111，否则为 000。从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 000，这样在运行结束的时候我们即能知道质数的个数。
     *
     * 这种方法的正确性是比较显然的：这种方法显然不会将质数标记成合数；另一方面，当从小到大遍历到数 xxx 时，倘若它是合数，则它一定是某个小于 xxx 的质数 yyy 的整数倍，故根据此方法的步骤，我们在遍历到 yyy 时，就一定会在此时将 xxx 标记为 isPrime[x]=0\textit{isPrime}[x]=0isPrime[x]=0。因此，这种方法也不会将合数标记为质数。
     *
     * 当然这里还可以继续优化，对于一个质数 xxx，如果按上文说的我们从 2x2x2x 开始标记其实是冗余的，应该直接从 x⋅xx\cdot xx⋅x 开始标记，因为 2x,3x,…2x,3x,\ldots2x,3x,… 这些数一定在 xxx 之前就被其他数的倍数标记过了，例如 222 的所有倍数，333 的所有倍数等。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：23 ms, 在所有 Java 提交中击败了39.27% 的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了9.32% 的用户
     */
    public int countPrimes1(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 官方解法2
     * 方法三：线性筛
     *
     * 此方法不属于面试范围范畴，本节只做简单讲解。
     *
     * 埃氏筛其实还是存在冗余的标记操作，比如对于 454545 这个数，它会同时被 3,53,53,5 两个数标记为合数，因此我们优化的目标是让每个合数只被标记一次，这样时间复杂度即能保证为 O(n)O(n)O(n)，这就是我们接下来要介绍的线性筛。
     *
     * 相较于埃氏筛，我们多维护一个 primes\textit{primes}primes 数组表示当前得到的质数集合。我们从小到大遍历，如果当前的数 xxx 是质数，就将其加入 primes\textit{primes}primes 数组。
     *
     * 另一点与埃氏筛不同的是，「标记过程」不再仅当 xxx 为质数时才进行，而是对每个整数 xxx 都进行。对于整数 xxx，我们不再标记其所有的倍数 x⋅x,x⋅(x+1),…x\cdot x,x\cdot (x+1),\ldotsx⋅x,x⋅(x+1),…，而是只标记质数集合中的数与 xxx 相乘的数，即 x⋅primes0,x⋅primes1,…x\cdot\textit{primes}_0,x\cdot\textit{primes}_1,\ldotsx⋅primes0​,x⋅primes1​,…，且在发现 x mod primesi=0x\bmod \textit{primes}_i=0xmodprimesi​=0 的时候结束当前标记。
     *
     * 核心点在于：如果 xxx 可以被 primesi\textit{primes}_iprimesi​ 整除，那么对于合数 y=x⋅primesi+1y=x\cdot \textit{primes}_{i+1}y=x⋅primesi+1​ 而言，它一定在后面遍历到 xprimesi⋅primesi+1\frac{x}{\textit{primes}_i}\cdot\textit{primes}_{i+1}primesi​x​⋅primesi+1​ 这个数的时候会被标记，其他同理，这保证了每个合数只会被其「最小的质因数」筛去，即每个合数被标记一次。
     *
     * 线性筛还有其他拓展用途，有能力的读者可以搜索关键字「积性函数」继续探究如何利用线性筛来求解积性函数相关的题目。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：59 ms, 在所有 Java 提交中击败了18.93% 的用户
     * 内存消耗：46 MB, 在所有 Java 提交中击败了5.01% 的用户
     */
    public int countPrimes2(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }
}
