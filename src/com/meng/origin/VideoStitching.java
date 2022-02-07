package com.meng.origin;

import java.util.Arrays;

/**
 * 1024. 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 *
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 *
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 *
 * 示例 2：
 *
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 *
 * 示例 3：
 *
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 *
 * 示例 4：
 *
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 *
 *
 *
 * 提示：
 *
 *     1 <= clips.length <= 100
 *     0 <= clips[i][0] <= clips[i][1] <= 100
 *     0 <= T <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VideoStitching {
    public static void main(String[] args) {
        int[][] clips ={{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        VideoStitching demo = new VideoStitching();
        System.out.println(demo.videoStitching2(clips,10));
    }

    /**
     * 思路
     * 1.确定每次开始的时间start，最常间隔max,结果count,数组长度len
     * 2.当start<T时重复循环
     * 3.当当前数组[i][0]小于等于start并且[i][1]大于start，并且[i][1]-start>max时，更新max的为[i][1]-start
     * 4.当遍历完数据后，若max的值不为-1，则更新start的值为start+max,count++,max=-1
     * 5.若max仍为-1表示当前数组不能满足要求，返回-1
     * @param clips
     * @param T
     * @return
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了98.63% 的用户
     */
    public int videoStitching(int[][] clips, int T) {
        int start = 0 ,max = -1,count = 0 ,len = clips.length;
        while(start < T){
            for(int i = 0 ; i < len ; i++){
                int first = clips[i][0],last = clips[i][1];
                if (first<= start && last>start && last -start>max){
                    max = last -start;
                }
            }
            if (max == -1 )
                return -1;
            start = start + max;
            max =-1;
            count ++;
        }
        return count;
    }
    /**
     * 官方解法1
     * 法一：动态规划
     *
     * 思路及解法
     *
     * 比较容易想到的方法是动态规划，我们令 dp[i]\textit{dp}[i]dp[i] 表示将区间 [0,i)[0,i)[0,i) 覆盖所需的最少子区间的数量。由于我们希望子区间的数目尽可能少，因此可以将所有 dp[i]\textit{dp}[i]dp[i] 的初始值设为一个大整数，并将 dp[0]\textit{dp}[0]dp[0]（即空区间）的初始值设为 000。
     *
     * 我们可以枚举所有的子区间来依次计算出所有的 dp\textit{dp}dp 值。我们首先枚举 iii，同时对于任意一个子区间 [aj,bj)[a_j,b_j)[aj​,bj​)，若其满足 aj<i≤bja_j < i \leq b_jaj​<i≤bj​，那么它就可以覆盖区间 [0,i)[0, i)[0,i) 的后半部分，而前半部分则可以用 dp[aj]\textit{dp}[a_j]dp[aj​] 对应的最优方法进行覆盖，因此我们可以用 dp[aj]+1dp[a_j] + 1dp[aj​]+1 来更新 dp[i]\textit{dp}[i]dp[i]。状态转移方程如下：
     *
     * dp[i]=min⁡{dp[aj]}+1(aj<i≤bj)\textit{dp}[i] = \min \{ \textit{dp}[a_j] \} + 1 \quad (a_j < i \leq b_j) dp[i]=min{dp[aj​]}+1(aj​<i≤bj​)
     *
     * 最终的答案即为 dp[T]\textit{dp}[T]dp[T]。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：2 ms, 在所有 Java 提交中击败了22.95% 的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了93.99% 的用户
     */
    public int videoStitching1(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }
/**
 * 官方解法2
 * 方法二：贪心
 * 思路及解法
 *
 * 注意到对于所有左端点相同的子区间，其右端点越远越有利。且最佳方案中不可能出现两个左端点相同的子区间。于是我们预处理所有的子区间，对于每一个位置 iii，我们记录以其为左端点的子区间中最远的右端点，记为 maxn[i]\textit{maxn}[i]maxn[i]。
 *
 *
 * 具体地，我们枚举每一个位置，假设当枚举到位置 iii 时，记左端点不大于 iii 的所有子区间的最远右端点为 last\textit{last}last。这样 last\textit{last}last 就代表了当前能覆盖到的最远的右端点。
 *
 * 每次我们枚举到一个新位置，我们都用 maxn[i]\textit{maxn}[i]maxn[i] 来更新 last\textit{last}last。如果更新后 last==i\textit{last} == ilast==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
 *
 * 同时我们还需要记录上一个被使用的子区间的结束位置为 pre\textit{pre}pre，每次我们越过一个被使用的子区间，就说明我们要启用一个新子区间，这个新子区间的结束位置即为当前的 last\textit{last}last。也就是说，每次我们遇到 i==prei == \textit{pre}i==pre，则说明我们用完了一个被使用的子区间。这种情况下我们让答案加 111，并更新 pre\textit{pre}pre 即可
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
 * 内存消耗：35.7 MB, 在所有 Java 提交中击败了98.63% 的用户
 */
public int videoStitching2(int[][] clips, int T) {
    int[] maxn = new int[T];
    int last = 0, ret = 0, pre = 0;
    for (int[] clip : clips) {
        if (clip[0] < T) {
            maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
        }
    }
    for (int i = 0; i < T; i++) {
        last = Math.max(last, maxn[i]);
        if (i == last) {
            return -1;
        }
        if (i == pre) {
            ret++;
            pre = last;
        }
    }
    return ret;
}
}
