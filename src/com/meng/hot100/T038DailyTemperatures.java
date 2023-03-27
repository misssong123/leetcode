package com.meng.hot100;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class T038DailyTemperatures {
    //超时
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] answer = new int[len];
        int index = len-1,max = temperatures[index];
        answer[len-1] = 0;
        for(int i = len - 2 ; i >=0 ; i--){
            int num = temperatures[i];
            int count = 0;
            if (num >= max ){
                max = num;
                index = i;
            }else {
                for(int j = i +1 ; j<= index ;j++){
                    count++;
                    if (temperatures[j] > num){
                        break;
                    }
                }
            }
            answer[i] = count;
        }
        return answer;
    }

    public static void main(String[] args) {
        T038DailyTemperatures demo = new T038DailyTemperatures();
        int [] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(demo.dailyTemperatures1(temperatures)));
    }

    /**
     * 方法一：暴力
     * 对于温度列表中的每个元素 temperatures[i]，需要找到最小的下标 j，使得 i < j 且 temperatures[i] < temperatures[j]。
     *
     * 由于温度范围在 [30, 100] 之内，因此可以维护一个数组 next 记录每个温度第一次出现的下标。数组 next 中的元素初始化为无穷大，在遍历温度列表的过程中更新 next 的值。
     *
     * 反向遍历温度列表。对于每个元素 temperatures[i]，在数组 next 中找到从 temperatures[i] + 1 到 100 中每个温度第一次出现的下标，将其中的最小下标记为 warmerIndex，则 warmerIndex 为下一次温度比当天高的下标。如果 warmerIndex 不为无穷大，则 warmerIndex - i 即为下一次温度比当天高的等待天数，最后令 next[temperatures[i]] = i。
     *
     * 为什么上述做法可以保证正确呢？因为遍历温度列表的方向是反向，当遍历到元素 temperatures[i] 时，只有 temperatures[i] 后面的元素被访问过，即对于任意 t，当 next[t] 不为无穷大时，一定存在 j 使得 temperatures[j] == t 且 i < j。又由于遍历到温度列表中的每个元素时都会更新数组 next 中的对应温度的元素值，因此对于任意 t，当 next[t] 不为无穷大时，令 j = next[t]，则 j 是满足 temperatures[j] == t 且 i < j 的最小下标。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param temperatures
     * @return
     * 执行用时：
     * 39 ms
     * , 在所有 Java 提交中击败了
     * 47.03%
     * 的用户
     * 内存消耗：
     * 55.7 MB
     * , 在所有 Java 提交中击败了
     * 43.68%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[temperatures[i]] = i;
            System.out.println(Arrays.toString(next));
        }
        return ans;
    }

    /**
     * 方法二：单调栈
     * 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
     *
     * 正向遍历温度列表。对于温度列表中的每个元素 temperatures[i]，如果栈为空，则直接将 i 进栈，如果栈不为空，则比较栈顶元素 prevIndex 对应的温度 temperatures[prevIndex] 和当前温度 temperatures[i]，如果 temperatures[i] > temperatures[prevIndex]，则将 prevIndex 移除，并将 prevIndex 对应的等待天数赋为 i - prevIndex，重复上述操作直到栈为空或者栈顶元素对应的温度小于等于当前温度，然后将 i 进栈。
     *
     * 为什么可以在弹栈的时候更新 ans[prevIndex] 呢？因为在这种情况下，即将进栈的 i 对应的 temperatures[i] 一定是 temperatures[prevIndex] 右边第一个比它大的元素，试想如果 prevIndex 和 i 有比它大的元素，假设下标为 j，那么 prevIndex 一定会在下标 j 的那一轮被弹掉。
     *
     * 由于单调栈满足从栈底到栈顶元素对应的温度递减，因此每次有元素进栈时，会将温度更低的元素全部移除，并更新出栈元素对应的等待天数，这样可以确保等待天数一定是最小的。
     *
     * 以下用一个具体的例子帮助读者理解单调栈。对于温度列表 [73,74,75,71,69,72,76,73][73,74,75,71,69,72,76,73]，单调栈 \textit{stack}stack 的初始状态为空，答案 \textit{ans}ans 的初始状态是 [0,0,0,0,0,0,0,0][0,0,0,0,0,0,0,0]，按照以下步骤更新单调栈和答案，其中单调栈内的元素都是下标，括号内的数字表示下标在温度列表中对应的温度。
     *
     * 当 i=0i=0 时，单调栈为空，因此将 00 进栈。
     *
     * \textit{stack}=[0(73)]stack=[0(73)]
     *
     * \textit{ans}=[0,0,0,0,0,0,0,0]ans=[0,0,0,0,0,0,0,0]
     *
     * 当 i=1i=1 时，由于 7474 大于 7373，因此移除栈顶元素 00，赋值 ans[0]:=1-0ans[0]:=1−0，将 11 进栈。
     *
     * \textit{stack}=[1(74)]stack=[1(74)]
     *
     * \textit{ans}=[1,0,0,0,0,0,0,0]ans=[1,0,0,0,0,0,0,0]
     *
     * 当 i=2i=2 时，由于 7575 大于 7474，因此移除栈顶元素 11，赋值 ans[1]:=2-1ans[1]:=2−1，将 22 进栈。
     *
     * \textit{stack}=[2(75)]stack=[2(75)]
     *
     * \textit{ans}=[1,1,0,0,0,0,0,0]ans=[1,1,0,0,0,0,0,0]
     *
     * 当 i=3i=3 时，由于 7171 小于 7575，因此将 33 进栈。
     *
     * \textit{stack}=[2(75),3(71)]stack=[2(75),3(71)]
     *
     * \textit{ans}=[1,1,0,0,0,0,0,0]ans=[1,1,0,0,0,0,0,0]
     *
     * 当 i=4i=4 时，由于 6969 小于 7171，因此将 44 进栈。
     *
     * \textit{stack}=[2(75),3(71),4(69)]stack=[2(75),3(71),4(69)]
     *
     * \textit{ans}=[1,1,0,0,0,0,0,0]ans=[1,1,0,0,0,0,0,0]
     *
     * 当 i=5i=5 时，由于 7272 大于 6969 和 7171，因此依次移除栈顶元素 44 和 33，赋值 ans[4]:=5-4ans[4]:=5−4 和 ans[3]:=5-3ans[3]:=5−3，将 55 进栈。
     *
     * \textit{stack}=[2(75),5(72)]stack=[2(75),5(72)]
     *
     * \textit{ans}=[1,1,0,2,1,0,0,0]ans=[1,1,0,2,1,0,0,0]
     *
     * 当 i=6i=6 时，由于 7676 大于 7272 和 7575，因此依次移除栈顶元素 55 和 22，赋值 ans[5]:=6-5ans[5]:=6−5 和 ans[2]:=6-2ans[2]:=6−2，将 66 进栈。
     *
     * \textit{stack}=[6(76)]stack=[6(76)]
     *
     * \textit{ans}=[1,1,4,2,1,1,0,0]ans=[1,1,4,2,1,1,0,0]
     *
     * 当 i=7i=7 时，由于 7373 小于 7676，因此将 77 进栈。
     *
     * \textit{stack}=[6(76),7(73)]stack=[6(76),7(73)]
     *
     * \textit{ans}=[1,1,4,2,1,1,0,0]ans=[1,1,4,2,1,1,0,0]
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param temperatures
     * @return
     * 执行用时：
     * 24 ms
     * , 在所有 Java 提交中击败了
     * 79.45%
     * 的用户
     * 内存消耗：
     * 57.2 MB
     * , 在所有 Java 提交中击败了
     * 22.81%
     * 的用户
     * 通过测试用例：
     * 48 / 48
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

}
