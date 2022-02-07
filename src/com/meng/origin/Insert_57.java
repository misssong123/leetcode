package com.meng.origin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Insert_57 {
    public static void main(String[] args) {
        Insert_57 demo = new Insert_57();
        int[][]intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[]newInterval = {0,31};
        int[][] ans = demo.insert(intervals, newInterval);
        for (int[] link : ans)
            System.out.println(Arrays.toString(link));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.65% 的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了46.14% 的用户
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length,pre=-1,last=-1,flag=0;
        boolean isContain = true;
        List<int[]> list = new ArrayList<>();
        for(int i = 0 ; i < len ; i++){
            int [] temp = intervals[i];
            if (flag==0){
               if (temp[1]<newInterval[0]){
                   list.add(intervals[i]);
               }else {
                   if (temp[0]>newInterval[0]){
                       isContain =false;
                   }
                   flag = 1;
                   pre = i;
                   i--;
                   continue;
               }
            }else if (flag == 1){
                if (temp[0]>newInterval[flag]){
                    last = i;
                    break;
                }else {
                    if (temp[1]>= newInterval[flag]){
                        flag=2;
                        last=i;
                        break;
                    }
                }
            }
        }
        if (pre == -1){
            list.add(newInterval);
        }else {
            if (last==-1){
                if (isContain){
                    list.add(new int[]{intervals[pre][0],newInterval[1]});
                }else {
                    list.add(new int[]{newInterval[0],newInterval[1]});
                }

            }else {
                if (flag == 1){
                    if (isContain){
                        list.add(new int[]{intervals[pre][0],newInterval[1]});
                    }else {
                        list.add(new int[]{newInterval[0],newInterval[1]});
                    }
                    for (int i = last;i<len;i++){
                        list.add(intervals[i]);
                    }
                }else if(flag ==2){
                    if (isContain){
                        list.add(new int[]{intervals[pre][0],intervals[last][1]});
                    }else {
                        list.add(new int[]{newInterval[0],intervals[last][1]});
                    }

                    for (int i = last+1;i<len;i++){
                        list.add(intervals[i]);
                    }
                }
            }
        }
        int[][]ans= new int[list.size()][2];
        for(int i = 0 ; i<list.size();i++){
            ans[i][0]=list.get(i)[0];
            ans[i][1]=list.get(i)[1];
        }
        return ans;
    }
    /**
     * 官方解法
     * 方法一：模拟
     *
     * 思路与算法
     *
     * 在给定的区间集合 X\mathcal{X}X 互不重叠的前提下，当我们需要插入一个新的区间 S=[left,right]S = [\textit{left}, \textit{right}]S=[left,right] 时，我们只需要：
     *
     *     找出所有与区间 SSS 重叠的区间集合 X′\mathcal{X}'X′；
     *     将 X′\mathcal{X}'X′ 中的所有区间连带上区间 SSS 合并成一个大区间；
     *     最终的答案即为不与 X′\mathcal{X}'X′ 重叠的区间以及合并后的大区间
     *这样做的正确性在于，给定的区间集合中任意两个区间都是没有交集的，因此所有需要合并的区间，就是所有与区间 SSS 重叠的区间。
     *
     * 并且，在给定的区间集合已经按照左端点排序的前提下，所有与区间 SSS 重叠的区间在数组 intervals\textit{intervals}intervals 中下标范围是连续的，因此我们可以对所有的区间进行一次遍历，就可以找到这个连续的下标范围。
     *
     * 当我们遍历到区间 [li,ri][l_i, r_i][li​,ri​] 时：
     *
     *     如果 ri<leftr_i < \textit{left}ri​<left，说明 [li,ri][l_i, r_i][li​,ri​] 与 SSS 不重叠并且在其左侧，我们可以直接将 [li,ri][l_i, r_i][li​,ri​] 加入答案；
     *
     *     如果 li>rightl_i > \textit{right}li​>right，说明 [li,ri][l_i, r_i][li​,ri​] 与 SSS 不重叠并且在其右侧，我们可以直接将 [li,ri][l_i, r_i][li​,ri​] 加入答案；
     *
     *     如果上面两种情况均不满足，说明 [li,ri][l_i, r_i][li​,ri​] 与 SSS 重叠，我们无需将 [li,ri][l_i, r_i][li​,ri​] 加入答案。此时，我们需要将 SSS 与 [li,ri][l_i, r_i][li​,ri​] 合并，即将 SSS 更新为其与 [li,ri][l_i, r_i][li​,ri​] 的并集。
     *
     * 那么我们应当在什么时候将区间 SSS 加入答案呢？由于我们需要保证答案也是按照左端点排序的，因此当我们遇到第一个 满足 li>rightl_i > \textit{right}li​>right 的区间时，说明以后遍历到的区间不会与 SSS 重叠，并且它们左端点一定会大于 SSS 的左端点。此时我们就可以将 SSS 加入答案。特别地，如果不存在这样的区间，我们需要在遍历结束后，将 SSS 加入答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/insert-interval/solution/cha-ru-qu-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 执行用时：2 ms, 在所有 Java 提交中击败了79.70% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了59.78% 的用户
     */
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
