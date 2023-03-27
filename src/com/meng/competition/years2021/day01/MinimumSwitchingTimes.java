package com.meng.competition.years2021.day01;
/**
 * LCP 39. 无人机方阵(简单)
 * 在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：
 *
 * 调整无人机的位置布局
 * 切换无人机展示的灯光颜色
 * 给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，
 * 由于无人机切换灯光颜色的耗能很大，请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。
 *
 * 注意： 调整无人机的位置布局时无人机的位置可以随意变动。
 *
 * 示例 1：
 *
 * 输入：source = [[1,3],[5,4]], target = [[3,1],[6,5]]
 *
 * 输出：1
 *
 * 解释：
 * 最佳方案为
 * 将 [0,1] 处的无人机移动至 [0,0] 处；
 * 将 [0,0] 处的无人机移动至 [0,1] 处；
 * 将 [1,0] 处的无人机移动至 [1,1] 处；
 * 将 [1,1] 处的无人机移动至 [1,0] 处，其灯光颜色切换为颜色编号为 6 的灯光；
 * 因此从source 到 target 所需要的最少灯光切换次数为 1。
 * 8819ccdd664e91c78cde3bba3c701986.gif
 *
 * 示例 2：
 *
 * 输入：source = [[1,2,3],[3,4,5]], target = [[1,3,5],[2,3,4]]
 *
 * 输出：0
 * 解释：
 * 仅需调整无人机的位置布局，便可完成图案切换。因此不需要无人机切换颜色
 *
 * 提示：
 * n == source.length == target.length
 * m == source[i].length == target[i].length
 * 1 <= n, m <=100
 * 1 <= source[i][j], target[i][j] <=10^4
 */
public class MinimumSwitchingTimes {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 96.19%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 95.60%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     * @param source
     * @param target
     * @return
     */
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int [] cache = new int[10001];
        int n = source.length;
        int m = source[0].length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                cache[source[i][j]]++;
            }
        }
        int sum = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if (cache[target[i][j]]>0){
                    cache[target[i][j]]--;
                }
            }
        }
        for(int i = 0 ; i < 10001 ; i++){
            sum += cache[i];
        }
        return sum;
    }

    /**
     * 解题思路
     * 题目说了target[i][j]<10的4次方，开辟数组p[10005]
     * 如果sorcesorce[i][j]出现，p[i*m+j]就加一
     * 如果target[i][j]出现，p[i*m+j]就减一
     * p数组为正的值，就是多出来没有找到相应灯的***，遍历一遍，相加即可
     *
     * 作者：dong-nan-wu-yan-zu-a
     * 链接：https://leetcode.cn/problems/0jQkd0/solution/hashbiao-shu-zu-shi-xian-ji-bai-shuang-b-5qko/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param source
     * @param target
     * @return
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 96.19%
     * 的用户
     * 内存消耗：
     * 42.2 MB
     * , 在所有 Java 提交中击败了
     * 66.57%
     * 的用户
     * 通过测试用例：
     * 85 / 85
     */
    public int minimumSwitchingTimes1(int[][] source, int[][] target) {
        int n=source.length,m=source[0].length,sum=0;
        int[] p=new int[10005];
        for(int i=0;i<n*m;i++){
            p[source[i/m][i%m]]++;
            p[target[i/m][i%m]]--;
        }
        for(int i=0;i<10005;i++){
            if(p[i]>0) sum+=p[i];
        }
        return sum;
    }
}
