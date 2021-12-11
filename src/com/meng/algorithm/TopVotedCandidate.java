package com.meng.algorithm;

import java.util.*;

/**
 * 911. 在线选举
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 *
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 *
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 *
 * 实现 TopVotedCandidate 类：
 *
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *
 * 示例：
 *
 * 输入：
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * 输出：
 * [null, 0, 1, 1, 0, 0, 1]
 *
 * 解释：
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
 * topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
 * topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
 * topVotedCandidate.q(15); // 返回 0
 * topVotedCandidate.q(24); // 返回 0
 * topVotedCandidate.q(8); // 返回 1
 *
 *
 * 提示：
 *
 * 1 <= persons.length <= 5000
 * times.length == persons.length
 * 0 <= persons[i] < persons.length
 * 0 <= times[i] <= 109
 * times 是一个严格递增的有序数组
 * times[0] <= t <= 109
 * 每个测试用例最多调用 104 次 q
 * 执行用时：
 * 624 ms
 * , 在所有 Java 提交中击败了
 * 5.30%
 * 的用户
 * 内存消耗：
 * 47.4 MB
 * , 在所有 Java 提交中击败了
 * 90.61%
 * 的用户
 * 通过测试用例：
 * 97 / 97
 */
public class TopVotedCandidate {
    int[] tops ;
    Map<Integer,Integer> cache = new HashMap<>();
    int[] times ;
    public TopVotedCandidate(int[] persons, int[] times) {
        int len = persons.length;
        tops = new int[len];
        int max = -1;
        int index = 0;
        for(int person : persons){
            int score = cache.getOrDefault(person,0) + 1;
            if (score >= cache.getOrDefault(max,0)){
                max = person;
            }
            tops[index++] = max;
            cache.put(person,score);
        }
        this.times = times;

    }

    public int q(int t) {
        int l = 0 , r = tops.length - 1;
        while (l < r){
            int mid = l + (r - l + 1) /2 ;
            if (times[mid] <= t){
                l = mid;
            }else {
                r--;
            }
        }
        return tops[l];
    }
}

/**
 * 方法一：预计算 + 二分查找
 * 思路及解法
 *
 * 记 \textit{persons}persons 的长度为 NN。我们对输入进行预计算，用一个长度为 NN 的数组 \textit{tops}tops 记录各时间段得票领先的候选人。具体来说，\textit{tops}[i]tops[i] 表示
 *
 * \begin{cases} \textit{times}[i] \leq t < \textit{times}[i+1], &0 \leq i < N-1\\ t \ge \textit{times}[i], &i = N-1 \end{cases}
 * {
 * times[i]≤t<times[i+1],
 * t≥times[i],
 * ​
 *
 * 0≤i<N−1
 * i=N−1
 * ​
 *
 *
 * 的时间段中领先的候选人。这样的预计算可以通过对 \textit{persons}persons 在 \textit{times}times 上的计数完成。具体实现方法是，我们用一个哈希表 \textit{voteCounts}voteCounts 记录不同候选人的得票数，用一个变量 \textit{top}top 表示当前领先的候选人。按时间从小到大遍历 \textit{persons}persons 和 \textit{times}times，并更新 \textit{voteCounts}voteCounts 和 \textit{top}top，把 \textit{top}top 放入 \textit{tops}tops。遍历结束后，我们可以得到一个长度为 NN 的 \textit{tops}tops，表示各个时间段得票领先的候选人。
 *
 * 每次查询时，我们在 \textit{times}times 中找出不大于 tt 且离 tt 最近的元素的下标，这步操作可以通过二分查找完成。到 \textit{tops}tops 索引相同的下标即可返回结果。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/online-election/solution/zai-xian-xuan-ju-by-leetcode-solution-4835/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 执行用时：
 * 79 ms
 * , 在所有 Java 提交中击败了
 * 51.43%
 * 的用户
 * 内存消耗：
 * 47.5 MB
 * , 在所有 Java 提交中击败了
 * 82.86%
 * 的用户
 * 通过测试用例：
 */
class TopVotedCandidate1 {
    List<Integer> tops;
    Map<Integer, Integer> voteCounts;
    int[] times;

    public TopVotedCandidate1(int[] persons, int[] times) {
        tops = new ArrayList<Integer>();
        voteCounts = new HashMap<Integer, Integer>();
        voteCounts.put(-1, -1);
        int top = -1;
        for (int i = 0; i < persons.length; ++i) {
            int p = persons[i];
            voteCounts.put(p, voteCounts.getOrDefault(p, 0) + 1);
            if (voteCounts.get(p) >= voteCounts.get(top)) {
                top = p;
            }
            tops.add(top);
        }
        System.out.println(tops);
        System.out.println(voteCounts);
        this.times = times;
    }

    public int q(int t) {
        int l = 0, r = times.length - 1;
        // 找到满足 times[l] <= t 的最大的 l
        while (l < r) {
            int m = l + (r - l + 1) / 2;
            if (times[m] <= t) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return tops.get(l);
    }

    public static void main(String[] args) {
        int[] persons = {0,1,1,0,0,1,0};
        int[] times = {0,5,10,15,20,25,30};
        TopVotedCandidate demo = new TopVotedCandidate(persons,times);
        TopVotedCandidate1 demo1 = new TopVotedCandidate1(persons,times);
    }
}