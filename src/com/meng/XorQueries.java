package com.meng;

/**
 * 1310. 子数组异或查询
 *
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 *
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 *
 * 并返回一个包含给定查询 queries 所有结果的数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 *
 * 示例 2：
 *
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 *
 *
 *
 * 提示：
 *
 *     1 <= arr.length <= 3 * 10^4
 *     1 <= arr[i] <= 10^9
 *     1 <= queries.length <= 3 * 10^4
 *     queries[i].length == 2
 *     0 <= queries[i][0] <= queries[i][1] < arr.length
 */
public class XorQueries {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：49.2 MB, 在所有 Java 提交中击败了60.35% 的用户
     * @param arr
     * @param queries
     * @return
     */
    public int [] xorQueries(int[] arr,int[][]queries){
        int n = arr.length;
        int [] cache = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            cache[i] = cache[i-1] ^ arr[i-1];
        }
        int len = queries.length;
        int [] res = new int[len];
        int index = 0;
        for(int[] temp : queries){
            if (temp[0] == temp[1]){
                res[index++] = arr[temp[0]];
            }else {
                res[index++] = cache[temp[1]+1] ^ cache[temp[0]];
            }
        }
        return res;
    }
    /**
     * 方法一：前缀异或
     *
     * 朴素的想法是，对每个查询，计算数组中的对应下标范围内的元素的异或结果。每个查询的计算时间取决于查询对应的下标范围的长度。如果数组 arr\textit{arr}arr 的长度为 nnn，数组 queries\textit{queries}queries 的长度为 mmm（即有 mmm 个查询），则最坏情况下每个查询都需要 O(n)O(n)O(n) 的时间计算结果，总时间复杂度是 O(nm)O(nm)O(nm)，会超出时间限制，因此必须优化。
     *
     * 由于有 mmm 个查询，对于每个查询都要计算结果，因此应该优化每个查询的计算时间。理想情况下，每个查询的计算时间应该为 O(1)O(1)O(1)。为了将每个查询的计算时间从 O(n)O(n)O(n) 优化到 O(1)O(1)O(1)，需要计算数组的前缀异或。
     *
     * 定义长度为 n+1n+1n+1 的数组 xors\textit{xors}xors。令 xors[0]=0\textit{xors}[0]=0xors[0]=0，对于 0≤i<n0 \le i<n0≤i<n，xors[i+1]=xors[i]⊕arr[i]\textit{xors}[i+1]=\textit{xors}[i] \oplus \textit{arr}[i]xors[i+1]=xors[i]⊕arr[i]，其中 ⊕\oplus⊕ 是异或运算符。当 1≤i≤n1 \le i \le n1≤i≤n 时，xors[i]\textit{xors}[i]xors[i] 为从 arr[0]\textit{arr}[0]arr[0] 到 arr[i−1]\textit{arr}[i-1]arr[i−1] 的元素的异或运算结果：
     *
     * xors[i]=arr[0]⊕…⊕arr[i−1]\textit{xors}[i]=\textit{arr}[0] \oplus \ldots \oplus \textit{arr}[i-1] xors[i]=arr[0]⊕…⊕arr[i−1]
     *
     * 对于查询 [left,right](left≤right)[\textit{left},\textit{right}](\textit{left} \le \textit{right})[left,right](left≤right)，用 Q(left,right)Q(\textit{left},\textit{right})Q(left,right) 表示该查询的结果。
     *
     *     当 left=0\textit{left}=0left=0 时，Q(left,right)=xors[right+1]Q(\textit{left},\textit{right})=\textit{xors}[\textit{right}+1]Q(left,right)=xors[right+1]。
     *
     *     当 left>0\textit{left}>0left>0 时，Q(left,right)Q(\textit{left},\textit{right})Q(left,right) 的计算如下：
     *
     *  Q(left,right)=arr[left]⊕…⊕arr[right]=(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[left−1])⊕(arr[left]⊕…⊕arr[right])=(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[right])=xors[left]⊕xors[right+1]\begin{aligned} & \quad ~ Q(\textit{left},\textit{right}) \\ &= \textit{arr}[\textit{left}] \oplus \ldots \oplus \textit{arr}[\textit{right}] \\ &= (\textit{arr}[0] \oplus \ldots \oplus \textit{arr}[\textit{left}-1]) \oplus (\textit{arr}[0] \oplus \ldots \oplus \textit{arr}[\textit{left}-1]) \oplus (\textit{arr}[\textit{left}] \oplus \ldots \oplus \textit{arr}[\textit{right}]) \\ &= (\textit{arr}[0] \oplus \ldots \oplus \textit{arr}[\textit{left}-1]) \oplus (\textit{arr}[0] \oplus \ldots \oplus \textit{arr}[\textit{right}]) \\ &= \textit{xors}[\textit{left}] \oplus \textit{xors}[\textit{right}+1] \end{aligned} ​ Q(left,right)=arr[left]⊕…⊕arr[right]=(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[left−1])⊕(arr[left]⊕…⊕arr[right])=(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[right])=xors[left]⊕xors[right+1]​
     *
     * 上述计算用到了异或运算的结合律，以及异或运算的性质 x⊕x=0x \oplus x=0x⊕x=0。
     *
     * 当 left=0\textit{left}=0left=0 时，xors[left]=0\textit{xors}[\textit{left}]=0xors[left]=0，因此 Q(left,right)=xors[left]⊕xors[right+1]Q(\textit{left},\textit{right})=\textit{xors}[\textit{left}] \oplus \textit{xors}[\textit{right}+1]Q(left,right)=xors[left]⊕xors[right+1] 也成立。
     *
     * 因此对任意 0≤left≤right<n0 \le \textit{left} \le \textit{right}<n0≤left≤right<n，都有 Q(left,right)=xors[left]⊕xors[right+1]Q(\textit{left},\textit{right})=\textit{xors}[\textit{left}] \oplus \textit{xors}[\textit{right}+1]Q(left,right)=xors[left]⊕xors[right+1]，即可在 O(1)O(1)O(1) 的时间内完成一个查询的计算。
     *
     * 根据上述分析，这道题可以分两步求解。
     *
     *     计算前缀异或数组 xors\textit{xors}xors；
     *
     *     计算每个查询的结果，第 iii 个查询的结果为 xors[queries[i][0]]⊕xors[queries[i][1]+1]\textit{xors}[\textit{queries}[i][0]] \oplus \textit{xors}[\textit{queries}[i][1]+1]xors[queries[i][0]]⊕xors[queries[i][1]+1]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray/solution/zi-shu-zu-yi-huo-cha-xun-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arr
     * @param queries
     * @return
     *执行用时：2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：49.1 MB, 在所有 Java 提交中击败了68.97% 的用户
     */
    public int[] xorQueries1(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xors = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
        }
        return ans;
    }
}
