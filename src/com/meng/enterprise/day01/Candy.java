package com.meng.enterprise.day01;


import java.util.*;

/**
 * 135. 分发糖果(困难)
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */
public class Candy {
    /**
     * 执行用时：
     * 45 ms
     * , 在所有 Java 提交中击败了
     * 5.02%
     * 的用户
     * 内存消耗：
     * 50.8 MB
     * , 在所有 Java 提交中击败了
     * 5.02%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int res = 0;
        TreeMap<Integer, List<Integer>> cache = new TreeMap<>();
        int len = ratings.length;
        int[] nums = new int[len];
        Arrays.fill(nums,-1);
        int index = 0;
        for(int num : ratings){
            if (!cache.containsKey(num)){
                cache.put(num,new ArrayList<>());
            }
            cache.get(num).add(index++);
        }
        boolean first = true;
        for(Map.Entry<Integer, List<Integer>> entry : cache.entrySet()){
            if (first){
                for(int num : entry.getValue()){
                    nums[num] = 1;
                    res++;
                }
                first = false;
            }else {
                for(int num : entry.getValue()){
                    int pre = 1;
                    if (num>0 && ratings[num] > ratings[num-1]){
                        pre = nums[num-1]+1;
                    }
                    if (num<len-1 && ratings[num] > ratings[num+1]){
                        pre = Math.max(nums[num+1]+1,pre);
                    }
                    nums[num] = pre;
                    res+=pre;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Candy demo = new Candy();
        int[] ratings = {1,2,2};
        System.out.println(demo.candy(ratings));
    }
    /**
     *方法一：两次遍历
     * 思路及解法
     *
     * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
     *
     * 左规则：当 \textit{ratings}[i - 1] < \textit{ratings}[i]ratings[i−1]<ratings[i] 时，ii 号学生的糖果数量将比 i - 1i−1 号孩子的糖果数量多。
     *
     * 右规则：当 \textit{ratings}[i] > \textit{ratings}[i + 1]ratings[i]>ratings[i+1] 时，ii 号学生的糖果数量将比 i + 1i+1 号孩子的糖果数量多。
     *
     * 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。每个人最终分得的糖果数量即为这两个数量的最大值。
     *
     * 具体地，以左规则为例：我们从左到右遍历该数组，假设当前遍历到位置 ii，如果有 \textit{ratings}[i - 1] < \textit{ratings}[i]ratings[i−1]<ratings[i] 那么 ii 号学生的糖果数量将比 i - 1i−1 号孩子的糖果数量多，我们令 \textit{left}[i] = \textit{left}[i - 1] + 1left[i]=left[i−1]+1 即可，否则我们令 \textit{left}[i] = 1left[i]=1。
     *
     * 在实际代码中，我们先计算出左规则 \textit{left}left 数组，在计算右规则的时候只需要用单个变量记录当前位置的右规则，同时计算答案即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/candy/solution/fen-fa-tang-guo-by-leetcode-solution-f01p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param ratings
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 98.42%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 70.81%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    /**
     * 方法二：常数空间遍历
     * 思路及解法
     *
     * 注意到糖果总是尽量少给，且从 11 开始累计，每次要么比相邻的同学多给一个，要么重新置为 11。依据此规则，我们可以画出下图：
     *
     *
     *
     * 其中相同颜色的柱状图的高度总恰好为 1,2,3 \dots1,2,3…。
     *
     * 而高度也不一定一定是升序，也可能是 \dots 3,2,1…3,2,1 的降序：
     *
     *
     *
     * 注意到在上图中，对于第三个同学，他既可以被认为是属于绿色的升序部分，也可以被认为是属于蓝色的降序部分。因为他同时比两边的同学评分更高。我们对序列稍作修改：
     *
     *
     *
     * 注意到右边的升序部分变长了，使得第三个同学不得不被分配 44 个糖果。
     *
     * 依据前面总结的规律，我们可以提出本题的解法。我们从左到右枚举每一个同学，记前一个同学分得的糖果数量为 \textit{pre}pre：
     *
     * 如果当前同学比上一个同学评分高，说明我们就在最近的递增序列中，直接分配给该同学 \textit{pre} + 1pre+1 个糖果即可。
     *
     * 否则我们就在一个递减序列中，我们直接分配给当前同学一个糖果，并把该同学所在的递减序列中所有的同学都再多分配一个糖果，以保证糖果数量还是满足条件。
     *
     * 我们无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
     *
     * 同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中。
     *
     * 这样，我们只要记录当前递减序列的长度 \textit{dec}dec，最近的递增序列的长度 \textit{inc}inc 和前一个同学分得的糖果数量 \textit{pre}pre 即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/candy/solution/fen-fa-tang-guo-by-leetcode-solution-f01p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param ratings
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 98.42%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 50.67%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

}
