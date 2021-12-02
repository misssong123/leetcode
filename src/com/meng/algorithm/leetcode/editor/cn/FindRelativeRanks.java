package com.meng.algorithm.leetcode.editor.cn;

import java.util.*;

/**
 * 506. 相对名次
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 *
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 *
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 *
 *
 *
 * 示例 1：
 *
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 * 示例 2：
 *
 * 输入：score = [10,3,8,9,4]
 * 输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * 解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
 *
 *
 * 提示：
 *
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * score 中的所有值 互不相同
 */
public class FindRelativeRanks {
    /**
     * 执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 17.54%
     * 的用户
     * 内存消耗：
     * 39.5 MB
     * , 在所有 Java 提交中击败了
     * 52.30%
     * 的用户
     * 通过测试用例：
     * 17 / 17
     * @param score
     * @return
     */
    public String[] findRelativeRanks(int[] score) {
        if (score.length == 0){
            return new String[]{"Gold Medal"};
        }
        Map<Integer,String>  map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int num : score){
            list.add(num);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0 ; i < list.size() ; i++){
            switch (i){
                case 0:
                    map.put(list.get(i),"Gold Medal");
                    break;
                case 1:
                    map.put(list.get(i),"Silver Medal" );
                    break;
                case 2:
                    map.put(list.get(i),"Bronze Medal");
                    break;
                default:
                    map.put(list.get(i),(i+1)+"");
            }
        }
        String[] res = new String[score.length];
        for(int i = 0 ; i< score.length ; i++){
            res[i] = map.get(score[i]);
        }
        return res;
    }

    /**
     * 方法一：排序
     * 题目要求找到每个运动员的相对名次，并同时给前三名标记为 \texttt{"Gold Medal", "Silver Medal", "Bronze Medal"}"Gold Medal", "Silver Medal", "Bronze Medal"，其余的运动员则标记为其相对名次。
     * 将所有的运动员按照成绩的高低进行排序，然后将按照名次进行标记即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/relative-ranks/solution/xiang-dui-ming-ci-by-leetcode-solution-5sua/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param score
     * @return
     * 执行用时：
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 61.18%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 5.15%
     * 的用户
     * 通过测试用例：
     * 17 / 17
     */
    public String[] findRelativeRanks1(int[] score) {
        int n = score.length;
        String[] desc = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            if (i >= 3) {
                ans[arr[i][1]] = Integer.toString(i + 1);
            } else {
                ans[arr[i][1]] = desc[i];
            }
        }
        return ans;
    }


}
