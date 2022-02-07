package com.meng.origin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 *
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 *
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 *
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 *
 *     您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 *     如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * 示例：
 *
 *
 *
 *
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 *  对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 *  当然, 我们还需要1步进行拼写。
 *  因此最终的输出是 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindRotateSteps {
    /**
     * 动态规划
     * 1.获取ring中每个字母所存在的坐标情况
     * 2.创建int[key.len][ring.len]用来保存到达该字母坐标所需的最小长度
     * 3.数组的每一列的值为上一行所有情况到该坐标的最小值
     * 4.最后选取最后一行中的最小值即可
     * 注意：到达该店的坐标可能为正向也可能为反向，取其中的较小值
     * @param ring
     * @param key
     * @return
     * 执行用时：11 ms, 在所有 Java 提交中击败了76.03% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了89.05% 的用户
     */
    public int findRotateSteps(String ring, String key) {
        int m = ring.length(),n=key.length();
        //创建数组存放不同到达情况
        int [][] array = new int[n][m];
        for(int i = 0 ; i < n ; i++)
            Arrays.fill(array[i],Integer.MAX_VALUE);
        //存放坐标情况
        List<Integer>[] lists = new ArrayList[26];
        //初始化
        for(int i = 0 ; i < 26 ;i++)
            lists[i] = new ArrayList<>();
        //存放字母存在情况
        for(int i = 0 ; i < m ;i++){
            int index = ring.charAt(i)-'a';
            lists[index].add(i);
        }
        //初始化第一列
        for(int i : lists[key.charAt(0)-'a']){
            //正向或反向
            array[0][i] = Math.min(i,m-i)+1;
        }
        for(int i = 1 ; i < n ; i++){
            //当前待遍历的数组下标和上一个字母的下标
            int indexC = key.charAt(i)-'a',indexP = key.charAt(i-1) -'a';
            for(int k : lists[indexC]){
                for(int w : lists[indexP]){
                    array[i][k] = Math.min(array[i][k],array[i-1][w]+Math.min(Math.abs(k-w),m-Math.abs(k-w))+1);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i : array[n-1])
            ans = Math.min(ans,i);
        return ans;
    }
    /**
     * 官方解法
     * 方法一：动态规划
     *
     * 定义 dp[i][j]\textit{dp}[i][j]dp[i][j] 表示从前往后拼写出 key\textit{key}key 的第 iii 个字符， ring\textit{ring}ring 的第 jjj 个字符与 12:0012:0012:00 方向对齐的最少步数（下标均从 000 开始）。
     *
     * 显然，只有当字符串 ring\textit{ring}ring 的第 jjj 个字符需要和 key\textit{key}key 的第 iii 个字符相同时才能拼写出 key\textit{key}key 的第 iii 个字符，因此对于 key\textit{key}key 的第 iii 个字符，需要考虑计算的 ring\textit{ring}ring 的第 jjj 个字符只有 key[i]\textit{key}[i]key[i] 在 ring\textit{ring}ring 中出现的下标集合。我们对每个字符维护一个位置数组 pos[i]\textit{pos}[i]pos[i]，表示字符 iii 在 ring\textit{ring}ring 中出现的位置集合，用来加速计算转移的过程。
     *
     * 对于状态 dp[i][j]\textit{dp}[i][j]dp[i][j]，需要枚举上一次与 12:0012:0012:00 方向对齐的位置 kkk，因此可以列出如下的转移方程：
     *
     * dp[i][j]=min⁡k∈pos[key[i−1]]{dp[i−1][k]+min⁡{abs(j−k),n−abs(j−k)}+1}\textit{dp}[i][j]=\min_{k \in pos[key[i-1]]}\{dp[i-1][k]+\min\{\text{abs}(j-k),n-\text{abs}(j-k)\}+1\} dp[i][j]=k∈pos[key[i−1]]min​{dp[i−1][k]+min{abs(j−k),n−abs(j−k)}+1}
     *
     * 其中 min⁡{abs(j−k),n−abs(j−k)}+1\min\{\text{abs}(j-k),n-\text{abs}(j-k)\}+1min{abs(j−k),n−abs(j−k)}+1 表示在当前第 kkk 个字符与 12:0012:0012:00 方向对齐时第 jjj 个字符旋转到 12:0012:0012:00 方向并按下拼写的最少步数。
     *
     * 最后答案即为 min⁡i=0n−1{dp[m−1][i]}\min_{i=0}^{n-1}\{\textit{dp}[m-1][i]\}mini=0n−1​{dp[m−1][i]}。
     *
     * 这样的做法需要开辟 O(mn)O(mn)O(mn) 的空间来存放 dp\textit{dp}dp 值。考虑到每次转移状态 dp[i][]\textit{dp}[i][]dp[i][] 只会从 dp[i−1][]\textit{dp}[i-1][]dp[i−1][] 转移过来，因此我们可以利用滚动数组优化第一维的空间复杂度，有能力的读者可以尝试实现。下面只给出最朴素的 O(mn)O(mn)O(mn) 空间复杂度的实现。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/freedom-trail/solution/zi-you-zhi-lu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：14 ms, 在所有 Java 提交中击败了58.22% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了50.37% 的用户
     */
    public int findRotateSteps1(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
