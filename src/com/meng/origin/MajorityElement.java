package com.meng.origin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 229. 求众数 II
 * 难度
 * 中等
 *
 * 494
 *
 *
 *
 *
 *
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 */
public class MajorityElement {
    /**
     * 执行用时：
     * 11 ms
     * , 在所有 Java 提交中击败了
     * 28.12%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 72.16%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        int target = len / 3;
        Map<Integer,Integer> cache = new HashMap<>();
        for(int num : nums){
            cache.put(num,cache.getOrDefault(num,0)+1);
        }
        for(Map.Entry<Integer,Integer> entry : cache.entrySet()){
            if (entry.getValue() > target){
                res.add(entry.getKey());
            }
        }
        return res;
    }

    /**
     * 方法一：哈希统计
     *
     * 思路
     *
     * 我们用哈希统计数组中每个元素出现的次数，设数组的长度为
     * n
     * n，返回所有统计次数超过
     * ⌊
     * n
     * 3
     * ⌋
     * ⌊
     * 3
     * n
     * ​
     *  ⌋ 的元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/majority-element-ii/solution/qiu-zhong-shu-ii-by-leetcode-solution-y1rn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 12 ms
     * , 在所有 Java 提交中击败了
     * 17.84%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 92.76%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     */
    public List<Integer> majorityElement1(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (cnt.containsKey(nums[i])) {
                cnt.put(nums[i], cnt.get(nums[i]) + 1);
            } else {
                cnt.put(nums[i], 1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : cnt.keySet()) {
            if (cnt.get(x) > nums.length / 3) {
                ans.add(x);
            }
        }

        return ans;
    }

    /**
     * 方法二：摩尔投票法
     *
     * 背景知识
     *
     * 摩尔投票法：摩尔投票法的核心思想为对拼消耗。首先我们考虑最基本的摩尔投票问题，比如找出一组数字序列中出现次数大于总数
     * 1
     * 2
     * 2
     * 1
     * ​
     *   的数字（并且假设这个数字一定存在）。我们可以直接利用反证法证明这样的数字只可能有一个。摩尔投票算法的核心思想是基于这个事实：
     *
     * 每次从序列里选择两个不相同的数字删除掉（或称为「抵消」），最后剩下一个数字或几个相同的数字，就是出现次数大于总数一半的那个元素。假设我们当前数组中存在次数大于总数一半的元素为
     * x
     * x，数组的总长度为
     * n
     * n，则我们可以把数组分为两部分，一部分为相同的
     * k
     * k 个元素
     * x
     * x，另一部分为
     * n
     * −
     * x
     * 2
     * 2
     * n−x
     * ​
     *  对个不同的元素配对，此时我们假设还存在另外一个次数大于总数一半的元素
     * y
     * y，则此时
     * y
     * y 因该满足
     * y
     * >
     * n
     * 2
     * y>
     * 2
     * n
     * ​
     *  ，但是按照我们之前的推理
     * y
     * y 应当满足
     * y
     * ≤
     * n
     * −
     * k
     * 2
     * y≤
     * 2
     * n−k
     * ​
     *  ，二者自相矛盾。
     * 具体有兴趣的同学可以参考论文的证明过程，论文地址：MJRTYA Fast Majority Vote Algorithm
     * 解题思路
     *
     * 题目要求找出其中所有出现超过
     * ⌊
     * n
     * 3
     * ⌋
     * ⌊
     * 3
     * n
     * ​
     *  ⌋ 次的元素。我们可以利用反证法推断出满足这样条件的元素最多只有两个，我们可以利用摩尔投票法的核心思想，每次选择三个互不相同的元素进行删除（或称为「抵消」）。我们可以假设数组中一定只存在一个次数大于
     * ⌊
     * n
     * 3
     * ⌋
     * ⌊
     * 3
     * n
     * ​
     *  ⌋ 的元素
     * x
     * x，其中
     * n
     * n 为数组的长度，则此时我们可以把数组分成两部分：一部分相同的
     * k
     * k 个元素
     * x
     * x，另一部分为
     * n
     * −
     * k
     * 3
     * 3
     * n−k
     * ​
     *  组三个不同的元素，我们知道三个不同的元素会被抵消，因此最终只会剩下一个元素为
     * x
     * x。如果只存在
     * 2
     * 2 个次数大于
     * ⌊
     * n
     * 3
     * ⌋
     * ⌊
     * 3
     * n
     * ​
     *  ⌋ 的元素时，我们假设这两个不同的元素分别为
     * x
     * x 和
     * y
     * y，则此时我们一定可以把数组分成三部分：第一部分相同的
     * m
     * m 个元素
     * x
     * x，第二部分相同的
     * n
     * n 个元素
     * y
     * y，第三部分为
     * n
     * −
     * x
     * −
     * y
     * 3
     * 3
     * n−x−y
     * ​
     *  组三个互不同的元素，我们知道三个互不同的元素会被抵消，因此最终只会剩下两个元素为
     * x
     * x 和
     * y
     * y。
     *
     * 我们每次检测当前元素是否为第一个选中的元素或者第二个选中的元素。
     * 每次我们发现当前元素与已经选中的两个元素都不相同，则进行抵消一次。
     * 如果存在最终选票大于
     * 0
     * 0 的元素，我们还需要再次统计已选中元素的次数,检查元素的次数是否大于
     * ⌊
     * n
     * 3
     * ⌋
     * ⌊
     * 3
     * n
     * ​
     *  ⌋ 。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/majority-element-ii/solution/qiu-zhong-shu-ii-by-leetcode-solution-y1rn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 53.04%
     * 的用户
     * 内存消耗：
     * 42.5 MB
     * , 在所有 Java 提交中击败了
     * 18.17%
     * 的用户
     * 通过测试用例：
     * 82 / 82
     */
    public List<Integer> majorityElement2(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }

}
