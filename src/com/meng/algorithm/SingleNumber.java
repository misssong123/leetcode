package com.meng.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 260. 只出现一次的数字 III
 * 难度
 * 中等
 *
 * 520
 *
 *
 *
 *
 *
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 *
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 *
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 */
public class SingleNumber {
    /**
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 23.06%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 19.13%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int len = nums.length;
        if (len == 2 ){
            return nums;
        }
        int [] res = new int[2];
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if (!set.add(num)){
                set.remove(num);
            }
        }
        int index = 0;
        for(int num : set){
            res[index++] = num;
        }
        return res;
    }
    /**
     * 方法一：哈希表
     *
     * 思路与算法
     *
     * 我们可以使用一个哈希映射统计数组中每一个元素出现的次数。
     *
     * 在统计完成后，我们对哈希映射进行遍历，将所有只出现了一次的数放入答案中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leet-4i8e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 11.92%
     * 的用户
     * 内存消耗：
     * 39.1 MB
     * , 在所有 Java 提交中击败了
     * 17.89%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public int[] singleNumber1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }
    /**
     * 思路与算法
     *
     * 在理解如何使用位运算解决本题前，读者需要首先掌握「136. 只出现一次的数字」中的位运算做法。
     *
     * 假设数组
     * nums
     * nums 中只出现一次的元素分别是
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *  。如果把
     * nums
     * nums 中的所有元素全部异或起来，得到结果
     * x
     * x，那么一定有：
     *
     * x
     * =
     * x
     * 1
     * ⊕
     * x
     * 2
     * x=x
     * 1
     * ​
     *  ⊕x
     * 2
     * ​
     *
     *
     * 其中
     * ⊕
     * ⊕ 表示异或运算。这是因为
     * nums
     * nums 中出现两次的元素都会因为异或运算的性质
     * a
     * ⊕
     * b
     * ⊕
     * b
     * =
     * a
     * a⊕b⊕b=a 抵消掉，那么最终的结果就只剩下
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *   的异或和。
     *
     * x
     * x 显然不会等于
     * 0
     * 0，因为如果
     * x
     * =
     * 0
     * x=0，那么说明
     * x
     * 1
     * =
     * x
     * 2
     * x
     * 1
     * ​
     *  =x
     * 2
     * ​
     *  ，这样
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *   就不是只出现一次的数字了。因此，我们可以使用位运算
     * x
     *  
     * &
     *  
     * -x
     * x & -x 取出
     * x
     * x 的二进制表示中最低位那个
     * 1
     * 1，设其为第
     * l
     * l 位，那么
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *   中的某一个数的二进制表示的第
     * l
     * l 位为
     * 0
     * 0，另一个数的二进制表示的第
     * l
     * l 位为
     * 1
     * 1。在这种情况下，
     * x
     * 1
     * ⊕
     * x
     * 2
     * x
     * 1
     * ​
     *  ⊕x
     * 2
     * ​
     *   的二进制表示的第
     * l
     * l 位才能为
     * 1
     * 1。
     *
     * 这样一来，我们就可以把
     * nums
     * nums 中的所有元素分成两类，其中一类包含所有二进制表示的第
     * l
     * l 位为
     * 0
     * 0 的数，另一类包含所有二进制表示的第
     * l
     * l 位为
     * 1
     * 1 的数。可以发现：
     *
     * 对于任意一个在数组
     * nums
     * nums 中出现两次的元素，该元素的两次出现会被包含在同一类中；
     * 对于任意一个在数组
     * nums
     * nums 中只出现了一次的元素，即
     * x
     * 1
     * x
     * 1
     * ​
     *   和
     * x
     * 2
     * x
     * 2
     * ​
     *  ，它们会被包含在不同类中。
     * 因此，如果我们将每一类的元素全部异或起来，那么其中一类会得到
     * x
     * 1
     * x
     * 1
     * ​
     *  ，另一类会得到
     * x
     * 2
     * x
     * 2
     * ​
     *  。这样我们就找出了这两个只出现一次的元素。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leet-4i8e/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 81.72%
     * 的用户
     * 通过测试用例：
     * 32 / 32
     */
    public int[] singleNumber2(int[] nums) {
        int xorsum = 0;
        for (int num : nums) {
            xorsum ^= num;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        return new int[]{type1, type2};
    }
}
