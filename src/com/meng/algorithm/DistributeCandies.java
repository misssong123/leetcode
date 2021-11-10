package com.meng.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 *575. 分糖果
 * 难度
 * 简单
 *
 * 149
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 接收动态
 * 反馈
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 *
 * 示例 1:
 *
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 *      最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * 示例 2 :
 *
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * 注意:
 *
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 */
public class DistributeCandies {
    /**
     * 执行用时：
     * 32 ms
     * , 在所有 Java 提交中击败了
     * 90.94%
     * 的用户
     * 内存消耗：
     * 40.2 MB
     * , 在所有 Java 提交中击败了
     * 85.31%
     * 的用户
     * 通过测试用例：
     * 206 / 206
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> cache = new HashSet<>();
        for(int num : candyType){
            cache.add(num);
        }
        return cache.size() > candyType.length / 2 ? candyType.length/2 : cache.size();
    }

    /**
     * 方法一：贪心
     *
     * 一方面，设糖果数量为
     * n
     * n，由于妹妹只能分到一半的糖果，所以答案不会超过
     * n
     * 2
     * 2
     * n
     * ​
     *  ；另一方面，设这些糖果一共有
     * m
     * m 种，答案也不会超过
     * m
     * m。
     *
     * 若
     * m
     * ≤
     * n
     * 2
     * m≤
     * 2
     * n
     * ​
     *  ，则可以每种糖果至少分一颗给妹妹，此时答案为
     * m
     * m；若
     * m
     * >
     * n
     * 2
     * m>
     * 2
     * n
     * ​
     *  ，则妹妹只能分到
     * n
     * 2
     * 2
     * n
     * ​
     *   种糖果，每种糖果分一颗，此时答案为
     * n
     * 2
     * 2
     * n
     * ​
     *  。
     *
     * 综上所述，答案为
     * min
     * ⁡
     * (
     * m
     * ,
     * n
     * 2
     * )
     * min(m,
     * 2
     * n
     * ​
     *  )。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/distribute-candies/solution/fen-tang-guo-by-leetcode-solution-l4f6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param candyType
     * @return
     * 执行用时：
     * 33 ms
     * , 在所有 Java 提交中击败了
     * 62.34%
     * 的用户
     * 内存消耗：
     * 40.3 MB
     * , 在所有 Java 提交中击败了
     * 71.76%
     * 的用户
     * 通过测试用例：
     * 206 / 206
     */
    public int distributeCandies1(int[] candyType) {
        Set<Integer> set = new HashSet<Integer>();
        for (int candy : candyType) {
            set.add(candy);
        }
        return Math.min(set.size(), candyType.length / 2);
    }

}
