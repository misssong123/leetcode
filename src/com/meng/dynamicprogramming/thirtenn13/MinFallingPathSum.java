package com.meng.dynamicprogramming.thirtenn13;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 */
public class MinFallingPathSum {
    /**
     *超时
     * @param triangle
     * @return
     */
    int sum = Integer.MAX_VALUE;
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        dfs(0,0,len,0,triangle);
        return sum;
    }

    private void dfs(int num,int index, int len, int temp,List<List<Integer>> triangle) {
        if (index == len){
            sum = Math.min(temp,sum);
            return;
        }
        List<Integer> list = triangle.get(index);
        //选择相同坐标
        dfs(num,index+1,len,temp+list.get(num),triangle);
        //选择+1坐标
        if (num+1<=index){
            dfs(num+1,index+1,len,temp+list.get(num+1),triangle);
        }
    }

    /**
     * 类似于爬楼梯问题
     * @param triangle
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 96.20%
     * 的用户
     * 内存消耗：
     * 40.7 MB
     * , 在所有 Java 提交中击败了
     * 80.26%
     * 的用户
     * 通过测试用例：
     * 44 / 44
     */
    public int minimumTotalProv(List<List<Integer>> triangle){
        int len = triangle.size();
        int[][] nums = new int[len][len];
        nums[0][0] = triangle.get(0).get(0);
        for(int i = 1 ; i < len ; i++){
            List<Integer> list = triangle.get(i);
            //首个元素
            nums[i][0] = nums[i-1][0] + list.get(0);
            //中间元素
            for(int j = 1 ; j < i ; j++){
                nums[i][j] = Math.min(nums[i-1][j-1] ,nums[i-1][j])+ list.get(j);
            }
            //最后一个元素
            nums[i][i] = nums[i-1][i-1] + list.get(i);
        }
        int min = Integer.MAX_VALUE;
        for(int num : nums[len-1]){
            min = Math.min(min,num);
        }
        return min;
    }

    /**
     * 方法二：动态规划 + 空间优化
     * 思路与算法
     *
     * 在题目描述中的「进阶」部分，提到了可以将空间复杂度优化至 O(n)O(n)。
     *
     * 我们回顾方法一中的状态转移方程：
     *
     * \begin{aligned} f[i][j] = \begin{cases} f[i-1][0] + c[i][0], & j=0\\ f[i-1][i-1] + c[i][i], & j=i \\ \min(f[i-1][j-1], f[i-1][j]) + c[i][j], & \text{otherwise} \end{cases} \end{aligned}
     * f[i][j]=
     * ⎩
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎧
     * ​
     *
     * f[i−1][0]+c[i][0],
     * f[i−1][i−1]+c[i][i],
     * min(f[i−1][j−1],f[i−1][j])+c[i][j],
     * ​
     *
     * j=0
     * j=i
     * otherwise
     * ​
     *
     * ​
     *
     *
     * 可以发现，f[i][j]f[i][j] 只与 f[i-1][..]f[i−1][..] 有关，而与 f[i-2][..]f[i−2][..] 及之前的状态无关，因此我们不必存储这些无关的状态。具体地，我们使用两个长度为 nn 的一维数组进行转移，将 ii 根据奇偶性映射到其中一个一维数组，那么 i-1i−1 就映射到了另一个一维数组。这样我们使用这两个一维数组，交替地进行状态转移。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param triangle
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 79.04%
     * 的用户
     * 内存消耗：
     * 40.6 MB
     * , 在所有 Java 提交中击败了
     * 87.05%
     * 的用户
     * 通过测试用例：
     * 44 / 44
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }

    /**
     * 上述方法的空间复杂度为 O(n)O(n)，使用了 2n2n 的空间存储状态。我们还可以继续进行优化吗？
     *
     * 答案是可以的。我们从 ii 到 00 递减地枚举 jj，这样我们只需要一个长度为 nn 的一维数组 ff，就可以完成状态转移。
     *
     * 为什么只有在递减地枚举 jj 时，才能省去一个一维数组？当我们在计算位置 (i, j)(i,j) 时，f[j+1]f[j+1] 到 f[i]f[i] 已经是第 ii 行的值，而 f[0]f[0] 到 f[j]f[j] 仍然是第 i-1i−1 行的值。此时我们直接通过
     *
     * f[j] = \min(f[j-1], f[j]) + c[i][j]
     * f[j]=min(f[j−1],f[j])+c[i][j]
     *
     * 进行转移，恰好就是在 (i-1, j-1)(i−1,j−1) 和 (i-1, j)(i−1,j) 中进行选择。但如果我们递增地枚举 jj，那么在计算位置 (i, j)(i,j) 时，f[0]f[0] 到 f[j-1]f[j−1] 已经是第 ii 行的值。如果我们仍然使用上述状态转移方程，那么是在 (i, j-1)(i,j−1) 和 (i-1, j)(i−1,j) 中进行选择，就产生了错误。
     *
     * 这样虽然空间复杂度仍然为 O(n)O(n)，但我们只使用了 nn 的空间存储状态，减少了一半的空间消耗。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param triangle
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 96.20%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 34.71%
     * 的用户
     * 通过测试用例：
     * 44 / 44
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

}
