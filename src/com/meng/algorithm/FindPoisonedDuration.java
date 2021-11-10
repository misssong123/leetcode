package com.meng.algorithm;

/**
 * 495. 提莫攻击
 *
 * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄。他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
 *
 * 当提莫攻击艾希，艾希的中毒状态正好持续 duration 秒。
 *
 * 正式地讲，提莫在 t 发起发起攻击意味着艾希在时间区间 [t, t + duration - 1]（含 t 和 t + duration - 1）处于中毒状态。如果提莫在中毒影响结束 前 再次攻击，中毒状态计时器将会 重置 ，在新的攻击之后，中毒影响将会在 duration 秒后结束。
 *
 * 给你一个 非递减 的整数数组 timeSeries ，其中 timeSeries[i] 表示提莫在 timeSeries[i] 秒时对艾希发起攻击，以及一个表示中毒持续时间的整数 duration 。
 *
 * 返回艾希处于中毒状态的 总 秒数。
 *
 *
 * 示例 1：
 *
 * 输入：timeSeries = [1,4], duration = 2
 * 输出：4
 * 解释：提莫攻击对艾希的影响如下：
 * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
 * - 第 4 秒，提莫再次攻击艾希，艾希中毒状态又持续 2 秒，即第 4 秒和第 5 秒。
 * 艾希在第 1、2、4、5 秒处于中毒状态，所以总中毒秒数是 4 。
 *
 * 示例 2：
 *
 * 输入：timeSeries = [1,2], duration = 2
 * 输出：3
 * 解释：提莫攻击对艾希的影响如下：
 * - 第 1 秒，提莫攻击艾希并使其立即中毒。中毒状态会维持 2 秒，即第 1 秒和第 2 秒。
 * - 第 2 秒，提莫再次攻击艾希，并重置中毒计时器，艾希中毒状态需要持续 2 秒，即第 2 秒和第 3 秒。
 * 艾希在第 1、2、3 秒处于中毒状态，所以总中毒秒数是 3 。
 *
 *
 *
 * 提示：
 *
 *     1 <= timeSeries.length <= 104
 *     0 <= timeSeries[i], duration <= 107
 *     timeSeries 按 非递减 顺序排列
 */
public class FindPoisonedDuration {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了29.73% 的用户
     * 通过测试用例：38 / 38
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (duration == 0){
            return 0;
        }
        int len = timeSeries.length;
        int res = 0;
        int pre = 0;
        int temp = timeSeries[0] + duration-1;
        for(int i = 1 ; i < len ; i++){
            if (temp<timeSeries[i]){
                res += (temp - timeSeries[pre]+1);
                pre = i;
                temp = timeSeries[i] + duration-1;
            }else {
                temp = timeSeries[i] + duration-1;
            }
        }
        res += (temp - timeSeries[pre]+1);
        return res;
    }

    /**
     * 方法一：单次扫描
     *
     * 我们只需要对数组进行一次扫描就可以计算出总的中毒持续时间。我们记录艾希恢复为未中毒的起始时间 expired\textit{expired}expired，设艾希遭遇第 iii 次的攻击的时间为 timeSeries[i]\textit{timeSeries}[i]timeSeries[i]。当艾希遭遇第 iii 攻击时：
     *
     *     如果当前他正处于未中毒状态，则此时他的中毒持续时间应增加 duration\textit{duration}duration​，同时更新本次中毒结束时间 expired\textit{expired}expired​ 等于 timeSeries[i]+duration\textit{timeSeries}[i] + \textit{duration}timeSeries[i]+duration​；
     *     如果当前他正处于中毒状态，由于中毒状态不可叠加，我们知道上次中毒后结束时间为 expired\textit{expired}expired​​，本次中毒后结束时间为 timeSeries[i]+duration\textit{timeSeries}[i] + \textit{duration}timeSeries[i]+duration​​，因此本次中毒增加的持续中毒时间为 timeSeries[i]+duration−expired\textit{timeSeries}[i] + \textit{duration} -\textit{expired}timeSeries[i]+duration−expired​​；
     *     我们将每次中毒后增加的持续中毒时间相加即为总的持续中毒时间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/teemo-attacking/solution/ti-mo-gong-ji-by-leetcode-solution-6p4k/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param timeSeries
     * @param duration
     * @return
     * 执行用时：2 ms, 在所有 Java 提交中击败了90.57% 的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了53.42% 的用户
     * 通过测试用例：38 / 38
     */
    public int findPoisonedDuration1(int[] timeSeries, int duration) {
        int ans = 0;
        int expired = 0;
        for (int i = 0; i < timeSeries.length; ++i) {
            if (timeSeries[i] >= expired) {
                ans += duration;
            } else {
                ans += timeSeries[i] + duration - expired;
            }
            expired = timeSeries[i] + duration;
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5,6,7,8,9,};
        int duration = 5;
        FindPoisonedDuration demo = new FindPoisonedDuration();
        System.out.println(demo.findPoisonedDuration(nums,duration));
    }
}
