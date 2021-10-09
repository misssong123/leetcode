package com.meng.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1436. 旅行终点站
 * 难度
 * 简单
 *
 * 95
 *
 *
 *
 *
 *
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 *
 * 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。
 *
 *
 *
 * 示例 1：
 *
 * 输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * 输出："Sao Paulo"
 * 解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
 * 示例 2：
 *
 * 输入：paths = [["B","C"],["D","B"],["C","A"]]
 * 输出："A"
 * 解释：所有可能的线路是：
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * 显然，旅行终点站是 "A" 。
 * 示例 3：
 *
 * 输入：paths = [["A","Z"]]
 * 输出："Z"
 *
 *
 * 提示：
 *
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * 所有字符串均由大小写英文字母和空格字符组成。
 */
public class DestCity {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 24.49%
     * 的用户
     * 内存消耗：
     * 37.7 MB
     * , 在所有 Java 提交中击败了
     * 99.67%
     * 的用户
     * @param paths
     * @return
     */
    public String destCity(List<List<String>> paths) {
        Set<String> dest = new HashSet<>();
        Set<String> source = new HashSet<>();
        for(List<String> list : paths){
            dest.add(list.get(1));
            source.add(list.get(0));
        }
        dest.removeAll(source);
        String s = "";
        for(String s1 : dest){
            s = s1;
        }
        return s;
    }

    /**
     *方法一：哈希表
     *
     * 根据终点站的定义，终点站不会出现在
     * cityA
     * i
     * cityA
     * i
     * ​
     *   中，因为存在从
     * cityA
     * i
     * cityA
     * i
     * ​
     *   出发的线路，所以终点站只会出现在
     * cityB
     * i
     * cityB
     * i
     * ​
     *   中。据此，我们可以遍历
     * cityB
     * i
     * cityB
     * i
     * ​
     *  ，返回不在
     * cityA
     * i
     * cityA
     * i
     * ​
     *   中的城市，即为答案。
     *
     * 代码实现时，可以先将所有
     * cityA
     * i
     * cityA
     * i
     * ​
     *   存于一哈希表中，然后遍历
     * cityB
     * i
     * cityB
     * i
     * ​
     *   并查询
     * cityB
     * i
     * cityB
     * i
     * ​
     *   是否在哈希表中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/destination-city/solution/lu-xing-zhong-dian-zhan-by-leetcode-solu-pscd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param paths
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 95.86%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 5.41%
     * 的用户
     */
    public String destCity1(List<List<String>> paths) {
        Set<String> citiesA = new HashSet<String>();
        for (List<String> path : paths) {
            citiesA.add(path.get(0));
        }
        for (List<String> path : paths) {
            if (!citiesA.contains(path.get(1))) {
                return path.get(1);
            }
        }
        return "";
    }

}
